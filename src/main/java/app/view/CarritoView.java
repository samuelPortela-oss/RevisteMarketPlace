package app.view;

import app.domain.Carrito;
import app.domain.Producto;
import app.service.CarritoServiceImpl;
import app.service.inputports.CarritoUseCase;

import java.util.Scanner;

public class CarritoView {
    private final CarritoUseCase carritoUseCase;
    private final Scanner scanner;

    public CarritoView() {
        this.carritoUseCase = new CarritoServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        Long carritoId = 1L; // ID de carrito por defecto para la prueba
        int opcion = 0;

        do {
            System.out.println("\n--- MENÚ CARRITO DE COMPRAS ---");
            System.out.println("1. Ver Carrito y Total");
            System.out.println("2. Agregar Producto de Prueba");
            System.out.println("3. Eliminar Producto");
            System.out.println("4. Vaciar Carrito");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
            } else {
                scanner.nextLine();
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    Carrito carrito = carritoUseCase.obtenerCarrito(carritoId);
                    System.out.println("\n--- TUS PRODUCTOS ---");
                    if (carrito.getItems().isEmpty()) {
                        System.out.println("El carrito está vacío.");
                    } else {
                        carrito.getItems().forEach(item ->
                                System.out.println("- " + item.getProducto().getTitulo() +
                                        " | Cantidad: " + item.getCantidad() +
                                        " | Subtotal: $" + item.getSubtotal())
                        );
                        System.out.println("TOTAL A PAGAR: $" + carrito.getTotal());
                    }
                    break;

                case 2:
                    // Creamos un producto de prueba estático para la demo
                    Producto productoDemo = new Producto(101L, "Camiseta Oversize", 45000.0);
                    carritoUseCase.agregarProductoAlCarrito(carritoId, productoDemo, 2);
                    System.out.println("¡Producto agregado con éxito!");
                    break;

                case 3:
                    System.out.print("Ingresa el ID del producto a eliminar (ej. 101): ");
                    Long prodId = scanner.nextLong();
                    carritoUseCase.eliminarProductoDelCarrito(carritoId, prodId);
                    System.out.println("Producto eliminado del carrito.");
                    break;

                case 4:
                    carritoUseCase.vaciarCarrito(carritoId);
                    System.out.println("Se ha vaciado el carrito.");
                    break;

                case 5:
                    System.out.println("Saliendo del módulo de carrito...");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 5);
    }
}