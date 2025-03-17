/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Insurance;
import model.User;

public class InsuranceDAO extends DBContext {

    public List<Insurance> getAllInsurance() {
        List<Insurance> insuranceList = new ArrayList<>();
        String sql = "SELECT * FROM Insurance";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Insurance insurance = new Insurance(
                        rs.getInt("InsuranceID"),
                        rs.getInt("ProviderID"),
                        rs.getString("InsuranceName"),
                        rs.getString("Type"),
                        rs.getFloat("FeeRate"),
                        rs.getFloat("CoverageRate"),
                        rs.getDouble("MaxAmountOfLoan"),
                        rs.getBoolean("Status")
                );
                insuranceList.add(insurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceList;
    }

    public ArrayList<Insurance> getAllInsuranceByPage(int page, int pageSize) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String sql = "SELECT * FROM Insurance ORDER BY InsuranceID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (page - 1) * pageSize); // Bỏ qua các dòng trước đó
            st.setInt(2, pageSize); // Lấy số dòng tương ứng với pageSize
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Insurance insurance = new Insurance(
                        rs.getInt("InsuranceID"),
                        rs.getInt("ProviderID"),
                        rs.getString("InsuranceName"),
                        rs.getString("Type"),
                        rs.getFloat("FeeRate"),
                        rs.getFloat("CoverageRate"),
                        rs.getDouble("MaxAmountOfLoan"),
                        rs.getBoolean("Status")
                );
                insuranceList.add(insurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceList;
    }

    public Insurance getInsuranceByID(int insuranceID) {
        String sql = "SELECT * FROM Insurance WHERE InsuranceID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, insuranceID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Insurance(
                        rs.getInt("InsuranceID"),
                        rs.getInt("ProviderID"),
                        rs.getString("InsuranceName"),
                        rs.getString("Type"),
                        rs.getFloat("FeeRate"),
                        rs.getFloat("CoverageRate"),
                        rs.getDouble("MaxAmountOfLoan"),
                        rs.getBoolean("Status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Insurance> getInsuranceByProviderID(int providerID) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String sql = "SELECT * FROM Insurance WHERE ProviderID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, providerID); // Lọc theo ProviderID
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Insurance insurance = new Insurance(
                        rs.getInt("InsuranceID"),
                        rs.getInt("ProviderID"),
                        rs.getString("InsuranceName"),
                        rs.getString("Type"),
                        rs.getFloat("FeeRate"),
                        rs.getFloat("CoverageRate"),
                        rs.getDouble("MaxAmountOfLoan"),
                        rs.getBoolean("Status")
                );
                insuranceList.add(insurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceList;
    }

    public ArrayList<Insurance> getAllInsuranceByProviderIDByPage(int providerID, int page, int pageSize) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String sql = "SELECT * FROM Insurance WHERE ProviderID = ? ORDER BY InsuranceID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, providerID); // Lọc theo ProviderID
            st.setInt(2, (page - 1) * pageSize); // Bỏ qua các dòng trước đó
            st.setInt(3, pageSize); // Lấy số dòng tương ứng với pageSize
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Insurance insurance = new Insurance(
                        rs.getInt("InsuranceID"),
                        rs.getInt("ProviderID"),
                        rs.getString("InsuranceName"),
                        rs.getString("Type"),
                        rs.getFloat("FeeRate"),
                        rs.getFloat("CoverageRate"),
                        rs.getDouble("MaxAmountOfLoan"),
                        rs.getBoolean("Status")
                );
                insuranceList.add(insurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceList;
    }

    public ArrayList<Insurance> getAllInsuranceByProviderID(int providerID) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String sql = "SELECT * FROM Insurance WHERE ProviderID = ? ORDER BY InsuranceID"; // Không có phân trang
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, providerID); // Lọc theo ProviderID
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Insurance insurance = new Insurance(
                        rs.getInt("InsuranceID"),
                        rs.getInt("ProviderID"),
                        rs.getString("InsuranceName"),
                        rs.getString("Type"),
                        rs.getFloat("FeeRate"),
                        rs.getFloat("CoverageRate"),
                        rs.getDouble("MaxAmountOfLoan"),
                        rs.getBoolean("Status")
                );
                insuranceList.add(insurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceList;
    }

    public boolean addInsurance(Insurance insurance) {
        String sql = "INSERT INTO Insurance (ProviderID, InsuranceName, Type, FeeRate, CoverageRate, MaxAmountOfLoan, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, insurance.getProviderID());
            st.setString(2, insurance.getInsuranceName()); // Thêm InsuranceName
            st.setString(3, insurance.getType());
            st.setFloat(4, insurance.getFeeRate());
            st.setFloat(5, insurance.getCoverageRate());
            st.setDouble(6, insurance.getMaxAmountOfLoan());
            st.setBoolean(7, insurance.isStatus());
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu có ít nhất một hàng được chèn
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    public ArrayList<Insurance> searchByNameByPage(int providerID, String keyword, int page, int pageSize) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String sql = "SELECT * FROM Insurance WHERE ProviderID = ? AND InsuranceName LIKE ? ORDER BY InsuranceID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, providerID); // Lọc theo ProviderID
            st.setString(2, "%" + keyword + "%"); // Tìm kiếm tên bảo hiểm
            st.setInt(3, (page - 1) * pageSize); // Bỏ qua các dòng trước đó
            st.setInt(4, pageSize); // Lấy số dòng tương ứng với pageSize
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Insurance insurance = new Insurance(
                        rs.getInt("InsuranceID"),
                        rs.getInt("ProviderID"),
                        rs.getString("InsuranceName"),
                        rs.getString("Type"),
                        rs.getFloat("FeeRate"),
                        rs.getFloat("CoverageRate"),
                        rs.getDouble("MaxAmountOfLoan"),
                        rs.getBoolean("Status")
                );
                insuranceList.add(insurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceList;
    }

    public int getTotalAfterSearchByName(int providerID, String keyword) {
        String sql = "SELECT COUNT(*) AS Total FROM Insurance WHERE ProviderID = ? AND InsuranceName LIKE ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, providerID); // Lọc theo ProviderID
            st.setString(2, "%" + keyword + "%"); // Tìm kiếm tên bảo hiểm
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total"); // Trả về tổng số bản ghi
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy bản ghi nào
    }

    public ArrayList<Insurance> searchByTypeByPage(int providerID, String type, int page, int pageSize) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String sql = "SELECT * FROM Insurance WHERE ProviderID = ? AND Type = ? ORDER BY InsuranceID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, providerID); // Lọc theo ProviderID
            st.setString(2, type); // Tìm kiếm loại bảo hiểm
            st.setInt(3, (page - 1) * pageSize); // Bỏ qua các dòng trước đó
            st.setInt(4, pageSize); // Lấy số dòng tương ứng với pageSize
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Insurance insurance = new Insurance(
                        rs.getInt("InsuranceID"),
                        rs.getInt("ProviderID"),
                        rs.getString("InsuranceName"),
                        rs.getString("Type"),
                        rs.getFloat("FeeRate"),
                        rs.getFloat("CoverageRate"),
                        rs.getDouble("MaxAmountOfLoan"),
                        rs.getBoolean("Status")
                );
                insuranceList.add(insurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceList;
    }

    public int getTotalAfterSearchByType(int providerID, String type) {
        String sql = "SELECT COUNT(*) AS Total FROM Insurance WHERE ProviderID = ? AND Type = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, providerID); // Lọc theo ProviderID
            st.setString(2, type); // Thiết lập giá trị Type
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total"); // Trả về tổng số bản ghi
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy bản ghi nào
    }

    public ArrayList<Insurance> sortByFeeRate(int providerID, int page, int pageSize, boolean isDescending) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String order = isDescending ? "DESC" : "ASC";
        String sql = "SELECT * FROM Insurance WHERE ProviderID = ? ORDER BY FeeRate " + order
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, providerID); // Lọc theo ProviderID
            st.setInt(2, (page - 1) * pageSize); // Bỏ qua các dòng trước đó
            st.setInt(3, pageSize); // Lấy số dòng tương ứng với pageSize
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Insurance insurance = new Insurance(
                        rs.getInt("InsuranceID"),
                        rs.getInt("ProviderID"),
                        rs.getString("InsuranceName"),
                        rs.getString("Type"),
                        rs.getFloat("FeeRate"),
                        rs.getFloat("CoverageRate"),
                        rs.getDouble("MaxAmountOfLoan"),
                        rs.getBoolean("Status")
                );
                insuranceList.add(insurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceList;
    }

    public ArrayList<Insurance> sortByCoverageRate(int providerID, int page, int pageSize, boolean isDescending) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String order = isDescending ? "DESC" : "ASC";
        String sql = "SELECT * FROM Insurance WHERE ProviderID = ? ORDER BY CoverageRate " + order
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, providerID); // Lọc theo ProviderID
            st.setInt(2, (page - 1) * pageSize); // Bỏ qua các dòng trước đó
            st.setInt(3, pageSize); // Lấy số dòng tương ứng với pageSize
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Insurance insurance = new Insurance(
                        rs.getInt("InsuranceID"),
                        rs.getInt("ProviderID"),
                        rs.getString("InsuranceName"),
                        rs.getString("Type"),
                        rs.getFloat("FeeRate"),
                        rs.getFloat("CoverageRate"),
                        rs.getDouble("MaxAmountOfLoan"),
                        rs.getBoolean("Status")
                );
                insuranceList.add(insurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceList;
    }

