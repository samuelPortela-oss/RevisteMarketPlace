package app.service;

import app.domain.FotoPrenda;
import app.domain.Prenda;
import app.service.inputports.FotoPrendaUseCase;
import app.service.outputports.FotoPrendaRepositoryInterface;
import app.service.outputports.PrendaRepositoryInterface;

import java.util.List;
import java.util.Optional;

public class FotoPrendaServiceImpl implements FotoPrendaUseCase {

    private final FotoPrendaRepositoryInterface repository;
    private final PrendaRepositoryInterface prendaRepository;

    public FotoPrendaServiceImpl(
            FotoPrendaRepositoryInterface repository,
            PrendaRepositoryInterface prendaRepository) {

        this.repository = repository;
        this.prendaRepository = prendaRepository;
    }

    @Override
    public void agregarFotoPrenda(FotoPrenda fotoPrenda) {

        if (prendaRepository.obtenerPrendaPorId(
                fotoPrenda.getIdPrenda()).isEmpty()) {

            throw new RuntimeException(
                    "La prenda no existe.");
        }

        if (fotoPrenda.getRuta() == null
                || fotoPrenda.getRuta().isBlank()) {

            throw new RuntimeException(
                    "La ruta de la foto es obligatoria.");
        }

        List<FotoPrenda> fotos =
                repository.listarFotosPorPrenda(
                        fotoPrenda.getIdPrenda());

        if (fotos.size() >= Prenda.MAXIMO_FOTOS) {

            throw new RuntimeException(
                    "Una prenda admite máximo "
                            + Prenda.MAXIMO_FOTOS + " fotos.");
        }

        // Asigna la primera posición libre (0..4); la posición 0 es la portada
        fotoPrenda.setOrden(primeraPosicionLibre(fotos));

        repository.crearFotoPrenda(fotoPrenda);
    }

    @Override
    public Optional<FotoPrenda> obtenerFotoPrendaPorId(int idFoto) {

        return repository.obtenerFotoPrendaPorId(idFoto);
    }

    @Override
    public List<FotoPrenda> listarFotosPorPrenda(int idPrenda) {

        return repository.listarFotosPorPrenda(idPrenda);
    }

    @Override
    public boolean eliminarFotoPrenda(int idFoto) {

        return repository.eliminarFotoPrenda(idFoto);
    }

    private int primeraPosicionLibre(List<FotoPrenda> fotos) {

        for (int posicion = 0; posicion < Prenda.MAXIMO_FOTOS; posicion++) {

            boolean ocupada = false;

            for (FotoPrenda foto : fotos) {

                if (foto.getOrden() == posicion) {

                    ocupada = true;
                    break;
                }
            }

            if (!ocupada) {

                return posicion;
            }
        }

        throw new RuntimeException(
                "No hay posiciones libres para la foto.");
    }
}
