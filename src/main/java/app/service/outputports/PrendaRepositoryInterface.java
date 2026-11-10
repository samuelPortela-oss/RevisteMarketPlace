package app.service.outputports;

import app.domain.Prenda;

import java.util.List;
import java.util.Optional;

public interface PrendaRepositoryInterface {

    void crearPrenda(Prenda prenda);

    Optional<Prenda> obtenerPrendaPorId(int idPrenda);

    List<Prenda> listarPrendas();

    List<Prenda> listarPrendasPorVendedor(int idVendedor);

    List<Prenda> listarPrendasPorCategoria(int idCategoria);

    boolean actualizarPrenda(Prenda prenda);

    boolean eliminarPrenda(int idPrenda);
}