    public ArrayList<Insurance> sortByMaxAmountOfLoan(int providerID, int page, int pageSize, boolean isDescending) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String order = isDescending ? "DESC" : "ASC";
        String sql = "SELECT * FROM Insurance WHERE ProviderID = ? ORDER BY MaxAmountOfLoan " + order
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, providerID); // Lọc theo ProviderID
            st.setInt(2, (page - 1) * pageSize); // Bỏ qua các dòng trước đó
            st.setInt(3, pageSize); // Lấy số dòng tương ứng với pageSize
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Insurance insurance = new Insurance(
                        rs.getInt("InsuranceID"),
                        rs.getInt("ProviderID"),
                        rs.getString("InsuranceName"),
                        rs.getString("Type"),
                        rs.getFloat("FeeRate"),
                        rs.getFloat("CoverageRate"),
                        rs.getDouble("MaxAmountOfLoan"),
                        rs.getBoolean("Status")
                );
                insuranceList.add(insurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceList;
    }

    public ArrayList<Insurance> findByStatusByPage(int providerID, boolean status, int page, int pageSize) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String sql = "SELECT * FROM Insurance WHERE ProviderID = ? AND Status = ? ORDER BY InsuranceID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, providerID); // Lọc theo ProviderID
            st.setBoolean(2, status); // Lọc theo trạng thái
            st.setInt(3, (page - 1) * pageSize); // Bỏ qua các dòng trước đó
            st.setInt(4, pageSize); // Lấy số dòng tương ứng với pageSize
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Insurance insurance = new Insurance(
                        rs.getInt("InsuranceID"),
                        rs.getInt("ProviderID"),
                        rs.getString("InsuranceName"),
                        rs.getString("Type"),
                        rs.getFloat("FeeRate"),
                        rs.getFloat("CoverageRate"),
                        rs.getDouble("MaxAmountOfLoan"),
                        rs.getBoolean("Status")
                );
                insuranceList.add(insurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceList;
    }

    public int getTotalAfterSearchStatus(int providerID, String status) {
        String sql = "SELECT COUNT(*) AS Total FROM Insurance WHERE ProviderID = ? AND Status = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, providerID); // Lọc theo ProviderID
            st.setBoolean(2, Boolean.parseBoolean(status)); // Chuyển đổi String sang boolean
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean updateStatus(int insuranceID, boolean status) {
        String sql = "UPDATE Insurance SET Status = ? WHERE InsuranceID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBoolean(1, status); // Thiết lập giá trị Status mới
            st.setInt(2, insuranceID); // Thiết lập InsuranceID
            int rowsUpdated = st.executeUpdate(); // Thực thi câu lệnh SQL và lấy số dòng được cập nhật
            return rowsUpdated > 0; // Trả về true nếu có ít nhất một dòng được cập nhật
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    public User getUserByProviderId(int providerId) {
        String sql = "SELECT DISTINCT u.* FROM [User] u "
                + "INNER JOIN Insurance i ON u.UserID = i.ProviderID "
                + "WHERE i.ProviderID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, providerId); // Thiết lập giá trị ProviderID
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                return new User(rs.getString("Username"), rs.getString("Phone"), rs.getString("Email"), rs.getDate("DateOfBirth"), rs.getBoolean("Gender"), rs.getString("Address"), rs.getString("CCCD"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy người dùng
    }

    public boolean updateInsurance(Insurance insurance) {
        // Chỉ cập nhật các trường Type, FeeRate, CoverageRate, và MaxAmountOfLoan
        String sql = "UPDATE Insurance SET Type=?, FeeRate=?, CoverageRate=?, MaxAmountOfLoan=? WHERE InsuranceID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, insurance.getType());
            st.setFloat(2, insurance.getFeeRate());
            st.setFloat(3, insurance.getCoverageRate());
            st.setDouble(4, insurance.getMaxAmountOfLoan());
            st.setInt(5, insurance.getInsuranceID());

            // Thực thi câu lệnh UPDATE
            int rowsUpdated = st.executeUpdate();

            // Trả về true nếu có ít nhất một hàng được cập nhật
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Trả về false nếu có lỗi xảy ra
            return false;
        }

    }

    public boolean isInsuranceNameExists(String insuranceName) {
        String sql = "SELECT COUNT(*) AS Count FROM Insurance WHERE InsuranceName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, insuranceName); // Thiết lập giá trị InsuranceName
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("Count"); // Lấy số lượng bản ghi có InsuranceName trùng khớp
                return count > 0; // Trả về true nếu có ít nhất một bản ghi trùng
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi hoặc không tìm thấy bản ghi nào
    }

    public static void main(String[] args) {
        // Tạo một đối tượng InsuranceDAO
        InsuranceDAO insuranceDAO = new InsuranceDAO();

        // Tạo một đối tượng Insurance với các thông tin cần thiết
        Insurance insurance = new Insurance();
        insurance.setProviderID(7); // ID của nhà cung cấp
        insurance.setInsuranceName("thoai");
        insurance.setType("Secured Loan"); // Loại bảo hiểm
        insurance.setFeeRate(0.05f); // Tỷ lệ phí
        insurance.setCoverageRate(0.8f); // Tỷ lệ bao phủ
        insurance.setMaxAmountOfLoan(100000.0); // Số tiền tối đa được vay
        insurance.setStatus(true); // Trạng thái hoạt động

        // Gọi hàm addInsurance và kiểm tra kết quả
        boolean isAdded = insuranceDAO.addInsurance(insurance);
        if (isAdded) {
            System.out.println("Thêm bảo hiểm thành công!");
        } else {
            System.out.println("Thêm bảo hiểm thất bại!");
        }
    }

    // Test sortByFeeRate
//    System.out.println("Testing sortByFeeRate:");
//    ArrayList<Insurance> feeRateList = insuranceDAO.sortByFeeRate(1, 10, true); // Sắp xếp giảm dần
//    for (Insurance insurance : feeRateList) {
//        System.out.println(insurance);
//    }
//
//    // Test getTotalAfterSearchByType
//    System.out.println("\nTesting getTotalAfterSearchByType:");
//    int totalByType = insuranceDAO.getTotalAfterSearchByType("Health");
//    System.out.println("Total records for type 'Health': " + totalByType);
//
//    // Test sortByCoverageRate
//    System.out.println("\nTesting sortByCoverageRate:");
//    ArrayList<Insurance> coverageRateList = insuranceDAO.sortByCoverageRate(1, 10, false); // Sắp xếp tăng dần
//    for (Insurance insurance : coverageRateList) {
//        System.out.println(insurance);
//    }
//
//    // Test sortByMaxAmountOfLoan
//    System.out.println("\nTesting sortByMaxAmountOfLoan:");
//    ArrayList<Insurance> maxAmountList = insuranceDAO.sortByMaxAmountOfLoan(1, 10, true); // Sắp xếp giảm dần
//    for (Insurance insurance : maxAmountList) {
//        System.out.println(insurance);
//    }
//
//    // Test findByStatusByPage
//    System.out.println("\nTesting findByStatusByPage:");
//    ArrayList<Insurance> statusList = insuranceDAO.findByStatusByPage(true, 1, 10); // Tìm các bản ghi có Status = true
//    for (Insurance insurance : statusList) {
//        System.out.println(insurance);
//    }
//
//    // Test getTotalAfterSearchStatus
//    System.out.println("\nTesting getTotalAfterSearchStatus:");
//    int totalByStatus = insuranceDAO.getTotalAfterSearchStatus("true"); // Tìm tổng số bản ghi có Status = true
//    System.out.println("Total records with status 'true': " + totalByStatus);
    // Giả sử providerId = 1 tồn tại trong cơ sở dữ liệu
    public void deleteInsurance(int insuranceID) {
        String sql = "DELETE FROM Insurance WHERE InsuranceID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, insuranceID);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
