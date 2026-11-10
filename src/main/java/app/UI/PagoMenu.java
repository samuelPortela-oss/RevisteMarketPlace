package app.UI;

import app.domain.Orden;
import app.domain.Pago;
import app.service.inputports.OrdenUseCase;
import app.service.inputports.PagoUseCase;
import app.utils.FormRuleValidator;
import app.view.OrdenView;
import app.view.PagoView;

public class PagoMenu {

    private final PagoUseCase pagoUseCase;
    private final OrdenUseCase ordenUseCase;
    private final PagoView pagoView;
    private final OrdenView ordenView;

    public PagoMenu(
            PagoUseCase pagoUseCase,
            OrdenUseCase ordenUseCase,
            PagoView pagoView,
            OrdenView ordenView) {

        this.pagoUseCase = pagoUseCase;
        this.ordenUseCase = ordenUseCase;
        this.pagoView = pagoView;
        this.ordenView = ordenView;
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
                        int idOrden =
                                pagoView.leerIdOrden();

                        Orden orden =
                                ordenUseCase.obtenerOrdenPorId(
                                        idOrden).orElse(null);

                        ordenView.mostrarOrden(orden);

                        if (orden == null) {
                            break;
                        }

                        int idMetodoPago =
                                pagoView.leerIdMetodoPago();

                        Pago pago =
                                pagoUseCase.registrarPago(
                                        idOrden,
                                        idMetodoPago);

                        pagoView.mostrarMensaje(
                                "Pago registrado correctamente. ID: "
                                        + pago.getIdPago());

                        break;

                    case 2:
                        int idOrdenPagos =
                                pagoView.leerIdOrden();

                        pagoView.mostrarPagos(
                                pagoUseCase.listarPagosPorOrden(
                                        idOrdenPagos));

                        break;

                    case 3:
                        pagoView.mostrarPagos(
                                pagoUseCase.listarPagos());

                        break;

                    case 0:
                        break;

                    default:
                        pagoView.mostrarMensaje(
                                "Opción inválida.");
                }

            } catch (RuntimeException e) {

                pagoView.mostrarMensaje(
                        e.getMessage());
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {

        System.out.println("\n=== MENÚ PAGOS ===");
        System.out.println("1. Pagar una orden");
        System.out.println("2. Listar pagos de una orden");
        System.out.println("3. Listar todos los pagos");
        System.out.println("0. Volver");
    }
}
