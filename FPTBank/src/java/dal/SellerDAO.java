package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Seller;

public class SellerDAO extends DBContext {

    public List<Seller> selectAllSeller() {
        List<Seller> sellerList = new ArrayList<>();
        String sql = "SELECT * FROM Seller";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Seller sellerToAdd = new Seller(rs.getInt("SellerID"),
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
                sellerList.add(sellerToAdd);
            }
        } catch (SQLException e) {
        }
        return sellerList;
    }

    public Seller checkSeller(String username, String password) {
        String sql = "SELECT * FROM [Seller] WHERE Username = ? and Password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Seller(rs.getInt("SellerID"),
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

    public void addASeller(Seller sellerToAdd) {
        String sql = "INSERT INTO [dbo].[Seller] (Username, Password, FullName, Phone, Email, DateOfBirth, Gender, CCCD, RoleID)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sellerToAdd.getUsername());
            st.setString(2, sellerToAdd.getPassword());
            st.setString(3, sellerToAdd.getName());
            st.setString(4, sellerToAdd.getPhone());
            st.setString(5, sellerToAdd.getEmail());
            st.setDate(6, sellerToAdd.getDob());
            st.setBoolean(7, sellerToAdd.isGender());
            st.setString(8, sellerToAdd.getCccd());
            st.setInt(9, sellerToAdd.getRoleID());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void updateASeller(Seller sellerToUpdate) {
        String sql = "	UPDATE [Seller] SET Password=?, FullName=?, Phone=?, Email=?, "
                + "DateOfBirth=?, Gender=?, CCCD=?, Status=? WHERE Username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sellerToUpdate.getPassword());
            st.setString(2, sellerToUpdate.getName());
            st.setString(3, sellerToUpdate.getPhone());
            st.setString(4, sellerToUpdate.getEmail());
            st.setDate(5, sellerToUpdate.getDob());
            st.setBoolean(6, sellerToUpdate.isGender());
            st.setString(7, sellerToUpdate.getCccd());
            st.setBoolean(8, sellerToUpdate.isStatus());
            st.setString(9, sellerToUpdate.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
