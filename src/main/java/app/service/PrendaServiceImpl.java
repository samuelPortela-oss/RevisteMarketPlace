package app.service;

import app.domain.Prenda;
import app.service.inputports.PrendaUseCase;
import app.service.outputports.PrendaRepositoryInterface;

import java.util.List;

public class PrendaServiceImpl implements PrendaUseCase {

    private final PrendaRepositoryInterface prendaRepository;

    public PrendaServiceImpl(PrendaRepositoryInterface prendaRepository) {
        this.prendaRepository = prendaRepository;
    }

    @Override
    public void createPrenda(int id, String nombre, String marca, String talla, String estado, double precio) {
        Prenda prenda = new Prenda(id, nombre, marca, talla, estado, precio);
        prendaRepository.savePrenda(prenda);
    }

    @Override
    public void showPrendas() {
        List<Prenda> prendas = prendaRepository.getAllPrendas();
        for (Prenda prenda : prendas) {
            System.out.println("ID: " + prenda.getId() + " | Nombre: " + prenda.getNombre() + " | Marca: " + prenda.getMarca() + " | Talla: " + prenda.getTalla() + " | Estado: " + prenda.getEstado() + " | Precio: $" + prenda.getPrecio());
        }
    }

    @Override
    public void showPrendaById(int id) {
        Prenda prenda = prendaRepository.getPrendaById(id);
        if (prenda != null) {
            System.out.println("ID: " + prenda.getId() + " | Nombre: " + prenda.getNombre() + " | Marca: " + prenda.getMarca() + " | Talla: " + prenda.getTalla() + " | Estado: " + prenda.getEstado() + " | Precio: $" + prenda.getPrecio());
        } else {
            System.out.println("No se encontró la prenda con ID: " + id);
        }
    }

    @Override
    public void deletePrenda(int id) {
        prendaRepository.deletePrenda(id);
    }
}
