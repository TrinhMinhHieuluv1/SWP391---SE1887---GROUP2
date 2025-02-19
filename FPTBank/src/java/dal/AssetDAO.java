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
public class AssetDAO extends DBContext {

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
                asset.setComments(resultSet.getString("Comments"));
                asset.setValuationAmount(resultSet.getBigDecimal("ValuationAmount"));
                asset.setUsed(resultSet.getBoolean("Used"));
                asset.setStatus(resultSet.getString("Status"));
                asset.setPdfPath(resultSet.getString("PdfPath"));
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
        String sql = "UPDATE Asset SET CustomerID = ?, Image = ?, Description = ?, "
                + "Value = ?, Comments = ?, ValuationAmount = ?, Used = ?, "
                + "Status = ?,PdfPath=?, CreatedAt = ? WHERE AssetID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, asset.getCustomerId());
            pstmt.setString(2, asset.getImage());
            pstmt.setString(3, asset.getDescription());
            pstmt.setBigDecimal(4, asset.getValue());
            pstmt.setString(5, asset.getComments());
            pstmt.setBigDecimal(6, asset.getValuationAmount());
            pstmt.setBoolean(7, asset.isUsed());
            pstmt.setString(8, asset.getStatus());
            pstmt.setString(9, asset.getPdfPath());
            pstmt.setTimestamp(10, new java.sql.Timestamp(asset.getCreatedAt().getTime()));
            pstmt.setInt(11, asset.getId());

            int rowsUpdated = pstmt.executeUpdate();
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
                asset.setComments(resultSet.getString("Comments"));
                asset.setValuationAmount(resultSet.getBigDecimal("ValuationAmount"));
                asset.setUsed(resultSet.getBoolean("Used"));
                asset.setStatus(resultSet.getString("Status"));
                asset.setPdfPath(resultSet.getString("PdfPath"));
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
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Asset asset = new Asset();
                asset.setId(resultSet.getInt("AssetId"));
                asset.setCustomerId(resultSet.getInt("CustomerId"));
                asset.setImage(resultSet.getString("Image"));
                asset.setDescription(resultSet.getString("Description"));
                asset.setValue(resultSet.getBigDecimal("Value"));
                asset.setComments(resultSet.getString("Comments"));
                asset.setValuationAmount(resultSet.getBigDecimal("ValuationAmount"));
                asset.setUsed(resultSet.getBoolean("Used"));
                asset.setStatus(resultSet.getString("Status"));
                asset.setPdfPath(resultSet.getString("PdfPath"));
                asset.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
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
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Asset asset = new Asset();
                asset.setId(resultSet.getInt("AssetId"));
                asset.setCustomerId(resultSet.getInt("CustomerId"));
                asset.setImage(resultSet.getString("Image"));
                asset.setDescription(resultSet.getString("Description"));
                asset.setValue(resultSet.getBigDecimal("Value"));
                asset.setComments(resultSet.getString("Comments"));
                asset.setValuationAmount(resultSet.getBigDecimal("ValuationAmount"));
                asset.setUsed(resultSet.getBoolean("Used"));
                asset.setStatus(resultSet.getString("Status"));
                asset.setPdfPath(resultSet.getString("PdfPath"));
                asset.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
                assets.add(asset);
            }
            return assets;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }
    public List<Asset> getAssetsByStatus(String status) throws SQLException {
        List<Asset> assets = new ArrayList<>();
        String query = "SELECT * FROM Asset WHERE Status = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, status);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Asset asset = new Asset();
                asset.setId(resultSet.getInt("AssetId"));
                asset.setCustomerId(resultSet.getInt("CustomerId"));
                asset.setImage(resultSet.getString("Image"));
                asset.setDescription(resultSet.getString("Description"));
                asset.setValue(resultSet.getBigDecimal("Value"));
                asset.setComments(resultSet.getString("Comments"));
                asset.setValuationAmount(resultSet.getBigDecimal("ValuationAmount"));
                asset.setUsed(resultSet.getBoolean("Used"));
                asset.setStatus(resultSet.getString("Status"));
                asset.setPdfPath(resultSet.getString("PdfPath"));
                asset.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
                assets.add(asset);
            }
            return assets;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }
    public List<Asset> getAssetsByUsed(boolean status) throws SQLException {
        List<Asset> assets = new ArrayList<>();
        String query = "SELECT * FROM Asset WHERE Used = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setBoolean(1, status);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Asset asset = new Asset();
                asset.setId(resultSet.getInt("AssetId"));
                asset.setCustomerId(resultSet.getInt("CustomerId"));
                asset.setImage(resultSet.getString("Image"));
                asset.setDescription(resultSet.getString("Description"));
                asset.setValue(resultSet.getBigDecimal("Value"));
                asset.setComments(resultSet.getString("Comments"));
                asset.setValuationAmount(resultSet.getBigDecimal("ValuationAmount"));
                asset.setUsed(resultSet.getBoolean("Used"));
                asset.setStatus(resultSet.getString("Status"));
                asset.setPdfPath(resultSet.getString("PdfPath"));
                asset.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
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
        String query = "SELECT a.* FROM Asset a "
                + " join Customer c on a.CustomerId = c.CustomerId"
                + " WHERE a.Description LIKE ? or c.FullName LIKE ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, "%" + description + "%");
            pstmt.setString(2, "%" + description + "%");
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Asset asset = new Asset();
                asset.setId(resultSet.getInt("AssetId"));
                asset.setCustomerId(resultSet.getInt("CustomerId"));
                asset.setImage(resultSet.getString("Image"));
                asset.setDescription(resultSet.getString("Description"));
                asset.setValue(resultSet.getBigDecimal("Value"));
                asset.setComments(resultSet.getString("Comments"));
                asset.setValuationAmount(resultSet.getBigDecimal("ValuationAmount"));
                asset.setUsed(resultSet.getBoolean("Used"));
                asset.setStatus(resultSet.getString("Status"));
                asset.setPdfPath(resultSet.getString("PdfPath"));
                asset.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
                assets.add(asset);
            }
            return assets;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }
}
