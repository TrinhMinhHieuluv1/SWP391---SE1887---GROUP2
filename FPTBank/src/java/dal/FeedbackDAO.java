/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Feedback;

/**
 *
 * @author ADMIN
 */
public class FeedbackDAO extends DBContext {

    public List<Feedback> selectAllFeedback() {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM Feedback";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback fbToAdd = new Feedback(rs.getInt("FeedbackID"),
                        rs.getInt("CustomerID"),
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"));
                feedbackList.add(fbToAdd);
            }
        } catch (SQLException e) {
        }
        return feedbackList;
    }

    public Feedback findFBByID(int CustomerID) {
        Feedback feedback = null;
        String sql = "SELECT * FROM Feedback WHERE CustomerID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, CustomerID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                feedback = new Feedback(rs.getInt("FeedbackID"),
                        rs.getInt("CustomerID"),
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedback;
    }

    public List<Feedback> findFBByDate(Date date) {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE CONVERT(DATE, CreatedAt) = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback fbToAdd = new Feedback(rs.getInt("FeedbackID"),
                        rs.getInt("CustomerID"),
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"));
                feedbackList.add(fbToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbackList;
    }

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

    public List<Feedback> findFBByID2(int CustomerID) {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE CustomerID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, CustomerID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Feedback feedback = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("CustomerID"),
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt")
                );
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbackList;
    }

    public List<Feedback> getFeedbacksFromDate(int day, int month, int year, int cid) {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE DAY(CreatedAt) >= ? AND MONTH(CreatedAt) >= ? AND YEAR(CreatedAt) >= ? AND CustomerID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Truyền 3 giá trị vào câu lệnh SQL
            ps.setInt(1, day);
            ps.setInt(2, month);
            ps.setInt(3, year);
            ps.setInt(4, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("FeedbackID"));
                feedback.setCustomerID(rs.getInt("CustomerID"));
                feedback.setMessage(rs.getString("Message"));
                feedback.setResponse(rs.getString("Response"));
                feedback.setStatus(rs.getBoolean("Status"));
                feedback.setCreatedAt(rs.getDate("CreatedAt"));

                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbackList;
    }

    public List<Feedback> searchFeedbackByMessage(String txt) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE Message LIKE ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("FeedbackID"));
                feedback.setCustomerID(rs.getInt("CustomerID"));
                feedback.setMessage(rs.getString("Message"));
                feedback.setResponse(rs.getString("Response"));
                feedback.setStatus(rs.getBoolean("Status"));
                feedback.setCreatedAt(rs.getTimestamp("CreatedAt"));
                list.add(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateStatus(int feedbackId, boolean newStatus) {
        String sql = "UPDATE Feedback SET Status = ? WHERE FeedbackID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setBoolean(1, newStatus);
            stmt.setInt(2, feedbackId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        FeedbackDAO dao = new FeedbackDAO();
        List<Feedback> list = dao.getFeedbacksFromDate(06, 02, 2025, 2);
        for (Feedback feedback : list) {
            System.out.println(feedback);
        }
    }
}
