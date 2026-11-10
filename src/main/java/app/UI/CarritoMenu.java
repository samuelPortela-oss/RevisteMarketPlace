package app.UI;

import app.domain.Carrito;
import app.service.inputports.CarritoUseCase;
import app.service.inputports.ItemCarritoUseCase;
import app.utils.FormRuleValidator;
import app.view.CarritoView;
import app.view.ItemCarritoView;

public class CarritoMenu {

    private final CarritoUseCase carritoUseCase;
    private final CarritoView carritoView;
    private final ItemCarritoUseCase itemCarritoUseCase;
    private final ItemCarritoView itemCarritoView;

    public CarritoMenu(
            CarritoUseCase carritoUseCase,
            CarritoView carritoView,
            ItemCarritoUseCase itemCarritoUseCase,
            ItemCarritoView itemCarritoView) {

        this.carritoUseCase = carritoUseCase;
        this.carritoView = carritoView;
        this.itemCarritoUseCase = itemCarritoUseCase;
        this.itemCarritoView = itemCarritoView;
    }

    public void abrir() {

        int opcion;

        do {

            mostrarMenu();

            opcion = FormRuleValidator.validateInt(
                    "Seleccione una opción:");

            try {

                switch (opcion) {

                    case 1:
                        int idUsuarioCarrito =
                                carritoView.leerIdUsuario();

                        Carrito carrito =
                                carritoUseCase.crearCarrito(
                                        idUsuarioCarrito);

                        carritoView.mostrarMensaje(
                                "Carrito creado correctamente. ID: "
                                        + carrito.getIdCarrito());

                        break;

                    case 2:
                        int idUsuarioAgregar =
                                carritoView.leerIdUsuario();

                        int idPrenda =
                                carritoView.leerIdPrenda();

                        int cantidad =
                                carritoView.leerCantidad();

                        carritoUseCase.agregarPrendaAlCarrito(
                                idUsuarioAgregar,
                                idPrenda,
                                cantidad);

                        carritoView.mostrarMensaje(
                                "Prenda agregada al carrito.");

                        break;

                    case 3:
                        int idUsuarioVer =
                                carritoView.leerIdUsuario();

                        carritoView.mostrarCarrito(
                                carritoUseCase.obtenerCarritoPorUsuario(
                                        idUsuarioVer).orElse(null));

                        break;

                    case 4:
                        int idItemCarrito =
                                carritoView.leerIdItemCarrito();

                        carritoView.mostrarMensaje(
                                carritoUseCase.eliminarItem(idItemCarrito)
                                        ? "Item eliminado del carrito."
                                        : "Item de carrito no encontrado.");

                        break;

                    case 5:
                        int idUsuarioVaciar =
                                carritoView.leerIdUsuario();

                        Carrito carritoVaciar =
                                carritoUseCase.obtenerCarritoPorUsuario(
                                        idUsuarioVaciar).orElse(null);

                        if (carritoVaciar == null) {

                            carritoView.mostrarMensaje(
                                    "El usuario no tiene carrito.");
                        } else {

                            carritoUseCase.vaciarCarrito(
                                    carritoVaciar.getIdCarrito());

                            carritoView.mostrarMensaje(
                                    "Carrito vaciado.");
                        }

                        break;

                    case 6:
                        int idCarrito =
                                carritoView.leerIdCarrito();

                        itemCarritoView.mostrarItemsCarrito(
                                itemCarritoUseCase.listarItemsPorCarrito(
                                        idCarrito));

                        break;

                    case 0:
                        break;

                    default:
                        carritoView.mostrarMensaje(
                                "Opción inválida.");
                }

            } catch (RuntimeException e) {

                carritoView.mostrarMensaje(
                        e.getMessage());
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {

        System.out.println("\n=== MENÚ CARRITO ===");
        System.out.println("1. Crear carrito");
        System.out.println("2. Agregar prenda al carrito");
        System.out.println("3. Ver carrito de un usuario");
        System.out.println("4. Quitar item del carrito");
        System.out.println("5. Vaciar carrito");
        System.out.println("6. Listar items por ID de carrito");
        System.out.println("0. Volver");
    }
}
