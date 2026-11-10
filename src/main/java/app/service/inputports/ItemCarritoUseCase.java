package app.service.inputports;

import app.domain.ItemCarrito;

import java.util.List;
import java.util.Optional;

public interface ItemCarritoUseCase {

    void crearItemCarrito(ItemCarrito itemCarrito);

    Optional<ItemCarrito> obtenerItemCarritoPorId(int idItemCarrito);

    List<ItemCarrito> listarItemsPorCarrito(int idCarrito);

    boolean actualizarItemCarrito(ItemCarrito itemCarrito);

    boolean eliminarItemCarrito(int idItemCarrito);
}
