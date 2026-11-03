package app.config;

import app.UI.AppUserInterface;
import app.domain.Prenda;
import app.domain.Usuario;
import app.persistence.db.DataBaseConnection;
import app.persistence.mapper.PrendaMapper;
import app.persistence.mapper.RowMapper;
import app.persistence.mapper.UsuarioMapper;
import app.persistence.repository.PrendaRepositoryImplBD;
import app.persistence.repository.UsuarioRepositoryImplBD;
import app.service.CarritoServiceImpl;
import app.service.PrendaServiceImpl;
import app.service.UsuarioServiceImpl;
import app.service.inputports.CarritoUseCase;
import app.service.inputports.PrendaUseCase;
import app.service.inputports.UsuarioUseCase;
import app.service.outputports.PrendaRepositoryInterface;
import app.service.outputports.UsuarioRepositoryInterface;
import app.view.CarritoView;
import app.view.PrendaView;
import app.view.UsuarioView;

import java.sql.Connection;

public class Config {

    public static AppUserInterface app() {

        Connection connection = DataBaseConnection.getInstance().getConnection();

        RowMapper<Usuario> usuarioRowMapper = new UsuarioMapper();
        UsuarioRepositoryInterface usuarioRepositoryInterface = new UsuarioRepositoryImplBD(connection, usuarioRowMapper);

        RowMapper<Prenda> prendaRowMapper = new PrendaMapper();
        PrendaRepositoryInterface prendaRepositoryInterface = new PrendaRepositoryImplBD(connection, prendaRowMapper);

        UsuarioUseCase usuarioUseCase = new UsuarioServiceImpl(usuarioRepositoryInterface);
        CarritoUseCase carritoUseCase = new CarritoServiceImpl();
        PrendaUseCase prendaUseCase = new PrendaServiceImpl(prendaRepositoryInterface);

        UsuarioView usuarioView = new UsuarioView(usuarioUseCase);
        CarritoView carritoView = new CarritoView(carritoUseCase);
        PrendaView prendaView = new PrendaView(prendaUseCase);

        return new AppUserInterface(usuarioView, carritoView, prendaView);
    }
}
