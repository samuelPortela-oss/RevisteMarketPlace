package app.UI;

import app.domain.Orden;
import app.service.inputports.ItemOrdenUseCase;
import app.service.inputports.OrdenUseCase;
import app.utils.FormRuleValidator;
import app.view.ItemOrdenView;
import app.view.OrdenView;

public class OrdenMenu {

    private final OrdenUseCase ordenUseCase;
    private final OrdenView ordenView;
    private final ItemOrdenUseCase itemOrdenUseCase;
    private final ItemOrdenView itemOrdenView;

    public OrdenMenu(
            OrdenUseCase ordenUseCase,
            OrdenView ordenView,
            ItemOrdenUseCase itemOrdenUseCase,
            ItemOrdenView itemOrdenView) {

        this.ordenUseCase = ordenUseCase;
        this.ordenView = ordenView;
        this.itemOrdenUseCase = itemOrdenUseCase;
        this.itemOrdenView = itemOrdenView;
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
                        int idUsuarioOrden =
                                ordenView.leerIdUsuario();

                        Orden orden =
                                ordenUseCase.crearOrden(
                                        idUsuarioOrden);

                        ordenView.mostrarMensaje(
                                "Orden creada correctamente. ID: "
                                        + orden.getIdOrden()
                                        + " | Total: "
                                        + orden.getMontoTotal());

                        break;

                    case 2:
                        int idUsuarioOrdenes =
                                ordenView.leerIdUsuario();

                        ordenView.mostrarOrdenes(
                                ordenUseCase.listarOrdenesPorUsuario(
                                        idUsuarioOrdenes));

                        break;

                    case 3:
                        ordenView.mostrarOrdenes(
                                ordenUseCase.listarOrdenes());

                        break;

                    case 4:
                        ordenView.mostrarOrden(
                                ordenUseCase.obtenerOrdenPorId(
                                        ordenView.leerIdOrden()).orElse(null));

                        break;

                    case 5:
                        itemOrdenView.mostrarItemsOrden(
                                itemOrdenUseCase.listarItemsPorOrden(
                                        ordenView.leerIdOrden()));

                        break;

                    case 6:
                        ordenUseCase.cancelarOrden(
                                ordenView.leerIdOrden());

                        ordenView.mostrarMensaje(
                                "Orden cancelada y stock devuelto.");

                        break;

                    case 0:
                        break;

                    default:
                        ordenView.mostrarMensaje(
                                "Opción inválida.");
                }

            } catch (RuntimeException e) {

                ordenView.mostrarMensaje(
                        e.getMessage());
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {

        System.out.println("\n=== MENÚ ÓRDENES ===");
        System.out.println("1. Crear orden desde el carrito");
        System.out.println("2. Listar órdenes de un usuario");
        System.out.println("3. Listar todas las órdenes");
        System.out.println("4. Ver detalle de una orden");
        System.out.println("5. Listar items de una orden");
        System.out.println("6. Cancelar orden");
        System.out.println("0. Volver");
    }
}
