package app.view;

import app.domain.Usuario;
import app.service.inputports.UsuarioUseCase;
import app.utils.FormRuleValidator;

import java.util.List;

public class UsuarioView {

    private UsuarioUseCase usuarioUseCase;

    public UsuarioView(
            UsuarioUseCase usuarioUseCase) {

        this.usuarioUseCase = usuarioUseCase;
    }

    public Usuario leerUsuario() {

        Usuario usuario = new Usuario();

        usuario.setNombre(
                FormRuleValidator.validateString(
                        "Ingrese el nombre:"));

        usuario.setCorreo(
                FormRuleValidator.validateEmail(
                        "Ingrese el correo:"));

        usuario.setContrasena(
                FormRuleValidator.validatePassword(
                        "Ingrese la contraseña:"));

        usuario.setTelefono(
                FormRuleValidator.validatePhone(
                        "Ingrese el teléfono:"));

        usuario.setDireccion(
                FormRuleValidator.validateString(
                        "Ingrese la dirección:"));

        usuario.setActivo(
                FormRuleValidator.validateYesNo(
                        "¿Usuario activo?"));

        return usuario;
    }

    public int leerIdUsuario() {

        return FormRuleValidator.validateInt(
                "Ingrese el ID del usuario:");
    }

    public void mostrarUsuario(Usuario usuario) {

        System.out.println(usuario);
    }

    public void mostrarUsuarios(List<Usuario> usuarios) {

        if (usuarios.isEmpty()) {

            System.out.println("No se encontraron usuarios.");
            return;
        }

        System.out.println("\n========== USUARIOS ==========");

        for (Usuario usuario : usuarios) {

            System.out.println("ID: " + usuario.getIdUsuario());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Correo: " + usuario.getCorreo());
            System.out.println("Teléfono: " + usuario.getTelefono());
            System.out.println("Dirección: " + usuario.getDireccion());
            System.out.println("Registro: " + usuario.getFechaRegistro());
            System.out.println("Estado: "
                    + (Boolean.TRUE.equals(usuario.getActivo()) ? "Activo" : "Inactivo"));
            System.out.println("=====================");
        }
    }

    public void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }
}
