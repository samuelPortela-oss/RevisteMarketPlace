package app.persistence.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static DataBaseConnection instance;
    private final Connection connection;

    private static final String url = "jdbc:mysql://localhost:3306/reviste_db";
    private static final String userName = "root";
    private static final String password = "reviste123";

    private DataBaseConnection() {

        try {
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Conexion exitosa a la base de datos");
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos");
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
