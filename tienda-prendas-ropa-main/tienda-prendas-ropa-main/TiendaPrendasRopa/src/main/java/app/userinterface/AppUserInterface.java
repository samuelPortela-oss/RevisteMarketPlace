package app.userinterface;

import app.persistence.db.DataBaseConnection;
import app.persistence.repository.AccountRepositoryImpl;
import app.persistence.repository.PersonRepositoryImp;
import app.persistence.repository.PersonRepositoryImplBD;
import app.service.AccountServiceImpl;
import app.service.PersonServiceImpl;
import app.service.inputports.AccountUseCase;
import app.service.inputports.PersonUseCase;
import app.service.outputports.AccountRepositoryInterface;
import app.service.outputports.PersonRepositoryInterface;
import app.utils.FormRuleValidator;
import app.view.AccountView;
import app.view.PersonView;

import java.sql.Connection;

public class AppUserInterface {

    Connection connection = DataBaseConnection.getInstance().getConnection();

    PersonRepositoryInterface personRepositoryInterface = new PersonRepositoryImplBD(connection);
    PersonUseCase personUseCase = new PersonServiceImpl(personRepositoryInterface);
    PersonView personView = new PersonView(personUseCase);
    AccountRepositoryInterface accountRepositoryInterface = new AccountRepositoryImpl();
    AccountUseCase accountUseCase = new AccountServiceImpl(accountRepositoryInterface, personRepositoryInterface);
    AccountView accountView = new AccountView(accountUseCase);


    public void startApp(){
        int init= FormRuleValidator.validateInt("Presione 1 para iniciar la aplicacion");
        do {
            DataBaseConnection.getInstance();
            int option = FormRuleValidator.validateInt("""
                1. Registro.
                2. Login.
                3. Salir.
                """);

            System.out.println("Bienvenido a la aplicacion de gestion de tienda de prendas de ropa");

            switch (option){
                case 1:
                    System.out.println("Registro de Persona");
                    personView.createPerson();
                    break;
                case 2:
                    System.out.println("Login");
                    menuApp();
                    break;
                case 3:
                    System.out.println("Salir");
                    init = 0;
                    break;
                default:
                    System.out.println("Seleccione una opcion valida");
            }

        }while(init != 0);
    }


    public void menuApp(){

        while(true){
            int option = FormRuleValidator.validateInt("""
                    1. Menu de clientes.
                    2. Menu de prendas.
                    """);

            switch (option){
                case 1:
                    System.out.println("Menu de clientes");
                    personMenu();
                    break;
                case 2:
                    System.out.println("Menu de prendas");
                    accountMenu();
                    break;
                default:
                    System.out.println("Seleccione una opcion valida");
            }
        }
    }


    public void personMenu(){
        while(true){
            int option = FormRuleValidator.validateInt("""
                    1. crear Cliente.
                    2. Buscar cliente por Id.
                    3. Listar todos los clientes.
                    """);

            switch (option){
                case 1:
                    System.out.println("Crear cliente");
                    personView.createPerson();
                    break;
                case 2:
                    System.out.println("Buscar cliente por Id");
                    personView.showPersonById();
                    break;
                case 3:
                    System.out.println("Listar todos los clientes");
                    personView.showPersons();
                    break;
                default:
                    System.out.println("Seleccione una opcion valida");
            }
        }
    }

    public void accountMenu(){

        while(true){

            int option = FormRuleValidator.validateInt("""
                    1. crear Prenda.
                    2. Buscar prenda por Id.
                    3. Listar todas las prendas.
                    """);

            switch (option){
                case 1:
                    System.out.println("Crear prenda");
                    accountView.createAccount();
                    break;
                case 2:
                    System.out.println("Buscar prenda por Id");
                    int id = FormRuleValidator.validateInt("Ingrese el id de la prenda a buscar");
                    accountView.selectAccountById(id);
                    break;
                case 3:
                    System.out.println("Listar todas las prendas");
                    accountView.selectAllAccounts();
                    break;
                default:
                    System.out.println("Seleccione una opcion valida");
            }
        }
    }
}
