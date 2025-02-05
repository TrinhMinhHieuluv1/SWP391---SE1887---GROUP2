/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author ACER
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Feedback;

public class FeedbackDAO extends DBContext {

    public void addFeedback(int cid, String message, boolean status) {
        String sql = "INSERT INTO [Feedback] (CustomerID, Message, Status, CreatedAt) VALUES (?, ?, ?, GETDATE())";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);              // Sử dụng cid (Customer ID)
            st.setString(2, message);       // Sử dụng message
            st.setBoolean(3, status);       // Sử dụng status

            st.executeUpdate();  // Thực thi câu lệnh
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi log lỗi để dễ debug
        }
    }
    public static void main(String[] args) {
        FeedbackDAO dao = new FeedbackDAO();
        dao.addFeedback(2, "Tot", true);
    }
}
