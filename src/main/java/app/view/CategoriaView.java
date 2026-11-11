package app.view;

import app.domain.Categoria;
import app.service.inputports.CategoriaUseCase;
import app.utils.FormRuleValidator;

import java.util.List;

public class CategoriaView {

    private CategoriaUseCase categoriaUseCase;

    public CategoriaView(
            CategoriaUseCase categoriaUseCase) {

        this.categoriaUseCase = categoriaUseCase;
    }

    public Categoria leerCategoria() {

        Categoria categoria = new Categoria();

        categoria.setNombre(
                FormRuleValidator.validateString(
                        "Ingrese el nombre de la categoría:"));

        categoria.setDescripcion(
                FormRuleValidator.validateString(
                        "Ingrese la descripción de la categoría:"));

        categoria.setActivo(true);

        return categoria;
    }

    public int leerIdCategoria() {

        return FormRuleValidator.validateInt(
                "Ingrese el ID de la categoría:");
    }

    public void mostrarCategoria(Categoria categoria) {

        System.out.println(categoria);
    }

    public void mostrarCategorias(List<Categoria> categorias) {

        if (categorias.isEmpty()) {

            System.out.println("No se encontraron categorías.");
            return;
        }

        System.out.println("\n========== CATEGORÍAS ==========");

        for (Categoria categoria : categorias) {

            System.out.println("ID: " + categoria.getIdCategoria());
            System.out.println("Nombre: " + categoria.getNombre());
            System.out.println("Descripción: " + categoria.getDescripcion());
            System.out.println("=====================");
        }
    }

    public void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }
}
