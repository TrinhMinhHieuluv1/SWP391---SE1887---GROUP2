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
        }
        return null;
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

    public List<Asset> selectAllAssets() {
        List<Asset> assets = new ArrayList<>();

        try {

            String sql = "SELECT  * FROM Asset";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                Asset asset = new Asset();
                asset.setId(resultSet.getInt("AssetId"));
                asset.setCustomerId(resultSet.getInt("CustomerId"));
                asset.setImage(resultSet.getString("Image"));
                asset.setDescription(resultSet.getString("Description"));
                asset.setValue(resultSet.getBigDecimal("Value"));
                asset.setVerification(resultSet.getBoolean("Verification"));
                asset.setStatus(resultSet.getBoolean("Status"));
                asset.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
                assets.add(asset);
            }
            return assets;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Salary> selectAllSalary() {
        List<Salary> salarys = new ArrayList<>();
        try {

            String sql = "SELECT  * FROM Salary";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                Salary salary = new Salary();
                salary.setId(resultSet.getInt("SalaryId"));
                salary.setCustomerId(resultSet.getInt("CustomerId"));
                salary.setImage(resultSet.getString("Image"));
                salary.setDescription(resultSet.getString("Description"));
                salary.setValue(resultSet.getBigDecimal("Value"));
                salary.setVerification(resultSet.getBoolean("Verification"));
                salary.setStatus(resultSet.getBoolean("Status"));
                salary.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
                salarys.add(salary);
            }
            return salarys;
        } catch (SQLException e) {
            e.printStackTrace();
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
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getDate("DateOfBirth"),
                        rs.getBoolean("Gender"),
                        rs.getString("CCCD"),
                        rs.getInt("RoleID"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"));
                customer.setUser(user);
                return customer;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public boolean updateAsset(Asset asset) {
        String sql = "UPDATE Asset SET CustomerId = ?, Image = ?, Description = ?, "
                + "Value = ?, Verification = ?, Status = ?, CreatedAt = ? WHERE AssetId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, asset.getCustomerId());
            preparedStatement.setString(2, asset.getImage());
            preparedStatement.setString(3, asset.getDescription());
            preparedStatement.setBigDecimal(4, asset.getValue());
            preparedStatement.setBoolean(5, asset.isVerification());
            preparedStatement.setBoolean(6, asset.isStatus());
            preparedStatement.setTimestamp(7, new java.sql.Timestamp(asset.getCreatedAt().getTime()));
            preparedStatement.setInt(8, asset.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có ít nhất một hàng được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    public Asset getAssetById(int assetId) {

        String sql = "SELECT * FROM Asset WHERE AssetId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, assetId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Asset asset = new Asset();
                asset.setId(resultSet.getInt("AssetId"));
                asset.setCustomerId(resultSet.getInt("CustomerId"));
                asset.setImage(resultSet.getString("Image"));
                asset.setDescription(resultSet.getString("Description"));
                asset.setValue(resultSet.getBigDecimal("Value"));
                asset.setVerification(resultSet.getBoolean("Verification"));
                asset.setStatus(resultSet.getBoolean("Status"));
                asset.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
                return asset;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Asset> getAssetsSortedByValue(String ascending) throws SQLException {
        List<Asset> assets = new ArrayList<>();
        String query = "SELECT * FROM Asset ORDER BY Value " + ascending;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Asset asset = new Asset();
                asset.setId(rs.getInt("AssetId"));
                asset.setCustomerId(rs.getInt("CustomerId"));
                asset.setImage(rs.getString("Image"));
                asset.setDescription(rs.getString("Description"));
                asset.setValue(rs.getBigDecimal("Value"));
                asset.setVerification(rs.getBoolean("Verification"));
                asset.setStatus(rs.getBoolean("Status"));
                asset.setCreatedAt(rs.getTimestamp("CreatedAt"));
                assets.add(asset);
            }
            return assets;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }

    public List<Asset> getAssetsSortedByDate(String ascending) throws SQLException {
        List<Asset> assets = new ArrayList<>();
        String query = "SELECT * FROM Asset ORDER BY CreatedAt " + ascending;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Asset asset = new Asset();
                asset.setId(rs.getInt("AssetId"));
                asset.setCustomerId(rs.getInt("CustomerId"));
                asset.setImage(rs.getString("Image"));
                asset.setDescription(rs.getString("Description"));
                asset.setValue(rs.getBigDecimal("Value"));
                asset.setVerification(rs.getBoolean("Verification"));
                asset.setStatus(rs.getBoolean("Status"));
                asset.setCreatedAt(rs.getTimestamp("CreatedAt"));
                assets.add(asset);
            }
            return assets;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }

    public List<Asset> getAssetsByStatus(boolean status) throws SQLException {
        List<Asset> assets = new ArrayList<>();
        String query = "SELECT * FROM Asset WHERE Status = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setBoolean(1, status);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Asset asset = new Asset();
                asset.setId(rs.getInt("AssetId"));
                asset.setCustomerId(rs.getInt("CustomerId"));
                asset.setImage(rs.getString("Image"));
                asset.setDescription(rs.getString("Description"));
                asset.setValue(rs.getBigDecimal("Value"));
                asset.setVerification(rs.getBoolean("Verification"));
                asset.setStatus(rs.getBoolean("Status"));
                asset.setCreatedAt(rs.getTimestamp("CreatedAt"));
                assets.add(asset);
            }
             return assets;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }
        public List<Asset> getAssetsByVerify(boolean status) throws SQLException {
        List<Asset> assets = new ArrayList<>();
        String query = "SELECT * FROM Asset WHERE Verification = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setBoolean(1, status);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Asset asset = new Asset();
                asset.setId(rs.getInt("AssetId"));
                asset.setCustomerId(rs.getInt("CustomerId"));
                asset.setImage(rs.getString("Image"));
                asset.setDescription(rs.getString("Description"));
                asset.setValue(rs.getBigDecimal("Value"));
                asset.setVerification(rs.getBoolean("Verification"));
                asset.setStatus(rs.getBoolean("Status"));
                asset.setCreatedAt(rs.getTimestamp("CreatedAt"));
                assets.add(asset);
            }
             return assets;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
//        Asset a = dao.getAssetById(2)
//        a.setVerification(true);
//        a.setStatus(true);
//        dao.updateAsset(a);
//        System.out.println(a.toString());

    }
}
