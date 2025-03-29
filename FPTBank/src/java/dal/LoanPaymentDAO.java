package dal;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.LoanPayment;
import java.sql.Types;

public class LoanPaymentDAO extends DBContext {

    ContractDAO ctdao = new ContractDAO();

    public List<LoanPayment> selectAllLoanPayment() {
        List<LoanPayment> loanPaymentList = new ArrayList<>();
        String sql = "SELECT * FROM [LoanPayment]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LoanPayment loanPaymentToAdd
                        = new LoanPayment(rs.getInt("LoanPaymentID"),
                                ctdao.selectAContractByID(rs.getInt("ContractID")),
                                rs.getDate("PaymentDate"),
                                rs.getDate("PaidDate"),
                                rs.getBigDecimal("PaymentAmount"),
                                rs.getBigDecimal("LateAmount"),
                                rs.getString("PaymentStatus"));
                loanPaymentList.add(loanPaymentToAdd);
            }
        } catch (SQLException e) {
        }
        return loanPaymentList;
    }

    public LoanPayment selectALoanPaymentByID(int LoanPaymentID) {
        String sql = "SELECT * FROM [LoanPayment] WHERE LoanPaymentID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, LoanPaymentID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LoanPayment loanPaymentToAdd
                        = new LoanPayment(rs.getInt("LoanPaymentID"),
                                ctdao.selectAContractByID(rs.getInt("ContractID")),
                                rs.getDate("PaymentDate"),
                                rs.getDate("PaidDate"),
                                rs.getBigDecimal("PaymentAmount"),
                                rs.getBigDecimal("LateAmount"),
                                rs.getString("PaymentStatus"));
                return loanPaymentToAdd;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<LoanPayment> selectAllLoanPaymentWithConditions(int ContractID, String Status, String SortBy) {
        List<LoanPayment> loanPaymentList = new ArrayList<>();
        String sql = "SELECT * FROM [LoanPayment] WHERE (1=1)";

        if (ContractID != 0) {
            sql = sql + " AND (ContractID=" + ContractID + ")";
        }

        if (Status != null && !Status.isEmpty() && !Status.equals("none")) {
            sql = sql + " AND (PaymentStatus='" + Status + "')";
        }

        if (SortBy != null && !SortBy.isEmpty()) {
            switch (SortBy) {
                case "PaymentDateASC": {
                    sql = sql + " ORDER BY PaymentDate ASC";
                    break;
                }
                case "PaymentDateDESC": {
                    sql = sql + " ORDER BY PaymentDate DESC";
                    break;
                }
                case "PaymentAmountASC": {
                    sql = sql + " ORDER BY PaymentAmount ASC";
                    break;
                }
                case "PaymentAmountDESC": {
                    sql = sql + " ORDER BY PaymentAmount DESC";
                    break;
                }
            }
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LoanPayment loanPaymentToAdd
                        = new LoanPayment(rs.getInt("LoanPaymentID"),
                                ctdao.selectAContractByID(rs.getInt("ContractID")),
                                rs.getDate("PaymentDate"),
                                rs.getDate("PaidDate"),
                                rs.getBigDecimal("PaymentAmount"),
                                rs.getBigDecimal("LateAmount"),
                                rs.getString("PaymentStatus"));
                loanPaymentList.add(loanPaymentToAdd);
            }
        } catch (SQLException e) {
        }
        return loanPaymentList;
    }

    public void addALoanPayment(LoanPayment loanPaymentToAdd) {
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

    public void updateALoanPayment(LoanPayment loanPaymentToUpdate) {
        String sql = "UPDATE [LoanPayment] SET "
                + "PaymentDate=?, PaidDate=?, PaymentAmount=?, LateAmount=?, PaymentStatus=? WHERE LoanPaymentID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, loanPaymentToUpdate.getPaymentDate());
            if (loanPaymentToUpdate.getPaidDate() != null) {
                st.setDate(2, loanPaymentToUpdate.getPaidDate());
            } else {
                st.setNull(2, Types.DATE);
            }
            st.setBigDecimal(3, loanPaymentToUpdate.getPaymentAmount());
            if (loanPaymentToUpdate.getLateAmount() != null) {
                st.setBigDecimal(4, loanPaymentToUpdate.getLateAmount());
            } else {
                st.setNull(4, Types.DECIMAL);
            }
            st.setString(5, loanPaymentToUpdate.getPaymentStatus());
            st.setInt(6, loanPaymentToUpdate.getLoanPaymentID());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public List<LoanPayment> selectAllLoanPaymentUpToDate() {
        List<LoanPayment> loanPaymentList = new ArrayList<>();
        String sql = "SELECT * FROM LoanPayment WHERE PaymentDate <= DATEADD(day, 3, CAST(GETDATE() AS DATE))";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LoanPayment loanPaymentToAdd
                        = new LoanPayment(rs.getInt("LoanPaymentID"),
                                ctdao.selectAContractByID(rs.getInt("ContractID")),
                                rs.getDate("PaymentDate"),
                                rs.getDate("PaidDate"),
                                rs.getBigDecimal("PaymentAmount"),
                                rs.getBigDecimal("LateAmount"),
                                rs.getString("PaymentStatus"));
                loanPaymentList.add(loanPaymentToAdd);
            }
        } catch (SQLException e) {
        }
        return loanPaymentList;
    }
}
