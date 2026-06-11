package FirstJdbcApp.src.main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LaunchApp2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/jdbclearning";
        String user = "root";
        String password = "SOMEmysql05";

        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();

        String sqlQuery = "UPDATE studentInfo set sage=24 where id=2";
        int rowsAffected = statement.executeUpdate(sqlQuery);

        if(rowsAffected != 0) {
            System.out.printf("%d rows affected", rowsAffected);
        } else {
            System.out.println("Unable to insert data");
        }

        statement.close();
        connection.close();
    }
}
