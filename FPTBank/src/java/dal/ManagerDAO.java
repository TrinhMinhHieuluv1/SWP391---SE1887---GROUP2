package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Manager;

public class ManagerDAO extends DBContext {

    public List<Manager> selectAllManager() {
        List<Manager> managerList = new ArrayList<>();
        String sql = "SELECT * FROM Manager";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Manager managerToAdd = new Manager(rs.getInt("ManagerID"),
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
                managerList.add(managerToAdd);
            }
        } catch (SQLException e) {
        }
        return managerList;
    }

    public Manager checkManager(String username, String password) {
        String sql = "SELECT * FROM [Manager] WHERE Username = ? and Password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Manager(rs.getInt("ManagerID"),
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

    public void addAManager(Manager managerToAdd) {
        String sql = "INSERT INTO [dbo].[Manager] (Username, Password, FullName, Phone, Email, DateOfBirth, Gender, CCCD, RoleID)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, managerToAdd.getUsername());
            st.setString(2, managerToAdd.getPassword());
            st.setString(3, managerToAdd.getName());
            st.setString(4, managerToAdd.getPhone());
            st.setString(5, managerToAdd.getEmail());
            st.setDate(6, managerToAdd.getDob());
            st.setBoolean(7, managerToAdd.isGender());
            st.setString(8, managerToAdd.getCccd());
            st.setInt(9, managerToAdd.getRoleID());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void updateAManager(Manager managerToUpdate) {
        String sql = "	UPDATE [Manager] SET Password=?, FullName=?, Phone=?, Email=?, "
                + "DateOfBirth=?, Gender=?, CCCD=?, Status=? WHERE Username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, managerToUpdate.getPassword());
            st.setString(2, managerToUpdate.getName());
            st.setString(3, managerToUpdate.getPhone());
            st.setString(4, managerToUpdate.getEmail());
            st.setDate(5, managerToUpdate.getDob());
            st.setBoolean(6, managerToUpdate.isGender());
            st.setString(7, managerToUpdate.getCccd());
            st.setBoolean(8, managerToUpdate.isStatus());
            st.setString(9, managerToUpdate.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ManagerDAO mdao = new ManagerDAO();
        for (Manager manager : mdao.selectAllManager()) {
            System.out.println(manager.getUsername());
        }
    }
}
