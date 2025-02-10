package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;
import java.sql.*;

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
                        getManagerForSeller(rs.getInt("ManageID")),
                        rs.getDate("CreatedAt"));
                return user;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public User selectAnUserByConditions(int UserID, String Username, String Phone, String Email) {
        String sql = "SELECT * FROM [User] WHERE 1=1";
        if (UserID != 0) {
            sql = sql + " AND UserID=" + UserID;
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
                        getManagerForSeller(rs.getInt("ManageID")),
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
                        rs.getString("Image"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getDate("DateOfBirth"),
                        rs.getBoolean("Gender"),
                        rs.getString("Address"),
                        rs.getString("CCCD"),
                        rs.getInt("RoleID"),
                        rs.getBoolean("Status"),
                        getManagerForSeller(rs.getInt("ManageID")),
                        rs.getDate("CreatedAt"));
                userList.add(userToAdd);
            }
        } catch (SQLException e) {
        }
        return userList;
    }

    public void updateAUser(User userToUpdate) {
        String sql = "UPDATE [User] SET Password=?, FullName=?, Image=?, Phone=?, Email=?, DateOfBirth=?, Gender=?, Address=?, CCCD=?, RoleID=?, Status=?, ManageID=? WHERE UserID=?";
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
            if (userToUpdate.getManager() != null) {
                st.setInt(12, userToUpdate.getManager().getUserID());
            } else {
                st.setNull(12, Types.INTEGER);
            }
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
                    manager = getManagerForSeller(managerID); // Lấy thông tin Manager từ ID
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
    public User getManagerForSeller(int userID) {
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
                        null,
                        rs.getDate("CreatedAt")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //---------------------------------------------------------------------------------------------------------------
    //Duy
    // kiểm tra sự tồn tại của username / cccd / phonenum / email
    public boolean isFieldExistsToAdd(String fieldName, String value) {
        String query = "SELECT COUNT(*) FROM [User] WHERE " + fieldName + " = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, value);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Add an user 
    public int addUserReturnRow(User userToAdd) {
        // Kiểm tra trùng lặp Username, CCCD, Email, Phone trước khi thêm
        if (isFieldExistsToAdd("Username", userToAdd.getUsername())) {
            return 2;
        }

        if (isFieldExistsToAdd("CCCD", userToAdd.getCCCD())) {
            return 3;
        }

        if (isFieldExistsToAdd("Email", userToAdd.getEmail())) {
            return 4;
        }

        if (isFieldExistsToAdd("Phone", userToAdd.getPhone())) {
            return 5;
        }

        String sql = """
                 INSERT INTO [User](Username, Password, FullName, Image, Phone, Email, DateOfBirth, Gender, Address, CCCD, RoleID, Status, ManageID)
                 VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";

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

            // Nếu manager == null, đặt ManageID là NULL
            if (userToAdd.getManager() != null) {
                st.setInt(13, userToAdd.getManager().getUserID());
            } else {
                st.setNull(13, java.sql.Types.INTEGER);
            }
            int row = st.executeUpdate();
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Tìm kiếm user dựa trên keyword dc nhập vào 
    public List<User> searchUsers(String keyword, int page, int pageSize) {
        List<User> listUsers = new ArrayList<>();

        String sql = """
         SELECT * FROM [dbo].[User] 
         JOIN [dbo].[Role] ON [dbo].[User].[RoleID] = [dbo].[Role].RoleID
         WHERE Username LIKE ? 
            OR FullName LIKE ? 
            OR Email LIKE ? 
            OR Phone LIKE ? 
            OR FORMAT([DateOfBirth], 'dd-MM-yyyy') LIKE ?
            OR Gender = CASE 
                           WHEN ? = 'male' THEN 1 
                           WHEN ? = 'female' THEN 0 
                        END
            OR [CCCD] LIKE ? 
            OR RoleName LIKE ? 
            OR Address LIKE ?
            OR FORMAT(CreatedAt, 'dd-MM-yyyy') LIKE ?
         ORDER BY [UserID] 
         OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
    """;

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            for (int i = 1; i <= 5; i++) {
                st.setString(i, "%" + keyword + "%");
            }
            st.setString(6, keyword); // Gender
            st.setString(7, keyword); // Gender
            st.setString(8, "%" + keyword + "%");
            st.setString(9, "%" + keyword + "%");
            st.setString(10, "%" + keyword + "%");
            st.setString(11, "%" + keyword + "%");

            st.setInt(12, (page - 1) * pageSize);
            st.setInt(13, pageSize);

            try (ResultSet rs = st.executeQuery()) {
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
                            getManagerForSeller(rs.getInt("ManageID")),
                            rs.getDate("CreatedAt"));

                    listUsers.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    // lấy ra tổng số lượng user sau khi search user by keyword
    public int getTotalUsersAfterSearching(String keyword) {
        String sql = """
         SELECT COUNT(*) FROM [dbo].[User] 
         JOIN [dbo].[Role] ON [dbo].[User].[RoleID] = [dbo].[Role].RoleID
         WHERE Username LIKE ? 
            OR FullName LIKE ? 
            OR Email LIKE ? 
            OR Phone LIKE ? 
            OR FORMAT([DateOfBirth], 'dd-MM-yyyy') LIKE ?
            OR Gender = CASE 
                           WHEN ? = 'male' THEN 1 
                           WHEN ? = 'female' THEN 0 
                        END
            OR [CCCD] LIKE ? 
            OR RoleName LIKE ? 
            OR FORMAT(CreatedAt, 'dd-MM-yyyy') LIKE ?
    """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 1; i <= 5; i++) {
                stmt.setString(i, "%" + keyword + "%");
            }
            stmt.setString(6, keyword); // Gender
            stmt.setString(7, keyword); // Gender
            stmt.setString(8, "%" + keyword + "%");
            stmt.setString(9, "%" + keyword + "%");
            stmt.setString(10, "%" + keyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    // sort list user by fullname/created at
    public List<User> sortListUser(String sortBy, String typeOfSort, int page, int pageSize) {
        List<User> listUser = new ArrayList<>();

        // Xác định cột cần sắp xếp
        String column = sortBy.equalsIgnoreCase("CreatedAt") ? "CreatedAt" : "FullName";
        String order = typeOfSort.equalsIgnoreCase("asc") ? "ASC" : "DESC";

        String sql = "SELECT * FROM [dbo].[User] ORDER BY " + column + " " + order
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (page - 1) * pageSize); // Tính số dòng cần bỏ qua
            st.setInt(2, pageSize); // Số lượng user trên mỗi trang
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
                        getManagerForSeller(rs.getInt("ManageID")),
                        rs.getDate("CreatedAt"));
                listUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }

    // filter list user by roleID / status
    public List<User> filterListUser(String filterType, int filterValue, int page, int pageSize) {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[User] "
                + "WHERE " + filterType + " = ? "
                + "ORDER BY [UserID] "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, filterValue);
            st.setInt(2, (page - 1) * pageSize);
            st.setInt(3, pageSize);

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
                        getManagerForSeller(rs.getInt("ManageID")),
                        rs.getDate("CreatedAt"));
                listUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }

    // tính số lượng user theo RoleID / Status / toàn bộ user hiện có
    // fieldName = null, fieldValue = null nếu muốn count toàn bộ user
    public int getTotalUsers(String fieldName, Integer fieldValue) {
        String sql = "SELECT count(*) FROM [dbo].[User]";

        // Nếu có điều kiện lọc, thêm WHERE vào SQL
        if (fieldName != null && fieldName != null) {
            sql += " WHERE " + fieldName + " = ?";
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            // Nếu có điều kiện lọc, set giá trị tham số
            if (fieldValue != null && fieldValue != null) {
                stmt.setInt(1, fieldValue);
            }

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    //Lấy ra listuser ở trang hiện tại 
    public ArrayList<User> getListUserByPage(int page, int pageSize) {
        ArrayList<User> listUser = new ArrayList<>();

        String sql = "select * from [User] order by [UserID] offset ? rows fetch next ? rows only";
        // offset ? rows:    Bỏ qua một số dòng dựa trên số trang.
        // fetch next ? rows only:  Lấy tiếp số dòng tương ứng với pageSize.

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (page - 1) * pageSize);
            stmt.setInt(2, pageSize);
            ResultSet rs = stmt.executeQuery();
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
                        getManagerForSeller(rs.getInt("ManageID")),
                        rs.getDate("CreatedAt"));
                listUser.add(user);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listUser;
    }
    
    //---------------------------------------------------------------------------------------------------------------
    //Cuong
    public boolean isFieldExistsToUpdate(String fieldName, String value, int UserID) {
        String query = "SELECT COUNT(*) FROM [User] WHERE " + fieldName + " = ? AND UserID <> ?";

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

    public boolean isUserExists(String fullName, String email) {
        String query = "SELECT COUNT(*) FROM [dbo].[User] WHERE FullName = ? AND Email = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, fullName);
            pstmt.setString(2, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
