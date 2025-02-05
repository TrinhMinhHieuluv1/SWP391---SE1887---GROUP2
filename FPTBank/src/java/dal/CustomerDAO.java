/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author ACER
 */
public class CustomerDAO extends DBContext{
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT CustomerId, UserID, CreditScore, Balance FROM [dbo].[Customer]";

        try (
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerid(rs.getInt("CustomerId"));
                customer.setUserid(rs.getInt("UserID"));
                customer.setCreditscore(rs.getInt("CreditScore"));
                customer.setBalance(rs.getBigDecimal("Balance"));
                
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
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
    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();
        System.out.println(dao.getCustomerIdByUserId(12));
    }    
    }
