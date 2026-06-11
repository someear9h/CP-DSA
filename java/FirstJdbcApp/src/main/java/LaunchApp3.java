package FirstJdbcApp.src.main.java;

import java.sql.*;

public class LaunchApp3 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/jdbclearning";
        String user = "root";
        String password = "SOMEmysql05";

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM studentInfo");

        while(rs.next()) {
//            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +
//            rs.getInt(3) + " " + rs.getString(4));

            System.out.println(rs.getInt("id") + " " + rs.getString("sname") + " " +
                    rs.getInt("sage") + " " + rs.getString("scity"));
        }

        statement.close();
        connection.close();
    }
}
