package app.view;

import app.domain.Usuario;
import app.service.inputports.UsuarioUseCase;
import app.utils.FormRuleValidator;

import java.util.List;

public class UsuarioView {

    private UsuarioUseCase usuarioUseCase;

    public UsuarioView(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    public Usuario readUsuario() {
        Usuario usuario = new Usuario();

        usuario.setIdUsuario(FormRuleValidator.validateInt("Enter user id:"));

        usuario.setNombre(FormRuleValidator.validateString("Enter name:"));

        usuario.setCorreo(FormRuleValidator.validateEmail("Enter email:"));

        usuario.setContrasena(FormRuleValidator.validatePassword("Enter password:"));

        usuario.setCelular(FormRuleValidator.validatePhone("Enter phone:"));

        usuario.setDireccion(FormRuleValidator.validateString("Enter address:"));

        usuario.setActivo(FormRuleValidator.validateYesNo("Active user?"));

        usuario.setCalificacion("0.0");
        usuario.setNumeroVentas(0);
        usuario.setProductosPublicados(0);
        usuario.setComprasRealizadas(0);
        usuario.setListaDeseos("");
        usuario.setMetodoPago("");

        return usuario;
    }

    public void showUsuario(Usuario usuario) {
        System.out.println(usuario);
    }

    public void showUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        for (Usuario usuario : usuarios){
            System.out.println("ID: " + usuario.getIdUsuario());
            System.out.println("Name: " + usuario.getNombre());
            System.out.println("Email: " + usuario.getCorreo());
            System.out.println("Phone: " + usuario.getCelular());
            System.out.println("Address: " + usuario.getDireccion());
            System.out.println("Status: "
                    + (usuario.getActivo() ? "Active" : "Inactive"));
            System.out.println("=====================");
        };
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
