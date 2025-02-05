package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.User;

public class userDAO extends DBContext {

    public User checkAuthen(String username, String password) {
        String sql = "SELECT * FROM [User] WHERE Username=? AND Password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
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
                        rs.getDate("CreatedAt"));
                return userToAdd;
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
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getDate("DateOfBirth"),
                        rs.getBoolean("Gender"),
                        rs.getString("CCCD"),
                        rs.getInt("RoleID"),
                        rs.getBoolean("Status"),
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
                User userToAdd = new User(rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("FullName"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getDate("DateOfBirth"),
                        rs.getBoolean("Gender"),
                        rs.getString("CCCD"),
                        rs.getInt("RoleID"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"));
                managerList.add(userToAdd);
            }
        } catch (SQLException e) {
        }
        return managerList;
    }

    public List<User> selectAllSeller() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM [User] WHERE RoleID=2";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User userToAdd = new User(rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("FullName"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getDate("DateOfBirth"),
                        rs.getBoolean("Gender"),
                        rs.getString("CCCD"),
                        rs.getInt("RoleID"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"));
                userList.add(userToAdd);
            }
        } catch (SQLException e) {
        }
        return userList;
    }

    public void addAUser(User userToAdd) {
        String sql = "INSERT INTO [User](Username, Password, FullName, Phone, Email, DateOfBirth, Gender, CCCD, RoleID, Status)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userToAdd.getUsername());
            st.setString(2, userToAdd.getPassword());
            st.setString(3, userToAdd.getFullName());
            st.setString(4, userToAdd.getPhone());
            st.setString(5, userToAdd.getEmail());
            st.setDate(6, userToAdd.getDateOfBirth());
            st.setBoolean(7, userToAdd.isGender());
            st.setString(8, userToAdd.getCCCD());
            st.setInt(9, userToAdd.getRoleID());
            st.setInt(10, 1);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void updateAUserByUserID(User userToUpdate) {
        String sql = "UPDATE [User] SET Password=?, FullName=?, Phone=?, Email=?, DateOfBirth=?, Gender=?, CCCD=?, RoleID=?, Status=? WHERE UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userToUpdate.getPassword());
            st.setString(2, userToUpdate.getFullName());
            st.setString(3, userToUpdate.getPhone());
            st.setString(4, userToUpdate.getEmail());
            st.setDate(5, userToUpdate.getDateOfBirth());
            st.setBoolean(6, userToUpdate.isGender());
            st.setString(7, userToUpdate.getCCCD());
            st.setInt(8, userToUpdate.getRoleID());
            st.setBoolean(9, userToUpdate.isStatus());
            st.setInt(10, userToUpdate.getUserID());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void updateUser(int uID, String name, String phone, String email, String cccd, Date dob, boolean gender, String address) {
        String sql = "UPDATE [dbo].[User]\n"
                + "SET \n"
                + "    [FullName] = ?,\n"
                + "    [Phone] = ?,\n"
                + "    [Email] = ?,\n"
                + "    [DateOfBirth] = ?,\n"
                + "    [Gender] = ?,\n"
                + "    [CCCD] = ?,\n"
                + "[Address] = ?\n"
                + "WHERE [UserID] = ?;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);

            pre.setString(1, name);
            pre.setString(2, phone);
            pre.setString(3, email);
            pre.setDate(4, (java.sql.Date) dob);  
            pre.setBoolean(5, gender);  
            pre.setString(6, cccd);
            pre.setString(7, address);  
            pre.setInt(8, uID);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isPhoneUsed(String phone, int UserID) {
        String sql = "SELECT COUNT(*) FROM [dbo].[User] WHERE phone = ? AND UserID <> ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, phone);
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

    public boolean isEmailUsed(String email, int userID) {
        String sql = "SELECT COUNT(*) FROM [dbo].[User] WHERE email = ? AND UserID <> ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setInt(2, userID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Nếu có ít nhất 1 kết quả, email đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Nếu có lỗi hoặc không tìm thấy email trùng
    }

    public User getUserProfileByID(int userID) {
        String sql = "SELECT Username, FullName, Email, Phone, CCCD, DateOfBirth, Gender "
                + "FROM [dbo].[User] WHERE UserID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("Username"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("CCCD"),
                        rs.getDate("DateOfBirth"), 
                        rs.getBoolean("Gender") 
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy user
    }

    public boolean isCCCDUsedExceptUser(String cccd,int UserID) {
        String sql = "SELECT COUNT(*) FROM [dbo].[User] WHERE CCCD = ? AND UserID <> ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cccd);
            stmt.setInt(2,UserID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu có ít nhất 1 bản ghi, CCCD đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Không tìm thấy CCCD trùng
    }

    public User getUserByID(int userID) {
        String sql = "SELECT UserID, Username, Password, FullName,Image, Phone, Email, DateOfBirth, Gender,Address, CCCD, RoleID, Status, CreatedAt "
                + "FROM [User] WHERE UserID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

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
                        rs.getDate("CreatedAt")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy User
    }

    public void updateUserImage(int uID, String imagePath) {
        String sql = "UPDATE [dbo].[User] SET [Image] = ? WHERE [UserID] = ?;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, imagePath);
            pre.setInt(2, uID);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserProfileByID2(int userID) {
        String sql = "SELECT Username, FullName, Email, Phone, CCCD, DateOfBirth, Gender, Image "
                + "FROM [dbo].[User] WHERE UserID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("Username"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("CCCD"),
                        rs.getDate("DateOfBirth"), // Hoặc đổi sang Date nếu cần
                        rs.getString("Image"), // Thêm trường Image vào UserProfile
                        rs.getBoolean("Gender") // Giới tính lưu dạng boolean (true = Nam, false = Nữ)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy user
    }

    public boolean isUserExists(String fullName, String email) {
        String query = "SELECT COUNT(*) FROM [dbo].[User] WHERE FullName = ? AND Email = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, fullName);
            pstmt.setString(2, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Nếu số lượng > 0, nghĩa là có tồn tại
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
}
