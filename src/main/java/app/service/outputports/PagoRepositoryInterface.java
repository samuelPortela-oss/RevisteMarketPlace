package app.service.outputports;

import app.domain.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoRepositoryInterface {

    void crearPago(Pago pago);

    Optional<Pago> obtenerPagoPorId(int idPago);

    Optional<Pago> obtenerPagoPorOrden(int idOrden);

    List<Pago> listarPagosPorOrden(int idOrden);

    List<Pago> listarPagos();

    boolean actualizarPago(Pago pago);

    boolean eliminarPago(int idPago);
}
