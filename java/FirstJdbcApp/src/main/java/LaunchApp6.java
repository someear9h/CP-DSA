package FirstJdbcApp.src.main.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LaunchApp6 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtil.getConnection();
            String query = "UPDATE studentInfo SET sage=? WHERE id=?";
            preparedStatement = connection.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter id: ");
            int id = sc.nextInt();

            System.out.println("Enter age to update: ");
            int age = sc.nextInt();

            preparedStatement.setInt(2, id);
            preparedStatement.setInt(1, age);

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected != 0)
                System.out.printf("%d rows affected", rowsAffected);
            else
                System.out.println("Something went wrong");

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
