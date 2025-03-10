/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.DetailBill;
import model.User;

/**
 *
 * @author ACER
 */
public class DetailBillDAO extends DBContext {

    CustomerDAO cdao = new CustomerDAO();
    UserDAO udao = new UserDAO();

    public List<DetailBill> getAllBill() {
        List<DetailBill> list = new ArrayList<>();
        String sql = "select * from DetailBill";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                Customer customer = cdao.getCustomerByID(customerID);
                User user = udao.selectAnUserByConditions(rs.getInt("ProviderID"), "", "", "");
                DetailBill d = new DetailBill(rs.getInt("BillID"),
                        rs.getInt("Status"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("StartDate"),
                        rs.getString("EndDate"),
                        rs.getString("CreatedAt"),
                        rs.getBigDecimal("Total"),
                        customer,
                        user);
                list.add(d);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public boolean add(DetailBill bill) {
        // Câu lệnh SQL INSERT
        String sql = "INSERT INTO DetailBill (Status, Title, Description, StartDate, EndDate, CreatedAt, Total, CustomerID, ProviderID) "
                + "VALUES (1, ?, ?, ?, ?, GETDATE(), ?, ?, ?)";

        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, bill.getTitle());
            pre.setString(2, bill.getDescription());
            pre.setString(3, bill.getStartDate());  // Giả sử StartDate là String
            pre.setString(4, bill.getEndDate());    // Giả sử EndDate là String

            pre.setBigDecimal(5, bill.getTotal());
            pre.setInt(6, bill.getCustomer().getCustomerId());  // CustomerID từ đối tượng Customer
            pre.setInt(7, bill.getProvider().getUserID());  // ProviderID từ đối tượng Provider

            // Thực thi câu lệnh INSERT
            int affectedRows = pre.executeUpdate();

            // Kiểm tra nếu có ít nhất một dòng bị ảnh hưởng, tức là dữ liệu đã được chèn thành công
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        

    public DetailBill getDetailBillByID(int billID) {
        String sql = "SELECT * FROM DetailBill WHERE BillID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, billID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                Customer customer = cdao.getCustomerByID(customerID);
                User user = udao.selectAnUserByConditions(rs.getInt("ProviderID"), "", "", "");

                return new DetailBill(
                        rs.getInt("BillID"),
                        rs.getInt("Status"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("StartDate"),
                        rs.getString("EndDate"),
                        rs.getString("CreatedAt"),
                        rs.getBigDecimal("Total"),
                        customer,
                        user
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
     public static void main(String[] args) {
       DetailBillDAO dao = new DetailBillDAO();
       DetailBill a = dao.getDetailBillByID(1);
         System.out.println(a);
        
    }
}


   
       

