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
    
    CustomerDAO customerDAO = new CustomerDAO();
     public List<Salary> selectAllSalary() {
        List<Salary> salarys = new ArrayList<>();
        try {

            String sql = "SELECT  * FROM Salary";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                Salary salary = new Salary();
                salary.setId(resultSet.getInt("SalaryId"));
                salary.setCustomer(customerDAO.getCustomerByID(resultSet.getInt("CustomerID")));
                salary.setImage(resultSet.getString("Image"));
                salary.setTitle(resultSet.getString("Title"));
                salary.setDescription(resultSet.getString("Description"));
                salary.setValue(resultSet.getBigDecimal("Value"));
                salary.setComments(resultSet.getString("Comments"));
                salary.setValuationAmount(resultSet.getBigDecimal("ValuationAmount"));
                salary.setUsed(resultSet.getBoolean("Used"));
                salary.setStatus(resultSet.getString("Status"));
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
        String sql = "UPDATE Salary SET CustomerId = ?, Image = ?, Tilte = ?, Description = ?, "
                + "Value = ?,Comments = ?, ValuationAmount = ?, Used = ?, Status = ?,CreatedAt = ? WHERE SalaryId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, salary.getCustomer().getCustomerId());
            pstmt.setString(2, salary.getImage());
             pstmt.setString(3, salary.getTitle());
            pstmt.setString(4, salary.getDescription());
            pstmt.setBigDecimal(5, salary.getValue());
            pstmt.setString(6, salary.getComments());
            pstmt.setBigDecimal(7, salary.getValuationAmount());
            pstmt.setBoolean(8, salary.isUsed());
            pstmt.setString(9, salary.getStatus());
            pstmt.setTimestamp(10, new java.sql.Timestamp(salary.getCreatedAt().getTime()));
            pstmt.setInt(11, salary.getId());

            int rowsUpdated = pstmt.executeUpdate();
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
                salary.setCustomer(customerDAO.getCustomerByID(resultSet.getInt("CustomerID")));
                salary.setImage(resultSet.getString("Image"));
                salary.setTitle(resultSet.getString("Title"));
                salary.setDescription(resultSet.getString("Description"));
                salary.setValue(resultSet.getBigDecimal("Value"));
                salary.setComments(resultSet.getString("Comments"));
                salary.setValuationAmount(resultSet.getBigDecimal("ValuationAmount"));
                salary.setUsed(resultSet.getBoolean("Used"));
                salary.setStatus(resultSet.getString("Status"));
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
        String query = "SELECT * FROM Salary s "
                + " join Customer c on s.CustomerId = c.CustomerId"
                + " ORDER BY c.CreatedAt " + ascending;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Salary salary = new Salary();
                salary.setId(resultSet.getInt("SalaryId"));
                salary.setCustomer(customerDAO.getCustomerByID(resultSet.getInt("CustomerID")));
                salary.setImage(resultSet.getString("Image"));
                   salary.setTitle(resultSet.getString("Title"));
                salary.setDescription(resultSet.getString("Description"));
                salary.setValue(resultSet.getBigDecimal("Value"));
                salary.setComments(resultSet.getString("Comments"));
                salary.setValuationAmount(resultSet.getBigDecimal("ValuationAmount"));
                salary.setUsed(resultSet.getBoolean("Used"));
                salary.setStatus(resultSet.getString("Status"));
                salary.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
                salarys.add(salary);
            }
            return salarys;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }
  public List<Salary> getSalaryByStatus(String status) throws SQLException {
        List<Salary> salarys = new ArrayList<>();
        String query = "SELECT * FROM Salary WHERE Status = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, status);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Salary salary = new Salary();
                salary.setId(resultSet.getInt("SalaryId"));
                salary.setCustomer(customerDAO.getCustomerByID(resultSet.getInt("CustomerID")));
                salary.setImage(resultSet.getString("Image"));
                   salary.setTitle(resultSet.getString("Title"));
                salary.setDescription(resultSet.getString("Description"));
                salary.setValue(resultSet.getBigDecimal("Value"));
                salary.setComments(resultSet.getString("Comments"));
                salary.setValuationAmount(resultSet.getBigDecimal("ValuationAmount"));
                salary.setUsed(resultSet.getBoolean("Used"));
                salary.setStatus(resultSet.getString("Status"));
                salary.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
                salarys.add(salary);
            }
            return salarys;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }
  public List<Salary> getSalaryByUse(boolean status) throws SQLException {
        List<Salary> salarys = new ArrayList<>();
        String query = "SELECT * FROM Salary WHERE Used = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setBoolean(1, status);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Salary salary = new Salary();
                salary.setId(resultSet.getInt("SalaryId"));
                salary.setCustomer(customerDAO.getCustomerByID(resultSet.getInt("CustomerID")));
                salary.setImage(resultSet.getString("Image"));
                   salary.setTitle(resultSet.getString("Title"));
                salary.setDescription(resultSet.getString("Description"));
                salary.setValue(resultSet.getBigDecimal("Value"));
                salary.setComments(resultSet.getString("Comments"));
                salary.setValuationAmount(resultSet.getBigDecimal("ValuationAmount"));
                salary.setUsed(resultSet.getBoolean("Used"));
                salary.setStatus(resultSet.getString("Status"));
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
        String query = "SELECT * FROM Salary s "
                + " join Customer c on s.CustomerId = c.CustomerId"
                + " WHERE s.Description LIKE ? or c.FullName LIKE ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, "%" + description + "%");
            pstmt.setString(2, "%" + description + "%");
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
               Salary salary = new Salary();
                salary.setId(resultSet.getInt("SalaryId"));
                salary.setCustomer(customerDAO.getCustomerByID(resultSet.getInt("CustomerID")));
                salary.setImage(resultSet.getString("Image"));
                salary.setTitle(resultSet.getString("Title"));
                salary.setDescription(resultSet.getString("Description"));
                salary.setValue(resultSet.getBigDecimal("Value"));
                salary.setComments(resultSet.getString("Comments"));
                salary.setValuationAmount(resultSet.getBigDecimal("ValuationAmount"));
                salary.setUsed(resultSet.getBoolean("Used"));
                salary.setStatus(resultSet.getString("Status"));
                salary.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
                salarys.add(salary);
            }
            return salarys;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }
    
   public Salary getSalaryForCustomer(int CustomerID) {

        String sql = "SELECT * FROM Salary WHERE (CustomerID=?) AND (Used=0) AND (Status='Approved')";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, CustomerID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Salary salary = new Salary();
                salary.setId(resultSet.getInt("SalaryId"));
                salary.setCustomer(customerDAO.getCustomerByID(resultSet.getInt("CustomerID")));
                salary.setImage(resultSet.getString("Image"));
                salary.setTitle(resultSet.getString("Title"));
                salary.setDescription(resultSet.getString("Description"));
                salary.setValue(resultSet.getBigDecimal("Value"));
                salary.setComments(resultSet.getString("Comments"));
                salary.setValuationAmount(resultSet.getBigDecimal("ValuationAmount"));
                salary.setUsed(resultSet.getBoolean("Used"));
                salary.setStatus(resultSet.getString("Status"));
                salary.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
                return salary;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
