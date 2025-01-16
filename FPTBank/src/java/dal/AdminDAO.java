package dal;

import java.sql.Date;
import model.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO extends DBContext {

    public List<Admin> selectAllAdmin() {
        List<Admin> adminList = new ArrayList<>();
        String sql = "SELECT * FROM Admin";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Admin adminToAdd = new Admin(rs.getInt("AdminID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("FullName"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getDate("DateOfBirth"),
                        rs.getBoolean("Gender"),
                        rs.getString("CCCD"),
                        rs.getInt("RoleID"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getBoolean("Status"));
                adminList.add(adminToAdd);
            }
        } catch (SQLException e) {
        }
        return adminList;
    }

    public Admin checkAdmin(String username, String password) {
        String sql = "SELECT * FROM [Admin] WHERE Username = ? and Password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Admin(rs.getInt("AdminID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("FullName"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getDate("DateOfBirth"),
                        rs.getBoolean("Gender"),
                        rs.getString("CCCD"),
                        rs.getInt("RoleID"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getBoolean("Status"));
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
    public void addAnAdmin(Admin adminToAdd) {
        String sql = "INSERT INTO [dbo].[Admin] (Username, Password, FullName, Phone, Email, DateOfBirth, Gender, CCCD, RoleID)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, adminToAdd.getUsername());
            st.setString(2, adminToAdd.getPassword());
            st.setString(3, adminToAdd.getName());
            st.setString(4, adminToAdd.getPhone());
            st.setString(5, adminToAdd.getEmail());
            st.setDate(6, adminToAdd.getDob());
            st.setBoolean(7, adminToAdd.isGender());
            st.setString(8, adminToAdd.getCccd());
            st.setInt(9, adminToAdd.getRoleID());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public void updateAnAdmin(Admin adminToUpdate) {
        String sql = "	UPDATE [Admin] SET Password=?, FullName=?, Phone=?, Email=?, "
                + "DateOfBirth=?, Gender=?, CCCD=?, Status=? WHERE Username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, adminToUpdate.getPassword());
            st.setString(2, adminToUpdate.getName());
            st.setString(3, adminToUpdate.getPhone());
            st.setString(4, adminToUpdate.getEmail());
            st.setDate(5, adminToUpdate.getDob());
            st.setBoolean(6, adminToUpdate.isGender());
            st.setString(7, adminToUpdate.getCccd());
            st.setBoolean(8, adminToUpdate.isStatus());
            st.setString(9, adminToUpdate.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
