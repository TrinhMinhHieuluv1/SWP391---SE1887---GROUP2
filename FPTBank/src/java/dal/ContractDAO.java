package dal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.Contract;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
                        rs.getDate("CreatedAt"),
                        rs.getFloat("InsuranceCoverage"));
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
                        rs.getDate("CreatedAt"),
                        rs.getFloat("InsuranceCoverage"));

                return contractToAdd;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<Contract> selectContractListWithConditions(int CustomerID, String filterType, String filterMonthlyPayment, int filterStatus, String sortBy) {
        List<Contract> contractList = new ArrayList<>();
        String sql = "SELECT * FROM [Contract] WHERE (1=1)";
        if (CustomerID != 0) {
            sql = sql + " AND (CustomerID= " + CustomerID + ")";
        }
        if (filterType != null && !filterType.isEmpty() && !filterType.equals("none")) {
            sql = sql + " AND (Type='" + filterType + "')";
        }
        if (filterMonthlyPayment != null && !filterMonthlyPayment.equals("none")) {
            switch (filterMonthlyPayment) {
                case "nomonthlypayment":
                    sql = sql + " AND (MonthlyPayment=0)";
                    break;
                case "fixed":
                    sql = sql + " AND (MonthlyPaymentType='Fixed')";
                    break;
                case "reducing":
                    sql = sql + " AND (MonthlyPaymentType='Reducing Balance')";
                    break;
            }
        }
        if (filterStatus != 0) {
            sql = sql + " AND (StatusID=" + filterStatus + ")";
        }
        if (sortBy != null && !sortBy.isEmpty()) {
            switch (sortBy) {
                case "AmountASC":
                    sql = sql + " ORDER BY Amount ASC";
                    break;
                case "AmountDESC":
                    sql = sql + " ORDER BY Amount DESC";
                    break;
                case "PeriodASC":
                    sql = sql + " ORDER BY Period ASC";
                    break;
                case "PeriodDESC":
                    sql = sql + " ORDER BY Period DESC";
                    break;
                case "CreatedAtAC":
                    sql = sql + " ORDER BY CreatedAt ASC";
                    break;
                case "CreatedAtDESC":
                    sql = sql + " ORDER BY CreatedAt DESC";
                    break;
            }
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
                        rs.getDate("CreatedAt"),
                        rs.getFloat("InsuranceCoverage"));
                contractList.add(contractToAdd);
            }
        } catch (SQLException e) {
        }
        return contractList;
    }

    public int addAContract(Contract contractToAdd) {
        String sql = "INSERT INTO [Contract] (CustomerID, Amount, Period, LatePaymentRate, EarlyWithdrawRate, InterestRate, Type, Description, AssetID, SalaryID, InsuranceID, MonthlyPayment, MonthlyPaymentType, StatusID, InsuranceCoverage)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (contractToAdd.getType().equals("Secured Loan")) {
                st.setInt(1, contractToAdd.getCustomer().getCustomerId());
                st.setBigDecimal(2, contractToAdd.getAmount());
                st.setInt(3, contractToAdd.getPeriod());
                st.setFloat(4, contractToAdd.getLatePaymentRate());
                st.setNull(5, Types.FLOAT);
                st.setFloat(6, contractToAdd.getInterestRate());
                st.setString(7, contractToAdd.getType());
                st.setString(8, contractToAdd.getDescription());
                st.setInt(9, contractToAdd.getAsset().getId());
                st.setNull(10, Types.INTEGER);
                st.setInt(11, contractToAdd.getInsurance().getInsuranceID());
                st.setBoolean(12, contractToAdd.isMonthlyPayment());
                if (contractToAdd.isMonthlyPayment()) {
                    st.setString(13, contractToAdd.getMonthlyPaymentType());
                } else {
                    st.setNull(13, Types.NVARCHAR);
                }
                st.setInt(14, 1);
                st.setFloat(15, contractToAdd.getInsuranceCoverage());
                st.executeUpdate();
                try {
                    ResultSet generatedKeys = st.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                } catch (SQLException e) {
                }
            } else if (contractToAdd.getType().equals("Unsecured Loan")) {
                st.setInt(1, contractToAdd.getCustomer().getCustomerId());
                st.setBigDecimal(2, contractToAdd.getAmount());
                st.setInt(3, contractToAdd.getPeriod());
                st.setFloat(4, contractToAdd.getLatePaymentRate());
                st.setNull(5, Types.FLOAT);
                st.setFloat(6, contractToAdd.getInterestRate());
                st.setString(7, contractToAdd.getType());
                st.setString(8, contractToAdd.getDescription());
                st.setNull(9, Types.INTEGER);
                st.setInt(10, contractToAdd.getSalary().getId());
                st.setInt(11, contractToAdd.getInsurance().getInsuranceID());
                st.setBoolean(12, contractToAdd.isMonthlyPayment());
                if (contractToAdd.isMonthlyPayment()) {
                    st.setString(13, contractToAdd.getMonthlyPaymentType());
                } else {
                    st.setNull(13, Types.NVARCHAR);
                }
                st.setInt(14, 1);
                st.setFloat(15, contractToAdd.getInsuranceCoverage());
                st.executeUpdate();
                try {
                    ResultSet generatedKeys = st.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                } catch (SQLException e) {
                }
            }
        } catch (SQLException e) {

        }
        return -1;
    }

    public void updateAContract(Contract ContractToUpdate) {
        String sql = "UPDATE [Contract] SET Amount=?, Period=?, "
                + "LatePaymentRate=?, EarlyWithdrawRate=?, InterestRate=?, "
                + "Description=?, AssetID=?, SalaryID=?, InsuranceID=?, "
                + "MonthlyPayment=?, MonthlyPaymentType=?, StatusID=?, InsuranceCoverage=? WHERE ContractID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (ContractToUpdate.getType().equals("Secured Loan")) {
                st.setBigDecimal(1, ContractToUpdate.getAmount());
                st.setInt(2, ContractToUpdate.getPeriod());
                st.setFloat(3, ContractToUpdate.getLatePaymentRate());
                st.setNull(4, Types.FLOAT);
                st.setFloat(5, ContractToUpdate.getInterestRate());
                st.setString(6, ContractToUpdate.getDescription());
                st.setInt(7, ContractToUpdate.getAsset().getId());
                st.setNull(8, Types.INTEGER);
                st.setInt(9, ContractToUpdate.getInsurance().getInsuranceID());
                st.setBoolean(10, ContractToUpdate.isMonthlyPayment());
                if (ContractToUpdate.isMonthlyPayment()) {
                    st.setString(11, ContractToUpdate.getMonthlyPaymentType());
                } else {
                    st.setNull(11, Types.NVARCHAR);
                }
                st.setInt(12, ContractToUpdate.getStatusID());
                st.setFloat(13, ContractToUpdate.getInsuranceCoverage());
                st.setInt(14, ContractToUpdate.getContractID());
            } else {
                st.setBigDecimal(1, ContractToUpdate.getAmount());
                st.setInt(2, ContractToUpdate.getPeriod());
                st.setFloat(3, ContractToUpdate.getLatePaymentRate());
                st.setNull(4, Types.FLOAT);
                st.setFloat(5, ContractToUpdate.getInterestRate());
                st.setString(6, ContractToUpdate.getDescription());
                st.setNull(7, Types.INTEGER);
                st.setInt(8, ContractToUpdate.getSalary().getId());
                st.setInt(9, ContractToUpdate.getInsurance().getInsuranceID());
                st.setBoolean(10, ContractToUpdate.isMonthlyPayment());
                if (ContractToUpdate.isMonthlyPayment()) {
                    st.setString(11, ContractToUpdate.getMonthlyPaymentType());
                } else {
                    st.setNull(11, Types.NVARCHAR);
                }
                st.setInt(12, ContractToUpdate.getStatusID());
                st.setFloat(13, ContractToUpdate.getInsuranceCoverage());
                st.setInt(14, ContractToUpdate.getContractID());
            }
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public boolean updateStatus(int contractID, int statusID) {
        String sql = "UPDATE [Contract] SET StatusID=? WHERE ContractID=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, statusID);
            st.setInt(2, contractID);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        ContractDAO ctdao = new ContractDAO();
        for (Contract ct : ctdao.selectContractListWithConditions(2, null, null, 0, null)) {
            System.out.println(ct.getContractID());
        }
    }
}
