package app.config;

import app.UI.AppUserInterface;
import app.UI.CarritoMenu;
import app.UI.CategoriaMenu;
import app.UI.MetodoPagoMenu;
import app.UI.OrdenMenu;
import app.UI.PagoMenu;
import app.UI.PrendaMenu;
import app.UI.UsuarioMenu;
import app.persistence.db.DataBaseConnection;
import app.persistence.mapper.CarritoMapper;
import app.persistence.mapper.CategoriaMapper;
import app.persistence.mapper.FotoPrendaMapper;
import app.persistence.mapper.ItemCarritoMapper;
import app.persistence.mapper.ItemOrdenMapper;
import app.persistence.mapper.MetodoPagoMapper;
import app.persistence.mapper.OrdenMapper;
import app.persistence.mapper.PagoMapper;
import app.persistence.mapper.PrendaMapper;
import app.persistence.mapper.UsuarioMapper;
import app.persistence.repository.CarritoRepositoryImplBD;
import app.persistence.repository.CategoriaRepositoryImplBD;
import app.persistence.repository.FotoPrendaRepositoryImplBD;
import app.persistence.repository.ItemCarritoRepositoryImplBD;
import app.persistence.repository.ItemOrdenRepositoryImplBD;
import app.persistence.repository.MetodoPagoRepositoryImplBD;
import app.persistence.repository.OrdenRepositoryImplBD;
import app.persistence.repository.PagoRepositoryImplBD;
import app.persistence.repository.PrendaRepositoryImplBD;
import app.persistence.repository.UsuarioRepositoryImplBD;
import app.service.CarritoServiceImpl;
import app.service.CategoriaServiceImpl;
import app.service.FotoPrendaServiceImpl;
import app.service.ItemCarritoServiceImpl;
import app.service.ItemOrdenServiceImpl;
import app.service.MetodoPagoServiceImpl;
import app.service.OrdenServiceImpl;
import app.service.PagoServiceImpl;
import app.service.PrendaServiceImpl;
import app.service.UsuarioServiceImpl;
import app.service.inputports.CarritoUseCase;
import app.service.inputports.CategoriaUseCase;
import app.service.inputports.FotoPrendaUseCase;
import app.service.inputports.ItemCarritoUseCase;
import app.service.inputports.ItemOrdenUseCase;
import app.service.inputports.MetodoPagoUseCase;
import app.service.inputports.OrdenUseCase;
import app.service.inputports.PagoUseCase;
import app.service.inputports.PrendaUseCase;
import app.service.inputports.UsuarioUseCase;
import app.service.outputports.CarritoRepositoryInterface;
import app.service.outputports.CategoriaRepositoryInterface;
import app.service.outputports.FotoPrendaRepositoryInterface;
import app.service.outputports.ItemCarritoRepositoryInterface;
import app.service.outputports.ItemOrdenRepositoryInterface;
import app.service.outputports.MetodoPagoRepositoryInterface;
import app.service.outputports.OrdenRepositoryInterface;
import app.service.outputports.PagoRepositoryInterface;
import app.service.outputports.PrendaRepositoryInterface;
import app.service.outputports.UsuarioRepositoryInterface;
import app.view.CarritoView;
import app.view.CategoriaView;
import app.view.FotoPrendaView;
import app.view.ItemCarritoView;
import app.view.ItemOrdenView;
import app.view.MetodoPagoView;
import app.view.OrdenView;
import app.view.PagoView;
import app.view.PrendaView;
import app.view.UsuarioView;

import java.sql.Connection;

/**
 * Raíz de composición: construye y conecta todas las dependencias de la app.
 */
public class Config {

