package app.service.inputports;

import app.domain.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaUseCase {

    void crearCategoria(Categoria categoria);

    Optional<Categoria> obtenerCategoriaPorId(int idCategoria);

    List<Categoria> listarCategorias();

    boolean actualizarCategoria(Categoria categoria);

    boolean eliminarCategoria(int idCategoria);
}
