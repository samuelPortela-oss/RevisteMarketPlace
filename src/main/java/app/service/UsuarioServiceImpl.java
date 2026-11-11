package app.service;

import app.domain.Usuario;
import app.service.inputports.UsuarioUseCase;
import app.service.outputports.UsuarioRepositoryInterface;
import app.utils.FechaUtil;

import java.util.List;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioUseCase {

    private final UsuarioRepositoryInterface repository;

    public UsuarioServiceImpl(
            UsuarioRepositoryInterface repository) {

        this.repository = repository;
    }

    @Override
    public void crearUsuario(Usuario usuario) {

        if (repository.obtenerUsuarioPorCorreo(
                usuario.getCorreo()).isPresent()) {

            throw new RuntimeException(
                    "El correo ya está registrado.");
        }

        usuario.setFechaRegistro(FechaUtil.ahora());

        repository.crearUsuario(usuario);
    }

    @Override
    public Usuario iniciarSesion(
            String correo,
            String contrasena) {

        return repository.obtenerUsuarioPorCorreo(correo)
                .filter(usuario ->
                        Boolean.TRUE.equals(usuario.getActivo())
                        && usuario.getContrasena().equals(contrasena))
                .orElse(null);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(int idUsuario) {

        return repository.obtenerUsuarioPorId(idUsuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {

        return repository.listarUsuarios();
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) {

        boolean correoDeOtroUsuario =
                repository.obtenerUsuarioPorCorreo(usuario.getCorreo())
                        .filter(conMismoCorreo ->
                                conMismoCorreo.getIdUsuario()
                                        != usuario.getIdUsuario())
                        .isPresent();

        if (correoDeOtroUsuario) {

            throw new RuntimeException(
                    "El correo ya está registrado por otro usuario.");
        }

        return repository.actualizarUsuario(usuario);
    }

    @Override
    public boolean eliminarUsuario(int idUsuario) {

        return repository.eliminarUsuario(idUsuario);
    }
}
