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

    public void updateAServiceItem(ServiceItem serviceItemToUpdate) {
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

    public List<ServiceItem> getServiceItemList(BigDecimal Amount, String Type) {
        List<ServiceItem> serviceItemList = new ArrayList<>();
        String sql = "";
        if (Type.equals("Saving")) {
            sql = "SELECT * FROM [ServiceItem] WHERE (Type=?) AND (MinAmount<=?) AND (Status=1)";
        } else {
            sql = "SELECT * FROM [ServiceItem] WHERE (Type=?) AND (MaxAmount>=?) AND (Status=1)";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Type);
            st.setBigDecimal(2, Amount);
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

    //Duy
    // kiem tra su ton tai cua service name
    public boolean isFieldExistsToAdd(String fieldName, String value) {
        String query = "SELECT COUNT(*) FROM [ServiceItem] WHERE " + fieldName + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, value);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // kiem tra su ton tai cua max/min amount
    public boolean isFieldExistsToAdd2(String fieldName, BigDecimal value) {
        String query = "SELECT COUNT(*) FROM [ServiceItem] WHERE " + fieldName + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBigDecimal(1, value);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // kiem tra su ton tai cua max/min period
    public boolean isFieldExistsToAdd3(String fieldName, int value) {
        String query = "SELECT COUNT(*) FROM [ServiceItem] WHERE " + fieldName + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, value);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Add service item
    public int addServiceItemReturnRow(ServiceItem serviceItemToAdd) {
        // Kiểm tra trùng lặp service item name
        if (isFieldExistsToAdd("ServiceItemName", serviceItemToAdd.getServiceItemName())) {
            return 2;
        }

        // nếu là loan thì k dc trùng max amount và max period
        if (serviceItemToAdd.getType().equalsIgnoreCase("Secured Loan") || serviceItemToAdd.getType().equalsIgnoreCase("Unsecured Loan")) {
            if (isFieldExistsToAdd2("MaxAmount", serviceItemToAdd.getMaxAmount()) && isFieldExistsToAdd3("MaxPeriod", serviceItemToAdd.getMaxPeriod())) {
                return 3;
            }
        }

        // nếu là saving thì k đc trùng min amount và min period
        if (serviceItemToAdd.getType().equalsIgnoreCase("Saving")) {
            if (isFieldExistsToAdd2("MinAmount", serviceItemToAdd.getMinAmount()) && isFieldExistsToAdd3("MinPeriod", serviceItemToAdd.getMinPeriod())) {
                return 4;
            }
        }

        String sql = "INSERT INTO [ServiceItem] (ServiceItemName, MaxAmount, MaxPeriod, MinCreditScore, LatePaymentRate, MinAmount, MinPeriod, EarlyWithdrawRate, InterestRate, Type, Status)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
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
            st.setBoolean(11, serviceItemToAdd.isStatus());
            int row = st.executeUpdate();

            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<ServiceItem> sortServiceItemsByInterestRate(String sortOrder, int page, int pageSize) {
        List<ServiceItem> listSItems = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[ServiceItem] "
                + "ORDER BY InterestRate " + (sortOrder.equalsIgnoreCase("asc") ? "ASC" : "DESC") + " "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, (page - 1) * pageSize);
            st.setInt(2, pageSize);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ServiceItem serviceItem = new ServiceItem(
                            rs.getInt("ServiceItemID"),
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
                            rs.getBoolean("Status")
                    );
                    listSItems.add(serviceItem);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error sorting ServiceItems: " + e.getMessage());
            throw new RuntimeException("Database sort failed", e);
        }

        return listSItems;
    }

    public void updateStatusServiceItem(ServiceItem serviceItemToUpdate) {
        String sql = "UPDATE [ServiceItem] SET Status = ? WHERE ServiceItemID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setBoolean(1, serviceItemToUpdate.isStatus());
            st.setInt(2, serviceItemToUpdate.getServiceItemID());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No ServiceItem found with ID: " + serviceItemToUpdate.getServiceItemID());
            }
        } catch (SQLException e) {

        }
    }

    public List<ServiceItem> filterListServiceItems(String filterType, String filterValue, int page, int pageSize) {
        List<ServiceItem> listSItems = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[ServiceItem] "
                + "WHERE " + filterType + " = ? "
                + "ORDER BY [ServiceItemID] "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, filterValue);
            st.setInt(2, (page - 1) * pageSize);
            st.setInt(3, pageSize);
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
                listSItems.add(serviceItemToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSItems;
    }

    public int getTotalServiceItemsAfterFilterType(String filterType, String filterValue) {
        String sql = " SELECT COUNT(*) FROM [dbo].[ServiceItem] "
                + "WHERE " + filterType + " = ? ";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, filterValue);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<ServiceItem> filterListServiceItemsByStatus(String filterType, int filterValue, int page, int pageSize) {
        List<ServiceItem> listSItems = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[ServiceItem] "
                + "WHERE " + filterType + " = ? "
                + "ORDER BY [ServiceItemID] "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, filterValue);
            st.setInt(2, (page - 1) * pageSize);
            st.setInt(3, pageSize);
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
                listSItems.add(serviceItemToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSItems;
    }

    public int getTotalServiceItemsAfterFilterStatus(String filterType, int filterValue) {
        String sql = " SELECT COUNT(*) FROM [dbo].[ServiceItem] "
                + "WHERE " + filterType + " = ? ";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, filterValue);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<ServiceItem> searchServiceItems(String keyword, int page, int pageSize) {
        List<ServiceItem> result = new ArrayList<>();
        String sql = """
                     SELECT * FROM [ServiceItem] WHERE ServiceItemName LIKE ? AND Type LIKE ?  ORDER BY [ServiceItemID] 
                              OFFSET ? ROWS FETCH NEXT ? ROWS ONLY""";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, "%" + keyword + "%");
            st.setString(2, "%" + keyword + "%");
            st.setInt(3, (page - 1) * pageSize);
            st.setInt(4, pageSize);

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
                result.add(serviceItemToAdd);
            }
        } catch (SQLException e) {
            System.err.println("Error searching ServiceItems: " + e.getMessage());
            throw new RuntimeException("Database search failed", e);
        }

        return result;
    }

    public int getTotalServiceItemsAfterSearching(String keyword) {
        String sql = """
                     SELECT COUNT(*) FROM [ServiceItem] WHERE ServiceItemName LIKE ? AND Type LIKE ? """;

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, "%" + keyword + "%");
            st.setString(2, "%" + keyword + "%");

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public ArrayList<ServiceItem> getListServiceItemsByPage(int page, int pageSize) {
        ArrayList<ServiceItem> serviceItemList = new ArrayList<>();

        String sql = "SELECT * FROM [ServiceItem] order by [ServiceItemID] offset ? rows fetch next ? rows only";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (page - 1) * pageSize);
            stmt.setInt(2, pageSize);
            ResultSet rs = stmt.executeQuery();
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return serviceItemList;
    }
   
}
