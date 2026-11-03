package app.service.outputports;

import app.domain.Prenda;
import java.util.List;

public interface PrendaRepositoryInterface {
    void savePrenda(Prenda prenda);
    Prenda getPrendaById(int id);
    List<Prenda> getAllPrendas();
    void deletePrenda(int id);
    Prenda updatePrenda(Prenda prenda);
}
