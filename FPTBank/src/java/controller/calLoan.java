package controller;

import dal.LoanDAO;
import java.io.IOException;
import java.text.DecimalFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.LoanAccount;
import model.MonthlyPayment;
import java.sql.Timestamp;

public class calLoan extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String loanAmountStr = request.getParameter("loanAmount");
        String loanRateStr = request.getParameter("loanRate");
        String loanMonthsStr = request.getParameter("loanMonths");
        String calculationMethod = request.getParameter("calculationMethod");
        String nameLoan = request.getParameter("nameLoan");
        String emailLoan = request.getParameter("emailLoan");
        String TotalloanResult = request.getParameter("TotalloanResult");
        String TotalPayment = request.getParameter("TotalPayment");
        String TotalloanResultstr = TotalloanResult.replaceAll("[^\\d.]", "");
        String TotalPaymentstr = TotalPayment.replaceAll("[^\\d.]", "");
        String dateScheducle = request.getParameter("dateScheducle");
        String sanitizedLoanAmountStr = loanAmountStr.replaceAll("[^\\d.]", "");
        double loanAmount = Double.parseDouble(sanitizedLoanAmountStr);
        double annualRate = Double.parseDouble(loanRateStr);
        double TotalloanResultdb = Double.parseDouble(TotalloanResultstr);
        double TotalPaymentdb = Double.parseDouble(TotalPaymentstr);
        if (nameLoan != null) {
            nameLoan = nameLoan.trim(); // Xóa dấu cách đầu và cuối
            nameLoan = nameLoan.replaceAll("\\s+", " "); // Thay thế nhiều dấu cách bằng một dấu cách
        }
        int loanMonth = Integer.parseInt(loanMonthsStr);
        calculationMethod = calculationMethod.equals("initial") ? "Trả trên dư nợ ban đầu" : "Trả trên dư nợ giảm dần";
        Timestamp timenew = Timestamp.valueOf(dateScheducle);
        LoanDAO Ldao = new LoanDAO();
        LoanAccount L = new LoanAccount();
        L.setEmail(emailLoan);
        L.setInterest_rate(annualRate);
        L.setLoan_amount(loanAmount);
        L.setLoan_term(loanMonth);
        L.setName(nameLoan);
        L.setRepayment_method(calculationMethod);
        L.setTotal_interest(TotalloanResultdb);
        L.setTotal_amount_to_pay(TotalPaymentdb);
        L.setDisbursement_date(timenew);
        boolean isAdd = Ldao.addLoanAccount(L);
        String ms = "";
        if (isAdd) {
            ms = "Add sucesslly";
            session.setAttribute("message1", "xác nhận thành công hãy bấm nút bên dưới để tải xuống!");

        }
        request.setAttribute("ms", ms);
//        int loanID = Ldao.getLoanIdByDisbursementDate(dateScheducle);

        request.setAttribute("dateScheducle2", dateScheducle);

        request.getRequestDispatcher("calLoan.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                HttpSession session = request.getSession();

        String loanAmountStr = request.getParameter("loanAmount");
        String loanRateStr = request.getParameter("loanRate");
        String loanMonthsStr = request.getParameter("loanMonths");
        String calculationMethod = request.getParameter("calculationMethod");
        String nameLoan = request.getParameter("nameLoan");
        String emailLoan = request.getParameter("emailLoan");
        LocalDate today = LocalDate.now();  // Lấy ngày hôm nay 

        LocalDateTime todayHour = LocalDateTime.now(); // Lấy ngày + giờ hiện tại
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = todayHour.format(formatter);
        try {
            // Loại bỏ các ký tự không phải số và dấu thập phân trong amount
            String sanitizedLoanAmountStr = loanAmountStr.replaceAll("[^\\d.]", "");
            // Chuyển đổi dữ liệu
            double loanAmount = Double.parseDouble(sanitizedLoanAmountStr);
            double annualRate = Double.parseDouble(loanRateStr);
            int months = Integer.parseInt(loanMonthsStr);

            // Kiểm tra nếu loanAmount, annualRate hoặc months <= 0
            if (loanAmount <= 0 || annualRate <= 0 || months <= 0) {
                request.setAttribute("error", "Số tiền vay, lãi suất và kỳ hạn phải lớn hơn 0!");
                request.getRequestDispatcher("calLoan.jsp").forward(request, response);
                return;
            }

            // Tính toán
            DecimalFormat df = new DecimalFormat("#,###.##");
            String loanAmountFormat = df.format(loanAmount);
            String result;
            String rs;
            List<MonthlyPayment> monthlyPayments = new ArrayList<>();
            double totalInterest = 0;

            if ("reducing".equals(calculationMethod)) {
                // Tính theo dư nợ giảm dần
                double principalPerMonth = loanAmount / months;
                double remainingLoan = loanAmount;

                for (int i = 0; i < months; i++) {
                    double monthlyInterest = (remainingLoan * (annualRate / 100)) / 12;
                    totalInterest += monthlyInterest;
                    double totalPaymentPerMonth = principalPerMonth + monthlyInterest;

                    // Tính ngày thanh toán
                    LocalDate paymentDate = today.plusMonths(i);

                    // Lưu thông tin từng tháng
                    monthlyPayments.add(new MonthlyPayment(
                            i + 1,
                            df.format(remainingLoan),
                            df.format(principalPerMonth),
                            df.format(monthlyInterest),
                            df.format(totalPaymentPerMonth),
                            paymentDate.toString() // Ngày thanh toán
                    ));

                    remainingLoan -= principalPerMonth;
                }

                double totalPayment = loanAmount + totalInterest;
                result = df.format(totalInterest);
                rs = df.format(totalPayment);
            } else {
                // Tính theo dư nợ ban đầu
                double monthlyInterest = (loanAmount * (annualRate / 100)) / 12;
                totalInterest = monthlyInterest * months;
                double totalPayment = loanAmount + totalInterest;

                for (int i = 0; i < months; i++) {
                    // Tính ngày thanh toán
                    LocalDate paymentDate = today.plusMonths(i);

                    // Lưu thông tin từng tháng
                    monthlyPayments.add(new MonthlyPayment(
                            i + 1,
                            df.format(loanAmount - (i * (loanAmount / months))),
                            df.format(loanAmount / months),
                            df.format(monthlyInterest),
                            df.format((loanAmount / months) + monthlyInterest),
                            paymentDate.toString() // Ngày thanh toán
                    ));
                }

                result = df.format(totalInterest);
                rs = df.format(totalPayment);
            }

            // Gửi kết quả về JSP
            
            session.setAttribute("emailLoan", emailLoan);
            session.setAttribute("nameLoan", nameLoan);
            session.setAttribute("loanAmount", loanAmountFormat);
            session.setAttribute("loanRate", loanRateStr);
            session.setAttribute("loanMonths", loanMonthsStr);
            session.setAttribute("total", rs);
            session.setAttribute("loanResult", result);
            session.setAttribute("calculationMethod", calculationMethod);
            session.setAttribute("today", today.toString()); // Ngày hiện tại
            session.setAttribute("todayhour", formattedDate);
            session.setAttribute("monthlyPayments", monthlyPayments); // Danh sách các tháng

            request.getRequestDispatcher("calLoan.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Xử lý lỗi nhập liệu
            request.setAttribute("error", "Dữ liệu nhập không hợp lệ. Vui lòng kiểm tra lại!");
            request.getRequestDispatcher("calLoan.jsp").forward(request, response);
        }
    }
}
