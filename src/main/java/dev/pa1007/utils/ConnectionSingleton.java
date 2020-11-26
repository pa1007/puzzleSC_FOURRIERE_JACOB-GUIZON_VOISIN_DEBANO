package dev.pa1007.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static ConnectionSingleton instance;
    private        Connection          c;

    private ConnectionSingleton() {
        try {
            c = DriverManager.getConnection(
                    DatabaseConnection.URL,
                    DatabaseConnection.USER,
                    DatabaseConnection.PASSWORD
            );
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return c;
    }

    public static ConnectionSingleton getInstance() {
        if (instance == null) {
            instance = new ConnectionSingleton();
        }
        return instance;
    }
}
