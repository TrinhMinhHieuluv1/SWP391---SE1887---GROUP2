package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.MonthlyPayment;

public class calLoan extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loanAmountStr = request.getParameter("loanAmount");
        String loanRateStr = request.getParameter("loanRate");
        String loanMonthsStr = request.getParameter("loanMonths");
        String calculationMethod = request.getParameter("calculationMethod");
        LocalDate today = LocalDate.now();  // Lấy ngày hôm nay

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
        request.setAttribute("loanAmount", loanAmountFormat);
        request.setAttribute("loanRate", loanRateStr);
        request.setAttribute("loanMonths", loanMonthsStr);
        request.setAttribute("total", rs);
        request.setAttribute("loanResult", result);
        request.setAttribute("calculationMethod", calculationMethod);
        request.setAttribute("today", today.toString()); // Ngày hiện tại
        request.setAttribute("monthlyPayments", monthlyPayments); // Danh sách các tháng

        request.getRequestDispatcher("calLoan.jsp").forward(request, response);
    } catch (NumberFormatException e) {
        // Xử lý lỗi nhập liệu
        request.setAttribute("error", "Dữ liệu nhập không hợp lệ. Vui lòng kiểm tra lại!");
        request.getRequestDispatcher("calLoan.jsp").forward(request, response);
    }
    }
}

