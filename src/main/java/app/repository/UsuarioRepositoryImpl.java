package app.repository;

import app.domain.Usuario;
import app.service.outputports.UsuarioRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepositoryInterface {

    private final List<Usuario> usuarios;

    public UsuarioRepositoryImpl() {
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void createUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public Usuario getUsuarioById(int idUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public Usuario getUsuarioByEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(email)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarios;
    }

    @Override
    public boolean updateUsuario(Usuario usuarioActualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getIdUsuario() == usuarioActualizado.getIdUsuario()) {
                usuarios.set(i, usuarioActualizado);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteUsuario(int idUsuario) {
        return usuarios.removeIf(usuario -> usuario.getIdUsuario() == idUsuario);
    }
}
