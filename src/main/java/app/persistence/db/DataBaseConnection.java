package app.persistence.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static DataBaseConnection instance;
    private final Connection connection;

    // Credenciales por defecto de XAMPP (MySQL/MariaDB). Solo para desarrollo local:
    // exponer secretos en el código es una mala práctica; en producción usar un gestor de secretos.
    private static final String url = "jdbc:mysql://localhost:3306/reviste_marketplace_db";
    private static final String userName = "root";
    private static final String password = "";

    private DataBaseConnection() {
        try {
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static synchronized DataBaseConnection getInstance() {
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
