package app.service.outputports;

import app.domain.Orden;

import java.util.List;
import java.util.Optional;

public interface OrdenRepositoryInterface {

    void crearOrden(Orden orden);

    Optional<Orden> obtenerOrdenPorId(int idOrden);

    List<Orden> listarOrdenesPorUsuario(int idUsuario);

    List<Orden> listarOrdenes();

    boolean actualizarOrden(Orden orden);

    boolean eliminarOrden(int idOrden);
}
