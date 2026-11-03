package app.config;

import app.UI.AppUserInterface;
import app.domain.Usuario;
import app.persistence.db.DataBaseConnection;
import app.persistence.mapper.RowMapper;
import app.persistence.mapper.UsuarioMapper;
import app.persistence.repository.UsuarioRepositoryImplBD;
import app.service.CarritoServiceImpl;
import app.service.UsuarioServiceImpl;
import app.service.inputports.CarritoUseCase;
import app.service.inputports.UsuarioUseCase;
import app.service.outputports.UsuarioRepositoryInterface;
import app.view.CarritoView;
import app.view.UsuarioView;

import java.sql.Connection;

public class Config {

    public static AppUserInterface app() {

        Connection connection = DataBaseConnection.getInstance().getConnection();
        RowMapper<Usuario> rowMapper = new UsuarioMapper();
        UsuarioRepositoryInterface usuarioRepositoryInterface = new UsuarioRepositoryImplBD(connection, rowMapper);

        UsuarioUseCase usuarioUseCase = new UsuarioServiceImpl(usuarioRepositoryInterface);
        CarritoUseCase carritoUseCase = new CarritoServiceImpl();

        UsuarioView usuarioView = new UsuarioView(usuarioUseCase);
        CarritoView carritoView = new CarritoView(carritoUseCase);

        return new AppUserInterface(usuarioView, carritoView);
    }
}
