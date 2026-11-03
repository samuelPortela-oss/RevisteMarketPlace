package app.service.outputports;

import app.domain.Usuario;
import java.util.List;

public interface UsuarioRepositoryInterface {

    void createUsuario(Usuario usuario);

    Usuario getUsuarioById(int idUsuario);

    Usuario getUsuarioByEmail(String email);

    List<Usuario> getAllUsuarios();

    boolean updateUsuario(Usuario usuario);

    boolean deleteUsuario(int idUsuario);
}
