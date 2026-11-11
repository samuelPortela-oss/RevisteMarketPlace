package app.view;

import app.domain.ItemOrden;
import app.domain.Orden;
import app.service.inputports.OrdenUseCase;
import app.utils.FormRuleValidator;

import java.util.List;

public class OrdenView {

    private OrdenUseCase ordenUseCase;

    public OrdenView(
            OrdenUseCase ordenUseCase) {

        this.ordenUseCase = ordenUseCase;
    }

    public int leerIdUsuario() {

        return FormRuleValidator.validateInt(
                "Ingrese el ID del usuario:");
    }

    public int leerIdOrden() {

        return FormRuleValidator.validateInt(
                "Ingrese el ID de la orden:");
    }

    public void mostrarOrden(Orden orden) {

        if (orden == null) {

            System.out.println("Orden no encontrada.");
            return;
        }

        System.out.println("\n========== ORDEN ==========");
        System.out.println("ID orden: " + orden.getIdOrden());
        System.out.println("ID usuario: " + orden.getIdUsuario());
        System.out.println("Total: " + orden.getMontoTotal());
        System.out.println("Estado: " + orden.getEstado());
        System.out.println("Fecha: " + orden.getFechaCreacion());

        List<ItemOrden> items =
                ordenUseCase.listarItemsDeOrden(
                        orden.getIdOrden());

        for (ItemOrden item : items) {

            System.out.println("ID item: " + item.getIdItemOrden()
                    + " | Prenda: " + item.getNombrePrenda()
                    + " | Cantidad: " + item.getCantidad()
                    + " | Precio unitario: " + item.getPrecioUnitario()
                    + " | Subtotal: " + item.getSubtotal());
        }

        System.out.println("=====================");
    }

    public void mostrarOrdenes(List<Orden> ordenes) {

        if (ordenes.isEmpty()) {

            System.out.println("No se encontraron órdenes.");
            return;
        }

        System.out.println("\n========== ÓRDENES ==========");

        for (Orden orden : ordenes) {

            System.out.println("ID orden: " + orden.getIdOrden()
                    + " | ID usuario: " + orden.getIdUsuario()
                    + " | Total: " + orden.getMontoTotal()
                    + " | Estado: " + orden.getEstado());
        }

        System.out.println("=====================");
    }

    public void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }
}
