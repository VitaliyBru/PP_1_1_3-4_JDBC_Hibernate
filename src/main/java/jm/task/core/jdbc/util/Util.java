package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_test";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "kataacademy";
    private static Connection connection;

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
