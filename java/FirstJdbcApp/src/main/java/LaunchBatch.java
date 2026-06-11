package FirstJdbcApp.src.main.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LaunchBatch {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtil.getConnection();
            String query = "UPDATE studentInfo SET sage=? WHERE id=?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, 20);
            preparedStatement.setInt(2, 1);
            preparedStatement.addBatch();

            preparedStatement.setInt(1, 22);
            preparedStatement.setInt(2, 2);
            preparedStatement.addBatch();

            preparedStatement.setInt(1, 45);
            preparedStatement.setInt(2, 3);
            preparedStatement.addBatch();

            preparedStatement.executeBatch();
            System.out.println("Success: CHECK DB");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
