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

    public void addInsurance(Insurance insurance) {
        String sql = "INSERT INTO Insurance (ProviderID, Type, FeeRate, CoverageRate, MaxAmountOfLoan, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, insurance.getProviderID());
            st.setString(2, insurance.getType());
            st.setFloat(3, insurance.getFeeRate());
            st.setFloat(4, insurance.getCoverageRate());
            st.setDouble(5, insurance.getMaxAmountOfLoan());
            st.setBoolean(6, insurance.isStatus());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void updateInsurance(Insurance insurance) {
//        String sql = "UPDATE Insurance SET ProviderID=?, Type=?, FeeRate=?, CoverageRate=?, MaxAmountOfLoan=?, Status=? WHERE InsuranceID=?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, insurance.getProviderID());
//            st.setString(2, insurance.getType());
//            st.setFloat(3, insurance.getFeeRate());
//            st.setFloat(4, insurance.getCoverageRate());
//            st.setDouble(5, insurance.getMaxAmountOfLoan());
//            st.setBoolean(6, insurance.isStatus());
//            st.setInt(7, insurance.getInsuranceID());
//            st.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void deleteInsurance(int insuranceID) {
        String sql = "DELETE FROM Insurance WHERE InsuranceID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, insuranceID);

    public void updateInsurance(Insurance insurance) {
        String sql = "UPDATE Insurance SET ProviderID=?, Type=?, FeeRate=?, CoverageRate=?, MaxAmountOfLoan=?, Status=? WHERE InsuranceID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, insurance.getProviderID());
            st.setString(2, insurance.getType());
            st.setFloat(3, insurance.getFeeRate());
            st.setFloat(4, insurance.getCoverageRate());
            st.setDouble(5, insurance.getMaxAmountOfLoan());
            st.setBoolean(6, insurance.isStatus());
            st.setInt(7, insurance.getInsuranceID());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Insurance> searchByNameByPage(String keyword, int page, int pageSize) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String sql = "SELECT * FROM Insurance WHERE InsuranceName LIKE ? ORDER BY InsuranceID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + keyword + "%"); // Tìm kiếm tên bảo hiểm
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

    public int getTotalAfterSearchByName(String keyword) {
        String sql = "SELECT COUNT(*) AS Total FROM Insurance WHERE InsuranceName LIKE ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + keyword + "%"); // Tìm kiếm tên bảo hiểm với từ khóa
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total"); // Trả về tổng số bản ghi
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy bản ghi nào
    }

    public ArrayList<Insurance> searchByTypeByPage(String type, int page, int pageSize) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String sql = "SELECT * FROM Insurance WHERE Type = ? ORDER BY InsuranceID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, type); // Tìm kiếm loại bảo hiểm
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

   

    public ArrayList<Insurance> sortByFeeRate(int page, int pageSize, boolean isDescending) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String order = isDescending ? "DESC" : "ASC";
        String sql = "SELECT * FROM Insurance ORDER BY FeeRate " + order
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (page - 1) * pageSize);
            st.setInt(2, pageSize);
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

    public int getTotalAfterSearchByType(String type) {
        String sql = "SELECT COUNT(*) AS Total FROM Insurance WHERE Type = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, type); // Thiết lập giá trị Type
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total"); // Trả về tổng số bản ghi
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu có lỗi hoặc không tìm thấy bản ghi nào
    }

    public ArrayList<Insurance> sortByCoverageRate(int page, int pageSize, boolean isDescending) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        // Xác định thứ tự sắp xếp dựa trên giá trị của isDescending
        String order = isDescending ? "DESC" : "ASC";
        String sql = "SELECT * FROM Insurance ORDER BY CoverageRate " + order
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
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

    public ArrayList<Insurance> sortByMaxAmountOfLoan(int page, int pageSize, boolean isDescending) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String order = isDescending ? "DESC" : "ASC";
        String sql = "SELECT * FROM Insurance ORDER BY MaxAmountOfLoan " + order
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (page - 1) * pageSize);
            st.setInt(2, pageSize);
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

    public ArrayList<Insurance> findByStatusByPage(boolean status, int page, int pageSize) {
        ArrayList<Insurance> insuranceList = new ArrayList<>();
        String sql = "SELECT * FROM Insurance WHERE Status = ? ORDER BY InsuranceID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBoolean(1, status);
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

    public int getTotalAfterSearchStatus(String status) {
        String sql = "SELECT COUNT(*) AS Total FROM Insurance WHERE Status = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBoolean(1, Boolean.parseBoolean(status)); // Chuyển đổi String sang boolean
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
   public static void main(String[] args) {
    InsuranceDAO insuranceDAO = new InsuranceDAO();
   
        // Tạo một đối tượng InsuranceDAO

        // Tạo một đối tượng Insurance với các giá trị cần cập nhật
        Insurance insurance = new Insurance();
        insurance.setInsuranceID(1); // ID của bản ghi cần cập nhật
        insurance.setType("Secured Loan");
        insurance.setFeeRate(0.15f); // Giá trị mới cho FeeRate
        insurance.setCoverageRate(0.85f); // Giá trị mới cho CoverageRate
        insurance.setMaxAmountOfLoan(15000.0); // Giá trị mới cho MaxAmountOfLoan

        // Gọi hàm updateInsurance và kiểm tra kết quả
        boolean isUpdated = insuranceDAO.updateInsurance(insurance);
        if (isUpdated) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Cập nhật thất bại hoặc không có bản ghi nào được cập nhật.");
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
      

}
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
