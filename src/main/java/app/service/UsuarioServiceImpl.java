package app.service;

import app.domain.Usuario;
import app.service.inputports.UsuarioUseCase;
import app.service.outputports.UsuarioRepositoryInterface;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioUseCase {

    private final UsuarioRepositoryInterface repository;

    public UsuarioServiceImpl(UsuarioRepositoryInterface repository) {
        this.repository = repository;
    }

    @Override
    public void createUsuario(Usuario usuario) {
        Usuario exists = repository.getUsuarioByEmail(usuario.getCorreo());

        if (exists != null) {
            System.out.println("Email already registered.");
        }

        repository.createUsuario(usuario);
    }

    @Override
    public Usuario login(String email, String password) {
        Usuario usuario = repository.getUsuarioByEmail(email);

        if (usuario != null && usuario.getContrasena().equals(password)) {
            return usuario;
        }

        return null;
    }

    @Override
    public Usuario getUsuarioById(int idUsuario) {
        return repository.getUsuarioById(idUsuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return repository.getAllUsuarios();
    }

    @Override
    public boolean updateUsuario(Usuario usuario) {
        return repository.updateUsuario(usuario);
    }

    @Override
    public boolean deleteUsuario(int idUsuario) {
        return repository.deleteUsuario(idUsuario);
    }

    @Override
    public void sellProduct(int idUsuario) {
        Usuario usuario = repository.getUsuarioById(idUsuario);
        if (usuario != null) {
            usuario.sellProduct();
            repository.updateUsuario(usuario);
        }
    }

    @Override
    public void publishProduct(int idUsuario) {
        Usuario usuario = repository.getUsuarioById(idUsuario);
        if (usuario != null) {
            usuario.publishProduct();
            repository.updateUsuario(usuario);
        }
    }

    @Override
    public void buyProduct(int idUsuario) {
        Usuario usuario = repository.getUsuarioById(idUsuario);
        if (usuario != null) {
            usuario.buyProduct();
            repository.updateUsuario(usuario);
        }
    }
}
