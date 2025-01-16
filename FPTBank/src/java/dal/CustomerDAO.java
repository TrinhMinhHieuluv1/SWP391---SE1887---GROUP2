package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

public class CustomerDAO extends DBContext {

    public List<Customer> selectAllCustomer() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer customerToAdd = new Customer(rs.getInt("CustomerID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("FullName"),
                        rs.getString("CCCD"),
                        rs.getString("Phone"),
                        rs.getDate("DateOfBirth"),
                        rs.getBoolean("Gender"),
                        rs.getString("Email"),
                        rs.getDouble("Salary"),
                        rs.getDouble("Balance"),
                        rs.getInt("SellerID"),
                        rs.getInt("RoleID"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getBoolean("Status"));
                customerList.add(customerToAdd);
            }
        } catch (SQLException e) {
        }
        return customerList;
    }

    public Customer checkCustomer(String username, String password) {
        String sql = "SELECT * FROM [Customer] WHERE Username = ? and Password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt("CustomerID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("FullName"),
                        rs.getString("CCCD"),
                        rs.getString("Phone"),
                        rs.getDate("DateOfBirth"),
                        rs.getBoolean("Gender"),
                        rs.getString("Email"),
                        rs.getDouble("Salary"),
                        rs.getDouble("Balance"),
                        rs.getInt("SellerID"),
                        rs.getInt("RoleID"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getBoolean("Status"));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void addACustomer(Customer customerToAdd) {
        String sql = "INSERT INTO [dbo].[Customer] (Username, Password, FullName, Phone, Email, DateOfBirth, Gender, CCCD, RoleID, Balance)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, customerToAdd.getUsername());
            st.setString(2, customerToAdd.getPassword());
            st.setString(3, customerToAdd.getName());
            st.setString(4, customerToAdd.getPhone());
            st.setString(5, customerToAdd.getEmail());
            st.setDate(6, customerToAdd.getDob());
            st.setBoolean(7, customerToAdd.isGender());
            st.setString(8, customerToAdd.getCccd());
            st.setInt(9, customerToAdd.getRoleID());
            st.setDouble(10, 0);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void updateACustomer(Customer CustomerToUpdate) {
        String sql = "	UPDATE [Manager] SET Password=?, FullName=?, Phone=?, Email=?, "
                + "DateOfBirth=?, Gender=?, CCCD=?, Status=?, Salary=?, Balance=?, SellerID=?, RoleID=?, WHERE Username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CustomerToUpdate.getPassword());
            st.setString(2, CustomerToUpdate.getName());
            st.setString(3, CustomerToUpdate.getPhone());
            st.setString(4, CustomerToUpdate.getEmail());
            st.setDate(5, CustomerToUpdate.getDob());
            st.setBoolean(6, CustomerToUpdate.isGender());
            st.setString(7, CustomerToUpdate.getCccd());
            st.setBoolean(8, CustomerToUpdate.isStatus());
            st.setDouble(9, CustomerToUpdate.getSalary());
            st.setDouble(10, CustomerToUpdate.getBalance());
            st.setInt(11, CustomerToUpdate.getSellerID());
            st.setInt(12, CustomerToUpdate.getRoleID());
            st.setString(13, CustomerToUpdate.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
