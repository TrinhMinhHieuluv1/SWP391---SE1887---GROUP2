/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Asset;

/**
 *
 * @author tiend
 */
public class AssetDAO extends DBContext{
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
  public List<Asset> searchAssetsByDescription(String description) throws SQLException {
        List<Asset> assets = new ArrayList<>();
        String query = "SELECT * FROM Asset WHERE Description LIKE ?";
        
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, "%" + description + "%");
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
}
