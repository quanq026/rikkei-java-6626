package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/movie_db";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        String url = System.getenv().getOrDefault("DB_URL", DEFAULT_URL);
        String user = System.getenv().getOrDefault("DB_USER", DEFAULT_USER);
        String password = System.getenv().getOrDefault("DB_PASSWORD", DEFAULT_PASSWORD);
        return DriverManager.getConnection(url, user, password);
    }
}
