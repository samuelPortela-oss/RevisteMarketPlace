package app.service.inputports;

import app.domain.FotoPrenda;

import java.util.List;
import java.util.Optional;

public interface FotoPrendaUseCase {

    void agregarFotoPrenda(FotoPrenda fotoPrenda);

    Optional<FotoPrenda> obtenerFotoPrendaPorId(int idFoto);

    List<FotoPrenda> listarFotosPorPrenda(int idPrenda);

    boolean eliminarFotoPrenda(int idFoto);
}
