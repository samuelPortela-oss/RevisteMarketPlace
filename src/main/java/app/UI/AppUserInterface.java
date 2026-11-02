package app.UI;

import app.repository.PerfilRepositoryImpl;
import app.service.PerfilServiceImpl;
import app.service.inputports.PerfilUseCase;
import app.service.outputports.PerfilRepositoryInterface;
import app.utils.FormRuleValidator;
import app.view.PerfilView;
import app.view.CarritoView; // 1. Importa tu vista del carrito

public class AppUserInterface {

    PerfilRepositoryInterface perfilRepositoryInterface = new PerfilRepositoryImpl();
    PerfilUseCase perfilUseCase = new PerfilServiceImpl(perfilRepositoryInterface);
    PerfilView perfilView = new PerfilView(perfilUseCase);

    // 2. Instancia tu vista del carrito
    CarritoView carritoView = new CarritoView();

    public void startApp(){
        int init = FormRuleValidator.validateInt("Presione 1 para iniciar la aplicación");
        do {
            // 3. Agregamos la opción 4 para tu carrito en el menú
            int option = FormRuleValidator.validateInt("""
                    1. Registro.
                    2. Login.
                    3. Carrito de Compras.
                    4. Salir.
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
                    // 4. Llamas a tu menú del carrito aquí
                    System.out.println("Entrando al Carrito de Compras");
                    carritoView.mostrarMenu();
                    break;
                case 4:
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