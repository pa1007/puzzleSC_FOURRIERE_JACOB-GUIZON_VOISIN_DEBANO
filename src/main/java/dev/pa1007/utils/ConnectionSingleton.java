package dev.pa1007.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static ConnectionSingleton instance;
    private        Connection          c;

    /**
     * Constructor for db connection object
     */
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

    /**
     * @return connection object for database
     */
    public Connection getConnection() {
        return c;
    }

    /**
     * @return new instance of ConnectionSingleton
     */
    public static ConnectionSingleton getInstance() {
        if (instance == null) {
            instance = new ConnectionSingleton();
        }
        return instance;
    }
}
