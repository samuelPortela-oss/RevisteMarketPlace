package app.UI;

import app.repository.PerfilRepositoryImpl;
import app.service.PerfilServiceImpl;
import app.service.inputports.PerfilUseCase;
import app.service.outputports.PerfilRepositoryInterface;
import app.utils.FormRuleValidator;
import app.view.PerfilView;

public class AppUserInterface {

    PerfilRepositoryInterface perfilRepositoryInterface = new PerfilRepositoryImpl();
    PerfilUseCase perfilUseCase = new PerfilServiceImpl(perfilRepositoryInterface);
    PerfilView perfilView = new PerfilView(perfilUseCase);

    public void startApp(){
        int init = FormRuleValidator.validateInt("Presione 1 para iniciar la aplicación");
        do {
            int option = FormRuleValidator.validateInt("""
                    1. Registro.
                    2. Login.
                    3. Salir.
                    """);

            System.out.println("Bienvenido a la aplicacion de compra y venta de ropa de segunda mano -Reviste");

            switch (option) {
                case 1:
                    System.out.println("Registro de nuevo perfil");
                    perfilView.createPerfil();
                    break;
                case 2:
                    System.out.println("Login");
                    perfilMenu();
                    break;
                case 3:
                    System.out.println("Salir");
                    init = 0;
                    break;
                default:
                    System.out.println("Seleccione una opción válida");
            }
        } while (init != 0);
    }
public void perfilMenu(){
    while(true){
        int option = FormRuleValidator.validateInt("""
                    1. crear Perfil.
                    2. Buscar Perfil por Id.
                    3. Listar todos los Perfiles.
                    """);

        switch (option){
            case 1:
                System.out.println("Crear nuevo perfil");
                perfilView.createPerfil();
                break;
            case 2:
                System.out.println("Buscar perfil por Id");
                int id = FormRuleValidator.validateInt("Ingrese el id del perfil a buscar");
                perfilView.getPerfilById(id);
                break;
            case 3:
                System.out.println("Listar todos los perfiles");
                perfilView.getAllPerfils();
                break;
            default:
                System.out.println("Seleccione una opcion valida");
        }
    }
}
}
