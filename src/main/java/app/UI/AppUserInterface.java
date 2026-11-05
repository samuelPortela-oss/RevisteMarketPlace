package app.UI;

import app.repository.CategoriaRepositoryImpl;
import app.repository.PerfilRepositoryImpl;
import app.service.CategoriaServiceImpl;
import app.service.PerfilServiceImpl;
import app.service.inputports.CategoriaUseCase;
import app.service.inputports.PerfilUseCase;
import app.service.outputports.CategoriaRepositoryInterface;
import app.service.outputports.PerfilRepositoryInterface;
import app.utils.FormRuleValidator;
import app.view.CategoriaView;
import app.view.PerfilView;

public class AppUserInterface {

    PerfilRepositoryInterface perfilRepositoryInterface = new PerfilRepositoryImpl();
    PerfilUseCase perfilUseCase = new PerfilServiceImpl(perfilRepositoryInterface);
    PerfilView perfilView = new PerfilView(perfilUseCase);

    CategoriaRepositoryInterface categoriaRepositoryInterface = new CategoriaRepositoryImpl();
    CategoriaUseCase categoriaUseCase = new CategoriaServiceImpl(categoriaRepositoryInterface);
    CategoriaView categoriaView = new CategoriaView(categoriaUseCase);

    public void startApp(){
        int init = FormRuleValidator.validateInt("Presione 1 para iniciar la aplicación");
        do {
            int option = FormRuleValidator.validateInt("""
                    1. Registro.
                    2. Login.
                    3. Categorías.
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
                    System.out.println("Categorías");
                    categoriaMenu();
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

    public void categoriaMenu() {
        boolean volver = false;
        while (!volver) {
            int option = FormRuleValidator.validateInt("""
                    1. Crear categoría.
                    2. Buscar categoría por Id.
                    3. Listar todas las categorías.
                    4. Actualizar categoría.
                    5. Eliminar categoría.
                    0. Volver.
                    """);

            switch (option) {
                case 1:
                    categoriaView.createCategoria();
                    break;
                case 2:
                    int idBuscar = FormRuleValidator.validateInt("Ingrese el id de la categoría a buscar");
                    categoriaView.getCategoriaById(idBuscar);
                    break;
                case 3:
                    categoriaView.getAllCategorias();
                    break;
                case 4:
                    categoriaView.updateCategoria();
                    break;
                case 5:
                    int idEliminar = FormRuleValidator.validateInt("Ingrese el id de la categoría a eliminar");
                    categoriaView.deleteCategoria(idEliminar);
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Seleccione una opción válida");
            }
        }
    }
}
