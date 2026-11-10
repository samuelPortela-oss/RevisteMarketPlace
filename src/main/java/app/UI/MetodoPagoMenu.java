package app.UI;

import app.domain.MetodoPago;
import app.service.inputports.MetodoPagoUseCase;
import app.utils.FormRuleValidator;
import app.view.MetodoPagoView;

public class MetodoPagoMenu {

    private final MetodoPagoUseCase metodoPagoUseCase;
    private final MetodoPagoView metodoPagoView;

    public MetodoPagoMenu(
            MetodoPagoUseCase metodoPagoUseCase,
            MetodoPagoView metodoPagoView) {

        this.metodoPagoUseCase = metodoPagoUseCase;
        this.metodoPagoView = metodoPagoView;
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
                        MetodoPago metodoPago =
                                metodoPagoView.leerMetodoPago();

                        metodoPagoUseCase.crearMetodoPago(
                                metodoPago);

                        metodoPagoView.mostrarMensaje(
                                "Método de pago creado correctamente. ID: "
                                        + metodoPago.getIdMetodoPago());

                        break;

                    case 2:
                        metodoPagoView.mostrarMetodosPago(
                                metodoPagoUseCase.listarMetodosPago());

                        break;

                    case 3:
                        int idCambiar =
                                metodoPagoView.leerIdMetodoPago();

                        MetodoPago existente =
                                metodoPagoUseCase.obtenerMetodoPagoPorId(
                                        idCambiar).orElse(null);

                        if (existente == null) {

                            metodoPagoView.mostrarMensaje(
                                    "Método de pago no encontrado.");
                            break;
                        }

                        existente.setActivo(
                                !Boolean.TRUE.equals(existente.getActivo()));

                        metodoPagoUseCase.actualizarMetodoPago(existente);

                        metodoPagoView.mostrarMensaje(
                                Boolean.TRUE.equals(existente.getActivo())
                                        ? "Método de pago activado."
                                        : "Método de pago desactivado.");

                        break;

                    case 4:
                        int idEliminar =
                                metodoPagoView.leerIdMetodoPago();

                        metodoPagoView.mostrarMensaje(
                                metodoPagoUseCase.eliminarMetodoPago(idEliminar)
                                        ? "Método de pago eliminado."
                                        : "Método de pago no encontrado.");

                        break;

                    case 0:
                        break;

                    default:
                        metodoPagoView.mostrarMensaje(
                                "Opción inválida.");
                }

            } catch (RuntimeException e) {

                metodoPagoView.mostrarMensaje(
                        e.getMessage());
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {

        System.out.println("\n=== MENÚ MÉTODOS DE PAGO ===");
        System.out.println("1. Crear método de pago");
        System.out.println("2. Listar métodos de pago");
        System.out.println("3. Activar / desactivar método de pago");
        System.out.println("4. Eliminar método de pago");
        System.out.println("0. Volver");
    }
}
