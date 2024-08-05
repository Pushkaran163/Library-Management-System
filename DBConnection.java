import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{
    private static final String URL = "jdbc:mysql://localhost:3306/LibraryDB";

    private static final String USER = "root";

    private static final String PASSWORD = "enter your password here"; // Enter your password here

    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}