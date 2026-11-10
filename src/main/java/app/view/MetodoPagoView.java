package app.view;

import app.domain.MetodoPago;
import app.service.inputports.MetodoPagoUseCase;
import app.utils.FormRuleValidator;

import java.util.List;

public class MetodoPagoView {

    private MetodoPagoUseCase metodoPagoUseCase;

    public MetodoPagoView(
            MetodoPagoUseCase metodoPagoUseCase) {

        this.metodoPagoUseCase = metodoPagoUseCase;
    }

    public MetodoPago leerMetodoPago() {

        MetodoPago metodoPago = new MetodoPago();

        metodoPago.setNombre(
                FormRuleValidator.validateString(
                        "Ingrese el nombre del método de pago:"));

        metodoPago.setDescripcion(
                FormRuleValidator.validateString(
                        "Ingrese la descripción del método de pago:"));

        metodoPago.setActivo(true);

        return metodoPago;
    }

    public int leerIdMetodoPago() {

        return FormRuleValidator.validateInt(
                "Ingrese el ID del método de pago:");
    }

    public void mostrarMetodoPago(MetodoPago metodoPago) {

        System.out.println(metodoPago);
    }

    public void mostrarMetodosPago(
            List<MetodoPago> metodosPago) {

        if (metodosPago.isEmpty()) {

            System.out.println("No se encontraron métodos de pago.");
            return;
        }

        System.out.println("\n========== MÉTODOS DE PAGO ==========");

        for (MetodoPago metodoPago : metodosPago) {

            System.out.println("ID: " + metodoPago.getIdMetodoPago()
                    + " | Nombre: " + metodoPago.getNombre()
                    + " | Descripción: " + metodoPago.getDescripcion()
                    + " | Activo: "
                    + (Boolean.TRUE.equals(metodoPago.getActivo()) ? "Sí" : "No"));
        }

        System.out.println("=====================");
    }

    public void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }
}
