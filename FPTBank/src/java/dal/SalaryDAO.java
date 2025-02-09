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
import model.Salary;

/**
 *
 * @author tiend
 */
public class SalaryDAO extends DBContext{
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
      public boolean updateSalary(Salary salary) {
        String sql = "UPDATE Salary SET CustomerId = ?, Image = ?, Description = ?, "
                + "Value = ?, Verification = ?, Status = ?, CreatedAt = ? WHERE SalaryId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, salary.getCustomerId());
            preparedStatement.setString(2, salary.getImage());
            preparedStatement.setString(3, salary.getDescription());
            preparedStatement.setBigDecimal(4, salary.getValue());
            preparedStatement.setBoolean(5, salary.isVerification());
            preparedStatement.setBoolean(6, salary.isStatus());
            preparedStatement.setTimestamp(7, new java.sql.Timestamp(salary.getCreatedAt().getTime()));
            preparedStatement.setInt(8, salary.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có ít nhất một hàng được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }
    public Salary getSalaryById(int id) {

        String sql = "SELECT * FROM Salary WHERE SalaryId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Salary salary = new Salary();
                salary.setId(resultSet.getInt("SalaryId"));
                salary.setCustomerId(resultSet.getInt("CustomerId"));
                salary.setImage(resultSet.getString("Image"));
                salary.setDescription(resultSet.getString("Description"));
                salary.setValue(resultSet.getBigDecimal("Value"));
                salary.setVerification(resultSet.getBoolean("Verification"));
                salary.setStatus(resultSet.getBoolean("Status"));
                salary.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
                return salary;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
 public List<Salary> getSalarySortedByDate(String ascending) throws SQLException {
        List<Salary> salarys = new ArrayList<>();
        String query = "SELECT * FROM Salary ORDER BY CreatedAt " + ascending;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
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
  public List<Salary> getSalaryByStatus(boolean status) throws SQLException {
        List<Salary> salarys = new ArrayList<>();
        String query = "SELECT * FROM Salary WHERE Status = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setBoolean(1, status);
            ResultSet resultSet = pstmt.executeQuery();
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
  public List<Salary> getSalaryByVerify(boolean status) throws SQLException {
        List<Salary> salarys = new ArrayList<>();
        String query = "SELECT * FROM Salary WHERE Verification = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setBoolean(1, status);
            ResultSet resultSet = pstmt.executeQuery();
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
   public List<Salary> searchSalaryByDescription(String description) throws SQLException {
        List<Salary> salarys = new ArrayList<>();
        String query = "SELECT * FROM Salary WHERE Description LIKE ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, "%" + description + "%");
            ResultSet resultSet = pstmt.executeQuery();
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
    
}
