package app.service;

import app.domain.Categoria;
import app.service.inputports.CategoriaUseCase;
import app.service.outputports.CategoriaRepositoryInterface;

import java.util.List;

public class CategoriaServiceImpl implements CategoriaUseCase {

    private final CategoriaRepositoryInterface categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepositoryInterface categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria createCategoria(int idCategoria, String nombre, String descripcion) {
        Categoria categoria = new Categoria(idCategoria, nombre, descripcion);
        return categoriaRepository.saveCategoria(categoria);
    }

    @Override
    public Categoria getCategoriaById(int idCategoria) {
        return categoriaRepository.selectCategoriaById(idCategoria);
    }

    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.selectAllCategorias();
    }

    @Override
    public Categoria updateCategoria(int idCategoria, String nombre, String descripcion) {
        Categoria categoria = new Categoria(idCategoria, nombre, descripcion);
        return categoriaRepository.updateCategoria(categoria);
    }

    @Override
    public void deleteCategoria(int idCategoria) {
        categoriaRepository.deleteCategoria(idCategoria);
    }
}
