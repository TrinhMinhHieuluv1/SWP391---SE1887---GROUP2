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
                return userToAdd;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    // check ROLE ID của users
    public boolean isAdmin(String username, String password) {
        String sql = "select RoleID FROM [dbo].[User] where Username = ? and Password = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int roleID = rs.getInt("RoleID");
                if (roleID == 1) {
                    return true;
                }
            }

        } catch (SQLException e) {

        }
        return false;
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
            st.setString(3, userToAdd.getName());
            st.setString(4, userToAdd.getPhone());
            st.setString(5, userToAdd.getEmail());
            st.setDate(6, userToAdd.getDob());
            st.setBoolean(7, userToAdd.isGender());
            st.setString(8, userToAdd.getCccd());
            st.setInt(9, userToAdd.getRoleID());
            st.setInt(10, 1);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Add users và trả lại số hàng được chèn thành công
    public int addUserReturnRow(User userToAdd) {
        String sql = "INSERT INTO [User](Username, Password, FullName, Phone, Email, DateOfBirth, Gender, CCCD, RoleID, Status)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userToAdd.getUsername());
            st.setString(2, userToAdd.getPassword());
            st.setString(3, userToAdd.getName());
            st.setString(4, userToAdd.getPhone());
            st.setString(5, userToAdd.getEmail());
            st.setDate(6, userToAdd.getDob());
            st.setBoolean(7, userToAdd.isGender());
            st.setString(8, userToAdd.getCccd());
            st.setInt(9, userToAdd.getRoleID());
            st.setInt(10, 1);

            int row = st.executeUpdate();
            if (row > 0) {
                return row;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public User getUserByUserID(int userID) {
        String sql = "SELECT* FROM [dbo].[User] where [dbo].[User].UserID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("UserID"),
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
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // update user với vai trò admin ( trả về số hàng bị ảnh hưởng )
    public int updateUserByUserId2(User userToUpdate, int userId) {
        String sql = """
                     UPDATE [dbo].[User]
                        SET [Username] = ?
                           ,[FullName] = ?
                           ,[Phone] = ?
                           ,[Email] = ?
                           ,[DateOfBirth] = ?
                           ,[Gender] = ?
                           ,[CCCD] = ?
                           ,[RoleID] = ?
                     
                      WHERE UserID=?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userToUpdate.getUsername());
            st.setString(2, userToUpdate.getName());
            st.setString(3, userToUpdate.getPhone());
            st.setString(4, userToUpdate.getEmail());
            st.setDate(5, userToUpdate.getDob());
            st.setBoolean(6, userToUpdate.isGender());
            st.setString(7, userToUpdate.getCccd());
            st.setInt(8, userToUpdate.getRoleID());
            st.setInt(9, userId);

            int row = st.executeUpdate();
            if (row > 0) {
                return row;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateAUserByUserID(User userToUpdate) {
        String sql = "UPDATE [User] SET Password=?, FullName=?, Phone=?, Email=?, DateOfBirth=?, Gender=?, CCCD=?, RoleID=?, Status=? WHERE UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userToUpdate.getPassword());
            st.setString(2, userToUpdate.getName());
            st.setString(3, userToUpdate.getPhone());
            st.setString(4, userToUpdate.getEmail());
            st.setDate(5, userToUpdate.getDob());
            st.setBoolean(6, userToUpdate.isGender());
            st.setString(7, userToUpdate.getCccd());
            st.setInt(8, userToUpdate.getRoleID());
            st.setBoolean(9, userToUpdate.isStatus());
            st.setInt(10, userToUpdate.getUserID());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void removeUserByUserID(int userID) {
        String sql = """
                     DELETE FROM [dbo].[User]
                           WHERE [dbo].[User].UserID = ?""";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userID);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            OR [DateOfBirth] LIKE ? 
            OR Gender = CASE 
                           WHEN ? = 'male' THEN 1 
                           WHEN ? = 'female' THEN 0 
                        END
            OR [CCCD] LIKE ? 
            OR RoleName LIKE ? 
            OR CONVERT(VARCHAR, CreatedAt, 120) LIKE ?
         ORDER BY [UserID] 
         OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
    """;

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            // Thiết lập tham số tìm kiếm
            for (int i = 1; i <= 5; i++) {
                st.setString(i, "%" + keyword + "%");
            }
            st.setString(6, keyword); // Gender
            st.setString(7, keyword); // Gender
            st.setString(8, "%" + keyword + "%");
            st.setString(9, "%" + keyword + "%");
            st.setString(10, "%" + keyword + "%");

            // Thiết lập phân trang
            st.setInt(11, (page - 1) * pageSize);
            st.setInt(12, pageSize);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("UserID"),
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
                            rs.getDate("CreatedAt")
                    );

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
            OR [DateOfBirth] LIKE ? 
            OR Gender = CASE 
                           WHEN ? = 'male' THEN 1 
                           WHEN ? = 'female' THEN 0 
                        END
            OR [CCCD] LIKE ? 
            OR RoleName LIKE ? 
            OR CONVERT(VARCHAR, CreatedAt, 120) LIKE ?
    """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Thiết lập tham số tìm kiếm
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

    // Sort list user by full name ( asc / des )
    public List<User> sortListUserByFullName(String typeOfSort, int page, int pageSize) {
       
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[User] "
                + "ORDER BY FullName "
                + (typeOfSort.equalsIgnoreCase("asc") ? "ASC" : "DESC") + " "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (page - 1) * pageSize); // Tính số dòng cần bỏ qua
            st.setInt(2, pageSize); // Số lượng user trên mỗi trang

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("UserID"),
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
                        rs.getDate("CreatedAt")
                );
                listUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }

    // Sort list user by created date ( asc / des )
    public List<User> sortListUserByCreatedAt(String typeOfSort, int page, int pageSize) {
        String sql;
        List<User> listUser = new ArrayList<>();

        if (typeOfSort.equalsIgnoreCase("asc")) {
            sql = "SELECT * FROM [dbo].[User] ORDER BY CreatedAt ASC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        } else {
            sql = "SELECT * FROM [dbo].[User] ORDER BY CreatedAt DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (page - 1) * pageSize); // Tính số dòng cần bỏ qua
            st.setInt(2, pageSize); // Số lượng user trên mỗi trang
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("UserID"),
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
                        rs.getDate("CreatedAt")
                );
                listUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }

    // Filter list user by Role name
    public List<User> filterListUserByRoleName(int idOfRole, int page, int pageSize) {

        List<User> listUser = new ArrayList<>();
        String sql = """
                     SELECT * 
                     FROM [dbo].[User] 
                     WHERE RoleID = ?
                     ORDER BY [UserID]
                     OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;""";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, idOfRole);
            st.setInt(2, (page - 1) * pageSize); // Tính số dòng cần bỏ qua
            st.setInt(3, pageSize); // Số lượng user trên mỗi trang

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("UserID"),
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
                        rs.getDate("CreatedAt")
                );
                listUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }

    // lấy ra tổng số user sau lọc bằng role name
    public int getTotalUsersAfterFilteringByRole(int roleId) {
        String sql = "SELECT count(*) FROM [dbo].[User] WHERE RoleID = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            // Set the RoleID parameter
            stmt.setInt(1, roleId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    //Lấy ra list user từ trang hiện tại
    public ArrayList<User> getListUserByPage(int page, int pageSize) {
        // page: số trang hiện tại
        // pageSize: số lượng user có trong 1 trang
        ArrayList<User> listUser = new ArrayList<>();

        String sql = "select * from [User] order by [UserID] offset ? rows fetch next ? rows only";
        // offset ? rows:    Bỏ qua một số dòng dựa trên số trang.
        // fetch next ? rows only:  Lấy tiếp số dòng tương ứng với pageSize.

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, (page - 1) * pageSize);
            // (page - 1) * pageSize: Tính số dòng cần bỏ qua dựa trên số trang hiện tại

            /* ví dụ:   page = 1, pageSize = 10 → offset = (1-1) * 10 = 0 (lấy từ dòng đầu tiên).
                        page = 2, pageSize = 10 → offset = (2-1) * 10 = 10 (bỏ qua 10 dòng đầu).*/
            stmt.setInt(2, pageSize);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("UserID"),
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
                        rs.getDate("CreatedAt")
                );
                listUser.add(user);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listUser;
    }

    // lấy ra tổng số user hiện đang có
    public int getTotalUsers() {
        String sql = "select count(*) from [User]";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    //tính tổng số lượng của user theo từng Role 
    public int getTotalUsersOfEachRole(String roleOfUser) {

        String sql="";
        switch (roleOfUser) {
            case "Admin" -> sql = "select count(*) from [dbo].[User] where RoleID = '1' ";
            case "Seller" -> sql = "select count(*) from [dbo].[User] where RoleID = '2' ";
            case "Manager" -> sql = "select count(*) from [dbo].[User] where RoleID = '3' ";
            case "Provider Insurance" -> sql = "select count(*) from [dbo].[User] where RoleID = '4' ";
            case "Customer" -> sql = "select count(*) from [dbo].[User] where RoleID = '5' ";
        }
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
