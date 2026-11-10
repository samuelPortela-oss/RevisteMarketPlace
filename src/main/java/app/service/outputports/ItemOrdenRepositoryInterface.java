package app.service.outputports;

import app.domain.ItemOrden;

import java.util.List;
import java.util.Optional;

public interface ItemOrdenRepositoryInterface {

    void crearItemOrden(ItemOrden itemOrden);

    Optional<ItemOrden> obtenerItemOrdenPorId(int idItemOrden);

    List<ItemOrden> listarItemsPorOrden(int idOrden);

    boolean actualizarItemOrden(ItemOrden itemOrden);

    boolean eliminarItemOrden(int idItemOrden);
}
