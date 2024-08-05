// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// public class User {
//     private int userId;
//     private String name;
//     private String email;
//     private String phone;

//     public static void addUser(String name, String email, String phone){
//         String query = "INSERT INTO Users (name, email, phone) VALUES (?, ?, ?)";

//         try(Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//                 stmt.setString(1, name);
//                 stmt.setString(2, email);
//                 stmt.setString(3, phone);
//                 stmt.executeUpdate();
//             }catch (SQLException e){
//                 e.printStackTrace();
//             }
//     }
//     public static void updateUser(int userId, String name, String email, String phone){
//         String query = "UPDATE Users SET nme = ?, email = ?, phone = ? WHERE user_id = ?";
//         try(Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//                 stmt.setString(1, name);
//                 stmt.setString(2, email);
//                 stmt.setString(3, phone);
//                 stmt.setInt(4, userId);
//                 stmt.executeUpdate();
//             }catch(SQLException e){
//                 e.printStackTrace();
//             }
//     }
//     public static void deleteUser(int userId) {
//         String query = "DELETE FROM Users WHERE user_id = ?";
//         try(Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//                 stmt.setInt(1, userId);
//                 stmt.executeUpdate();
//             }catch(SQLException e){
//                 e.printStackTrace();
//             }
//     }
//     public static ResultSet getAllUsers(){
//         String query = "SELECT * FROM Users";
//         try{
//             Connection conn = DBConnection.getConnection();
//             return conn.prepareStatement(query).executeQuery();
//         }catch(SQLException e){
//             e.printStackTrace();
//             return null;
//         }
//     }
// }


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int userId;
    private String name;
    private String email;
    private String phone;

    // Constructors, getters, and setters

    public static void addUser(String name, String email, String phone) {
        String query = "INSERT INTO Users (name, email, phone) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(int userId, String name, String email, String phone) {
        String query = "UPDATE Users SET name = ?, email = ?, phone = ? WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setInt(4, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int userId) {
        String query = "DELETE FROM Users WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getAllUsers() {
        String query = "SELECT * FROM Users";
        try {
            Connection conn = DBConnection.getConnection();
            return conn.prepareStatement(query).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}