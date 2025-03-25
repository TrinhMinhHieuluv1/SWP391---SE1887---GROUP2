/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.math.BigDecimal;
import java.util.List;
import model.TransactionHistory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Customer;
import model.DetailBill;

/**
 *
 * @author ACER
 */
public class TransactionHistoryDAO extends DBContext {

    public List<TransactionHistory> getAll() {
        List<TransactionHistory> transactions = new ArrayList<>();
        String sql = "Select * from TransactionHistory";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CustomerDAO dao = new CustomerDAO();
                Customer customer = dao.getCustomerByID(rs.getInt("CustomerID"));
                Customer receiver = dao.getCustomerByID(rs.getInt("ReceiverID"));
                // Tạo đối tượng TransactionHistory
                TransactionHistory transaction = new TransactionHistory(
                        rs.getInt("TransactionID"),
                        rs.getInt("Status"),
                        customer,
                        receiver,
                        rs.getBigDecimal("Amount"),
                        rs.getBigDecimal("BalanceBefore"),
                        rs.getBigDecimal("BalanceAfter"),
                        rs.getString("Transaction_type"),
                        rs.getString("Note"),
                        rs.getDate("CreatedAt")
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
    public int getTotalTransactionID() {
    int total = 0;
    String sql = "SELECT COUNT(TransactionID) AS Total FROM TransactionHistory";
    
    try (PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        if (rs.next()) {
            total = rs.getInt("Total");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}

    public boolean addTransaction(TransactionHistory transaction) {
     String sql = "INSERT INTO TransactionHistory (CustomerID, Amount, BalanceBefore, BalanceAfter, Transaction_type, Status, ReceiverID, Note, CreatedAt) "
               + "VALUES (?, ?, ?, ?, ?, 1, ?, ?, GETDATE())";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, transaction.getCustomer().getCustomerId());
        stmt.setBigDecimal(2, transaction.getAmount());
        stmt.setBigDecimal(3, transaction.getBalanceBefore());
        stmt.setBigDecimal(4, transaction.getBalanceAfter());
        stmt.setString(5, transaction.getTransaction_type());
        stmt.setInt(6, transaction.getReceiver().getCustomerId());
        stmt.setString(7, transaction.getNote());

        return stmt.executeUpdate() > 0; // Trả về true nếu thêm thành công
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
    }

    public List<TransactionHistory> filterListCustomer(String date1, String date2, int uid) {
        String sql = "SELECT * FROM TransactionHistory WHERE 1=1 and Status = 1 and CustomerID = " + uid + " ";
        if (date1 != "") {
            sql += " and Cast(CreatedAt as date) >= '" + date1 + "' ";
        }
        if (date2 != "") {
            sql += " and Cast(CreatedAt as date) <= '" + date2 + "' ";
        }

        List<TransactionHistory> list = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CustomerDAO dao = new CustomerDAO();
                Customer customer = dao.getCustomerByID(rs.getInt("CustomerID"));
                Customer receiver = dao.getCustomerByID(rs.getInt("ReceiverID"));
                // Tạo đối tượng TransactionHistory
                TransactionHistory transaction = new TransactionHistory(
                        rs.getInt("TransactionID"),
                        rs.getInt("Status"),
                        customer,
                        receiver,
                        rs.getBigDecimal("Amount"),
                        rs.getBigDecimal("BalanceBefore"),
                        rs.getBigDecimal("BalanceAfter"),
                        rs.getString("Transaction_type"),
                        rs.getString("Note"),
                        rs.getDate("CreatedAt")
                );
                list.add(transaction);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public List<TransactionHistory> getListByPage(List<TransactionHistory> list, int start, int end) {
        List<TransactionHistory> listbill = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listbill.add(list.get(i));
        }
        return listbill;
    }

    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();
        TransactionHistoryDAO tdao = new TransactionHistoryDAO();
        Customer customer = dao.getCustomerByID(1);
        Customer receiver = dao.getCustomerByID(0);
        TransactionHistory history = new TransactionHistory(1, customer, receiver, customer.getBalance(), customer.getBalance(), customer.getBalance(),"OK", "Ok");
        tdao.addTransaction(history);
        
    }
}
