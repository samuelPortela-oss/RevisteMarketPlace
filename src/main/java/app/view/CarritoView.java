package app.view;

import app.domain.Carrito;
import app.domain.ItemCarrito;
import app.service.inputports.CarritoUseCase;
import app.utils.FormRuleValidator;

import java.util.List;

public class CarritoView {

    private CarritoUseCase carritoUseCase;

    public CarritoView(
            CarritoUseCase carritoUseCase) {

        this.carritoUseCase = carritoUseCase;
    }

    public int leerIdUsuario() {

        return FormRuleValidator.validateInt(
                "Ingrese el ID del usuario:");
    }

    public int leerIdPrenda() {

        return FormRuleValidator.validateInt(
                "Ingrese el ID de la prenda:");
    }

    public int leerCantidad() {

        return FormRuleValidator.validateInt(
                "Ingrese la cantidad:");
    }

    public int leerIdItemCarrito() {

        return FormRuleValidator.validateInt(
                "Ingrese el ID del item del carrito:");
    }

    public int leerIdCarrito() {

        return FormRuleValidator.validateInt(
                "Ingrese el ID del carrito:");
    }

    public void mostrarCarrito(Carrito carrito) {

        if (carrito == null) {

            System.out.println("El usuario no tiene carrito.");
            return;
        }

        System.out.println("\n========== CARRITO ==========");
        System.out.println("ID carrito: " + carrito.getIdCarrito());
        System.out.println("ID usuario: " + carrito.getIdUsuario());

        List<ItemCarrito> items =
                carritoUseCase.listarItemsPorCarrito(
                        carrito.getIdCarrito());

        if (items.isEmpty()) {

            System.out.println("El carrito está vacío.");
            return;
        }

        for (ItemCarrito item : items) {

            System.out.println("ID item: " + item.getIdItemCarrito()
                    + " | ID prenda: " + item.getIdPrenda()
                    + " | Cantidad: " + item.getCantidad());
        }

        System.out.println("Total: "
                + carritoUseCase.calcularTotalCarrito(
                carrito.getIdCarrito()));
        System.out.println("=====================");
    }

    public void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }
}
