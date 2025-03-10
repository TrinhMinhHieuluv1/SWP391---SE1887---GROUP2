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
import model.Customer;
import model.Feedback;
import dal.CustomerDAO;
import model.Service;

/**
 *
 * @author ADMIN
 */
public class FeedbackDAO extends DBContext {

    CustomerDAO dao = new CustomerDAO();
    ServiceDAO sdao = new ServiceDAO();

    public ArrayList<Feedback> selectAllFeedback() {
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        String sqlFeedback = "SELECT * FROM Feedback";
        try {
            PreparedStatement st = connection.prepareStatement(sqlFeedback);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID"); // Láº¥y CustomerID
                Customer customer = dao.getCustomerByID(customerID); // Gá»�i phÆ°Æ¡ng thá»©c láº¥y thÃ´ng tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID"));
                Feedback fbToAdd = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("StarScore"), // Láº¥y StarScore tá»« DB
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"),
                        service,
                        customer
                );
                feedbackList.add(fbToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                Customer customer = dao.getCustomerByID(CustomerID); // Láº¥y thÃ´ng tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Láº¥y thÃ´ng tin Service

                feedback = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("StarScore"), // Láº¥y StarScore tá»« DB
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"),
                        service, // Truyá»�n Ä‘á»‘i tÆ°á»£ng Service
                        customer // Truyá»�n Ä‘á»‘i tÆ°á»£ng Customer
                );
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
                Customer customer = dao.getCustomerByID(rs.getInt("CustomerID")); // Láº¥y thÃ´ng tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Láº¥y thÃ´ng tin Service

                Feedback fbToAdd = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("StarScore"), // Láº¥y StarScore tá»« DB
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"),
                        service, // Truyá»�n Ä‘á»‘i tÆ°á»£ng Service
                        customer // Truyá»�n Ä‘á»‘i tÆ°á»£ng Customer
                );
                feedbackList.add(fbToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbackList;
    }

    public void addFeedback(int cid, int serviceId, int starScore, String message, String response, boolean status) {
        String sql = "INSERT INTO Feedback (CustomerID, ServiceID, StarScore, Message, Response, Status, CreatedAt) "
                + "VALUES (?, ?, ?, ?, ?, ?, GETDATE())";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, cid);           // CustomerID
            st.setInt(2, serviceId);     // ServiceID
            st.setInt(3, starScore);     // StarScore
            st.setString(4, message);    // Message
            st.setString(5, response);   // Response
            st.setBoolean(6, status);    // Status

            st.executeUpdate();  // Thá»±c thi cÃ¢u lá»‡nh
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi log lá»—i Ä‘á»ƒ debug
        }
    }

    public List<Feedback> findFBByID2(int CustomerID) {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE CustomerID = ? AND Status = 1";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, CustomerID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = dao.getCustomerByID(CustomerID); // Láº¥y thÃ´ng tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Láº¥y thÃ´ng tin Service

                Feedback feedback = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("StarScore"), // Láº¥y StarScore tá»« DB
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"),
                        service, // Truyá»�n Ä‘á»‘i tÆ°á»£ng Service
                        customer // Truyá»�n Ä‘á»‘i tÆ°á»£ng Customer
                );
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbackList;
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

    public boolean updateResponse(int feedbackId, String response) {
        String sql = "UPDATE Feedback SET Response = ? WHERE FeedbackID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, response);
            stmt.setInt(2, feedbackId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Feedback findFBByfID(int CustomerID, int fid) {
        Feedback feedback = null;
        String sql = "SELECT * FROM Feedback WHERE CustomerID = ? AND FeedbackID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, CustomerID);
            st.setInt(2, fid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer customer = dao.getCustomerByID(CustomerID); // Láº¥y thÃ´ng tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Láº¥y thÃ´ng tin Service

                feedback = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("StarScore"), // Láº¥y StarScore tá»« DB
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"),
                        service, // Truyá»�n Ä‘á»‘i tÆ°á»£ng Service
                        customer // Truyá»�n Ä‘á»‘i tÆ°á»£ng Customer
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedback;
    }

    public Feedback findFBByfID(int fid) {
        Feedback feedback = null;
        String sql = "SELECT * FROM Feedback WHERE FeedbackID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, fid);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int customerID = rs.getInt("CustomerID");
                    Customer customer = dao.getCustomerByID(customerID);
                    Service service = sdao.getServiceByID(rs.getInt("ServiceID"));

                    feedback = new Feedback(
                            rs.getInt("FeedbackID"),
                            rs.getInt("StarScore"),
                            rs.getString("Message"),
                            rs.getString("Response"),
                            rs.getBoolean("Status"),
                            rs.getDate("CreatedAt"),
                            service,
                            customer
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedback;
    }

    public List<Feedback> filterFeedback(String date1, String date2, String status, String search, int cid) {
        String sql = "select * from Feedback where 1 = 1 and CustomerID = " + cid + " and status = 1 ";
        if (date1 != "") {
            sql += " and CAST(CreatedAt as date) >= '" + date1 + "'";
        }
        if (date2 != "") {
            sql += " and CAST(CreatedAt as date) <= '" + date2 + "'";
        }

        if (status != "" && status.equals("true")) {
            sql += " and Response is not null ";
        }
        if (status != "" && status.equals("false")) {
            sql += " and Response is null ";
        }
        if (search != "") {
            
            search = search.replaceAll("\\s+", " ").trim();
            sql += " and Message like '%" + search + "%'";
        }
        List<Feedback> listFeedback = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Service service = sdao.getServiceByID(rs.getInt("ServiceID"));
                Customer customer = dao.getCustomerByID(rs.getInt("CustomerID"));
                Feedback feedback = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("StarScore"),
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"),
                        service,
                        customer
                );
                listFeedback.add(feedback);
            }
            return listFeedback;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Feedback> filterFeedback2(String search_raw, String status_raw, String status_res, String date1, String date2) {
        String sql = "select * from Feedback where 1 = 1 ";
        if (search_raw != "") {
            search_raw = search_raw.replaceAll("\\s+", " ");
            sql += " and Message like '%" + search_raw + "%'";
        }
        if (status_raw != "" && status_raw.equals("active")) {
            sql += " and Status = 1";
        }
        if (status_raw != "" && status_raw.equals("inactive")) {
            sql += " and Status = 0";
        }
        if (status_res != "" && status_res.equals("true")) {
            sql += " and Response is not null";
        }
        if (status_res != "" && status_res.equals("false")) {
            sql += " and Response is null";
        }
        if (date1 != "") {
            sql += " and CAST(CreatedAt as Date) >= '" + date1 + "'";
        }
        if (date2 != "") {
            sql += " and CAST(CreatedAt as Date) <= '" + date2 + "'";
        }
        List<Feedback> listFeedback = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Service service = sdao.getServiceByID(rs.getInt("ServiceID"));
                Customer customer = dao.getCustomerByID(rs.getInt("CustomerID"));
                Feedback feedback = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("StarScore"),
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"),
                        service,
                        customer
                );
                listFeedback.add(feedback);
            }
            return listFeedback;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Feedback> getListByPage(List<Feedback> list, int start, int end) {
        List<Feedback> listfeedback = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listfeedback.add(list.get(i));
        }
        return listfeedback;
    }

}
