package app.view;

import app.domain.Pago;
import app.service.inputports.PagoUseCase;
import app.utils.FormRuleValidator;

import java.util.List;

public class PagoView {

    private PagoUseCase pagoUseCase;

    public PagoView(
            PagoUseCase pagoUseCase) {

        this.pagoUseCase = pagoUseCase;
    }

    public int leerIdOrden() {

        return FormRuleValidator.validateInt(
                "Ingrese el ID de la orden:");
    }

    public int leerIdMetodoPago() {

        return FormRuleValidator.validateInt(
                "Ingrese el ID del método de pago:");
    }

    public void mostrarPago(Pago pago) {

        if (pago == null) {

            System.out.println("No se encontró el pago.");
            return;
        }

        System.out.println("\n========== PAGO ==========");
        System.out.println("ID pago: " + pago.getIdPago());
        System.out.println("ID orden: " + pago.getIdOrden());
        System.out.println("Monto: " + pago.getMonto());
        System.out.println("ID método: " + pago.getIdMetodoPago());
        System.out.println("Estado: " + pago.getEstado());
        System.out.println("Fecha: " + pago.getFechaPago());
        System.out.println("=====================");
    }

    public void mostrarPagos(List<Pago> pagos) {

        if (pagos.isEmpty()) {

            System.out.println("No se encontraron pagos.");
            return;
        }

        System.out.println("\n========== PAGOS ==========");

        for (Pago pago : pagos) {

            System.out.println("ID pago: " + pago.getIdPago()
                    + " | ID orden: " + pago.getIdOrden()
                    + " | Monto: " + pago.getMonto()
                    + " | Estado: " + pago.getEstado());
        }

        System.out.println("=====================");
    }

    public void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }
}
