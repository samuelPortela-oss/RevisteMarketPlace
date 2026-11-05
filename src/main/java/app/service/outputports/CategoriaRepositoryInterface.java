package app.service.outputports;

import app.domain.Categoria;

import java.util.List;

public interface CategoriaRepositoryInterface {

    Categoria saveCategoria(Categoria categoria);
    Categoria selectCategoriaById(int id);
    List<Categoria> selectAllCategorias();
    Categoria updateCategoria(Categoria categoria);
    void deleteCategoria(int id);
}
