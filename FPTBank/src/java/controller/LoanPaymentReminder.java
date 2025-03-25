package controller;

import dal.LoanPaymentDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import model.LoanPayment;
import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import model.Emails;

@WebListener
public class LoanPaymentReminder implements ServletContextListener {

    private Timer timer = new Timer();
    private LoanPaymentDAO lpdao = new LoanPaymentDAO();
    private Emails sender = new Emails();
    private static final int THREAD_POOL_SIZE = 10; // Số luồng gửi email song song
    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //Send email to remind loan payment
                List<LoanPayment> lpList = lpdao.selectAllLoanPaymentUpToDate();
                for (LoanPayment loanPayment : lpList) {
                    executorService.submit(()->sender.sendMess(loanPayment.getContract().getCustomer().getEmail(),
                            "Timibank: Thư thông báo về khoản thanh toán!",
                            "<table style=\"width: 100%; max-width: 600px; font-family: Arial, sans-serif; border-collapse: collapse; margin: 0 auto;\">\n"
                            + "    <tr>\n"
                            + "        <td style=\"background-color: #4caf50; padding: 20px; text-align: center; color: white;\">\n"
                            + "            <h2>Loan Payment Reminder</h2>\n"
                            + "        </td>\n"
                            + "    </tr>\n"
                            + "    <tr>\n"
                            + "        <td style=\"padding: 20px; background-color: #f9f9f9; color: #333;\">\n"
                            + "            <p>Dear " + loanPayment.getContract().getCustomer().getFullName() + "+ ,</p>\n"
                            + "            <p>This is a reminder that your loan payment is due soon:</p>\n"
                            + "            <ul style=\"list-style: none; padding: 0;\">\n"
                            + "                <li><strong>Amount:</strong> " + loanPayment.getPaymentAmount() + "</li>\n"
                            + "                <li><strong>Due Date:</strong> " + loanPayment.getPaymentDate() + "</li>\n"
                            + "            </ul>\n"
                            + "            <p>Please make the payment before the due date to avoid any penalties.</p>\n"
                            + "            <p>Thank you,<br>TimiBank</p>\n"
                            + "        </td>\n"
                            + "    </tr>\n"
                            + "</table>"));
                }
                
                //Complete Saving contract and send notification
            }
        }, 0, 24 * 60 * 60 * 1000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        timer.cancel();
        executorService.shutdown();
    }

}
