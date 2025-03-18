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
import model.PdfLis;

/**
 *
 * @author tiend
 */
public class PdfDAO extends DBContext {

    public void addPdfLis(PdfLis pdfLis) {
        if (pdfLis.getAssetID() > 0) {
            String sql = "INSERT INTO PdfLis (PdfName, AssetID) VALUES (?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, pdfLis.getPdfName());
                pstmt.setInt(2, pdfLis.getAssetID());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pdfLis.getSalaryID() > 0) {
            String sql = "INSERT INTO PdfLis (PdfName, SalaryID) VALUES (?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, pdfLis.getPdfName());
                pstmt.setInt(2, pdfLis.getSalaryID());
                int re = pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    // Update
    public void updatePdfLis(PdfLis pdfLis) {
        String sql = "UPDATE PdfLis SET PdfName = ?, AssetID = ?, SalaryID = ? WHERE PdfID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pdfLis.getPdfName());
            pstmt.setInt(2, pdfLis.getAssetID());
            pstmt.setInt(3, pdfLis.getSalaryID());
            pstmt.setInt(4, pdfLis.getPdfID());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý lỗi tại đây
        }
    }

    // Delete
    public void deletePdfLis(int pdfID) {
        String sql = "DELETE FROM PdfLis WHERE PdfID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pdfID);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý lỗi tại đây
        }
    }

    public void deletePdfLisbyAId(int id) {
        String sql = "DELETE FROM PdfLis WHERE AssetID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý lỗi tại đây
        }
    }

    public void deletePdfLisbySId(int id) {
        String sql = "DELETE FROM PdfLis WHERE SalaryID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý lỗi tại đây
        }
    }

    public void deletePdfLisByName(String pdfname) {
        String sql = "DELETE FROM PdfLis WHERE PdfName = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pdfname);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý lỗi tại đây
        }
    }
    public void deletePdfs(List<Integer> pdfIds){
        try {
            String sql = "DELETE FROM PdfLis WHERE PdfID = ?";
           PreparedStatement pstmt = connection.prepareStatement(sql);
            for (Integer pdfId : pdfIds) {
                pstmt.setInt(1, pdfId);
                pstmt.executeUpdate();
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Get all
    public List<PdfLis> getAllPdfLis() {
        List<PdfLis> pdfLisList = new ArrayList<>();
        String sql = "SELECT * FROM PdfLis";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                PdfLis pdf = new PdfLis();
                pdf.setPdfID(rs.getInt("PdfID"));
                pdf.setPdfName(rs.getString("PdfName"));
                pdf.setAssetID(rs.getInt("AssetID"));
                pdf.setSalaryID(rs.getInt("SalaryID"));
                pdfLisList.add(pdf);
            }
            return pdfLisList;
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý lỗi tại đây
            return null;

        }
    }

    public List<PdfLis> getpdfByAssetId(int assetID) {
        List<PdfLis> pdfLisList = new ArrayList<>();
        String sql = "SELECT * FROM pdfLis WHERE AssetID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, assetID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                PdfLis pdf = new PdfLis();
                pdf.setPdfID(rs.getInt("PdfID"));
                pdf.setPdfName(rs.getString("PdfName"));
                pdf.setAssetID(rs.getInt("AssetID"));
                pdf.setSalaryID(rs.getInt("SalaryID"));
                pdfLisList.add(pdf);
            }
            return pdfLisList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<PdfLis> getpdfBySalaryId(int salaryID) {
        List<PdfLis> pdfLisList = new ArrayList<>();
        String sql = "SELECT * FROM pdfLis WHERE SalaryID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, salaryID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                PdfLis pdf = new PdfLis();
                pdf.setPdfID(rs.getInt("PdfID"));
                pdf.setPdfName(rs.getString("PdfName"));
                pdf.setAssetID(rs.getInt("AssetID"));
                pdf.setSalaryID(rs.getInt("SalaryID"));
                pdfLisList.add(pdf);
            }
            return pdfLisList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
