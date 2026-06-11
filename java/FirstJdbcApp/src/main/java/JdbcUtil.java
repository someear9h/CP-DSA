package FirstJdbcApp.src.main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/jdbclearning";
    private static final String USER = "root";
    private static final String PASSWORD = "SOMEmysql05";

    // The Static Block (Runs exactly ONCE when JdbcUtil is loaded into memory)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Could not find the database driver.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}