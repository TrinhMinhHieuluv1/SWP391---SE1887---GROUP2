package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class calSaving extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String amountStr = request.getParameter("amount");
        String rateStr = request.getParameter("rate");
        String monthsStr = request.getParameter("months");

        try {
            // Loại bỏ các ký tự không phải số và dấu thập phân trong amount
            String sanitizedAmountStr = amountStr.replaceAll("[^\\d.]", "");
            
            // Chuyển đổi sang số
            double amount = Double.parseDouble(sanitizedAmountStr);
            double rate = Double.parseDouble(rateStr);
            int months = Integer.parseInt(monthsStr);
            
            // Kiểm tra nếu amount <= 0
            if (amount <= 0 || rate <0 || months<=0 ) {
                request.setAttribute("error", "Nhập số phải lớn hơn 0!");
                request.getRequestDispatcher("calSaving.jsp").forward(request, response);
                return;
            }
            
            // Tính toán lãi suất
            double interestMonth = amount * (rate / 100) / 12;
            double interest = interestMonth * months;
            double totalAmount = interest + amount;
            
            // Định dạng số
            DecimalFormat df = new DecimalFormat("#,###.##");
            
            // Gửi kết quả về JSP
            request.setAttribute("amount", df.format(amount));
            request.setAttribute("rate", rateStr);
            request.setAttribute("months", monthsStr);
            request.setAttribute("monthlyInterest", df.format(interestMonth));
            request.setAttribute("totalInterest", df.format(interest));
            request.setAttribute("totalAmount", df.format(totalAmount));
            request.getRequestDispatcher("calSaving.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Báo lỗi nếu dữ liệu nhập vào không hợp lệ
            request.setAttribute("error", "Dữ liệu nhập không hợp lệ hoặc số tiền gửi phải lớn hơn 0!");
            request.getRequestDispatcher("calSaving.jsp").forward(request, response);
        }
    }
}
