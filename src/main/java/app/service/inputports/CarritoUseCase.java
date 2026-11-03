package app.service.inputports;

import app.domain.Carrito;
import app.domain.Producto;

public interface CarritoUseCase {Carrito obtenerCarrito(Long carritoId);
    void agregarProductoAlCarrito(Long carritoId, Producto producto, int cantidad);
    void eliminarProductoDelCarrito(Long carritoId, Long productoId);
    void vaciarCarrito(Long carritoId);
    double calcularTotalCarrito(Long carritoId);
}
