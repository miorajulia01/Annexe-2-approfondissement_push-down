package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final String url = "jdbc:postgresql://localhost:5432/annexe2_push_down";
    private final String user = "candidat";
    private final String password = "election";

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to PostgreSQL database");
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return conn;
    }
}
