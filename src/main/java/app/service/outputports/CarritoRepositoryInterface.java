package app.service.outputports;

import app.domain.Carrito;

import java.util.List;
import java.util.Optional;

public interface CarritoRepositoryInterface {

    void crearCarrito(Carrito carrito);

    Optional<Carrito> obtenerCarritoPorId(int idCarrito);

    Optional<Carrito> obtenerCarritoPorUsuario(int idUsuario);

    List<Carrito> listarCarritos();

    boolean actualizarCarrito(Carrito carrito);

    boolean eliminarCarrito(int idCarrito);
}
