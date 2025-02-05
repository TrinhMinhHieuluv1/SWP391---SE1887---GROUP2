package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO extends DBContext {

    public User checkAuthen(String username, String password) {
        String sql = "SELECT * FROM [User] WHERE Username=? AND Password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
                        getManagerForSeller(rs.getInt("ManagerID")),
                        rs.getDate("CreatedAt"));
                return user;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public User getManagerForSeller(int managerID) {
        String sql = "SELECT * FROM [User] WHERE UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, managerID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User manager = new User(rs.getInt("UserID"),
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
                        null,
                        rs.getDate("CreatedAt"));
                return manager;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<User> selectAllUser() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM [User]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User userToAdd = new User(rs.getInt("UserID"),
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
                        getManagerForSeller(rs.getInt("ManagerID")),
                        rs.getDate("CreatedAt"));
                userList.add(userToAdd);
            }
        } catch (SQLException e) {
        }
        return userList;
    }

    public List<User> selectAllManager() {
        List<User> managerList = new ArrayList<>();
        String sql = "SELECT * FROM [User] WHERE RoleID=3";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User manager = new User(rs.getInt("UserID"),
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
                        null,
                        rs.getDate("CreatedAt"));
                managerList.add(manager);
            }
        } catch (SQLException e) {
        }
        return managerList;
    }

    public List<User> selectAllSeller() {
        List<User> sellerList = new ArrayList<>();
        String sql = "SELECT * FROM [User] WHERE RoleID=2";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User seller = new User(rs.getInt("UserID"),
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
                        getManagerForSeller(rs.getInt("ManagerID")),
                        rs.getDate("CreatedAt"));
                sellerList.add(seller);
            }
        } catch (SQLException e) {
        }
        return sellerList;
    }

    public void addAUser(User userToAdd) {
        String sql = "INSERT INTO [User](Username, Password, FullName, Image, Phone, Email, DateOfBirth, Gender, Address, CCCD, RoleID, Status, ManagerID)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userToAdd.getUsername());
            st.setString(2, userToAdd.getPassword());
            st.setString(3, userToAdd.getFullName());
            st.setString(4, userToAdd.getImage());
            st.setString(5, userToAdd.getPhone());
            st.setString(6, userToAdd.getEmail());
            st.setDate(7, userToAdd.getDateOfBirth());
            st.setBoolean(8, userToAdd.isGender());
            st.setString(9, userToAdd.getAddress());
            st.setString(10, userToAdd.getCCCD());
            st.setInt(11, userToAdd.getRoleID());
            st.setBoolean(12, userToAdd.isStatus());
            st.setInt(13, userToAdd.getManager().getUserID());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void updateAUserByUserID(User userToUpdate) {
        String sql = "UPDATE [User] SET Password=?, FullName=?, Image=?, Phone=?, Email=?, DateOfBirth=?, Gender=?, Address, CCCD=?, RoleID=?, Status=?, ManagerID=? WHERE UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userToUpdate.getPassword());
            st.setString(2, userToUpdate.getFullName());
            st.setString(3, userToUpdate.getImage());
            st.setString(4, userToUpdate.getPhone());
            st.setString(5, userToUpdate.getEmail());
            st.setDate(6, userToUpdate.getDateOfBirth());
            st.setBoolean(7, userToUpdate.isGender());
            st.setString(8, userToUpdate.getAddress());
            st.setString(9, userToUpdate.getCCCD());
            st.setInt(10, userToUpdate.getRoleID());
            st.setBoolean(11, userToUpdate.isStatus());
            st.setInt(12, userToUpdate.getManager().getUserID());
            st.setInt(13, userToUpdate.getUserID());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
    public List<User> selectAllUsersByRole(int roleID) {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM [TimiBank].[dbo].[User] WHERE RoleID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, roleID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                // Tạo User Manager nếu có ManageID
                User manager = null;
                int managerID = rs.getInt("ManageID");
                if (!rs.wasNull()) {
                    manager = getUserByID(managerID); // Lấy thông tin Manager từ ID
                }

                // Tạo đối tượng User từ dữ liệu
                User userToAdd = new User(
                        rs.getInt("UserID"),
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
                        manager, // Đối tượng Manager (có thể null)
                        rs.getDate("CreatedAt")
                );
                userList.add(userToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Debug lỗi nếu có
        }
        return userList;
    }

    // Hàm lấy User theo UserID (hỗ trợ lấy Manager)
    private User getUserByID(int userID) {
        String sql = "SELECT * FROM [TimiBank].[dbo].[User] WHERE UserID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("UserID"),
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
                        null, // Không cần đệ quy lấy tiếp Manager
                        rs.getDate("CreatedAt")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countUsersByRole(int roleID) {
        int userCount = 0;
        String sql = "SELECT COUNT(*) FROM [TimiBank].[dbo].[User] WHERE RoleID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, roleID); // Set RoleID vào câu truy vấn
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                userCount = rs.getInt(1); // Lấy kết quả từ COUNT(*)
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Debug lỗi nếu có
        }

        return userCount;
    }
    
}
