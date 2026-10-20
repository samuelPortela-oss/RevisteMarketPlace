package app.view;

import app.domain.Perfil;
import app.domain.Usuario;
import app.service.inputports.PerfilUseCase;
import app.utils.FormRuleValidator;

import java.util.List;

public class PerfilView {

    private final PerfilUseCase perfilService;

    public PerfilView(PerfilUseCase perfilUseCase){
        this.perfilService = perfilUseCase;
    }

    public void createPerfil(){
        System.out.println("Creando nuevo perfil");

        int idUsuario = FormRuleValidator.validateInt("ingrese el número id de la persona");
        String nombre = FormRuleValidator.validateString("Ingrese el nombre completo");
        String correo = FormRuleValidator.validateString("Ingrese el correo");
        String contrasena = FormRuleValidator.validateString("Ingrese una contraseña de al menos 8 caracteres que contengan mayúsculas, números y símbolos: ");
        int celular = FormRuleValidator.validateInt("Ingrese un número celular");
        String direccion = FormRuleValidator.validateString("Ingrese la dirección de residencia");
        String fechaRegistro = FormRuleValidator.validateString("Ingrese la fecha de registro dd/mm/yyyy");
        Double reputacion = null;
        String descripcionPerfil = FormRuleValidator.validateString("Añada una breve descripción");

        perfilService.createPerfil(idUsuario, nombre, correo, contrasena, celular, direccion, fechaRegistro, reputacion, descripcionPerfil);
    }

    public void getPerfilById(int idUsuario){

    }

    public void getAllPerfils(){

        List<Perfil> perfils = perfilService.getAllPerfils();

        for (Perfil perfil : perfils){
            System.out.println(perfil.getIdUsuario() + " " +  perfil.getNombre() + " " + perfil.getCorreo() + " " + perfil.getFechaRegistro() + " " + perfil.getDescripcionPerfil());
        }

    }

    public void updatePerfil(){

    }

    public void deletePerfil(int idUsuario){

    }
}
