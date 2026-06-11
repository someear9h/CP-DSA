package FirstJdbcApp.src.main.java;

import java.sql.*;

public class LaunchApp5 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/jdbclearning";
            String user = "root";
            String password = "SOMEmysql05";

            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

//        String query = "SELECT * FROM studentInfo"; // read
            String query = "UPDATE studentInfo set sage=61 where id=1"; // update

            // status is true if we have select query
            // means we just read the data
            // status is false when we make changes to the db
            boolean status = statement.execute(query);

            if(status) {
                System.out.println("If block:");
                // select (retrieve)
                ResultSet rs = statement.getResultSet();

                while(rs.next()) {
                    System.out.println(rs.getInt("id") + " " + rs.getString("sname")
                            + " " + rs.getInt("sage") + " " + rs.getString("scity"));
                }
            } else {
                System.out.println("Else block");
                // insert, update, delete
                int rowsAffected = statement.getUpdateCount();
                if(rowsAffected != 0)
                    System.out.printf("%d affected\n", rowsAffected);
                else
                    System.out.println("Something went wrong");
            }
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            // close the resources
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
