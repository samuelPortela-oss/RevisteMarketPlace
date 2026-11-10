package app.service.inputports;

import app.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioUseCase {

    void crearUsuario(Usuario usuario);

    Usuario iniciarSesion(String correo, String contrasena);

    Optional<Usuario> obtenerUsuarioPorId(int idUsuario);

    List<Usuario> listarUsuarios();

    boolean actualizarUsuario(Usuario usuario);

    boolean eliminarUsuario(int idUsuario);
}
