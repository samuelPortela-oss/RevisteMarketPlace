package app.UI;

import app.domain.Categoria;
import app.service.inputports.CategoriaUseCase;
import app.utils.FormRuleValidator;
import app.view.CategoriaView;

public class CategoriaMenu {

    private final CategoriaUseCase categoriaUseCase;
    private final CategoriaView categoriaView;

    public CategoriaMenu(
            CategoriaUseCase categoriaUseCase,
            CategoriaView categoriaView) {

        this.categoriaUseCase = categoriaUseCase;
        this.categoriaView = categoriaView;
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
                        Categoria categoria =
                                categoriaView.leerCategoria();

                        categoriaUseCase.crearCategoria(categoria);

                        categoriaView.mostrarMensaje(
                                "Categoría creada correctamente. ID: "
                                        + categoria.getIdCategoria());

                        break;

                    case 2:
                        categoriaView.mostrarCategorias(
                                categoriaUseCase.listarCategorias());

                        break;

                    case 3:
                        int idActualizar =
                                categoriaView.leerIdCategoria();

                        if (categoriaUseCase.obtenerCategoriaPorId(
                                idActualizar).isEmpty()) {

                            categoriaView.mostrarMensaje(
                                    "Categoría no encontrada.");
                            break;
                        }

                        Categoria actualizada =
                                categoriaView.leerCategoria();

                        actualizada.setIdCategoria(idActualizar);

                        categoriaView.mostrarMensaje(
                                categoriaUseCase.actualizarCategoria(actualizada)
                                        ? "Categoría actualizada."
                                        : "No se pudo actualizar la categoría.");

                        break;

                    case 4:
                        int idEliminar =
                                categoriaView.leerIdCategoria();

                        categoriaView.mostrarMensaje(
                                categoriaUseCase.eliminarCategoria(idEliminar)
                                        ? "Categoría eliminada."
                                        : "Categoría no encontrada.");

                        break;

                    case 0:
                        break;

                    default:
                        categoriaView.mostrarMensaje(
                                "Opción inválida.");
                }

            } catch (RuntimeException e) {

                categoriaView.mostrarMensaje(
                        e.getMessage());
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {

        System.out.println("\n=== MENÚ CATEGORÍAS ===");
        System.out.println("1. Crear categoría");
        System.out.println("2. Listar categorías");
        System.out.println("3. Actualizar categoría");
        System.out.println("4. Eliminar categoría");
        System.out.println("0. Volver");
    }
}
