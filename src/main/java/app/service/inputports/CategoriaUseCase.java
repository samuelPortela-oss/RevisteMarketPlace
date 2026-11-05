package app.service.inputports;

import app.domain.Categoria;

import java.util.List;

public interface CategoriaUseCase {

    Categoria createCategoria(int idCategoria, String nombre, String descripcion);
    Categoria getCategoriaById(int idCategoria);
    List<Categoria> getAllCategorias();
    Categoria updateCategoria(int idCategoria, String nombre, String descripcion);
    void deleteCategoria(int idCategoria);
}
