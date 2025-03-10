package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.LoanPayment;

public class LoanPaymentDAO extends DBContext{
    
    ContractDAO ctdao = new ContractDAO();
    
    public List<LoanPayment> selectAllLoanPayment(){
        List<LoanPayment> loanPaymentList = new ArrayList<>();
        String sql = "SELECT * FROM [LoanPayment]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                LoanPayment loanPaymentToAdd = 
                        new LoanPayment(rs.getInt("LoanPaymentID"), 
                                        ctdao.selectAContractByID(rs.getInt("ContractID")), 
                                        rs.getDate("PaymentDate"),
                                        rs.getBigDecimal("PaymentAmount"), 
                                        rs.getString("PaymentStatus"));
                loanPaymentList.add(loanPaymentToAdd);
            }
        } catch (SQLException e) {
        }
        return loanPaymentList;
    }
    
    public LoanPayment selectALoanPaymentByID(int LoanPaymentID){
        String sql = "SELECT * FROM [LoanPayment] WHERE LoanPaymentID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, LoanPaymentID);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                LoanPayment loanPaymentToAdd = 
                        new LoanPayment(rs.getInt("LoanPaymentID"), 
                                        ctdao.selectAContractByID(rs.getInt("ContractID")), 
                                        rs.getDate("PaymentDate"), 
                                        rs.getBigDecimal("PaymentAmount"), 
                                        rs.getString("PaymentStatus"));
                return loanPaymentToAdd;
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
    public List<LoanPayment> selectAllLoanPaymentWithConditions(){
        List<LoanPayment> loanPaymentList = new ArrayList<>();
        String sql = "SELECT * FROM [LoanPayment]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                LoanPayment loanPaymentToAdd = 
                        new LoanPayment(rs.getInt("LoanPaymentID"), 
                                        ctdao.selectAContractByID(rs.getInt("ContractID")), 
                                        rs.getDate("PaymentDate"), 
                                        rs.getBigDecimal("PaymentAmout"), 
                                        rs.getString("PaymentStatus"));
                loanPaymentList.add(loanPaymentToAdd);
            }
        } catch (SQLException e) {
        }
        return loanPaymentList;
    }
    
    public void addALoanPayment(LoanPayment loanPaymentToAdd){
        String sql = "INSERT INTO [LoanPayment](ContractID, PaymentDate, PaymentAmount, PaymentStatus)"
                + " VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, loanPaymentToAdd.getContract().getContractID());
            st.setDate(2, loanPaymentToAdd.getPaymentDate());
            st.setBigDecimal(3, loanPaymentToAdd.getPaymentAmount());
            st.setString(4, loanPaymentToAdd.getPaymentStatus());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public void updateALoanPayment(LoanPayment loanPaymentToUpdate){
        String sql = "UPDATE [LoanPayment] SET "
                + "PaymentDate=?, PaymentAmount=?, PaymentStatus=? WHERE LoanPaymentID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, loanPaymentToUpdate.getPaymentDate());
            st.setBigDecimal(2, loanPaymentToUpdate.getPaymentAmount());
            st.setString(3, loanPaymentToUpdate.getPaymentStatus());
            st.setInt(4, loanPaymentToUpdate.getLoanPaymentID());
            st.executeUpdate();
            System.out.println(sql);
        } catch (SQLException e) {
        }
    }

}
