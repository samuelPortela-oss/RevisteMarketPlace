package app.service.inputports;

import app.domain.ItemOrden;
import app.domain.Orden;
import app.domain.enums.EstadoOrdenEnum;

import java.util.List;
import java.util.Optional;

public interface OrdenUseCase {

    Orden crearOrden(int idUsuario);

    Optional<Orden> obtenerOrdenPorId(int idOrden);

    List<Orden> listarOrdenesPorUsuario(int idUsuario);

    List<Orden> listarOrdenes();

    List<ItemOrden> listarItemsDeOrden(int idOrden);

    boolean actualizarEstadoOrden(int idOrden, EstadoOrdenEnum estado);

    void cancelarOrden(int idOrden);
}
