package app.UI;

import app.utils.FormRuleValidator;
import app.view.CarritoView;
import app.view.PrendaView;
import app.view.UsuarioView;

public class AppUserInterface {

    private final UsuarioView usuarioView;
    private final CarritoView carritoView;
    private final PrendaView prendaView;

    public AppUserInterface(UsuarioView usuarioView, CarritoView carritoView, PrendaView prendaView) {
        this.usuarioView = usuarioView;
        this.carritoView = carritoView;
        this.prendaView = prendaView;
    }

    public void startApp() {

        int option;

        do {

            showMenu();

            option = FormRuleValidator.validateInt("Select an option:");

            switch (option) {

                case 1:
                    usuarioView.createUsuario();
                    break;

                case 2:
                    usuarioView.showAllUsuarios();
                    break;

                case 3:
                    usuarioView.sellProduct();
                    break;

                case 4:
                    usuarioView.publishProduct();
                    break;

                case 5:
                    usuarioView.buyProduct();
                    break;

                case 6:
                    carritoView.mostrarMenu();
                    break;

                case 7:
                    menuPrendas();
                    break;

                case 0:
                    System.out.println("Exit...");
                    break;

                default:
                    System.out.println("Please select a valid option.");
                    break;
            }

        } while (option != 0);
    }

    public void menuPrendas() {
        while (true) {
            int option = FormRuleValidator.validateInt("""
                    1. Crear Prenda.
                    2. Buscar Prenda por Id.
                    3. Listar todas las Prendas.
                    4. Eliminar Prenda.
                    5. Volver.
                    """);

            switch (option) {
                case 1:
                    System.out.println("Crear nueva prenda");
                    prendaView.createPrenda();
                    break;
                case 2:
                    System.out.println("Buscar prenda por Id");
                    prendaView.showPrendaById();
                    break;
                case 3:
                    System.out.println("Listar todas las prendas");
                    prendaView.showPrenda();
                    break;
                case 4:
                    System.out.println("Eliminar prenda");
                    prendaView.deletePrenda();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Seleccione una opcion valida");
            }
        }
    }

    private void showMenu() {

        System.out.println("\n=== USER MENU ===");
        System.out.println("1. Create User");
        System.out.println("2. List Users");
        System.out.println("3. Sell Product");
        System.out.println("4. Publish Product");
        System.out.println("5. Buy Product");
        System.out.println("6. Shopping Cart");
        System.out.println("7. Prendas");
        System.out.println("0. Exit");
    }
}
