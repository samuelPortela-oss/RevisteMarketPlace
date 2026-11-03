package app.view;

import app.service.helpers.SetEstadoPrenda;
import app.service.inputports.PrendaUseCase;
import app.utils.FormRuleValidator;

public class PrendaView {

    private final PrendaUseCase prendaService;

    public PrendaView(PrendaUseCase prendaUseCase) {
        this.prendaService = prendaUseCase;
    }

    public void createPrenda() {

        System.out.println("Creando prenda");

        int id = FormRuleValidator.validateInt("Ingrese el ID de la prenda:");

        String nombre = FormRuleValidator.validateString("Ingrese el nombre/tipo de la prenda:");

        String marca = FormRuleValidator.validateString("Ingrese la marca de la prenda:");

        String talla = FormRuleValidator.validateString("Ingrese la talla de la prenda (XS, S, M, L, XL):");

        String estado = SetEstadoPrenda.getEstadoPrenda();

        double precio = FormRuleValidator.validateDouble("Ingrese el precio de la prenda:");

        prendaService.createPrenda(id, nombre, marca, talla, estado, precio);
    }

    public void showPrenda() {
        System.out.println("Listado de prendas");
        prendaService.showPrendas();
    }

    public void showPrendaById() {
        System.out.println("Buscar prenda por Id");
        int id = FormRuleValidator.validateInt("Ingrese el ID de la prenda a buscar:");
        prendaService.showPrendaById(id);
    }

    public void deletePrenda() {
        System.out.println("Eliminar prenda");
        int id = FormRuleValidator.validateInt("Ingrese el ID de la prenda a eliminar:");
        prendaService.deletePrenda(id);
    }
}
