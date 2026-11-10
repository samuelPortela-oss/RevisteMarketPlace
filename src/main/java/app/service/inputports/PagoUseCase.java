package app.service.inputports;

import app.domain.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoUseCase {

    Pago registrarPago(int idOrden, int idMetodoPago);

    Optional<Pago> obtenerPagoPorId(int idPago);

    Optional<Pago> obtenerPagoPorOrden(int idOrden);

    List<Pago> listarPagosPorOrden(int idOrden);

    List<Pago> listarPagos();
}
