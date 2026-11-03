package app.service;

import app.domain.Carrito;
import app.domain.Producto;
import app.service.inputports.CarritoUseCase;

import java.util.HashMap;
import java.util.Map;

public class CarritoServiceImpl implements CarritoUseCase {

    // Simulamos una base de datos en memoria para guardar los carritos por su ID
    private final Map<Long, Carrito> carritosDB = new HashMap<>();

    private Carrito obtenerOCrearCarrito(Long carritoId) {
        return carritosDB.computeIfAbsent(carritoId, Carrito::new);
    }

    @Override
    public Carrito obtenerCarrito(Long carritoId) {
        return obtenerOCrearCarrito(carritoId);
    }

    @Override
    public void agregarProductoAlCarrito(Long carritoId, Producto producto, int cantidad) {
        Carrito carrito = obtenerOCrearCarrito(carritoId);
        carrito.agregarProducto(producto, cantidad);
    }

    @Override
    public void eliminarProductoDelCarrito(Long carritoId, Long productoId) {
        Carrito carrito = obtenerOCrearCarrito(carritoId);
        carrito.eliminarProducto(productoId);
    }

    @Override
    public void vaciarCarrito(Long carritoId) {
        Carrito carrito = obtenerOCrearCarrito(carritoId);
        carrito.vaciarCarrito();
    }

    @Override
    public double calcularTotalCarrito(Long carritoId) {
        Carrito carrito = obtenerOCrearCarrito(carritoId);
        return carrito.calcularTotal();
    }
}