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
import dal.ServiceDAO;

/**
 *
 * @author ADMIN
 */
public class FeedbackDAO extends DBContext {

    CustomerDAO dao = new CustomerDAO();
    ServiceDAO sdao = new ServiceDAO();

    public List<Feedback> selectAllFeedback() {
        List<Feedback> feedbackList = new ArrayList<>();
        String sqlFeedback = "SELECT * FROM Feedback";
        try {
            PreparedStatement st = connection.prepareStatement(sqlFeedback);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID"); // Lấy CustomerID
                Customer customer = dao.getCustomerByID(customerID); // Gọi phương thức lấy thông tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID"));
                Feedback fbToAdd = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("StarScore"), // Lấy StarScore từ DB
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
                Customer customer = dao.getCustomerByID(CustomerID); // Lấy thông tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Lấy thông tin Service

                feedback = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("StarScore"), // Lấy StarScore từ DB
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"),
                        service, // Truyền đối tượng Service
                        customer // Truyền đối tượng Customer
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
                Customer customer = dao.getCustomerByID(rs.getInt("CustomerID")); // Lấy thông tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Lấy thông tin Service

                Feedback fbToAdd = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("StarScore"), // Lấy StarScore từ DB
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"),
                        service, // Truyền đối tượng Service
                        customer // Truyền đối tượng Customer
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

            st.executeUpdate();  // Thực thi câu lệnh
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi log lỗi để debug
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
                Customer customer = dao.getCustomerByID(CustomerID); // Lấy thông tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Lấy thông tin Service

                Feedback feedback = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("StarScore"), // Lấy StarScore từ DB
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"),
                        service, // Truyền đối tượng Service
                        customer // Truyền đối tượng Customer
                );
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbackList;
    }

    public ArrayList<Feedback> getFeedbacksFromDate(String date, int cid) {
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE CreatedAt >= ? AND CustomerID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, date);
            ps.setInt(2, cid);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = dao.getCustomerByID(rs.getInt("CustomerID")); // Lấy thông tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Lấy thông tin Service

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

                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbackList;
    }

    public ArrayList<Feedback> getFeedbacksToDate(String date, int cid) {
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE CreatedAt <= ? AND CustomerID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, date);
            ps.setInt(2, cid);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = dao.getCustomerByID(rs.getInt("CustomerID")); // Lấy thông tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Lấy thông tin Service

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

                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbackList;
    }

    public ArrayList<Feedback> getFeedbacksFromDateToDate(String date1, String date2, int cid) {
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE CreatedAt >= ? AND CreatedAt <= ? AND CustomerID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, date1);
            ps.setString(2, date2);
            ps.setInt(3, cid);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = dao.getCustomerByID(rs.getInt("CustomerID")); // Lấy thông tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Lấy thông tin Service

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

                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbackList;
    }

    public ArrayList<Feedback> searchFeedbackByMessage(String txt, int cid) {
        ArrayList<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE Message LIKE ? AND CustomerID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, cid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customer customer = dao.getCustomerByID(rs.getInt("CustomerID")); // Lấy thông tin Customer
                    Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Lấy thông tin Service

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
                    list.add(feedback);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Feedback> searchFeedbackByMessage2(String txt) {
        ArrayList<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE Message LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + txt + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customer customer = dao.getCustomerByID(rs.getInt("CustomerID")); // Lấy thông tin Customer
                    Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Lấy thông tin Service

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
                    list.add(feedback);
                }
            }
        } catch (SQLException e) {
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
                Customer customer = dao.getCustomerByID(CustomerID); // Lấy thông tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Lấy thông tin Service

                feedback = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("StarScore"), // Lấy StarScore từ DB
                        rs.getString("Message"),
                        rs.getString("Response"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"),
                        service, // Truyền đối tượng Service
                        customer // Truyền đối tượng Customer
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedback;
    }

    public ArrayList<Feedback> getFeedbacksWithResponse(int cid, String not) {
        ArrayList<Feedback> list = new ArrayList<>();
        String sql;

        try {
            // Nếu cid = -1, không lọc theo CustomerID
            if (cid == -1) {
                sql = "SELECT * FROM Feedback WHERE response IS " + not + " NULL";
            } else {
                sql = "SELECT * FROM Feedback WHERE CustomerID = ? AND response IS " + not + " NULL";
            }

            PreparedStatement ps = connection.prepareStatement(sql);

            // Nếu cid khác -1, set giá trị cho câu lệnh SQL
            if (cid != -1) {
                ps.setInt(1, cid);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = dao.getCustomerByID(rs.getInt("CustomerID")); // Lấy thông tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Lấy thông tin Service

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
                list.add(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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

    public ArrayList<Feedback> getFeedbacksByStatus(boolean status) {
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE Status = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBoolean(1, status);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = dao.getCustomerByID(rs.getInt("CustomerID")); // Lấy thông tin Customer
                Service service = sdao.getServiceByID(rs.getInt("ServiceID")); // Lấy thông tin Service

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

                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbackList;
    }

    public ArrayList<Feedback> getListFeedbackByPage(int page, int pageSize, int cid) {
        ArrayList<Feedback> listFeedback = new ArrayList<>();

        String sql = "SELECT f.FeedbackID, f.CustomerID, f.ServiceID, f.Message, f.Response, f.Status, f.StarScore, f.CreatedAt "
                + "FROM Feedback f "
                + "WHERE f.CustomerID = ? "
                + "ORDER BY f.FeedbackID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cid);
            stmt.setInt(2, (page - 1) * pageSize);  // Calculate the offset based on page and pageSize
            stmt.setInt(3, pageSize);  // Set the page size

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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listFeedback;
    }

    public ArrayList<Feedback> getListFeedbackByPage2(int page, int pageSize) {
        ArrayList<Feedback> listFeedback = new ArrayList<>();

        String sql = "SELECT f.FeedbackID, f.CustomerID, f.ServiceID, f.Message, f.Response, f.Status, f.StarScore, f.CreatedAt "
                + "FROM Feedback f "
                + "ORDER BY f.FeedbackID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (page - 1) * pageSize);  // Calculate the offset based on page and pageSize
            stmt.setInt(2, pageSize);  // Set the page size

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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listFeedback;
    }

    public static void main(String[] args) {
        FeedbackDAO dao = new FeedbackDAO();
        ArrayList<Feedback> list = dao.searchFeedbackByMessage("service", 1);
        for (Feedback feedback : list) {
            System.out.println(feedback);
        }
    }
}
