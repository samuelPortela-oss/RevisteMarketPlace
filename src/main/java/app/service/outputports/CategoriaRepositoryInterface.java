package app.service.outputports;

import app.domain.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepositoryInterface {

    void crearCategoria(Categoria categoria);

    Optional<Categoria> obtenerCategoriaPorId(int idCategoria);

    Optional<Categoria> obtenerCategoriaPorNombre(String nombre);

    List<Categoria> listarCategorias();

    boolean actualizarCategoria(Categoria categoria);

    boolean eliminarCategoria(int idCategoria);
}
