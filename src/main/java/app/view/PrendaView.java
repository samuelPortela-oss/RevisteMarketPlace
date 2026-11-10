package app.view;

import app.domain.Prenda;
import app.service.helpers.SetCondicionPrenda;
import app.service.inputports.PrendaUseCase;
import app.utils.FormRuleValidator;

import java.util.List;

public class PrendaView {

    private PrendaUseCase prendaUseCase;

    public PrendaView(
            PrendaUseCase prendaUseCase) {

        this.prendaUseCase = prendaUseCase;
    }

    public Prenda leerPrenda() {

        Prenda prenda = new Prenda();

        prenda.setIdVendedor(
                FormRuleValidator.validateInt(
                        "Ingrese el ID del vendedor (usuario):"));

        leerDatosEdicion(prenda);

        return prenda;
    }

    // Lee los datos editables de una prenda sobre el objeto recibido
    public void leerDatosEdicion(Prenda prenda) {

        prenda.setIdCategoria(
                FormRuleValidator.validateInt(
                        "Ingrese el ID de la categoría:"));

        prenda.setNombre(
                FormRuleValidator.validateString(
                        "Ingrese el nombre de la prenda:"));

        prenda.setDescripcion(
                FormRuleValidator.validateString(
                        "Ingrese la descripción:"));

        prenda.setMarca(
                FormRuleValidator.validateString(
                        "Ingrese la marca:"));

        prenda.setTalla(
                FormRuleValidator.validateString(
                        "Ingrese la talla (XS, S, M, L, XL, XXL, TALLA_UNICA o 6-44):")
                        .toUpperCase());

        prenda.setCondicion(SetCondicionPrenda.getCondicionPrenda());

        prenda.setPrecio(
                FormRuleValidator.validateDouble(
                        "Ingrese el precio:"));

        prenda.setStock(
                FormRuleValidator.validateInt(
                        "Ingrese el stock:"));
    }

    public int leerIdPrenda() {

        return FormRuleValidator.validateInt(
                "Ingrese el ID de la prenda:");
    }

    public void mostrarPrenda(Prenda prenda) {

        System.out.println(prenda);
    }

    public void mostrarPrendas(List<Prenda> prendas) {

        if (prendas.isEmpty()) {

            System.out.println("No se encontraron prendas.");
            return;
        }

        System.out.println("\n========== PRENDAS ==========");

        for (Prenda prenda : prendas) {

            System.out.println("ID: " + prenda.getIdPrenda());
            System.out.println("Nombre: " + prenda.getNombre());
            System.out.println("Descripción: " + prenda.getDescripcion());
            System.out.println("Marca: " + prenda.getMarca());
            System.out.println("Talla: " + prenda.getTalla());
            System.out.println("Condición: " + prenda.getCondicion());
            System.out.println("Precio: " + prenda.getPrecio());
            System.out.println("Stock: " + prenda.getStock());
            System.out.println("Estado: " + prenda.getEstado());
            System.out.println("ID vendedor: " + prenda.getIdVendedor());
            System.out.println("ID categoría: " + prenda.getIdCategoria());
            System.out.println("=====================");
        }
    }

    public void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }
}
