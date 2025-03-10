package dal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.Contract;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Insurance;

public class ContractDAO extends DBContext {

    CustomerDAO cdao = new CustomerDAO();
    AssetDAO adao = new AssetDAO();
    SalaryDAO sdao = new SalaryDAO();
    InsuranceDAO idao = new InsuranceDAO();

    public List<Contract> selectAllContract() {
        List<Contract> contractList = new ArrayList<>();
        String sql = "SELECT * FROM [Contract]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Contract contractToAdd = new Contract(rs.getInt("ContractID"),
                        cdao.getCustomerByID(rs.getInt("CustomerID")),
                        rs.getBigDecimal("Amount"),
                        rs.getInt("Period"),
                        rs.getFloat("LatePaymentRate"),
                        rs.getFloat("EarlyWithdrawRate"),
                        rs.getFloat("InterestRate"),
                        rs.getString("Type"),
                        rs.getString("Description"),
                        adao.getAssetById(rs.getInt("AssetID")),
                        sdao.getSalaryById(rs.getInt("SalaryID")),
                        idao.getInsuranceByID(rs.getInt("InsuranceID")),
                        rs.getBoolean("MonthlyPayment"),
                        rs.getString("MonthlyPaymentType"),
                        rs.getInt("StatusID"),
                        rs.getDate("CreatedAt"));
                contractList.add(contractToAdd);
            }
        } catch (SQLException e) {
        }
        return contractList;
    }

    public Contract selectAContractByID(int contractID) {
        String sql = "SELECT * FROM [Contract] WHERE ContractID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, contractID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Contract contractToAdd = new Contract(rs.getInt("ContractID"),
                        cdao.getCustomerByID(rs.getInt("CustomerID")),
                        rs.getBigDecimal("Amount"),
                        rs.getInt("Period"),
                        rs.getFloat("LatePaymentRate"),
                        rs.getFloat("EarlyWithdrawRate"),
                        rs.getFloat("InterestRate"),
                        rs.getString("Type"),
                        rs.getString("Description"),
                        adao.getAssetById(rs.getInt("AssetID")),
                        sdao.getSalaryById(rs.getInt("SalaryID")),
                        idao.getInsuranceByID(rs.getInt("InsuranceID")),
                        rs.getBoolean("MonthlyPayment"),
                        rs.getString("MonthlyPaymentType"),
                        rs.getInt("StatusID"),
                        rs.getDate("CreatedAt"));
                return contractToAdd;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<Contract> selectContractListWithConditions(int CustomerID, String Type, Boolean MonthlyPayment, int StatusID) {
        List<Contract> contractList = new ArrayList<>();
        String sql = "SELECT * FROM [Contract] WHERE (1=1)";
        if (CustomerID != 0) {
            sql = sql + " AND (CustomerID= " + CustomerID + ")";
        }
        if (Type != null && !Type.isEmpty()) {
            sql = sql + " AND (Type=" + Type + ")";
        }
        if (MonthlyPayment != null) {
            if (MonthlyPayment) {
                sql = sql + " AND (MonthlyPayment=1)";
            } else {
                sql = sql + " AND (MonthlyPayment=0)";
            }
        }
        if (StatusID != 0) {
            sql = sql + " AND (StatusID=" + StatusID + ")";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Contract contractToAdd = new Contract(rs.getInt("ContractID"),
                        cdao.getCustomerByID(rs.getInt("CustomerID")),
                        rs.getBigDecimal("Amount"),
                        rs.getInt("Period"),
                        rs.getFloat("LatePaymentRate"),
                        rs.getFloat("EarlyWithdrawRate"),
                        rs.getFloat("InterestRate"),
                        rs.getString("Type"),
                        rs.getString("Description"),
                        adao.getAssetById(rs.getInt("AssetID")),
                        sdao.getSalaryById(rs.getInt("SalaryID")),
                        idao.getInsuranceByID(rs.getInt("InsuranceID")),
                        rs.getBoolean("MonthlyPayment"),
                        rs.getString("MonthlyPaymentType"),
                        rs.getInt("StatusID"),
                        rs.getDate("CreatedAt"));
                contractList.add(contractToAdd);
            }
        } catch (SQLException e) {
        }
        return contractList;
    }

    public void addAContract(Contract contractToAdd) {
        String sql = "INSERT INTO [Contract] (CustomerID, Amount, Period, LatePaymentRate, EarlyWithdrawRate, InterestRate, Type, Description, AssetID, SalaryID, InsuranceID, MonthlyPayment, MonthlyPaymentType, StatusID)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, contractToAdd.getCustomer().getCustomerId());
            st.setBigDecimal(2, contractToAdd.getAmount());
            st.setInt(3, contractToAdd.getPeriod());
            st.setFloat(4, contractToAdd.getLatePaymentRate());
            st.setFloat(5, contractToAdd.getEarlyWithdrawRate());
            st.setFloat(6, contractToAdd.getInterestRate());
            st.setString(7, contractToAdd.getType());
            st.setString(8, contractToAdd.getDescription());
            st.setInt(9, contractToAdd.getAsset().getId());
            st.setInt(10, contractToAdd.getSalary().getId());
            st.setInt(11, contractToAdd.getInsurance().getInsuranceID());
            st.setBoolean(12, contractToAdd.isMonthlyPayment());
            st.setString(13, contractToAdd.getMonthlyPaymentType());
            st.setInt(14, contractToAdd.getStatusID());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void updateAContract(Contract ContractToUpdate) {
        String sql = "UPDATE [Contract] SET Amount=?, Period=?, "
                + "LatePaymentRate=?, EarlyWithdrawRate=?, InterestRate=?, "
                + "Description=?, AssetID=?, SalaryID=?, InsuranceID=?, "
                + "MonthlyPayment=?, MonthlyPaymentType=?, StatusID=? WHERE ContractID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBigDecimal(1, ContractToUpdate.getAmount());
            st.setInt(2, ContractToUpdate.getPeriod());
            st.setFloat(3, ContractToUpdate.getLatePaymentRate());
            st.setFloat(4, ContractToUpdate.getEarlyWithdrawRate());
            st.setFloat(5, ContractToUpdate.getInterestRate());
            st.setString(6, ContractToUpdate.getDescription());
            st.setInt(7, ContractToUpdate.getAsset().getId());
            st.setInt(8, ContractToUpdate.getSalary().getId());
            st.setInt(9, ContractToUpdate.getInsurance().getInsuranceID());
            st.setBoolean(10, ContractToUpdate.isMonthlyPayment());
            st.setString(11, ContractToUpdate.getMonthlyPaymentType());
            st.setInt(12, ContractToUpdate.getStatusID());
            st.setInt(13, ContractToUpdate.getContractID());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {
        ContractDAO ctdao = new ContractDAO();
        CustomerDAO cdao = new CustomerDAO();
        AssetDAO adao = new AssetDAO();
        SalaryDAO sdao = new SalaryDAO();
        InsuranceDAO idao = new InsuranceDAO();
//        Contract contractToAdd = new Contract(0, cdao.getCustomerByID(1), BigDecimal.valueOf(1000000000), 12, 1, 0, 10, "Secured Loan", "Contract 1: Secured Loan", adao.getAssetById(1), sdao.getSalaryById(1), idao.getInsuranceByID(1), false, "Fixed", 1, null);
//        ctdao.addAContract(contractToAdd);
        Contract contract = ctdao.selectAContractByID(11);
        contract.setInterestRate(20);
        System.out.println(contract.getInterestRate());
        ctdao.updateAContract(contract);
        for (Contract c : ctdao.selectAllContract()) {
            System.out.println(c.getDescription());
        }
    }
}
