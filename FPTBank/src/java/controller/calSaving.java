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

        // Lấy dữ liệu từ form
        String amountStr = request.getParameter("amount");
        String rateStr = request.getParameter("loanRate");
        String durationStr = request.getParameter("duration");
        String durationUnit = request.getParameter("durationUnit");

        try {
            // Xóa dấu phẩy trong amount (do input có định dạng số)
            double amount = Double.parseDouble(amountStr.replaceAll(",", ""));
            double rate = Double.parseDouble(rateStr);
            int duration = Integer.parseInt(durationStr);

            // Kiểm tra nếu dữ liệu nhập vào không hợp lệ
            if (amount <= 0 || rate < 0 || duration <= 0) {
                request.setAttribute("error", "Nhập số phải lớn hơn 0!");
                request.getRequestDispatcher("calSaving.jsp").forward(request, response);
                return;
            }

            double interest = 0;
            double totalAmount = 0;
            double interestPerDay = 0;
            double interestPerMonth = 0;
            double interestPerYear = 0;
            if ("days".equals(durationUnit)) {
                // Tính lãi suất cho ngày
                interestPerDay = amount * (rate / 100) / 365; // Chia cho 365 ngày trong năm
                interest = interestPerDay * duration;
                totalAmount = interest + amount;
            } else if ("months".equals(durationUnit)) {
                // Tính lãi suất cho tháng
                interestPerMonth = amount * (rate / 100) / 12; // Chia cho 12 tháng trong năm
                interest = interestPerMonth * duration;
                totalAmount = interest + amount;
            } else if ("years".equals(durationUnit)) {
                // Tính lãi suất cho năm
                interestPerYear = amount * (rate / 100); // Lãi suất cho một năm
                interest = interestPerYear * duration;
                totalAmount = interest + amount;
            } else {
                // Nếu đơn vị không hợp lệ, trả về thông báo lỗi
                request.setAttribute("error", "Đơn vị thời gian không hợp lệ!");
                request.getRequestDispatcher("calSaving.jsp").forward(request, response);
                return;
            }
            // Định dạng số
            DecimalFormat df = new DecimalFormat("#,###.##");

            // Gửi kết quả về JSP
            request.setAttribute("amount", df.format(amount));
            request.setAttribute("loanRate", rateStr);
            request.setAttribute("duration", durationStr);
            request.setAttribute("durationUnit", durationUnit);
            request.setAttribute("interestPerDay", df.format(interestPerDay));
            request.setAttribute("interestPerMonth", df.format(interestPerMonth));
            request.setAttribute("interestPerYear", df.format(interestPerYear));
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
