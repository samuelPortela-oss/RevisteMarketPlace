package app.service.outputports;

import app.domain.FotoPrenda;

import java.util.List;
import java.util.Optional;

public interface FotoPrendaRepositoryInterface {

    void crearFotoPrenda(FotoPrenda fotoPrenda);

    Optional<FotoPrenda> obtenerFotoPrendaPorId(int idFoto);

    List<FotoPrenda> listarFotosPorPrenda(int idPrenda);

    boolean actualizarFotoPrenda(FotoPrenda fotoPrenda);

    boolean eliminarFotoPrenda(int idFoto);
}
