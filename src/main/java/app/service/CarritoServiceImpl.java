package app.service;

import app.domain.Carrito;
import app.domain.ItemCarrito;
import app.domain.Prenda;
import app.domain.enums.EstadoPrendaEnum;
import app.service.inputports.CarritoUseCase;
import app.service.outputports.CarritoRepositoryInterface;
import app.service.outputports.ItemCarritoRepositoryInterface;
import app.service.outputports.PrendaRepositoryInterface;
import app.service.outputports.UsuarioRepositoryInterface;
import app.utils.FechaUtil;

import java.util.List;
import java.util.Optional;

public class CarritoServiceImpl implements CarritoUseCase {

    private final CarritoRepositoryInterface carritoRepository;
    private final ItemCarritoRepositoryInterface itemCarritoRepository;
    private final PrendaRepositoryInterface prendaRepository;
    private final UsuarioRepositoryInterface usuarioRepository;

    public CarritoServiceImpl(
            CarritoRepositoryInterface carritoRepository,
            ItemCarritoRepositoryInterface itemCarritoRepository,
            PrendaRepositoryInterface prendaRepository,
            UsuarioRepositoryInterface usuarioRepository) {

        this.carritoRepository = carritoRepository;
        this.itemCarritoRepository = itemCarritoRepository;
        this.prendaRepository = prendaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Carrito crearCarrito(int idUsuario) {

        if (usuarioRepository.obtenerUsuarioPorId(idUsuario).isEmpty()) {

            throw new RuntimeException(
                    "El usuario no existe.");
        }

        if (carritoRepository.obtenerCarritoPorUsuario(idUsuario).isPresent()) {

            throw new RuntimeException(
                    "El usuario ya tiene un carrito.");
        }

        Carrito carrito = new Carrito(
                0,
                idUsuario,
                FechaUtil.ahora());

        carritoRepository.crearCarrito(carrito);

        return carrito;
    }

    @Override
    public Optional<Carrito> obtenerCarritoPorUsuario(int idUsuario) {

        return carritoRepository.obtenerCarritoPorUsuario(idUsuario);
    }

    @Override
    public void agregarPrendaAlCarrito(
            int idUsuario,
            int idPrenda,
            int cantidad) {

        if (cantidad <= 0) {

            throw new RuntimeException(
                    "La cantidad debe ser mayor que cero.");
        }

        Carrito carrito =
                carritoRepository.obtenerCarritoPorUsuario(idUsuario)
                        .orElseThrow(() -> new RuntimeException(
                                "El usuario aún no tiene un carrito."));

        Prenda prenda =
                prendaRepository.obtenerPrendaPorId(idPrenda)
                        .orElseThrow(() -> new RuntimeException(
                                "La prenda no existe."));

        if (prenda.getIdVendedor() == idUsuario) {

            throw new RuntimeException(
                    "No puedes comprar tu propia prenda.");
        }

        if (prenda.getEstado()
                != EstadoPrendaEnum.DISPONIBLE) {

            throw new RuntimeException(
                    "La prenda no está disponible.");
        }

        for (ItemCarrito item :
                itemCarritoRepository.listarItemsPorCarrito(
                        carrito.getIdCarrito())) {

            if (item.getIdPrenda() == idPrenda) {

                int nuevaCantidad = item.getCantidad() + cantidad;

                validarStock(prenda, nuevaCantidad);

                item.setCantidad(nuevaCantidad);

                itemCarritoRepository.actualizarItemCarrito(item);

                return;
            }
        }

        validarStock(prenda, cantidad);

        ItemCarrito nuevoItem = new ItemCarrito(
                0,
                carrito.getIdCarrito(),
                idPrenda,
                cantidad);

        itemCarritoRepository.crearItemCarrito(nuevoItem);
    }

    @Override
    public List<ItemCarrito> listarItemsPorCarrito(int idCarrito) {

        return itemCarritoRepository.listarItemsPorCarrito(idCarrito);
    }

    @Override
    public double calcularTotalCarrito(int idCarrito) {

        double total = 0;

        for (ItemCarrito item :
                itemCarritoRepository.listarItemsPorCarrito(idCarrito)) {

            total += prendaRepository.obtenerPrendaPorId(
                            item.getIdPrenda())
                    .map(prenda -> prenda.getPrecio()
                            * item.getCantidad())
                    .orElse(0.0);
        }

        return total;
    }

    @Override
    public boolean eliminarItem(int idItemCarrito) {

        return itemCarritoRepository.eliminarItemCarrito(idItemCarrito);
    }

    @Override
    public void vaciarCarrito(int idCarrito) {

        itemCarritoRepository.eliminarItemsPorCarrito(idCarrito);
    }

    @Override
    public List<Carrito> listarCarritos() {

        return carritoRepository.listarCarritos();
    }

    private void validarStock(Prenda prenda, int cantidad) {

        if (prenda.getStock() < cantidad) {

            throw new RuntimeException(
                    "Stock insuficiente. Disponible: "
                            + prenda.getStock());
        }
    }
}
