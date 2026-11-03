package app.service.inputports;

import app.domain.Usuario;
import java.util.List;

public interface UsuarioUseCase {

    void createUsuario(Usuario usuario);

    Usuario login(String email, String password);

    Usuario getUsuarioById(int idUsuario);

    List<Usuario> getAllUsuarios();

    boolean updateUsuario(Usuario usuario);

    boolean deleteUsuario(int idUsuario);

    void sellProduct(int idUsuario);

    void publishProduct(int idUsuario);

    void buyProduct(int idUsuario);
}
