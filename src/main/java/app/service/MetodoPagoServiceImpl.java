package app.service;

import app.domain.MetodoPago;
import app.service.inputports.MetodoPagoUseCase;
import app.service.outputports.MetodoPagoRepositoryInterface;

import java.util.List;
import java.util.Optional;

public class MetodoPagoServiceImpl implements MetodoPagoUseCase {

    private final MetodoPagoRepositoryInterface repository;

    public MetodoPagoServiceImpl(
            MetodoPagoRepositoryInterface repository) {

        this.repository = repository;
    }

    @Override
    public void crearMetodoPago(MetodoPago metodoPago) {

        repository.crearMetodoPago(metodoPago);
    }

    @Override
    public Optional<MetodoPago> obtenerMetodoPagoPorId(int idMetodoPago) {

        return repository.obtenerMetodoPagoPorId(idMetodoPago);
    }

    @Override
    public List<MetodoPago> listarMetodosPago() {

        return repository.listarMetodosPago();
    }

    @Override
    public boolean actualizarMetodoPago(MetodoPago metodoPago) {

        return repository.actualizarMetodoPago(metodoPago);
    }

    @Override
    public boolean eliminarMetodoPago(int idMetodoPago) {

        return repository.eliminarMetodoPago(idMetodoPago);
    }
}
