package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Asset;
import model.Customer;
import model.Salary;
import model.User;
import java.sql.*;
import model.Customer;

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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return false;
    }

        
    public User selectAnUserByConditions(int UserID, String Username, String Phone, String Email) {
        String sql = "SELECT * FROM [User] WHERE 1=1";
        if (UserID != 0) {
            sql = sql + " AND UserID=" + UserID;
        }
        if (!Username.isEmpty()) {
            sql = sql + " AND Username='" + Username +"'";
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
                        getManagerForSeller(rs.getInt("ManageID")),
                        rs.getDate("CreatedAt"));
                sellerList.add(seller);
            }
        } catch (SQLException e) {
        }
        return sellerList;
    }

    public void addAUser(User userToAdd) {
        String sql = "INSERT INTO [User](Username, Password, FullName, Image, Phone, Email, DateOfBirth, Gender, Address, CCCD, RoleID, Status, ManageID)\n"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            if (userToAdd.getManager() != null) {
                st.setInt(13, userToAdd.getManager().getUserID());
            } else {
                st.setNull(13, Types.INTEGER);
            }
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateAUserByUserID(User userToUpdate) {
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
 public User checkUserByEmail(String email) {
        String sql = "SELECT * FROM [User] WHERE Email = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
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
  public Customer getUserbyCid(int customerID) {
        try {
            String sql = "Select *\n"
                    + "from Customer c \n"
                    + "join [User] u on u.UserID = c.UserID\n"
                    + "where c.CustomerId =?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerid(rs.getInt("CustomerId"));
                customer.setCreditscore(rs.getInt("CreditScore"));
                customer.setBalance(rs.getBigDecimal("Balance"));
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
                customer.setUser(user);
                return customer;
            }
        } catch (Exception e) {
        }

        return null;
    }


    // duy
    public int addUserReturnRow(User userToAdd) {
        // Kiểm tra trùng lặp Username, CCCD, Email, Phone trước khi thêm
        if (isUsernameExists(userToAdd.getUsername())) {
            return 2;
        }

        if (isCCCDExists(userToAdd.getCCCD())) {
            return 3;
        }

        if (isEmailExists(userToAdd.getEmail())) {
            return 4;
        }

        if (isPhoneExists(userToAdd.getPhone())) {
            return 5;
        }

        // Nếu không có trùng lặp, thực hiện thêm người dùng mới
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

// kiểm tra sự tồn tại của username
    private boolean isUsernameExists(String username) {
        String query = "SELECT COUNT(*) FROM [User] WHERE Username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // kiểm tra sự tồn tại của CCCD
    private boolean isCCCDExists(String cccd) {
        String query = "SELECT COUNT(*) FROM [User] WHERE CCCD = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cccd);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // kiểm tra sự tồn tại của Email
    private boolean isEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM [User] WHERE Email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // kiểm tra sự tồn tại của Phone
    private boolean isPhoneExists(String phone) {
        String query = "SELECT COUNT(*) FROM [User] WHERE Phone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
                           ,[Image] = ?
                           ,[Phone] = ?
                           ,[Email] = ?
                           ,[DateOfBirth] = ?
                           ,[Gender] = ?
                           ,[Address] = ?
                           ,[CCCD] = ?
                           ,[RoleID] = ?
                           ,[ManageID] = ?
                      WHERE UserID = ?""";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userToUpdate.getUsername());
            st.setString(2, userToUpdate.getFullName());
            st.setString(3, userToUpdate.getImage());
            st.setString(4, userToUpdate.getPhone());
            st.setString(5, userToUpdate.getEmail());
            st.setDate(6, userToUpdate.getDateOfBirth());
            st.setBoolean(7, userToUpdate.isGender());
            st.setString(8, userToUpdate.getAddress());
            st.setString(9, userToUpdate.getCCCD());
            st.setInt(10, userToUpdate.getRoleID());

            // Nếu manager == null, đặt ManageID là NULL
            if (userToUpdate.getManager() != null) {
                st.setInt(11, userToUpdate.getManager().getUserID());
            } else {
                st.setNull(11, java.sql.Types.INTEGER);
            }

            st.setInt(12, userId);
            int row = st.executeUpdate();
            if (row > 0) {
                return row;
            }
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
            OR [DateOfBirth] LIKE ? 
            OR Gender = CASE 
                           WHEN ? = 'male' THEN 1 
                           WHEN ? = 'female' THEN 0 
                        END
            OR [CCCD] LIKE ? 
            OR RoleName LIKE ? 
            OR Address LIKE ?
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
            st.setString(11, "%" + keyword + "%");

            // Thiết lập phân trang
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


     // lấy ra tổng số user sau lọc bằng role name
    public int getTotalUsersAfterFilteringByRole(int roleId) {
        String sql = "SELECT count(*) FROM [dbo].[User] WHERE RoleID = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
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

     // Filter list user by Status ( idOfStatus = 1 / 0 ( active / inactive )
    public List<User> filterListUserByStatus(int idOfStatus, int page, int pageSize) {

        List<User> listUser = new ArrayList<>();
        String sql = """
                     SELECT * 
                     FROM [dbo].[User] 
                     WHERE Status = ?
                     ORDER BY [UserID]
                     OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;""";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, idOfStatus);
            st.setInt(2, (page - 1) * pageSize); // Tính số dòng cần bỏ qua
            st.setInt(3, pageSize); // Số lượng user trên mỗi trang

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


     // lấy ra tổng số user sau lọc bằng Status
    public int getTotalUsersAfterFilteringByStatus(int idOfStatus) {
        String sql = "SELECT count(*) FROM [dbo].[User] WHERE Status = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idOfStatus);
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

        String sql = "";
        switch (roleOfUser) {
            case "Admin" ->
                sql = "select count(*) from [dbo].[User] where RoleID = '1' ";
            case "Seller" ->
                sql = "select count(*) from [dbo].[User] where RoleID = '2' ";
            case "Manager" ->
                sql = "select count(*) from [dbo].[User] where RoleID = '3' ";
            case "Provider Insurance" ->
                sql = "select count(*) from [dbo].[User] where RoleID = '4' ";
            case "Customer" ->
                sql = "select count(*) from [dbo].[User] where RoleID = '5' ";
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

    // update status of users ( active / inactive )
    public boolean updateStatusOfUsers(int userID, boolean statusIsChecked) {
        String query = """
                       UPDATE [dbo].[User]
                          SET [Status] = ?     
                        WHERE [UserID] = ? """;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setBoolean(1, statusIsChecked);
            stmt.setInt(2, userID);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
