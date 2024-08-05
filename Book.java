import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public static void addBook(String title, String author, String isbn){
        String query = "INSERT INTO BOOKS (title, author, isbn) VALUES (?, ?, ?)";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1, title);
                stmt.setString(2, author);
                stmt.setString(3, isbn);
                stmt.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
    }
    public static void updateBook(int bookId, String title, String author, String isbn){
        String query = "UPDATE Books SET title = ?, author = ?, isbn =? WHERE book_id = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1, title);
                stmt.setString(2, author);
                stmt.setString(3, isbn);
                stmt.setInt(4, bookId);
                stmt.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
    }
    public static void deleteBook(int bookId){
        String query = "DELETE FROM Books WHERE book_id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(1, bookId);
                stmt.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
    }
    public static ResultSet getAllBooks(){
        String query = "SELECT * FROM Books";
        try{
            Connection conn = DBConnection.getConnection();
            return conn.prepareStatement(query).executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public static ResultSet searchBooks(String keyword){
        String query = "SELECT * FROM Books HWERE title LIKE ? OR author LIKE ? OR isbn LIKE ?";
         try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            return stmt.executeQuery();
         }catch(SQLException e){
            e.printStackTrace();
            return null;
         }
    }
    
}