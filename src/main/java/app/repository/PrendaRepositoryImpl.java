package app.repository;

import app.domain.Prenda;
import app.service.outputports.PrendaRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class PrendaRepositoryImpl implements PrendaRepositoryInterface {

    private static List<Prenda> listaPrendas = new ArrayList<>();

    @Override
    public void savePrenda(Prenda prenda) {
        listaPrendas.add(prenda);
        System.out.println("Prenda guardada correctamente.");
    }

    @Override
    public Prenda getPrendaById(int id) {
        for (Prenda prenda : listaPrendas) {
            if (prenda.getId() == id) {
                return prenda;
            }
        }
        return null;
    }

    @Override
    public List<Prenda> getAllPrendas() {
        return listaPrendas;
    }

    @Override
    public void deletePrenda(int id) {
        Prenda prendaEliminar = getPrendaById(id);
        if (prendaEliminar != null) {
            listaPrendas.remove(prendaEliminar);
            System.out.println("Prenda eliminada correctamente.");
        } else {
            System.out.println("No se encontró la prenda con ID: " + id);
        }
    }

    @Override
    public Prenda updatePrenda(Prenda prenda) {
        for (int i = 0; i < listaPrendas.size(); i++) {
            if (listaPrendas.get(i).getId() == prenda.getId()) {
                listaPrendas.set(i, prenda);
                System.out.println("Prenda actualizada correctamente.");
                return prenda;
            }
        }
        return null;
    }
}
