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
public class FeedbackDAO extends DBContext{
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

}