    public static AppUserInterface app() {

        // Conexión única a MySQL (XAMPP)
        Connection connection = DataBaseConnection.getInstance().getConnection();

        // Repositories (output adapters JDBC + mappers)
        UsuarioRepositoryInterface usuarioRepository =
                new UsuarioRepositoryImplBD(connection, new UsuarioMapper());
        CategoriaRepositoryInterface categoriaRepository =
                new CategoriaRepositoryImplBD(connection, new CategoriaMapper());
        PrendaRepositoryInterface prendaRepository =
                new PrendaRepositoryImplBD(connection, new PrendaMapper());
        FotoPrendaRepositoryInterface fotoPrendaRepository =
                new FotoPrendaRepositoryImplBD(connection, new FotoPrendaMapper());
        CarritoRepositoryInterface carritoRepository =
                new CarritoRepositoryImplBD(connection, new CarritoMapper());
        ItemCarritoRepositoryInterface itemCarritoRepository =
                new ItemCarritoRepositoryImplBD(connection, new ItemCarritoMapper());
        OrdenRepositoryInterface ordenRepository =
                new OrdenRepositoryImplBD(connection, new OrdenMapper());
        ItemOrdenRepositoryInterface itemOrdenRepository =
                new ItemOrdenRepositoryImplBD(connection, new ItemOrdenMapper());
        PagoRepositoryInterface pagoRepository =
                new PagoRepositoryImplBD(connection, new PagoMapper());
        MetodoPagoRepositoryInterface metodoPagoRepository =
                new MetodoPagoRepositoryImplBD(connection, new MetodoPagoMapper());

        // Use cases (application services)
        UsuarioUseCase usuarioUseCase = new UsuarioServiceImpl(usuarioRepository);
        CategoriaUseCase categoriaUseCase = new CategoriaServiceImpl(categoriaRepository);
        PrendaUseCase prendaUseCase = new PrendaServiceImpl(
                prendaRepository, usuarioRepository, categoriaRepository);
        FotoPrendaUseCase fotoPrendaUseCase = new FotoPrendaServiceImpl(
                fotoPrendaRepository, prendaRepository);
        CarritoUseCase carritoUseCase = new CarritoServiceImpl(
                carritoRepository, itemCarritoRepository, prendaRepository, usuarioRepository);
        ItemCarritoUseCase itemCarritoUseCase = new ItemCarritoServiceImpl(itemCarritoRepository);
        OrdenUseCase ordenUseCase = new OrdenServiceImpl(
                ordenRepository, itemOrdenRepository, carritoRepository,
                itemCarritoRepository, prendaRepository, usuarioRepository);
        ItemOrdenUseCase itemOrdenUseCase = new ItemOrdenServiceImpl(itemOrdenRepository);
        PagoUseCase pagoUseCase = new PagoServiceImpl(
                pagoRepository, ordenRepository, itemOrdenRepository,
                prendaRepository, metodoPagoRepository);
        MetodoPagoUseCase metodoPagoUseCase =
                new MetodoPagoServiceImpl(metodoPagoRepository);

        // Views (console I/O)
        UsuarioView usuarioView = new UsuarioView(usuarioUseCase);
        CategoriaView categoriaView = new CategoriaView(categoriaUseCase);
        PrendaView prendaView = new PrendaView(prendaUseCase);
        FotoPrendaView fotoPrendaView = new FotoPrendaView(fotoPrendaUseCase);
        CarritoView carritoView = new CarritoView(carritoUseCase);
        ItemCarritoView itemCarritoView = new ItemCarritoView(itemCarritoUseCase);
        OrdenView ordenView = new OrdenView(ordenUseCase);
        ItemOrdenView itemOrdenView = new ItemOrdenView(itemOrdenUseCase);
        PagoView pagoView = new PagoView(pagoUseCase);
        MetodoPagoView metodoPagoView =
                new MetodoPagoView(metodoPagoUseCase);

        // Menus (flow control)
        UsuarioMenu usuarioMenu = new UsuarioMenu(usuarioUseCase, usuarioView);
        CategoriaMenu categoriaMenu = new CategoriaMenu(categoriaUseCase, categoriaView);
        PrendaMenu prendaMenu = new PrendaMenu(
                prendaUseCase, prendaView, fotoPrendaUseCase, fotoPrendaView);
        CarritoMenu carritoMenu = new CarritoMenu(
                carritoUseCase, carritoView, itemCarritoUseCase, itemCarritoView);
        OrdenMenu ordenMenu = new OrdenMenu(
                ordenUseCase, ordenView, itemOrdenUseCase, itemOrdenView);
        PagoMenu pagoMenu = new PagoMenu(
                pagoUseCase, ordenUseCase, pagoView, ordenView);
        MetodoPagoMenu metodoPagoMenu = new MetodoPagoMenu(
                metodoPagoUseCase, metodoPagoView);

        return new AppUserInterface(
                usuarioMenu, categoriaMenu, prendaMenu, carritoMenu,
                ordenMenu, pagoMenu, metodoPagoMenu);
    }
}
