package app.UI;

import app.domain.FotoPrenda;
import app.domain.Prenda;
import app.service.inputports.FotoPrendaUseCase;
import app.service.inputports.PrendaUseCase;
import app.utils.FormRuleValidator;
import app.view.FotoPrendaView;
import app.view.PrendaView;

public class PrendaMenu {

    private final PrendaUseCase prendaUseCase;
    private final PrendaView prendaView;
    private final FotoPrendaUseCase fotoPrendaUseCase;
    private final FotoPrendaView fotoPrendaView;

    public PrendaMenu(
            PrendaUseCase prendaUseCase,
            PrendaView prendaView,
            FotoPrendaUseCase fotoPrendaUseCase,
            FotoPrendaView fotoPrendaView) {

        this.prendaUseCase = prendaUseCase;
        this.prendaView = prendaView;
        this.fotoPrendaUseCase = fotoPrendaUseCase;
        this.fotoPrendaView = fotoPrendaView;
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
                        Prenda prenda =
                                prendaView.leerPrenda();

                        prendaUseCase.crearPrenda(prenda);

                        prendaView.mostrarMensaje(
                                "Prenda publicada correctamente. ID: "
                                        + prenda.getIdPrenda());

                        break;

                    case 2:
                        prendaView.mostrarPrendas(
                                prendaUseCase.listarPrendas());

                        break;

                    case 3:
                        prendaView.mostrarPrendas(
                                prendaUseCase.listarPrendasDisponibles());

                        break;

                    case 4:
                        int idCategoria =
                                FormRuleValidator.validateInt(
                                        "Ingrese el ID de la categoría:");

                        prendaView.mostrarPrendas(
                                prendaUseCase.listarPrendasPorCategoria(
                                        idCategoria));

                        break;

                    case 5:
                        int idVendedor =
                                FormRuleValidator.validateInt(
                                        "Ingrese el ID del vendedor:");

                        prendaView.mostrarPrendas(
                                prendaUseCase.listarPrendasPorVendedor(
                                        idVendedor));

                        break;

                    case 6:
                        actualizarPrenda();

                        break;

                    case 7:
                        int idVendida =
                                prendaView.leerIdPrenda();

                        prendaView.mostrarMensaje(
                                prendaUseCase.marcarPrendaComoVendida(idVendida)
                                        ? "Prenda marcada como vendida."
                                        : "Prenda no encontrada.");

                        break;

                    case 8:
                        int idEliminar =
                                prendaView.leerIdPrenda();

                        prendaView.mostrarMensaje(
                                prendaUseCase.eliminarPrenda(idEliminar)
                                        ? "Prenda eliminada."
                                        : "Prenda no encontrada.");

                        break;

                    case 9:
                        int idPrendaFoto =
                                prendaView.leerIdPrenda();

                        FotoPrenda foto =
                                fotoPrendaView.leerFotoPrenda(idPrendaFoto);

                        fotoPrendaUseCase.agregarFotoPrenda(foto);

                        fotoPrendaView.mostrarMensaje(
                                "Foto agregada. ID: " + foto.getIdFoto()
                                        + " | Posición: " + foto.getOrden());

                        break;

                    case 10:
                        int idPrendaFotos =
                                prendaView.leerIdPrenda();

                        fotoPrendaView.mostrarFotos(
                                fotoPrendaUseCase.listarFotosPorPrenda(
                                        idPrendaFotos));

                        break;

                    case 11:
                        int idFoto =
                                fotoPrendaView.leerIdFoto();

                        fotoPrendaView.mostrarMensaje(
                                fotoPrendaUseCase.eliminarFotoPrenda(idFoto)
                                        ? "Foto eliminada."
                                        : "Foto no encontrada.");

                        break;

                    case 0:
                        break;

                    default:
                        prendaView.mostrarMensaje(
                                "Opción inválida.");
                }

            } catch (RuntimeException e) {

                prendaView.mostrarMensaje(
                        e.getMessage());
            }

        } while (opcion != 0);
    }

    private void actualizarPrenda() {

        int idPrenda = prendaView.leerIdPrenda();

        Prenda prenda =
                prendaUseCase.obtenerPrendaPorId(idPrenda).orElse(null);

        if (prenda == null) {

            prendaView.mostrarMensaje(
                    "Prenda no encontrada.");
            return;
        }

        prendaView.leerDatosEdicion(prenda);

        prendaView.mostrarMensaje(
                prendaUseCase.actualizarPrenda(prenda)
                        ? "Prenda actualizada."
                        : "No se pudo actualizar la prenda.");
    }

    private void mostrarMenu() {

        System.out.println("\n=== MENÚ PRENDAS ===");
        System.out.println("1. Publicar prenda");
        System.out.println("2. Listar prendas");
        System.out.println("3. Listar prendas disponibles");
        System.out.println("4. Listar prendas por categoría");
        System.out.println("5. Listar prendas por vendedor");
        System.out.println("6. Actualizar prenda");
        System.out.println("7. Marcar prenda como vendida");
        System.out.println("8. Eliminar prenda");
        System.out.println("9. Agregar foto a una prenda");
        System.out.println("10. Ver fotos de una prenda");
        System.out.println("11. Eliminar foto");
        System.out.println("0. Volver");
    }
}
