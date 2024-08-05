import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Issue{
    private int issueId;
    private int userId;
    private int bookId;
    private Date issueDate;
    private Date returnDate;

    public static void issueBook(int userId, int bookId) {
        String query = "INSERT INTO Issues (user_id, book_id, issue_date) VALUES (?, ?, CURDATE())";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(1, userId);
                stmt.setInt(2, bookId);
                stmt.executeUpdate();

                String updateQuery = "UPDATE Books SET available = FALSE WHERE book_id = ?";
                try(PreparedStatement updateStmt = conn.prepareStatement(updateQuery)){
                    updateStmt.setInt(1, bookId);
                    updateStmt.executeUpdate();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
    }
    public static void returnBook(int issueId){
        String query = "UPDATE Issues SET return_date = CURDATE() WHERE issue_id = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(1, issueId);
                stmt.executeUpdate();

                String updateQuery = "UPDATE Books SET available = TRUE WHERE book_id = (SELECT book_id FROM Issues WHERE issue_id = ?)";
                try(PreparedStatement updateStmt = conn.prepareStatement(updateQuery)){
                    updateStmt.setInt(1, issueId);
                    updateStmt.executeUpdate();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
    }
    public static ResultSet getAllIssues(){
        String query = "SELECT * FROM Issues";
        try{
            Connection conn = DBConnection.getConnection();
            return conn.prepareStatement(query).executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}