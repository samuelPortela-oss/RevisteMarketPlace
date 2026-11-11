package app.service.inputports;

import app.domain.Carrito;
import app.domain.ItemCarrito;

import java.util.List;
import java.util.Optional;

public interface CarritoUseCase {

    Carrito crearCarrito(int idUsuario);

    Optional<Carrito> obtenerCarritoPorUsuario(int idUsuario);

    void agregarPrendaAlCarrito(int idUsuario, int idPrenda, int cantidad);

    List<ItemCarrito> listarItemsPorCarrito(int idCarrito);

    double calcularTotalCarrito(int idCarrito);

    boolean eliminarItem(int idItemCarrito);

    void vaciarCarrito(int idCarrito);

    List<Carrito> listarCarritos();
}
