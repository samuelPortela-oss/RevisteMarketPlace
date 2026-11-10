package app.service;

import app.domain.ItemCarrito;
import app.service.inputports.ItemCarritoUseCase;
import app.service.outputports.ItemCarritoRepositoryInterface;

import java.util.List;
import java.util.Optional;

public class ItemCarritoServiceImpl implements ItemCarritoUseCase {

    private final ItemCarritoRepositoryInterface repository;

    public ItemCarritoServiceImpl(
            ItemCarritoRepositoryInterface repository) {

        this.repository = repository;
    }

    @Override
    public void crearItemCarrito(ItemCarrito itemCarrito) {

        if (itemCarrito.getCantidad() <= 0) {

            throw new RuntimeException(
                    "La cantidad debe ser mayor que cero.");
        }

        repository.crearItemCarrito(itemCarrito);
    }

    @Override
    public Optional<ItemCarrito> obtenerItemCarritoPorId(int idItemCarrito) {

        return repository.obtenerItemCarritoPorId(idItemCarrito);
    }

    @Override
    public List<ItemCarrito> listarItemsPorCarrito(int idCarrito) {

        return repository.listarItemsPorCarrito(idCarrito);
    }

    @Override
    public boolean actualizarItemCarrito(ItemCarrito itemCarrito) {

        return repository.actualizarItemCarrito(itemCarrito);
    }

    @Override
    public boolean eliminarItemCarrito(int idItemCarrito) {

        return repository.eliminarItemCarrito(idItemCarrito);
    }
}
