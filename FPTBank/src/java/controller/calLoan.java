package controller;

import dal.FAQDAO;
import dal.LoanDAO;
import java.io.IOException;
import java.text.DecimalFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.FAQ;
import model.LoanAccount;
import model.MonthlyPayment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class calLoan extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loanAmountStr = request.getParameter("loanAmount");
        String loanRateStr = request.getParameter("loanRate");
        String loanMonthsStr = request.getParameter("loanMonths");
        String calculationMethod = request.getParameter("calculationMethod");
        String nameLoan = request.getParameter("nameLoan");
        String emailLoan = request.getParameter("emailLoan");

        String sanitizedLoanAmountStr = loanAmountStr.replaceAll("[^\\d.]", "");
        double loanAmount = Double.parseDouble(sanitizedLoanAmountStr);
        double annualRate = Double.parseDouble(loanRateStr);
        int loanMonth = Integer.parseInt(loanMonthsStr);
        if (calculationMethod.equals("initial")) {
            calculationMethod = "Trả trên dư nợ ban đầu";
        } else {
            calculationMethod = "Trả trên dư nợ giảm dần";
        }

        LoanDAO Ldao = new LoanDAO();
        LoanAccount L = new LoanAccount();
        L.setEmail(emailLoan);
        L.setInterest_rate(annualRate);
        L.setLoan_amount(loanAmount);
        L.setLoan_term(loanMonth);
        L.setName(nameLoan);
        L.setRepayment_method(calculationMethod);

        Ldao.addLoanAccount(L);

