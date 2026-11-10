package app.service;

import app.domain.ItemOrden;
import app.service.inputports.ItemOrdenUseCase;
import app.service.outputports.ItemOrdenRepositoryInterface;

import java.util.List;
import java.util.Optional;

public class ItemOrdenServiceImpl implements ItemOrdenUseCase {

    private final ItemOrdenRepositoryInterface repository;

    public ItemOrdenServiceImpl(
            ItemOrdenRepositoryInterface repository) {

        this.repository = repository;
    }

    @Override
    public void crearItemOrden(ItemOrden itemOrden) {

        if (itemOrden.getCantidad() <= 0) {

            throw new RuntimeException(
                    "La cantidad debe ser mayor que cero.");
        }

        repository.crearItemOrden(itemOrden);
    }

    @Override
    public Optional<ItemOrden> obtenerItemOrdenPorId(int idItemOrden) {

        return repository.obtenerItemOrdenPorId(idItemOrden);
    }

    @Override
    public List<ItemOrden> listarItemsPorOrden(int idOrden) {

        return repository.listarItemsPorOrden(idOrden);
    }

    @Override
    public boolean actualizarItemOrden(ItemOrden itemOrden) {

        return repository.actualizarItemOrden(itemOrden);
    }

    @Override
    public boolean eliminarItemOrden(int idItemOrden) {

        return repository.eliminarItemOrden(idItemOrden);
    }
}
