package app.service.outputports;

import app.domain.MetodoPago;

import java.util.List;
import java.util.Optional;

public interface MetodoPagoRepositoryInterface {

    void crearMetodoPago(MetodoPago metodoPago);

    Optional<MetodoPago> obtenerMetodoPagoPorId(int idMetodoPago);

    List<MetodoPago> listarMetodosPago();

    boolean actualizarMetodoPago(MetodoPago metodoPago);

    boolean eliminarMetodoPago(int idMetodoPago);
}