//        try {
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            XSSFSheet spreadsheet = workbook.createSheet("Khoản vay");
//            
//            XSSFRow row = null;
//            Cell cell = null;
//            
//            row = spreadsheet.createRow((short) 2);
//            row.setHeight((short) 500);
//            cell = row.createCell(0, CellType.STRING);
//            cell.setCellValue("THÔNG TIN KHAONR VAY");
//            
//            row = spreadsheet.createRow((short) 3);
//            row.setHeight((short) 500);
//            cell = row.createCell(0, CellType.STRING);
//            cell.setCellValue("Họ và tên");
//            cell = row.createCell(1, CellType.STRING);
//            cell.setCellValue("EMAIL");
//            cell = row.createCell(2, CellType.STRING);
//            cell.setCellValue("SỐ TIỀN VAY");
//            cell = row.createCell(3, CellType.STRING);
//            cell.setCellValue("LÃI SUẤT VAY");
//            cell = row.createCell(4, CellType.STRING);
//            cell.setCellValue("SỐ THẮNG VAY");
//            cell = row.createCell(5, CellType.STRING);
//            cell.setCellValue("PHƯƠNG THỨC VAY");
//            cell = row.createCell(6, CellType.STRING);
//            cell.setCellValue("NGÀY GIẢI NGÂN");
//            
//            List<LoanAccount> listItem = Ldao.getAllLoanAccounts();
//           
//            int loanId = Ldao.getIdByUserName(nameLoan);
//             LoanAccount hocVien = Ldao.getLoanById(loanId);
//           
//                row = spreadsheet.createRow((short) 4 );
//                row.setHeight((short) 400);
//                row.createCell(0).setCellValue( loanId);
//                row.createCell(1).setCellValue(hocVien.getName());
//                row.createCell(2).setCellValue(hocVien.getEmail());
//                row.createCell(3).setCellValue(hocVien.getLoan_amount());
//                row.createCell(4).setCellValue(hocVien.getInterest_rate());
//                row.createCell(5).setCellValue(hocVien.getLoan_term());
//                row.createCell(6).setCellValue(hocVien.getRepayment_method());
//                row.createCell(7).setCellValue(hocVien.getDisbursement_date());
//            
//            
//                
//                
//                
//                  row = spreadsheet.createRow((short) 8);
//            row.setHeight((short) 500);
//            cell = row.createCell(0, CellType.STRING);
//            cell.setCellValue("THÔNG TIN CHI TIẾT");
//            
//            row = spreadsheet.createRow((short) 9);
//            row.setHeight((short) 500);
//            cell = row.createCell(0, CellType.STRING);
//            cell.setCellValue("Họ và tên");
//            cell = row.createCell(1, CellType.STRING);
//            cell.setCellValue("EMAIL");
//            cell = row.createCell(2, CellType.STRING);
//            cell.setCellValue("SỐ TIỀN VAY");
//            cell = row.createCell(3, CellType.STRING);
//            cell.setCellValue("LÃI SUẤT VAY");
//            cell = row.createCell(4, CellType.STRING);
//            cell.setCellValue("SỐ THẮNG VAY");
//            cell = row.createCell(5, CellType.STRING);
//            cell.setCellValue("PHƯƠNG THỨC VAY");
//            cell = row.createCell(6, CellType.STRING);
//            cell.setCellValue("NGÀY GIẢI NGÂN");
//
//            
//                 List<MonthlyPayment> payments = Ldao.calculatePaymentSchedule(loanId);
//                 
//             for (int i = 0; i < payments.size(); i++) {
//                MonthlyPayment hocVien1 = payments.get(i);
//                row = spreadsheet.createRow((short) 10 + i);
//                row.setHeight((short) 400);
//                row.createCell(0).setCellValue(i + 1);
//                row.createCell(1).setCellValue(hocVien1.getMonth());
//                row.createCell(2).setCellValue(hocVien1.getPaymentDate());
//                row.createCell(3).setCellValue(hocVien1.getRemainingPrincipal());
//                row.createCell(4).setCellValue(hocVien1.getTotalPayment());
//                 row.createCell(5).setCellValue(hocVien1.getPrincipal());
//                row.createCell(6).setCellValue(hocVien1.getInterest());
//               
//            }
//            
//            
//            
//            
//            
//            
//                 
//
//            FileOutputStream out = new FileOutputStream(new File("D:/khoanvay2.xlsx"));
//            workbook.write(out);
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//       
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response.setHeader("Content-Disposition", "attachment; filename=khoanvay.xlsx");

    try (XSSFWorkbook workbook = new XSSFWorkbook()) {
        XSSFSheet sheet = workbook.createSheet("Khoản vay");

        // Tiêu đề chính
        XSSFRow row = sheet.createRow(2);
        row.createCell(0).setCellValue("THÔNG TIN KHOẢN VAY");

        // Tiêu đề cột
        String[] headers = {"Họ và tên", "EMAIL", "SỐ TIỀN VAY", "LÃI SUẤT VAY", "SỐ THÁNG VAY", "PHƯƠNG THỨC VAY", "NGÀY GIẢI NGÂN"};
        row = sheet.createRow(3);
        for (int i = 0; i < headers.length; i++) {
            row.createCell(i).setCellValue(headers[i]);
        }

   
        int loanId = Ldao.getIdByUserName(nameLoan);
        LoanAccount loan = Ldao.getLoanById(loanId);

        // Ghi dữ liệu vào file
        row = sheet.createRow(4);
        row.createCell(0).setCellValue(loan.getName());
        row.createCell(1).setCellValue(loan.getEmail());
        row.createCell(2).setCellValue(loan.getLoan_amount());
        row.createCell(3).setCellValue(loan.getInterest_rate());
        row.createCell(4).setCellValue(loan.getLoan_term());
        row.createCell(5).setCellValue(loan.getRepayment_method());
        row.createCell(6).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(loan.getDisbursement_date()));

        // Xuất file Excel
        try (ServletOutputStream out = response.getOutputStream()) {
            workbook.write(out);
            out.flush();
                workbook.close(); // Đóng workbook

        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }




        request.getRequestDispatcher("calLoan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loanAmountStr = request.getParameter("loanAmount");
        String loanRateStr = request.getParameter("loanRate");
        String loanMonthsStr = request.getParameter("loanMonths");
        String calculationMethod = request.getParameter("calculationMethod");
        String nameLoan = request.getParameter("nameLoan");
        String emailLoan = request.getParameter("emailLoan");

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
            request.setAttribute("emailLoan", emailLoan);
            request.setAttribute("nameLoan", nameLoan);
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
