package app.service;

import app.domain.Carrito;
import app.domain.ItemCarrito;
import app.domain.ItemOrden;
import app.domain.Orden;
import app.domain.Prenda;
import app.domain.enums.EstadoOrdenEnum;
import app.domain.enums.EstadoPrendaEnum;
import app.service.inputports.OrdenUseCase;
import app.service.outputports.CarritoRepositoryInterface;
import app.service.outputports.ItemCarritoRepositoryInterface;
import app.service.outputports.ItemOrdenRepositoryInterface;
import app.service.outputports.OrdenRepositoryInterface;
import app.service.outputports.PrendaRepositoryInterface;
import app.service.outputports.UsuarioRepositoryInterface;
import app.utils.FechaUtil;

import java.util.List;
import java.util.Optional;

public class OrdenServiceImpl implements OrdenUseCase {

    private final OrdenRepositoryInterface ordenRepository;
    private final ItemOrdenRepositoryInterface itemOrdenRepository;
    private final CarritoRepositoryInterface carritoRepository;
    private final ItemCarritoRepositoryInterface itemCarritoRepository;
    private final PrendaRepositoryInterface prendaRepository;
    private final UsuarioRepositoryInterface usuarioRepository;

    public OrdenServiceImpl(
            OrdenRepositoryInterface ordenRepository,
            ItemOrdenRepositoryInterface itemOrdenRepository,
            CarritoRepositoryInterface carritoRepository,
            ItemCarritoRepositoryInterface itemCarritoRepository,
            PrendaRepositoryInterface prendaRepository,
            UsuarioRepositoryInterface usuarioRepository) {

        this.ordenRepository = ordenRepository;
        this.itemOrdenRepository = itemOrdenRepository;
        this.carritoRepository = carritoRepository;
        this.itemCarritoRepository = itemCarritoRepository;
        this.prendaRepository = prendaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Orden crearOrden(int idUsuario) {

        if (usuarioRepository.obtenerUsuarioPorId(idUsuario).isEmpty()) {

            throw new RuntimeException(
                    "El usuario no existe.");
        }

        Carrito carrito =
                carritoRepository.obtenerCarritoPorUsuario(idUsuario)
                        .orElseThrow(() -> new RuntimeException(
                                "El usuario aún no tiene un carrito."));

        List<ItemCarrito> itemsCarrito =
                itemCarritoRepository.listarItemsPorCarrito(
                        carrito.getIdCarrito());

        if (itemsCarrito.isEmpty()) {

            throw new RuntimeException(
                    "El carrito está vacío.");
        }

        for (ItemCarrito itemCarrito : itemsCarrito) {

            Prenda prenda =
                    prendaRepository.obtenerPrendaPorId(
                            itemCarrito.getIdPrenda()).orElse(null);

            if (prenda == null
                    || prenda.getEstado() != EstadoPrendaEnum.DISPONIBLE
                    || prenda.getStock() < itemCarrito.getCantidad()) {

                throw new RuntimeException(
                        "Stock insuficiente o prenda no disponible: "
                                + (prenda != null
                                ? prenda.getNombre()
                                : itemCarrito.getIdPrenda()));
            }
        }

        double montoTotal = 0;

        Orden orden = new Orden(
                0,
                idUsuario,
                montoTotal,
                EstadoOrdenEnum.PENDIENTE,
                FechaUtil.ahora());

        ordenRepository.crearOrden(orden);

        for (ItemCarrito itemCarrito : itemsCarrito) {

            Prenda prenda =
                    prendaRepository.obtenerPrendaPorId(
                            itemCarrito.getIdPrenda())
                            .orElseThrow(() -> new RuntimeException(
                                    "La prenda no existe: "
                                            + itemCarrito.getIdPrenda()));

            // nombrePrenda y precioUnitario son una copia (snapshot) al momento de la compra
            ItemOrden itemOrden = new ItemOrden(
                    0,
                    orden.getIdOrden(),
                    prenda.getIdPrenda(),
                    prenda.getNombre(),
                    itemCarrito.getCantidad(),
                    prenda.getPrecio());

            itemOrdenRepository.crearItemOrden(itemOrden);

            montoTotal += itemOrden.getSubtotal();

            prenda.setStock(
                    prenda.getStock() - itemCarrito.getCantidad());

            prendaRepository.actualizarPrenda(prenda);
        }

        orden.setMontoTotal(montoTotal);

        ordenRepository.actualizarOrden(orden);

        itemCarritoRepository.eliminarItemsPorCarrito(
                carrito.getIdCarrito());

        return orden;
    }

    @Override
    public Optional<Orden> obtenerOrdenPorId(int idOrden) {

        return ordenRepository.obtenerOrdenPorId(idOrden);
    }

    @Override
    public List<Orden> listarOrdenesPorUsuario(int idUsuario) {

        return ordenRepository.listarOrdenesPorUsuario(idUsuario);
    }

    @Override
    public List<Orden> listarOrdenes() {

        return ordenRepository.listarOrdenes();
    }

    @Override
    public List<ItemOrden> listarItemsDeOrden(int idOrden) {

        return itemOrdenRepository.listarItemsPorOrden(idOrden);
    }

    @Override
    public boolean actualizarEstadoOrden(
            int idOrden,
            EstadoOrdenEnum estado) {

        return ordenRepository.obtenerOrdenPorId(idOrden)
                .map(orden -> {
                    orden.setEstado(estado);
                    return ordenRepository.actualizarOrden(orden);
                })
                .orElse(false);
    }

    @Override
    public void cancelarOrden(int idOrden) {

        Orden orden =
                ordenRepository.obtenerOrdenPorId(idOrden)
                        .orElseThrow(() -> new RuntimeException(
                                "La orden no existe."));

        if (orden.getEstado() == EstadoOrdenEnum.PAGADA) {

            throw new RuntimeException(
                    "No se puede cancelar una orden ya pagada.");
        }

        if (orden.getEstado() == EstadoOrdenEnum.CANCELADA) {

            throw new RuntimeException(
                    "La orden ya está cancelada.");
        }

        // Devuelve al inventario el stock reservado por la orden
        for (ItemOrden itemOrden :
                itemOrdenRepository.listarItemsPorOrden(idOrden)) {

            prendaRepository.obtenerPrendaPorId(
                            itemOrden.getIdPrenda())
                    .ifPresent(prenda -> {

                        prenda.setStock(
                                prenda.getStock() + itemOrden.getCantidad());

                        prenda.setEstado(EstadoPrendaEnum.DISPONIBLE);

                        prendaRepository.actualizarPrenda(prenda);
                    });
        }

        orden.setEstado(EstadoOrdenEnum.CANCELADA);

        ordenRepository.actualizarOrden(orden);
    }
}
