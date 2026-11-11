package app.service.outputports;

import app.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryInterface {

    void crearUsuario(Usuario usuario);

    Optional<Usuario> obtenerUsuarioPorId(int idUsuario);

    Optional<Usuario> obtenerUsuarioPorCorreo(String correo);

    List<Usuario> listarUsuarios();

    boolean actualizarUsuario(Usuario usuario);

    boolean eliminarUsuario(int idUsuario);
}
