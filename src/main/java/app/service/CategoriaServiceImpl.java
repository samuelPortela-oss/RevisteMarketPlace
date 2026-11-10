package app.service;

import app.domain.Categoria;
import app.service.inputports.CategoriaUseCase;
import app.service.outputports.CategoriaRepositoryInterface;

import java.util.List;
import java.util.Optional;

public class CategoriaServiceImpl implements CategoriaUseCase {

    private final CategoriaRepositoryInterface repository;

    public CategoriaServiceImpl(
            CategoriaRepositoryInterface repository) {

        this.repository = repository;
    }

    @Override
    public void crearCategoria(Categoria categoria) {

        if (repository.obtenerCategoriaPorNombre(
                categoria.getNombre()).isPresent()) {

            throw new RuntimeException(
                    "Ya existe una categoría con ese nombre.");
        }

        repository.crearCategoria(categoria);
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorId(int idCategoria) {

        return repository.obtenerCategoriaPorId(idCategoria);
    }

    @Override
    public List<Categoria> listarCategorias() {

        return repository.listarCategorias();
    }

    @Override
    public boolean actualizarCategoria(Categoria categoria) {

        boolean nombreDeOtraCategoria =
                repository.obtenerCategoriaPorNombre(categoria.getNombre())
                        .filter(conMismoNombre ->
                                conMismoNombre.getIdCategoria()
                                        != categoria.getIdCategoria())
                        .isPresent();

        if (nombreDeOtraCategoria) {

            throw new RuntimeException(
                    "Ya existe otra categoría con ese nombre.");
        }

        return repository.actualizarCategoria(categoria);
    }

    @Override
    public boolean eliminarCategoria(int idCategoria) {

        return repository.eliminarCategoria(idCategoria);
    }
}
