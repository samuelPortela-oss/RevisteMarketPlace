package app.config;

import app.UI.AppUserInterface;
import app.repository.UsuarioRepositoryImpl;
import app.service.CarritoServiceImpl;
import app.service.UsuarioServiceImpl;
import app.service.inputports.CarritoUseCase;
import app.service.inputports.UsuarioUseCase;
import app.service.outputports.UsuarioRepositoryInterface;
import app.view.CarritoView;
import app.view.UsuarioView;

public class Config {

    public static AppUserInterface app() {

        UsuarioRepositoryInterface usuarioRepositoryInterface = new UsuarioRepositoryImpl();

        UsuarioUseCase usuarioUseCase = new UsuarioServiceImpl(usuarioRepositoryInterface);
        CarritoUseCase carritoUseCase = new CarritoServiceImpl();

        UsuarioView usuarioView = new UsuarioView(usuarioUseCase);
        CarritoView carritoView = new CarritoView(carritoUseCase);

        return new AppUserInterface(usuarioView, carritoView);
    }
}
