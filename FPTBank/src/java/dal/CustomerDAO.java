/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Customer;
import model.User;

/**
 *
 * @author ACER
 */
public class CustomerDAO extends DBContext {

    public int getCustomerIdByUserId(int userId) {
        String query = "SELECT CustomerId FROM [dbo].[Customer] WHERE UserID = ?";

        try (
                PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("CustomerId");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Trả về null nếu không tìm thấy
    }

    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customer c "
                + "join [User] u on u.UserID = c.UserID WHERE CustomerId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerId"));
                customer.setCreditScore(rs.getInt("CreditScore"));
                customer.setBalance(rs.getBigDecimal("balance"));
                // customer.setUser(...); // Set the user if applicable
                UserDAO userDAO = new UserDAO();
                User user = new User(rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("FullName"),
                        rs.getString("Image"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getDate("DateOfBirth"),
                        rs.getBoolean("Gender"),
                        rs.getString("Address"),
                        rs.getString("CCCD"),
                        rs.getInt("RoleID"),
                        rs.getBoolean("Status"),
                        userDAO.getManagerForSeller(rs.getInt("ManageID")),
                        rs.getDate("CreatedAt"));
                customer.setUser(user);
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately

        }
        return null;
    }
}
