package app.service.inputports;

import app.domain.Prenda;

import java.util.List;
import java.util.Optional;

public interface PrendaUseCase {

    void crearPrenda(Prenda prenda);

    Optional<Prenda> obtenerPrendaPorId(int idPrenda);

    List<Prenda> listarPrendas();

    List<Prenda> listarPrendasDisponibles();

    List<Prenda> listarPrendasPorVendedor(int idVendedor);

    List<Prenda> listarPrendasPorCategoria(int idCategoria);

    boolean actualizarPrenda(Prenda prenda);

    boolean marcarPrendaComoVendida(int idPrenda);

    boolean eliminarPrenda(int idPrenda);
}
