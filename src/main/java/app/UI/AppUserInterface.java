package app.UI;

import app.repository.PerfilRepositoryImpl;
import app.repository.PrendaRepositoryImpl;
import app.service.PerfilServiceImpl;
import app.service.PrendaServiceImpl;
import app.service.inputports.PerfilUseCase;
import app.service.inputports.PrendaUseCase;
import app.service.outputports.PerfilRepositoryInterface;
import app.service.outputports.PrendaRepositoryInterface;
import app.utils.FormRuleValidator;
import app.view.PerfilView;
import app.view.PrendaView;

public class AppUserInterface {

    PerfilRepositoryInterface perfilRepositoryInterface = new PerfilRepositoryImpl();
    PerfilUseCase perfilUseCase = new PerfilServiceImpl(perfilRepositoryInterface);
    PerfilView perfilView = new PerfilView(perfilUseCase);

    PrendaRepositoryInterface prendaRepositoryInterface = new PrendaRepositoryImpl();
    PrendaUseCase prendaUseCase = new PrendaServiceImpl(prendaRepositoryInterface);
    PrendaView prendaView = new PrendaView(prendaUseCase);

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
                    1. Menu de Perfiles.
                    2. Menu de Prendas.
                    """);

        switch (option){
            case 1:
                System.out.println("Menu de perfiles");
                menuPerfiles();
                break;
            case 2:
                System.out.println("Menu de prendas");
                menuPrendas();
                break;
            default:
                System.out.println("Seleccione una opcion valida");
        }
    }
}

public void menuPerfiles(){
    while(true){
        int option = FormRuleValidator.validateInt("""
                    1. crear Perfil.
                    2. Buscar Perfil por Id.
                    3. Listar todos los Perfiles.
                    4. Volver.
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
            case 4:
                return;
            default:
                System.out.println("Seleccione una opcion valida");
        }
    }
}

public void menuPrendas(){
    while(true){
        int option = FormRuleValidator.validateInt("""
                    1. Crear Prenda.
                    2. Buscar Prenda por Id.
                    3. Listar todas las Prendas.
                    4. Eliminar Prenda.
                    5. Volver.
                    """);

        switch (option){
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
}
