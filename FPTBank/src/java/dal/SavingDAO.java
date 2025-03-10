/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.SavingsAccount;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class SavingDAO extends DBContext {

    public List<SavingsAccount> getAllSavingsAccounts() {
        List<SavingsAccount> accounts = new ArrayList<>();
        String sql = "SELECT * FROM SavingCalculator";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                accounts.add(new SavingsAccount(
                        rs.getInt("id"),
                        rs.getInt("term"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("termUnit"),
                        rs.getDouble("deposit_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getDouble("interest_per_term"),
                        rs.getDouble("total_amount_at_maturity"),
                        rs.getTimestamp("createDate"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public boolean addSavingsAccount(SavingsAccount account) {
        String sql = "INSERT INTO SavingCalculator (term, name, email, termUnit, deposit_amount, interest_rate, interest_per_term, total_amount_at_maturity, createDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, account.getTerm());
            st.setString(2, account.getName());
            st.setString(3, account.getEmail());
            st.setString(4, account.getTermUnit());
            st.setDouble(5, account.getDeposit_amount());
            st.setDouble(6, account.getInterest_rate());
            st.setDouble(7, account.getInterest_per_term());
            st.setDouble(8, account.getTotal_amount_at_maturity());
            st.setTimestamp(9, account.getCreateDate());
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getIdByUserName(String name) {
        String sql = "SELECT id FROM SavingCalculator WHERE name = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getSavingsIdByCreateDate(String createDate) {
        String sql = "SELECT id FROM SavingCalculator WHERE createDate = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, createDate);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public SavingsAccount getSavingsById(int accountId) {
        String sql = "SELECT * FROM SavingCalculator WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new SavingsAccount(
                        rs.getInt("id"),
                        rs.getInt("term"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("termUnit"),
                        rs.getDouble("deposit_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getDouble("interest_per_term"),
                        rs.getDouble("total_amount_at_maturity"),
                        rs.getTimestamp("createDate"),
                        rs.getBoolean("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isCreateDateDuplicate(Timestamp createDate) {
        String sql = "SELECT COUNT(*) FROM SavingCalculator WHERE createDate = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setTimestamp(1, createDate);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Nếu có ít nhất một bản ghi trùng, trả về true
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getEmailByID(int accountId) {
        String sql = "SELECT email FROM SavingCalculator WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public boolean updateStatus(int accountId, boolean newStatus) {
        String sql = "UPDATE SavingCalculator SET status = ? WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBoolean(1, newStatus);
            st.setInt(2, accountId);
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<SavingsAccount> getListSAVByPage(int page, int pageSize) {
        ArrayList<SavingsAccount> listSAV = new ArrayList<>();

        String sql = "select * from [SavingCalculator] order by [id] offset ? rows fetch next ? rows only";
        // offset ? rows:    Bá»� qua má»™t sá»‘ dÃ²ng dá»±a trÃªn sá»‘ trang.
        // fetch next ? rows only:  Láº¥y tiáº¿p sá»‘ dÃ²ng tÆ°Æ¡ng á»©ng vá»›i pageSize.

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (page - 1) * pageSize);
            stmt.setInt(2, pageSize);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SavingsAccount sav = new SavingsAccount(
                        rs.getInt("id"),
                        rs.getInt("term"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("termUnit"),
                        rs.getDouble("deposit_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getDouble("interest_per_term"),
                        rs.getDouble("total_amount_at_maturity"),
                        rs.getTimestamp("createDate"),
                        rs.getBoolean("status")
                );
                listSAV.add(sav);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listSAV;
    }

    public List<SavingsAccount> searchByNameSav(String keyword, int page, int pageSize) {
        List<SavingsAccount> list = new ArrayList<>();
        String sql = "SELECT * FROM SavingCalculator WHERE name LIKE ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + keyword + "%");
            st.setInt(2, (page - 1) * pageSize);
            st.setInt(3, pageSize);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new SavingsAccount(
                        rs.getInt("id"),
                        rs.getInt("term"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("termUnit"),
                        rs.getDouble("deposit_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getDouble("interest_per_term"),
                        rs.getDouble("total_amount_at_maturity"),
                        rs.getTimestamp("createDate"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SavingsAccount> searchByDateSav(String createDate, int page, int pageSize) {
        List<SavingsAccount> list = new ArrayList<>();
        String sql = "SELECT * FROM SavingCalculator WHERE CAST(createDate AS DATE) = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, createDate);
            st.setInt(2, (page - 1) * pageSize);
            st.setInt(3, pageSize);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new SavingsAccount(
                        rs.getInt("id"),
                        rs.getInt("term"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("termUnit"),
                        rs.getDouble("deposit_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getDouble("interest_per_term"),
                        rs.getDouble("total_amount_at_maturity"),
                        rs.getTimestamp("createDate"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

   public List<SavingsAccount> sortByAmountSav(int page, int pageSize, boolean isDescending) {
    List<SavingsAccount> list = new ArrayList<>();
    String sql = "SELECT * FROM SavingCalculator ORDER BY deposit_amount " 
                 + (isDescending ? "DESC" : "ASC") 
                 + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, (page - 1) * pageSize);
        st.setInt(2, pageSize);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(new SavingsAccount(
                    rs.getInt("id"),
                    rs.getInt("term"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("termUnit"),
                    rs.getDouble("deposit_amount"),
                    rs.getDouble("interest_rate"),
                    rs.getDouble("interest_per_term"),
                    rs.getDouble("total_amount_at_maturity"),
                    rs.getTimestamp("createDate"),
                    rs.getBoolean("status")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


public List<SavingsAccount> sortByDateSav(int page, int pageSize, boolean ascending) {
    List<SavingsAccount> list = new ArrayList<>();
    String sql = "SELECT * FROM SavingCalculator ORDER BY createDate " 
               + (ascending ? "ASC" : "DESC") 
               + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, (page - 1) * pageSize);
        st.setInt(2, pageSize);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(new SavingsAccount(
                    rs.getInt("id"),
                    rs.getInt("term"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("termUnit"),
                    rs.getDouble("deposit_amount"),
                    rs.getDouble("interest_rate"),
                    rs.getDouble("interest_per_term"),
                    rs.getDouble("total_amount_at_maturity"),
                    rs.getTimestamp("createDate"),
                    rs.getBoolean("status")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

    public List<SavingsAccount> findByStatusSav(boolean status, int page, int pageSize) {
        List<SavingsAccount> list = new ArrayList<>();
        String sql = "SELECT * FROM SavingCalculator WHERE status = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBoolean(1, status);
            st.setInt(2, (page - 1) * pageSize);
            st.setInt(3, pageSize);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new SavingsAccount(
                        rs.getInt("id"),
                        rs.getInt("term"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("termUnit"),
                        rs.getDouble("deposit_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getDouble("interest_per_term"),
                        rs.getDouble("total_amount_at_maturity"),
                        rs.getTimestamp("createDate"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalSavingsAfterSearching(String keyword) {
        String sql = "SELECT COUNT(*) FROM SavingCalculator WHERE name LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getTotalSavingsAfterDateSearch(String createDate) {
        String sql = "SELECT COUNT(*) FROM SavingCalculator WHERE CAST(createDate AS DATE) = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, createDate);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

 public int getTotalSavingsAfterSortingByAmount(String sortOrder) {
    String sql = "SELECT COUNT(*) FROM SavingCalculator";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return 0;
}


public int getTotalSavingsAfterSortingByDate(String ascending) {
    String sql = "SELECT COUNT(*) FROM SavingCalculator";

    try (PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return 0;
}


public int getTotalSavingsByStatus(String status) {
    String sql = "SELECT COUNT(*) FROM SavingCalculator WHERE status = ?";
    int total = 0;

    try (PreparedStatement st = connection.prepareStatement(sql)) {
        // Chuyển đổi chuỗi thành boolean
        boolean statusBool = Boolean.parseBoolean(status);
        st.setBoolean(1, statusBool);

        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}

    public static void main(String[] args) {
        SavingDAO dao = new SavingDAO();

        List<SavingsAccount> b = dao.getAllSavingsAccounts();
        for (SavingsAccount savingsAccount : b) {
            System.out.println(savingsAccount.getName());
        }

        java.sql.Timestamp timestamps = java.sql.Timestamp.valueOf("2025-03-01 21:44:37.503");
        // Thêm tài khoản tiết kiệm
        SavingsAccount newAccount = new SavingsAccount(3, "t", "ew@df.com", "dsd", 5, 7, 4, 4, timestamps);
        boolean isAdded = dao.addSavingsAccount(newAccount);
        System.out.println("Account added: " + isAdded);
        // Lấy danh sách tài khoản tiết kiệm
        List<SavingsAccount> accounts = dao.getAllSavingsAccounts();
        System.out.println("Total accounts: " + accounts.size());

        boolean isDuplicate = dao.isCreateDateDuplicate(timestamps);
        System.out.println("Is createDate duplicate? " + isDuplicate);

    }

}
