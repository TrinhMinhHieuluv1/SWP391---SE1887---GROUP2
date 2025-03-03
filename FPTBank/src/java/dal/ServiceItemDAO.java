package dal;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ServiceItem;

public class ServiceItemDAO extends DBContext {

    public List<ServiceItem> selectAllServiceItem() {
        List<ServiceItem> serviceItemList = new ArrayList<>();
        String sql = "SELECT * FROM [ServiceItem]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ServiceItem serviceItemToAdd
                        = new ServiceItem(rs.getInt("ServiceItemID"),
                                rs.getString("ServiceItemName"),
                                rs.getBigDecimal("MaxAmount"),
                                rs.getInt("MaxPeriod"),
                                rs.getInt("MinCreditScore"),
                                rs.getFloat("LatePaymentRate"),
                                rs.getBigDecimal("MinAmount"),
                                rs.getInt("MinPeriod"),
                                rs.getFloat("EarlyWithdrawRate"),
                                rs.getFloat("InterestRate"),
                                rs.getString("Type"),
                                rs.getBoolean("Status"));
                serviceItemList.add(serviceItemToAdd);
            }
        } catch (SQLException e) {
        }
        return serviceItemList;
    }

    public ServiceItem selectAServiceItemByID(int serviceItemIDToSearch) {
        String sql = "SELECT * FROM [ServiceItem] WHERE (ServiceItemID=?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, serviceItemIDToSearch);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ServiceItem serviceItemToAdd
                        = new ServiceItem(rs.getInt("ServiceItemID"),
                                rs.getString("ServiceItemName"),
                                rs.getBigDecimal("MaxAmount"),
                                rs.getInt("MaxPeriod"),
                                rs.getInt("MinCreditScore"),
                                rs.getFloat("LatePaymentRate"),
                                rs.getBigDecimal("MinAmount"),
                                rs.getInt("MinPeriod"),
                                rs.getFloat("EarlyWithdrawRate"),
                                rs.getFloat("InterestRate"),
                                rs.getString("Type"),
                                rs.getBoolean("Status"));
                return serviceItemToAdd;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<ServiceItem> selectAllServiceItemByConditions() {
        List<ServiceItem> serviceItemList = new ArrayList<>();
        String sql = "SELECT * FROM [ServiceItem]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ServiceItem serviceItemToAdd
                        = new ServiceItem(rs.getInt("ServiceItemID"),
                                rs.getString("ServiceItemName"),
                                rs.getBigDecimal("MaxAmount"),
                                rs.getInt("MaxPeriod"),
                                rs.getInt("MinCreditScore"),
                                rs.getFloat("LatePaymentRate"),
                                rs.getBigDecimal("MinAmount"),
                                rs.getInt("MinPeriod"),
                                rs.getFloat("EarlyWithdrawRate"),
                                rs.getFloat("InterestRate"),
                                rs.getString("Type"),
                                rs.getBoolean("Status"));
                serviceItemList.add(serviceItemToAdd);
            }
        } catch (SQLException e) {
        }
        return serviceItemList;
    }

    public void addAServiceItem(ServiceItem serviceItemToAdd) {
        String sql = "INSERT INTO [ServiceItem] (ServiceItemName, MaxAmount, MaxPeriod, MinCreditScore, LatePaymentRate, MinAmount, MinPeriod, EarlyWithdrawRate, InterestRate, Type)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, serviceItemToAdd.getServiceItemName());
            st.setBigDecimal(2, serviceItemToAdd.getMaxAmount());
            st.setInt(3, serviceItemToAdd.getMaxPeriod());
            st.setInt(4, serviceItemToAdd.getMinCreditScore());
            st.setFloat(5, serviceItemToAdd.getLatePaymentRate());
            st.setBigDecimal(6, serviceItemToAdd.getMinAmount());
            st.setInt(7, serviceItemToAdd.getMinPeriod());
            st.setFloat(8, serviceItemToAdd.getEarlyWithdrawRate());
            st.setFloat(9, serviceItemToAdd.getInterestRate());
            st.setString(10, serviceItemToAdd.getType());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public void updateAServiceItem(ServiceItem serviceItemToUpdate){
        String sql = "UPDATE [ServiceItem] SET "
                + "ServiceItemName=?, MaxAmount=?, MaxPeriod=?, MinCreditScore=?, LatePaymentRate=?, MinAmount=?, MinPeriod=?, EarlyWithdrawRate=?, InterestRate=? WHERE ServiceItemID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, serviceItemToUpdate.getServiceItemName());
            st.setBigDecimal(2, serviceItemToUpdate.getMaxAmount());
            st.setInt(3, serviceItemToUpdate.getMaxPeriod());
            st.setInt(4, serviceItemToUpdate.getMinCreditScore());
            st.setFloat(5, serviceItemToUpdate.getLatePaymentRate());
            st.setBigDecimal(6, serviceItemToUpdate.getMinAmount());
            st.setInt(7, serviceItemToUpdate.getMinPeriod());
            st.setFloat(8, serviceItemToUpdate.getEarlyWithdrawRate());
            st.setFloat(9, serviceItemToUpdate.getInterestRate());
            st.setInt(10, serviceItemToUpdate.getServiceItemID());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {
        ServiceItemDAO sidao = new ServiceItemDAO();
//        ServiceItem siToAdd = new ServiceItem(0, "Saving 5", BigDecimal.valueOf(1000000000), 12, 100, 10, BigDecimal.ZERO, 0, 0, 0, "Unsecured Loan", true);
        ServiceItem siTo = sidao.selectAServiceItemByID(12);
        siTo.setServiceItemName("Secured Loan 4");
        sidao.updateAServiceItem(siTo);
        for (ServiceItem si : sidao.selectAllServiceItem()) {
            System.out.println(si.getServiceItemName());
        }
    }
}
