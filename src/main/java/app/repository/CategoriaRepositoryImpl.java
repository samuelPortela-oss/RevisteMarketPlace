package app.repository;

import app.domain.Categoria;
import app.service.outputports.CategoriaRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryImpl implements CategoriaRepositoryInterface {

    private final List<Categoria> categorias = new ArrayList<>();

    @Override
    public Categoria saveCategoria(Categoria categoria) {
        categorias.add(categoria);
        return categoria;
    }

    @Override
    public Categoria selectCategoriaById(int id) {
        for (Categoria categoria : categorias) {
            if (categoria.getIdCategoria() == id) {
                return categoria;
            }
        }
        return null;
    }

    @Override
    public List<Categoria> selectAllCategorias() {
        return categorias;
    }

    @Override
    public Categoria updateCategoria(Categoria categoria) {
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getIdCategoria() == categoria.getIdCategoria()) {
                categorias.set(i, categoria);
                return categoria;
            }
        }
        return null;
    }

    @Override
    public void deleteCategoria(int id) {
        categorias.removeIf(categoria -> categoria.getIdCategoria() == id);
    }
}
