package app.service;

import app.domain.ItemOrden;
import app.domain.Orden;
import app.domain.Pago;
import app.domain.Prenda;
import app.domain.enums.EstadoOrdenEnum;
import app.domain.enums.EstadoPagoEnum;
import app.domain.enums.EstadoPrendaEnum;
import app.service.inputports.PagoUseCase;
import app.service.outputports.ItemOrdenRepositoryInterface;
import app.service.outputports.MetodoPagoRepositoryInterface;
import app.service.outputports.OrdenRepositoryInterface;
import app.service.outputports.PagoRepositoryInterface;
import app.service.outputports.PrendaRepositoryInterface;
import app.utils.FechaUtil;

import java.util.List;
import java.util.Optional;

/**
 * Pago interno de la plataforma: no hay pasarela externa,
 * el pago se registra directamente con su estado.
 */
public class PagoServiceImpl implements PagoUseCase {

    private final PagoRepositoryInterface pagoRepository;
    private final OrdenRepositoryInterface ordenRepository;
    private final ItemOrdenRepositoryInterface itemOrdenRepository;
    private final PrendaRepositoryInterface prendaRepository;
    private final MetodoPagoRepositoryInterface metodoPagoRepository;

    public PagoServiceImpl(
            PagoRepositoryInterface pagoRepository,
            OrdenRepositoryInterface ordenRepository,
            ItemOrdenRepositoryInterface itemOrdenRepository,
            PrendaRepositoryInterface prendaRepository,
            MetodoPagoRepositoryInterface metodoPagoRepository) {

        this.pagoRepository = pagoRepository;
        this.ordenRepository = ordenRepository;
        this.itemOrdenRepository = itemOrdenRepository;
        this.prendaRepository = prendaRepository;
        this.metodoPagoRepository = metodoPagoRepository;
    }

    @Override
    public Pago registrarPago(
            int idOrden,
            int idMetodoPago) {

        Orden orden =
                ordenRepository.obtenerOrdenPorId(idOrden)
                        .orElseThrow(() -> new RuntimeException(
                                "La orden no existe."));

        if (orden.getEstado() == EstadoOrdenEnum.PAGADA) {

            throw new RuntimeException(
                    "La orden ya está pagada.");
        }

        if (orden.getEstado() == EstadoOrdenEnum.CANCELADA) {

            throw new RuntimeException(
                    "No se puede pagar una orden cancelada.");
        }

        metodoPagoRepository.obtenerMetodoPagoPorId(idMetodoPago)
                .filter(metodoPago ->
                        Boolean.TRUE.equals(metodoPago.getActivo()))
                .orElseThrow(() -> new RuntimeException(
                        "Método de pago inválido."));

        Pago pago = new Pago(
                0,
                idOrden,
                idMetodoPago,
                orden.getMontoTotal(),
                EstadoPagoEnum.COMPLETADO,
                FechaUtil.ahora());

        pagoRepository.crearPago(pago);

        orden.setEstado(EstadoOrdenEnum.PAGADA);

        ordenRepository.actualizarOrden(orden);

        marcarPrendasAgotadasComoVendidas(idOrden);

        return pago;
    }

    private void marcarPrendasAgotadasComoVendidas(int idOrden) {

        for (ItemOrden itemOrden :
                itemOrdenRepository.listarItemsPorOrden(idOrden)) {

            prendaRepository.obtenerPrendaPorId(
                            itemOrden.getIdPrenda())
                    .filter(prenda -> prenda.getEstado()
                            == EstadoPrendaEnum.DISPONIBLE
                            && prenda.getStock() == 0)
                    .ifPresent(prenda -> {

                        prenda.setEstado(EstadoPrendaEnum.VENDIDA);

                        prendaRepository.actualizarPrenda(prenda);
                    });
        }
    }

    @Override
    public Optional<Pago> obtenerPagoPorId(int idPago) {

        return pagoRepository.obtenerPagoPorId(idPago);
    }

    @Override
    public Optional<Pago> obtenerPagoPorOrden(int idOrden) {

        return pagoRepository.obtenerPagoPorOrden(idOrden);
    }

    @Override
    public List<Pago> listarPagosPorOrden(int idOrden) {

        return pagoRepository.listarPagosPorOrden(idOrden);
    }

    @Override
    public List<Pago> listarPagos() {

        return pagoRepository.listarPagos();
    }
}
