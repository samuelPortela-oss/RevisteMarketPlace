package app.UI;

import app.domain.Usuario;
import app.repository.UsuarioRepositoryImpl;
import app.service.UsuarioServiceImpl;
import app.service.inputports.UsuarioUseCase;
import app.service.outputports.UsuarioRepositoryInterface;
import app.view.UsuarioView;
import app.utils.FormRuleValidator;

public class AppUserInterface {

    UsuarioRepositoryInterface usuarioRepositoryInterface = new UsuarioRepositoryImpl();
    UsuarioUseCase usuarioUseCase = new UsuarioServiceImpl(usuarioRepositoryInterface);
    UsuarioView usuarioView = new UsuarioView(usuarioUseCase);

    public void startApp() {

        int option;

        do {

            showMenu();

            option = FormRuleValidator.validateInt("Select an option:");

            switch (option) {

                case 1:
                    Usuario usuario = usuarioView.readUsuario();
                    usuarioUseCase.createUsuario(usuario);
                    usuarioView.showMessage("User created successfully.");
                    break;

                case 2:
                    usuarioView.showUsuarios(usuarioUseCase.getAllUsuarios());
                    break;

                case 3:
                    int sellId = FormRuleValidator.validateInt("Enter user id to sell product:");
                    usuarioUseCase.sellProduct(sellId);
                    usuarioView.showMessage("Product sold.");
                    break;

                case 4:
                    int publishId = FormRuleValidator.validateInt("Enter user id to publish product:");
                    usuarioUseCase.publishProduct(publishId);
                    usuarioView.showMessage("Product published.");
                    break;

                case 5:
                    int buyId = FormRuleValidator.validateInt("Enter user id to buy product:");
                    usuarioUseCase.buyProduct(buyId);
                    usuarioView.showMessage("Product bought.");
                    break;

                case 0:
                    System.out.println("Exit...");
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
        System.out.println("0. Exit");
    }
}
