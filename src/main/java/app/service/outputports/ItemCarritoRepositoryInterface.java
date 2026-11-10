package app.service.outputports;

import app.domain.ItemCarrito;

import java.util.List;
import java.util.Optional;

public interface ItemCarritoRepositoryInterface {

    void crearItemCarrito(ItemCarrito itemCarrito);

    Optional<ItemCarrito> obtenerItemCarritoPorId(int idItemCarrito);

    List<ItemCarrito> listarItemsPorCarrito(int idCarrito);

    boolean actualizarItemCarrito(ItemCarrito itemCarrito);

    boolean eliminarItemCarrito(int idItemCarrito);

    void eliminarItemsPorCarrito(int idCarrito);
}
