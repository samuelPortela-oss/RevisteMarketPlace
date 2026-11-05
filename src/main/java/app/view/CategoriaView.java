package app.view;

import app.domain.Categoria;
import app.service.inputports.CategoriaUseCase;
import app.utils.FormRuleValidator;

import java.util.List;

public class CategoriaView {

    private final CategoriaUseCase categoriaService;

    public CategoriaView(CategoriaUseCase categoriaUseCase) {
        this.categoriaService = categoriaUseCase;
    }

    public void createCategoria() {
        System.out.println("Creando nueva categoría");

        int idCategoria = FormRuleValidator.validateInt("Ingrese el id de la categoría");

        if (categoriaService.getCategoriaById(idCategoria) != null) {
            System.out.println("Ya existe una categoría con el id " + idCategoria);
            return;
        }

        String nombre = FormRuleValidator.validateString("Ingrese el nombre de la categoría");
        String descripcion = FormRuleValidator.validateString("Ingrese una breve descripción de la categoría");

        categoriaService.createCategoria(idCategoria, nombre, descripcion);
        System.out.println("Categoría creada correctamente");
    }

    public void getCategoriaById(int idCategoria) {
        Categoria categoria = categoriaService.getCategoriaById(idCategoria);

        if (categoria == null) {
            System.out.println("No se encontró una categoría con el id " + idCategoria);
            return;
        }

        System.out.println(categoria.getIdCategoria() + " " + categoria.getNombre() + " " + categoria.getDescripcion());
    }

    public void getAllCategorias() {
        List<Categoria> categorias = categoriaService.getAllCategorias();

        if (categorias.isEmpty()) {
            System.out.println("Aún no hay categorías registradas");
            return;
        }

        for (Categoria categoria : categorias) {
            System.out.println(categoria.getIdCategoria() + " " + categoria.getNombre() + " " + categoria.getDescripcion());
        }
    }

    public void updateCategoria() {
        int idCategoria = FormRuleValidator.validateInt("Ingrese el id de la categoría a actualizar");

        if (categoriaService.getCategoriaById(idCategoria) == null) {
            System.out.println("No se encontró una categoría con el id " + idCategoria);
            return;
        }

        String nombre = FormRuleValidator.validateString("Ingrese el nuevo nombre de la categoría");
        String descripcion = FormRuleValidator.validateString("Ingrese la nueva descripción de la categoría");

        categoriaService.updateCategoria(idCategoria, nombre, descripcion);
        System.out.println("Categoría actualizada correctamente");
    }

    public void deleteCategoria(int idCategoria) {
        if (categoriaService.getCategoriaById(idCategoria) == null) {
            System.out.println("No se encontró una categoría con el id " + idCategoria);
            return;
        }

        categoriaService.deleteCategoria(idCategoria);
        System.out.println("Categoría eliminada correctamente");
    }
}
