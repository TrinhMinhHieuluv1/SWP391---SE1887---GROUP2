/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import model.LoanAccount;
import model.MonthlyPayment;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class LoanDAO extends DBContext {

    public List<LoanAccount> getAllLoanAccounts() {
        List<LoanAccount> loans = new ArrayList<>();
        String sql = "SELECT * FROM LoanCalculator"; // Lấy tất cả thông tin từ bảng FAQ

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                loans.add(new LoanAccount(rs.getInt("id"),
                        rs.getInt("loan_term"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("repayment_method"),
                        rs.getDouble("loan_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getTimestamp("disbursement_date"),
                        rs.getDouble("total_interest"),
                        rs.getDouble("total_amount_to_pay"),
                        rs.getBoolean("status")
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loans;
    }

    public boolean addLoanAccount(LoanAccount loan) {
        String sql = "INSERT INTO LoanCalculator (name, email, repayment_method, loan_amount, interest_rate, loan_term,total_interest,total_amount_to_pay,disbursement_date) "
                + "VALUES (?, ?, ?, ?, ?, ?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, loan.getName());
            st.setString(2, loan.getEmail());
            st.setString(3, loan.getRepayment_method());
            st.setDouble(4, loan.getLoan_amount());
            st.setDouble(5, loan.getInterest_rate());
            st.setInt(6, loan.getLoan_term());
            st.setDouble(7, loan.getTotal_interest());
            st.setDouble(8, loan.getTotal_amount_to_pay());
            st.setTimestamp(9, loan.getDisbursement_date());

            // Chuyển từ java.util.Date sang SQL Date
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getIdByUserName(String name) {
        String sql = "SELECT id FROM LoanCalculator WHERE name = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id"); // Trả về loanId nếu tìm thấy
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }

    public int getLoanIdByDisbursementDate(String disbursementDate) {
        String sql = "SELECT id FROM LoanCalculator WHERE disbursement_date = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, disbursementDate);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id"); // Trả về loanId nếu tìm thấy
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }

    public LoanAccount getLoanById(int loanId) {
        String sql = "SELECT * FROM LoanCalculator WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, loanId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new LoanAccount(
                        rs.getInt("id"),
                        rs.getInt("loan_term"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("repayment_method"),
                        rs.getDouble("loan_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getTimestamp("disbursement_date"),
                        rs.getDouble("total_interest"),
                        rs.getDouble("total_amount_to_pay"),
                        rs.getBoolean("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MonthlyPayment> calculatePaymentSchedule(int loanId) {
        LoanAccount loan = getLoanById(loanId);
        if (loan == null) {
            System.out.println("Không tìm thấy khoản vay!");
            return new ArrayList<>();
        }

        DecimalFormat df = new DecimalFormat("#,###.##");
        List<MonthlyPayment> monthlyPayments = new ArrayList<>();
        double totalInterest = 0;
        double loanAmount = loan.getLoan_amount();
        double annualRate = loan.getInterest_rate();
        int months = loan.getLoan_term();
        String calculationMethod = loan.getRepayment_method();

        // Lấy ngày giải ngân từ database
        Date today = (Date) loan.getDisbursement_date();

        // Dùng Calendar để cộng tháng vì không dùng LocalDate
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        if ("Trả trên dư nợ giảm dần".equals(calculationMethod)) {
            double principalPerMonth = loanAmount / months;
            double remainingLoan = loanAmount;

            for (int i = 0; i < months; i++) {
                double monthlyInterest = (remainingLoan * (annualRate / 100)) / 12;
                totalInterest += monthlyInterest;
                double totalPaymentPerMonth = principalPerMonth + monthlyInterest;

                // Tính ngày thanh toán bằng cách cộng thêm 1 tháng
                calendar.add(Calendar.MONTH, 1);
                Date paymentDate = (Date) calendar.getTime();

                monthlyPayments.add(new MonthlyPayment(
                        i + 1,
                        df.format(remainingLoan),
                        df.format(principalPerMonth),
                        df.format(monthlyInterest),
                        df.format(totalPaymentPerMonth),
                        paymentDate.toString() // Chuyển Date thành String
                ));

                remainingLoan -= principalPerMonth;
            }
        } else {
            double monthlyInterest = (loanAmount * (annualRate / 100)) / 12;
            totalInterest = monthlyInterest * months;
            double totalPayment = loanAmount + totalInterest;

            for (int i = 0; i < months; i++) {
                // Tính ngày thanh toán bằng cách cộng thêm 1 tháng
                calendar.add(Calendar.MONTH, 1);
                Date paymentDate = (Date) calendar.getTime();

                monthlyPayments.add(new MonthlyPayment(
                        i + 1,
                        df.format(loanAmount - (i * (loanAmount / months))),
                        df.format(loanAmount / months),
                        df.format(monthlyInterest),
                        df.format((loanAmount / months) + monthlyInterest),
                        paymentDate.toString()
                ));
            }
        }

        return monthlyPayments;
    }

    public void insertLoanRepaymentSchedule(int loanId) throws ParseException {
        List<MonthlyPayment> schedule = calculatePaymentSchedule(loanId);
        if (schedule.isEmpty()) {
            System.out.println("Không có lịch trả nợ để thêm!");
            return;
        }

        String sql = "INSERT INTO LoanRepaymentSchedule (loan_id, month, payment_date, remaining_principal, principal_amount, interest_amount, total_payment) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement st = connection.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

            for (MonthlyPayment payment : schedule) {
                st.setInt(1, loanId);
                st.setInt(2, payment.getMonth());
                Date parsedDate = (Date) sdf.parse(payment.getPaymentDate());
                java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
                st.setDate(3, sqlDate);
                st.setBigDecimal(4, new BigDecimal(payment.getRemainingPrincipal().replace(",", "")));
                st.setBigDecimal(5, new BigDecimal(payment.getPrincipal().replace(",", "")));
                st.setBigDecimal(6, new BigDecimal(payment.getInterest().replace(",", "")));
                st.setBigDecimal(7, new BigDecimal(payment.getTotalPayment().replace(",", "")));

                st.addBatch(); // Thêm vào batch để tối ưu insert
            }
            st.executeBatch();
            System.out.println("Lịch trả nợ đã được thêm thành công vào database!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi thêm lịch trả nợ: " + e.getMessage());
        }
    }

    public List<MonthlyPayment> getAllLoanScheduleByLoanId(int loanId) {
        List<MonthlyPayment> monthlyPayments = new ArrayList<>();

        String sql = "SELECT month, payment_date, remaining_principal, principal_amount, interest_amount, total_payment "
                + "FROM LoanRepaymentSchedule WHERE loan_id = ? ORDER BY month ASC";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, loanId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                DecimalFormat df = new DecimalFormat("#,###.##");

                int month = rs.getInt("month");
                Date paymentDate = rs.getDate("payment_date");
                String remainingPrincipal = rs.getBigDecimal("remaining_principal").toString();
                String principalAmount = rs.getBigDecimal("principal_amount").toString();
                String interestAmount = rs.getBigDecimal("interest_amount").toString();
                String totalPayment = rs.getBigDecimal("total_payment").toString();

                MonthlyPayment payment = new MonthlyPayment(
                        month,
                        remainingPrincipal,
                        principalAmount,
                        interestAmount,
                        totalPayment,
                        paymentDate.toString() // Chuyển Date thành String
                );
                monthlyPayments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi lấy lịch trả nợ: " + e.getMessage());
        }

        return monthlyPayments;
    }

    public String getEmailByID(int accountId) {
        String sql = "SELECT email FROM LoanCalculator WHERE id = ?";
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
        String sql = "UPDATE LoanCalculator SET status = ? WHERE id = ?";
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

    public ArrayList<LoanAccount> getListLOANByPage(int page, int pageSize) {
        ArrayList<LoanAccount> listLOAN = new ArrayList<>();

        String sql = "select * from [LoanCalculator] order by [id] offset ? rows fetch next ? rows only";
        // offset ? rows:    Bá»� qua má»™t sá»‘ dÃ²ng dá»±a trÃªn sá»‘ trang.
        // fetch next ? rows only:  Láº¥y tiáº¿p sá»‘ dÃ²ng tÆ°Æ¡ng á»©ng vá»›i pageSize.

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (page - 1) * pageSize);
            stmt.setInt(2, pageSize);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LoanAccount loan = new LoanAccount(
                        rs.getInt("id"),
                        rs.getInt("loan_term"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("repayment_method"),
                        rs.getDouble("loan_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getTimestamp("disbursement_date"),
                        rs.getDouble("total_interest"),
                        rs.getDouble("total_amount_to_pay"),
                        rs.getBoolean("status")
                );
                listLOAN.add(loan);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listLOAN;
    }

    public List<LoanAccount> searchByNameLoan(String keyword, int page, int pageSize) {
        List<LoanAccount> list = new ArrayList<>();
        String sql = "SELECT * FROM LoanCalculator WHERE name LIKE ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + keyword + "%");
            st.setInt(2, (page - 1) * pageSize);
            st.setInt(3, pageSize);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new LoanAccount(
                        rs.getInt("id"),
                        rs.getInt("loan_term"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("repayment_method"),
                        rs.getDouble("loan_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getTimestamp("disbursement_date"),
                        rs.getDouble("total_interest"),
                        rs.getDouble("total_amount_to_pay"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<LoanAccount> searchByDateLoan(String disbursementDate, int page, int pageSize) {
        List<LoanAccount> list = new ArrayList<>();
        String sql = "SELECT * FROM LoanCalculator WHERE CAST(disbursement_date AS DATE) = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, disbursementDate);
            st.setInt(2, (page - 1) * pageSize);
            st.setInt(3, pageSize);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new LoanAccount(
                        rs.getInt("id"),
                        rs.getInt("loan_term"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("repayment_method"),
                        rs.getDouble("loan_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getTimestamp("disbursement_date"),
                        rs.getDouble("total_interest"),
                        rs.getDouble("total_amount_to_pay"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<LoanAccount> sortByAmountLoan(int page, int pageSize, boolean isDescending) {
        List<LoanAccount> list = new ArrayList<>();
        String sql = "SELECT * FROM LoanCalculator ORDER BY loan_amount "
                + (isDescending ? "DESC" : "ASC")
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (page - 1) * pageSize);
            st.setInt(2, pageSize);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new LoanAccount(
                        rs.getInt("id"),
                        rs.getInt("loan_term"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("repayment_method"),
                        rs.getDouble("loan_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getTimestamp("disbursement_date"),
                        rs.getDouble("total_interest"),
                        rs.getDouble("total_amount_to_pay"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<LoanAccount> sortByDateLoan(int page, int pageSize, boolean ascending) {
        List<LoanAccount> list = new ArrayList<>();
        String sql = "SELECT * FROM LoanCalculator ORDER BY disbursement_date "
                + (ascending ? "ASC" : "DESC")
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (page - 1) * pageSize);
            st.setInt(2, pageSize);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new LoanAccount(
                        rs.getInt("id"),
                        rs.getInt("loan_term"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("repayment_method"),
                        rs.getDouble("loan_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getTimestamp("disbursement_date"),
                        rs.getDouble("total_interest"),
                        rs.getDouble("total_amount_to_pay"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<LoanAccount> findByStatusLoan(boolean status, int page, int pageSize) {
        List<LoanAccount> list = new ArrayList<>();
        String sql = "SELECT * FROM LoanCalculator WHERE status = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBoolean(1, status);
            st.setInt(2, (page - 1) * pageSize);
            st.setInt(3, pageSize);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new LoanAccount(
                        rs.getInt("id"),
                        rs.getInt("loan_term"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("repayment_method"),
                        rs.getDouble("loan_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getTimestamp("disbursement_date"),
                        rs.getDouble("total_interest"),
                        rs.getDouble("total_amount_to_pay"),
                        rs.getBoolean("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalLoansAfterSearching(String keyword) {
        String sql = "SELECT COUNT(*) FROM LoanCalculator WHERE name LIKE ?";
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

    public int getTotalLoansAfterDateSearch(String disbursementDate) {
        String sql = "SELECT COUNT(*) FROM LoanCalculator WHERE CAST(disbursement_date AS DATE) = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, disbursementDate);
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

   

   public int getTotalLoansByStatus(String status) {
    String sql = "SELECT COUNT(*) FROM LoanCalculator WHERE status = ?";
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
    LoanDAO l = new LoanDAO();
    
    try {
        // 1. Test searchByNameLoan
        System.out.println("=== Test searchByNameLoan ===");
        List<LoanAccount> nameSearch = l.searchByNameLoan("thoại", 1, 5);
        System.out.println("Found " + nameSearch.size() + " records for name 'John'");
        for (LoanAccount loan : nameSearch) {
            System.out.println("ID: " + loan.getId() + ", Name: " + loan.getName() + 
                             ", Amount: " + loan.getLoan_amount());
        }

        // 2. Test searchByDateLoan
        System.out.println("\n=== Test searchByDateLoan ===");
        List<LoanAccount> dateSearch = l.searchByDateLoan("2025-03-06", 1, 5);
        System.out.println("Found " + dateSearch.size() + " records for date '2025-02-27'");
        for (LoanAccount loan : dateSearch) {
            System.out.println("ID: " + loan.getId() + ", Date: " + loan.getDisbursement_date());
        }

        // 3. Test sortByAmountLoan
        System.out.println("\n=== Test sortByAmountLoan ===");
        List<LoanAccount> amountSort = l.sortByAmountLoan(1, 5, true);
        System.out.println("Sorted by amount descending:");
        for (LoanAccount loan : amountSort) {
            System.out.println("ID: " + loan.getId() + ", Amount: " + loan.getLoan_amount());
        }

        // 4. Test sortByDateLoan
        System.out.println("\n=== Test sortByDateLoan ===");
        List<LoanAccount> dateSort = l.sortByDateLoan(1, 5, true);
        System.out.println("Sorted by date ascending:");
        for (LoanAccount loan : dateSort) {
            System.out.println("ID: " + loan.getId() + ", Date: " + loan.getDisbursement_date());
        }

        // 5. Test findByStatusLoan
        System.out.println("\n=== Test findByStatusLoan ===");
        List<LoanAccount> statusSearch = l.findByStatusLoan(true, 1, 5);
        System.out.println("Found " + statusSearch.size() + " active loans:");
        for (LoanAccount loan : statusSearch) {
            System.out.println("ID: " + loan.getId() + ", Status: " + loan.isStatus());
        }

        // 6. Test count methods
        System.out.println("\n=== Test count methods ===");
        System.out.println("Total loans by name 'John': " + l.getTotalLoansAfterSearching("John"));
        System.out.println("Total loans by date '2025-02-27': " + l.getTotalLoansAfterDateSearch("2025-02-27"));
              System.out.println("Total loans by status 'true': " + l.getTotalLoansByStatus("true"));

    } catch (Exception e) {
        System.out.println("Error during testing: " + e.getMessage());
        e.printStackTrace();
    } finally {
        // Đóng connection nếu cần
        try {
            if (l.connection != null && !l.connection.isClosed()) {
                l.connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

}
