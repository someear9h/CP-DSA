package FirstJdbcApp.src.main.java;

import java.sql.*;

public class LaunchApp1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/jdbclearning";
        String user = "root";
        String password = "SOMEmysql05";

        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();

        String sqlQuery = "INSERT INTO studentInfo(id, sname, sage, scity) VALUES(2, 'Samarth', 21, 'Mumbai')";
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
