import java.sql.*;
import java.time.LocalDate;

public class CheckoutDAO {
    public void checkoutBook(int bookId, int userId) throws SQLException {
        String sql = "INSERT INTO checkouts (book_id, user_id, checkout_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.setInt(2, userId);
            pstmt.setDate(3, Date.valueOf(LocalDate.now()));
            pstmt.executeUpdate();
        }


        new BookDAO().updateBookAvailability(bookId, false);
    }

    public void returnBook(int bookId) throws SQLException {
        String sql = "UPDATE checkouts SET return_date = ? WHERE book_id = ? AND return_date IS NULL";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(LocalDate.now()));
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
        }


        new BookDAO().updateBookAvailability(bookId, true);
    }
}

