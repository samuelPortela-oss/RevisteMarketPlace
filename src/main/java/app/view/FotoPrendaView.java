package app.view;

import app.domain.FotoPrenda;
import app.service.inputports.FotoPrendaUseCase;
import app.utils.FormRuleValidator;

import java.util.List;

public class FotoPrendaView {

    private FotoPrendaUseCase fotoPrendaUseCase;

    public FotoPrendaView(
            FotoPrendaUseCase fotoPrendaUseCase) {

        this.fotoPrendaUseCase = fotoPrendaUseCase;
    }

    public FotoPrenda leerFotoPrenda(int idPrenda) {

        FotoPrenda fotoPrenda = new FotoPrenda();

        fotoPrenda.setIdPrenda(idPrenda);

        fotoPrenda.setRuta(
                FormRuleValidator.validateString(
                        "Ingrese la ruta o URL de la imagen:"));

        return fotoPrenda;
    }

    public int leerIdFoto() {

        return FormRuleValidator.validateInt(
                "Ingrese el ID de la foto:");
    }

    public void mostrarFotos(List<FotoPrenda> fotos) {

        if (fotos.isEmpty()) {

            System.out.println("La prenda no tiene fotos.");
            return;
        }

        System.out.println("\n========== FOTOS ==========");

        for (FotoPrenda foto : fotos) {

            System.out.println("ID foto: " + foto.getIdFoto()
                    + " | Posición: " + foto.getOrden()
                    + (foto.getOrden() == 0 ? " (portada)" : "")
                    + " | Ruta: " + foto.getRuta());
        }

        System.out.println("=====================");
    }

    public void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }
}
