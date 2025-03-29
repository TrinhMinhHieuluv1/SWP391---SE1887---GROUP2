/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import dal.SavingsGoalsDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebServlet;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import model.Customer;
import model.Emails;
import model.SavingsGoal;

/**
 *
 * @author tiend
 */
@WebServlet(name = "FeeSchedulerListener", urlPatterns = {"/feeServlet"})
public class FeeSchedulerListener implements ServletContextListener {

    private ScheduledExecutorService scheduler;
    private CustomerDAO customerDAO = new CustomerDAO();
    private SavingsGoalsDAO savingsGoalsDAO = new SavingsGoalsDAO();
    private final double MAINTENANCE_FEE_INTERATE = 0.0012;
    private Emails emails;
    public static String FEE_MESS_1 = "THU PHI DICH VU SAVING GOAL HANG THANG";
    public static String FEE_MESS_2 = "YEU CAU NAP THEM TIEN DE SU DUNG DICH VU SAVING GOAL";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Khởi tạo scheduler với 1 thread
        scheduler = Executors.newScheduledThreadPool(1);
        // Tác vụ trừ phí định kỳ
        Runnable deductFeeTask = () -> {
            try {
                deductMonthlyFee();
                System.out.println("Đã trừ phí duy trì tài khoản lúc: " + java.time.LocalDateTime.now());
            } catch (Exception e) {
                System.err.println("Lỗi khi trừ phí: " + e.getMessage());
                e.printStackTrace();
            }
        };
        long initialDelay = 0;
        long period = 30;
        scheduler.scheduleAtFixedRate(deductFeeTask, initialDelay, period, TimeUnit.DAYS);
        System.out.println("Đã khởi động lập lịch trừ phí duy trì tài khoản.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Dừng scheduler khi ứng dụng tắt
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            try {
                // Đợi các tác vụ hoàn thành trong 5 giây trước khi buộc dừng
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
            System.out.println("Đã dừng lập lịch trừ phí duy trì tài khoản.");
        }
    }

    private void deductMonthlyFee() {

        for (Customer customer : customerDAO.selectAllCustomer()) {
            deducFeeCustomer(customer);
        }
        System.out.println("Đã trừ phí duy trì từ tất cả các tài khoản: " + System.currentTimeMillis());

    }

    private void deducFeeCustomer(Customer customer) {
        List<SavingsGoal> list = savingsGoalsDAO.findAllByUser(customer.getCustomerId());
        int number = list.size();
        if (number > 0) {
            String currentMonthYear = getCurrentMonthYear();
            String feeDueDate = getFeeDueDate();
            String topupDeadline = getTopupDeadline();
            BigDecimal fee = BigDecimal.valueOf(customer.getBalance().doubleValue() * MAINTENANCE_FEE_INTERATE * number);
            if (customer.getBalance().compareTo(fee) > 0) {
                customer.setBalance(customer.getBalance().subtract(fee));
                customerDAO.updateCustomer(customer);
                String content1 = createMonthlyFeeContent(
                        customer,
                        fee.doubleValue(), // Số tiền phí
                        currentMonthYear, // Tháng/Năm
                        feeDueDate // Ngày thu phí
                );
                emails.sendMess(customer.getEmail(), FEE_MESS_1, content1);
            } else {
                String content2 = createTopupRequestContent(
                        customer,
                        fee.subtract(customer.getBalance()).doubleValue(), // Số tiền cần nạp thêm
                        getActiveGoalsNames(list), // Tên mục tiêu
                        topupDeadline // Hạn nạp tiền
                );
                emails.sendMess(customer.getEmail(), FEE_MESS_2, content2);
            }

        }
    }

    private String getActiveGoalsNames(List<SavingsGoal> goals) {
        return goals.stream()
                .map(SavingsGoal::getGoalName)
                .collect(Collectors.joining(", "));
    }

