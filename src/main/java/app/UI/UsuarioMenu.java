package app.UI;

import app.domain.Usuario;
import app.service.inputports.UsuarioUseCase;
import app.utils.FormRuleValidator;
import app.view.UsuarioView;

public class UsuarioMenu {

    private final UsuarioUseCase usuarioUseCase;
    private final UsuarioView usuarioView;

    public UsuarioMenu(
            UsuarioUseCase usuarioUseCase,
            UsuarioView usuarioView) {

        this.usuarioUseCase = usuarioUseCase;
        this.usuarioView = usuarioView;
    }

    public void abrir() {

        int opcion;

        do {

            mostrarMenu();

            opcion = FormRuleValidator.validateInt(
                    "Seleccione una opción:");

            try {

                switch (opcion) {

                    case 1:
                        Usuario usuario = usuarioView.leerUsuario();

                        usuarioUseCase.crearUsuario(usuario);

                        usuarioView.mostrarMensaje(
                                "Usuario creado correctamente. ID: "
                                        + usuario.getIdUsuario());

                        break;

                    case 2:
                        usuarioView.mostrarUsuarios(
                                usuarioUseCase.listarUsuarios());

                        break;

                    case 3:
                        iniciarSesion();

                        break;

                    case 4:
                        actualizarUsuario();

                        break;

                    case 5:
                        int idEliminar =
                                usuarioView.leerIdUsuario();

                        boolean eliminado =
                                usuarioUseCase.eliminarUsuario(idEliminar);

                        usuarioView.mostrarMensaje(
                                eliminado
                                        ? "Usuario eliminado."
                                        : "Usuario no encontrado.");

                        break;

                    case 0:
                        break;

                    default:
                        usuarioView.mostrarMensaje(
                                "Opción inválida.");
                }

            } catch (RuntimeException e) {

                usuarioView.mostrarMensaje(
                        e.getMessage());
            }

        } while (opcion != 0);
    }

    private void iniciarSesion() {

        String correo =
                FormRuleValidator.validateEmail(
                        "Ingrese el correo:");

        String contrasena =
                FormRuleValidator.validatePassword(
                        "Ingrese la contraseña:");

        Usuario usuario =
                usuarioUseCase.iniciarSesion(correo, contrasena);

        if (usuario == null) {

            usuarioView.mostrarMensaje(
                    "Credenciales inválidas.");
        } else {

            usuarioView.mostrarMensaje(
                    "¡Bienvenido, " + usuario.getNombre() + "!");
        }
    }

    private void actualizarUsuario() {

        int idUsuario = usuarioView.leerIdUsuario();

        Usuario existente =
                usuarioUseCase.obtenerUsuarioPorId(idUsuario).orElse(null);

        if (existente == null) {

            usuarioView.mostrarMensaje(
                    "Usuario no encontrado.");
            return;
        }

        Usuario actualizado = usuarioView.leerUsuario();

        actualizado.setIdUsuario(idUsuario);
        actualizado.setFechaRegistro(existente.getFechaRegistro());

        boolean ok = usuarioUseCase.actualizarUsuario(actualizado);

        usuarioView.mostrarMensaje(
                ok
                        ? "Usuario actualizado."
                        : "No se pudo actualizar el usuario.");
    }

    private void mostrarMenu() {

        System.out.println("\n=== MENÚ USUARIOS ===");
        System.out.println("1. Crear usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Iniciar sesión");
        System.out.println("4. Actualizar usuario");
        System.out.println("5. Eliminar usuario");
        System.out.println("0. Volver");
    }
}
