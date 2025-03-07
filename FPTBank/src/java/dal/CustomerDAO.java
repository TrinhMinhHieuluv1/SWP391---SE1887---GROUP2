/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author ACER
 */
public class CustomerDAO extends DBContext {

    public Customer checkAuthen(String username, String password) {
        String sql = "SELECT * FROM Customer WHERE Username=? AND Password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerId"),
                        rs.getInt("CreditScore"),
                        rs.getInt("RoleID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("FullName"),
                        rs.getString("Image"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getString("CCCD"),
                        rs.getDate("DateOfBirth"),
                        rs.getDate("CreatedAt"),
                        rs.getBoolean("Gender"),
                        rs.getBoolean("Status"),
                        rs.getBigDecimal("Balance")
                );
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Customer> selectAllCustomer(){
        List<Customer> cList = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerId"),
                        rs.getInt("CreditScore"),
                        rs.getInt("RoleID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("FullName"),
                        rs.getString("Image"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getString("CCCD"),
                        rs.getDate("DateOfBirth"),
                        rs.getDate("CreatedAt"),
                        rs.getBoolean("Gender"),
                        rs.getBoolean("Status"),
                        rs.getBigDecimal("Balance")
                );
                cList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }       
        return cList;
    }

    public void updateACustomer(Customer customerToUpdate) {
        String sql = "UPDATE [Customer] SET Password=?, FullName=?, Image=?, Phone=?, Email=?, DateOfBirth=?, Gender=?, Address=?, CCCD=?, Status=? WHERE CustomerID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, customerToUpdate.getPassword());
            st.setString(2, customerToUpdate.getFullName());
            st.setString(3, customerToUpdate.getImage());
            st.setString(4, customerToUpdate.getPhone());
            st.setString(5, customerToUpdate.getEmail());
            st.setDate(6, (Date) customerToUpdate.getDateOfBirth());
            st.setBoolean(7, customerToUpdate.isGender());
            st.setString(8, customerToUpdate.getAddress());
            st.setString(9, customerToUpdate.getCCCD());
            st.setBoolean(10, customerToUpdate.isStatus());
            st.setInt(11, customerToUpdate.getCustomerId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isFieldExistsToUpdate(String fieldName, String value, int UserID) {
        String query = "SELECT COUNT(*) FROM [Customer] WHERE " + fieldName + " = ? AND CustomerID <> ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, value);
            stmt.setInt(2, UserID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer getCustomerByID(int customerID) {
        String sql = "SELECT * FROM Customer WHERE CustomerID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("CustomerID"),
                        rs.getInt("CreditScore"),
                        rs.getInt("RoleID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("FullName"),
                        rs.getString("Image"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getString("CCCD"),
                        rs.getDate("DateOfBirth"),
                        rs.getDate("CreatedAt"),
                        rs.getBoolean("Gender"),
                        rs.getBoolean("Status"),
                        rs.getBigDecimal("Balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCustomer(Customer customerToUpdate) {
        String sql = "UPDATE Customer SET Username=?, Password=?, FullName=?, Image=?, Phone=?, Email=?, DateOfBirth=?, Gender=?, Address=?, CCCD=?, CreditScore=?, Balance=?, RoleID=?, Status=?, CreatedAt=? WHERE CustomerID=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, customerToUpdate.getUsername());
            st.setString(2, customerToUpdate.getPassword());
            st.setString(3, customerToUpdate.getFullName());
            st.setString(4, customerToUpdate.getImage());
            st.setString(5, customerToUpdate.getPhone());
            st.setString(6, customerToUpdate.getEmail());
            st.setDate(7, new java.sql.Date(customerToUpdate.getDateOfBirth().getTime()));
            st.setBoolean(8, customerToUpdate.isGender());
            st.setString(9, customerToUpdate.getAddress());
            st.setString(10, customerToUpdate.getCCCD());
            st.setInt(11, customerToUpdate.getCreditScore());
            st.setBigDecimal(12, customerToUpdate.getBalance());
            st.setInt(13, customerToUpdate.getRoleID());
            st.setBoolean(14, customerToUpdate.isStatus());
            st.setTimestamp(15, new java.sql.Timestamp(customerToUpdate.getCreatedAt().getTime()));
            st.setInt(16, customerToUpdate.getCustomerId()); // Điều kiện WHERE để cập nhật đúng Customer

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc dùng logger để ghi log lỗi
        }
    }
    
    public Customer selectCustomerByConditions(int id, String Username, String Phone, String Email) {
        String sql = "SELECT * FROM [Customer] WHERE 1=1";
        if (id != 0) {
            sql = sql + " AND CustomerId=" + id;
        }
        if (!Username.isEmpty()) {
            sql = sql + " AND Username='" + Username + "'";
        }
        if (!Phone.isEmpty()) {
            sql = sql + " AND Phone='" + Phone + "'";
        }
        if (!Email.isEmpty()) {
            sql = sql + " AND Email='" + Email + "'";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerId"),
                        rs.getInt("CreditScore"),
                        rs.getInt("RoleID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("FullName"),
                        rs.getString("Image"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getString("CCCD"),
                        rs.getDate("DateOfBirth"),
                        rs.getDate("CreatedAt"),
                        rs.getBoolean("Gender"),
                        rs.getBoolean("Status"),
                        rs.getBigDecimal("Balance")
                );
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
