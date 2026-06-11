package FirstJdbcApp.src.main.java;

import java.sql.*;

public class LaunchApp4 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/jdbclearning";
        String user = "root";
        String password = "SOMEmysql05";

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();

        int rowsAffected = statement.executeUpdate("DELETE FROM studentInfo WHERE id=2");

        if(rowsAffected != 0) {
            System.out.println("Record deleted Successfully");
        } else {
            System.out.println("Could not delete the record");
        }

        statement.close();
        connection.close();
    }
}
