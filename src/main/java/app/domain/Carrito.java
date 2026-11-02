package app.domain;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private Long id;
    private List<ItemCarrito> items;
    private double total;

    public Carrito(Long id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        // Verificar si el producto ya está en el carrito para sumar la cantidad
        for (ItemCarrito item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                item.setCantidad(item.getCantidad() + cantidad);
                this.total = calcularTotal();
                return;
            }
        }
        // Si no está, se crea un nuevo item
        ItemCarrito nuevoItem = new ItemCarrito(producto, cantidad);
        items.add(nuevoItem);
        this.total = calcularTotal();
    }

    public void eliminarProducto(Long productoId) {
        items.removeIf(item -> item.getProducto().getId().equals(productoId));
        this.total = calcularTotal();
    }

    public double calcularTotal() {
        double suma = 0;
        for (ItemCarrito item : items) {
            suma += item.getSubtotal();
        }
        return suma;
    }

    public void vaciarCarrito() {
        items.clear();
        this.total = 0.0;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<ItemCarrito> getItems() { return items; }
    public void setItems(List<ItemCarrito> items) {
        this.items = items;
        this.total = calcularTotal();
    }

    public double getTotal() {
        return calcularTotal();
    }
}