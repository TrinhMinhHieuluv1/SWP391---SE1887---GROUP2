/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Customer;

/**
 *
 * @author tiend
 */
public class CustomerDAO extends DBContext {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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
        } catch (Exception e) {
            e.printStackTrace(); //
        }
        return null;
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
            st.setDate(6,  customerToUpdate.getDateOfBirth());
            st.setBoolean(7, customerToUpdate.isGender());
            st.setString(8, customerToUpdate.getAddress());
            st.setString(9, customerToUpdate.getCCCD());
            st.setBoolean(10, customerToUpdate.isStatus());
            st.setInt(11, customerToUpdate.getCustomerId());
            st.executeUpdate();
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
