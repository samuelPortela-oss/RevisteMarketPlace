package app.UI;

import app.utils.FormRuleValidator;
import app.view.CarritoView;
import app.view.UsuarioView;

public class AppUserInterface {

    private final UsuarioView usuarioView;
    private final CarritoView carritoView;

    public AppUserInterface(UsuarioView usuarioView, CarritoView carritoView) {
        this.usuarioView = usuarioView;
        this.carritoView = carritoView;
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

                case 0:
                    System.out.println("Exit...");
                    break;

                default:
                    System.out.println("Please select a valid option.");
                    break;
            }

        } while (option != 0);
    }

    private void showMenu() {

        System.out.println("\n=== USER MENU ===");
        System.out.println("1. Create User");
        System.out.println("2. List Users");
        System.out.println("3. Sell Product");
        System.out.println("4. Publish Product");
        System.out.println("5. Buy Product");
        System.out.println("6. Shopping Cart");
        System.out.println("0. Exit");
    }
}