    public String createTopupRequestContent(Customer customer, double requiredAmount, String goalName, String deadline) {
        return String.format("""
        <html>
        <body style="font-family: Arial, sans-serif; line-height: 1.6;">
            <div style="max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 5px;">
                <h2 style="color: #d9534f; text-align: center;">YÊU CẦU NẠP THÊM TIỀN ĐỂ TIẾP TỤC DỊCH VỤ SAVING GOAL</h2>
                
                <p>Kính gửi Quý khách <strong>%s</strong>,</p>
                
                <div style="background-color: #fff3f3; padding: 15px; border-radius: 5px; margin: 15px 0;">
                    <p>Hệ thống ghi nhận số dư không đủ để duy trì dịch vụ Saving Goal:</p>
                    <ul>
                        <li>Tài khoản: <strong>%s</strong></li>
                        <li>Tên mục tiêu: <strong>%s</strong></li>
                        <li>Số tiền cần nạp thêm: <strong>%,.0f VND</strong></li>
                        <li>Hạn nạp tiền: <strong>%s</strong></li>
                    </ul>
                </div>
                
                <h3 style="color: #333;">Vui lòng thực hiện:</h3>
                <ol>
                    <li>Chuyển khoản số tiền cần thiết vào tài khoản Saving Goal</li>
                    <li>Hoặc nạp tiền mặt tại chi nhánh ngân hàng gần nhất</li>
                </ol>
                
                <p style="color: #d9534f;"><strong>Lưu ý:</strong> Nếu không nạp đủ số tiền trước hạn, dịch vụ Saving Goal có thể bị tạm ngưng.</p>
                
                <div style="margin-top: 30px; font-size: 0.9em; color: #666; border-top: 1px solid #e0e0e0; padding-top: 15px;">
                    <p>Trân trọng,</p>
                    <p><strong>Ngân hàng ABC</strong></p>
                    <p>Hotline hỗ trợ Saving Goal: 1900 5678</p>
                </div>
            </div>
        </body>
        </html>
        """,
                customer.getFullName(),
                customer.getUsername(),
                goalName,
                requiredAmount,
                deadline);
    }

    public String createMonthlyFeeContent(Customer customer, double feeAmount, String monthYear, String dueDate) {
        return String.format("""
        <html>
        <body style="font-family: Arial, sans-serif; line-height: 1.6;">
            <div style="max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 5px;">
                <h2 style="color: #0066cc; text-align: center;">THÔNG BÁO THU PHÍ DỊCH VỤ SAVING GOAL HÀNG THÁNG</h2>
                
                <p>Kính gửi Quý khách <strong>%s</strong>,</p>
                
                <div style="background-color: #f5f5f5; padding: 15px; border-radius: 5px; margin: 15px 0;">
                    <p>Ngân hàng xin thông báo phí dịch vụ Saving Goal tháng <strong>%s</strong>:</p>
                    <ul>
                        <li>Số tài khoản: <strong>%s</strong></li>
                        <li>Số tiền phí: <strong>%,.0f VND</strong></li>
                        <li>Ngày thu phí dự kiến: <strong>%s</strong></li>
                    </ul>
                </div>
                
                <p>Phí sẽ được tự động trừ từ tài khoản thanh toán của Quý khách.</p>
                <p>Vui lòng đảm bảo số dư tài khoản đủ để thanh toán phí dịch vụ.</p>
                
                <div style="margin-top: 30px; font-size: 0.9em; color: #666; border-top: 1px solid #e0e0e0; padding-top: 15px;">
                    <p>Trân trọng,</p>
                    <p><strong>Ngân hàng ABC</strong></p>
                    <p>Hotline: 1900 1234</p>
                </div>
            </div>
        </body>
        </html>
        """,
                customer.getFullName(),
                monthYear,
                customer.getUsername(),
                feeAmount,
                dueDate);
    }

    private String getCurrentMonthYear() {
        return java.time.format.DateTimeFormatter.ofPattern("MM/yyyy").format(java.time.LocalDate.now());
    }

    private String getFeeDueDate() {
        // Ngày thu phí là ngày 5 hàng tháng
        return "05/" + java.time.format.DateTimeFormatter.ofPattern("MM/yyyy").format(java.time.LocalDate.now());
    }

    private String getTopupDeadline() {
        // Hạn nạp tiền là ngày 20 hàng tháng
        return "20/" + java.time.format.DateTimeFormatter.ofPattern("MM/yyyy").format(java.time.LocalDate.now());
    }
}
