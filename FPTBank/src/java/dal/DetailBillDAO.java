/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Timestamp;
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
                        rs.getInt("StatusOfBill"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getDate("CreatedAt"),
                        rs.getTimestamp("PaymentDate"),
                        rs.getBigDecimal("Total"),
                        customer,
                        user);
                list.add(d);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void add(DetailBill bill) {
        // Câu lệnh SQL INSERT
        String sql = "INSERT INTO DetailBill (Status, StatusOfBill, Title, Description, StartDate, EndDate, CreatedAt, Total, CustomerID, ProviderID) "
                + "VALUES (1,1, ?, ?, ?, ?, GETDATE(), ?, ?, ?)";

        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, bill.getTitle());
            pre.setString(2, bill.getDescription());
            pre.setDate(3, bill.getStartDate());
            pre.setDate(4, bill.getEndDate());    // Giả sử EndDate là String

            pre.setBigDecimal(5, bill.getTotal());
            pre.setInt(6, bill.getCustomer().getCustomerId());  // CustomerID từ đối tượng Customer
            pre.setInt(7, bill.getProvider().getUserID());  // ProviderID từ đối tượng Provider

            pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DetailBill getDetailBillByID(String a, int ID) {
        String sql = "SELECT * FROM DetailBill WHERE " + a + " = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                Customer customer = cdao.getCustomerByID(customerID);
                User user = udao.selectAnUserByConditions(rs.getInt("ProviderID"), "", "", "");

                return new DetailBill(
                        rs.getInt("BillID"),
                        rs.getInt("Status"),
                        rs.getInt("StatusOfBill"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getDate("CreatedAt"),
                        rs.getTimestamp("PaymentDate"),
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

    public List<DetailBill> getDetailBillByID2(String a, int ID) {
        List<DetailBill> list = new ArrayList<>();
        String sql = "SELECT * FROM DetailBill WHERE " + a + " = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                Customer customer = cdao.getCustomerByID(customerID);
                User user = udao.selectAnUserByConditions(rs.getInt("ProviderID"), "", "", "");

                DetailBill bill = new DetailBill(
                        rs.getInt("BillID"),
                        rs.getInt("Status"),
                        rs.getInt("StatusOfBill"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getDate("CreatedAt"),
                        rs.getTimestamp("PaymentDate"),
                        rs.getBigDecimal("Total"),
                        customer,
                        user
                );
                list.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DetailBill> filterList(String status, String statusbill, String date1, String date2, int uid) {
        String sql = "SELECT * FROM DetailBill WHERE 1=1 and ProviderID = " + uid + " ";
        if (date1 != "") {
            sql += " and Cast(CreatedAt as date) >= '" + date1 + "' ";
        }
        if (date2 != "") {
            sql += " and Cast(CreatedAt as date) <= '" + date2 + "' ";
        }
        if (status != "" && status.equals("true")) {
            sql += " and Status = 1";
        }
        if (status != "" && status.equals("false")) {
            sql += " and Status = 0";
        }
        if (statusbill != "" && statusbill.equals("true")) {
            sql += " and StatusOfBill = 0";
        }
        if (statusbill != "" && statusbill.equals("false")) {
            sql += " and StatusOfBill = 1";
        }
        List<DetailBill> list = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                Customer customer = cdao.getCustomerByID(customerID);
                User user = udao.selectAnUserByConditions(rs.getInt("ProviderID"), "", "", "");
                DetailBill d = new DetailBill(rs.getInt("BillID"),
                        rs.getInt("Status"),
                        rs.getInt("StatusOfBill"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getDate("CreatedAt"),
                        rs.getTimestamp("PaymentDate"),
                        rs.getBigDecimal("Total"),
                        customer,
                        user);
                list.add(d);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public List<DetailBill> filterListCustomer(String statusbill, String date1, String date2, int uid) {
        String sql = "SELECT * FROM DetailBill WHERE 1=1 and Status = 1 and CustomerID = " + uid + " ";
        if (date1 != "") {
            sql += " and Cast(CreatedAt as date) >= '" + date1 + "' ";
        }
        if (date2 != "") {
            sql += " and Cast(CreatedAt as date) <= '" + date2 + "' ";
        }
        if (statusbill != "" && statusbill.equals("true")) {
            sql += " and StatusOfBill = 0";
        }
        if (statusbill != "" && statusbill.equals("false")) {
            sql += " and StatusOfBill = 1";
        }
        List<DetailBill> list = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                Customer customer = cdao.getCustomerByID(customerID);
                User user = udao.selectAnUserByConditions(rs.getInt("ProviderID"), "", "", "");
                DetailBill d = new DetailBill(rs.getInt("BillID"),
                        rs.getInt("Status"),
                        rs.getInt("StatusOfBill"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getDate("CreatedAt"),
                        rs.getTimestamp("PaymentDate"),
                        rs.getBigDecimal("Total"),
                        customer,
                        user);
                list.add(d);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public List<DetailBill> getListByPage(List<DetailBill> list, int start, int end) {
        List<DetailBill> listbill = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listbill.add(list.get(i));
        }
        return listbill;
    }

    public void updateDetailBill(DetailBill detailBill) {
        String sql = "UPDATE DetailBill SET Status = ?, StatusOfBill = ?, Title = ?, Description = ?, "
                + "StartDate = ?, EndDate = ?, Total = ?, CustomerID = ?, ProviderID = ? , PaymentDate = ? "
                + "WHERE BillID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, detailBill.getStatus());
            pre.setInt(2, detailBill.getStatusOfBill());
            pre.setString(3, detailBill.getTitle());
            pre.setString(4, detailBill.getDescription());
            pre.setDate(5, detailBill.getStartDate());
            pre.setDate(6, detailBill.getEndDate());
            pre.setBigDecimal(7, detailBill.getTotal());
            pre.setInt(8, detailBill.getCustomer().getCustomerId());
            pre.setInt(9, detailBill.getProvider().getUserID());
            pre.setTimestamp(10, detailBill.getPaymentDate());
            pre.setInt(11, detailBill.getBillID());

            pre.executeUpdate();
            // Trả về true nếu có ít nhất một dòng được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public DetailBill getLastDetailBill(int CustomerID, int ProviderID) {
        String sql = "SELECT TOP 1 * \n"
                + "FROM DetailBill \n"
                + "WHERE CustomerID = ? and ProviderID = ?\n"
                + "ORDER BY CreatedAt DESC;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, CustomerID);
            pre.setInt(2, ProviderID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                Customer customer = cdao.getCustomerByID(customerID);
                User user = udao.selectAnUserByConditions(rs.getInt("ProviderID"), "", "", "");

                return new DetailBill(
                        rs.getInt("BillID"),
                        rs.getInt("Status"),
                        rs.getInt("StatusOfBill"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getDate("CreatedAt"),
                        rs.getTimestamp("PaymentDate"),
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
         DetailBill bill = dao.getLastDetailBill(1, 9);
         System.out.println(bill);
    }
}
