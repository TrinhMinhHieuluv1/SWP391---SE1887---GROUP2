package controller;

import dal.SavingDAO;
import java.sql.Timestamp;
import java.time.Instant;
import java.io.IOException;
import java.text.DecimalFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.SavingsAccount;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class calSaving extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form
        HttpSession session = request.getSession();

        String emailSaving = request.getParameter("emailSaving");
        String nameSaving = request.getParameter("nameSaving");
        String amountStr = request.getParameter("amount").replaceAll("[^\\d.]", "");
        String rateStr = request.getParameter("loanRate");
        String durationStr = request.getParameter("duration");
        String durationUnitStr = request.getParameter("durationUnit");
        double interestPerTerm = 0;
        if (nameSaving != null) {
            nameSaving = nameSaving.trim(); // Xóa dấu cách đầu và cuối
            nameSaving = nameSaving.replaceAll("\\s+", " "); // Thay thế nhiều dấu cách bằng một dấu cách
        }
        if ("days".equals(durationUnitStr)) {
            durationUnitStr = "Ngày";
            interestPerTerm = Double.parseDouble(request.getParameter("interestPerDay").replaceAll("[^\\d.]", ""));

        } else if ("months".equals(durationUnitStr)) {
            durationUnitStr = "Tháng";
            interestPerTerm = Double.parseDouble(request.getParameter("interestPerMonth").replaceAll("[^\\d.]", ""));
        } else {
            durationUnitStr = "Năm";
            interestPerTerm = Double.parseDouble(request.getParameter("interestPerYear").replaceAll("[^\\d.]", ""));
        }

        String totalAmountStr = request.getParameter("totalAmount").replaceAll("[^\\d.]", "");
        Timestamp currentTimestamp = Timestamp.from(Instant.now());

        double amount = Double.parseDouble(amountStr);
        double rate = Double.parseDouble(rateStr);
        double totalAmount = Double.parseDouble(totalAmountStr);
        int duration = Integer.parseInt(durationStr);

        SavingDAO savDAO = new SavingDAO();
        SavingsAccount savacc = new SavingsAccount(duration,
                nameSaving,
                emailSaving,
                durationUnitStr,
                amount,
                rate,
                interestPerTerm,
                totalAmount,
                currentTimestamp);

        boolean isadd = savDAO.addSavingsAccount(savacc);
        if (isadd) {
            session.setAttribute("message2", "Download file successfully!");

        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // Định dạng mong muốn
        String formattedDate = sdf.format(currentTimestamp);
        int savId = savDAO.getSavingsIdByCreateDate(formattedDate);
        SavingsAccount savv = savDAO.getSavingsById(savId);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=khoanvay.xlsx");

        try (XSSFWorkbook workbook = new XSSFWorkbook(); ServletOutputStream out = response.getOutputStream()) {
            XSSFSheet sheet = workbook.createSheet("Khoản gửi lãi");

            // Tiêu đề chính
            XSSFRow row = sheet.createRow(2);
            row.createCell(0).setCellValue("THÔNG TIN KHOẢN GỬI LÃI");

            // Tiêu đề cột
            String[] headers = {"Họ và tên", "EMAIL", "SỐ TIỀN GỬI", "LÃI SUẤT GỬI", "SỐ KÌ HẠN", "KÌ HẠN", "TIỀN LÃI 1 KÌ HẠN", "TỔNG SỐ TIỀN NHẬN ĐƯỢC", "NGÀY GIẢI NGÂN"};
            row = sheet.createRow(3);
            for (int i = 0; i < headers.length; i++) {
                row.createCell(i).setCellValue(headers[i]);
            }
            CellStyle cellStyle = sheet.getWorkbook().createCellStyle();

            // Ghi thông tin khoản vay
            row = sheet.createRow(4);
            row.createCell(0).setCellValue(savv.getName());
            row.createCell(1).setCellValue(savv.getEmail());
            row.createCell(2).setCellValue(savv.getDeposit_amount());
            row.createCell(3).setCellValue(savv.getInterest_rate());
            row.createCell(4).setCellValue(savv.getTerm());
            row.createCell(5).setCellValue(savv.getTermUnit());
            row.createCell(6).setCellValue(savv.getInterest_per_term());
            row.createCell(7).setCellValue(savv.getTotal_amount_at_maturity());
// Giả sử 'row' đã được tạo từ Sheet
            Timestamp disbursementDate = savv.getCreateDate(); // Lấy Timestamp
            Date dateValue = new Date(disbursementDate.getTime()); // Chuyển Timestamp về Date

// Ghi vào ô trên hàng (row)
            Cell cell = row.createCell(8); // Cột thứ 8
            cell.setCellValue(dateValue); // Ghi Date vào Excel

// Định dạng ngày tháng trong Excel (dd/MM/yyyy)
            CreationHelper createHelper = sheet.getWorkbook().getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy")); // Hoặc "yyyy-MM-dd"
            cell.setCellStyle(cellStyle);

            int columnCount = sheet.getRow(3).getLastCellNum();
            for (int i = 0; i < columnCount; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            response.flushBuffer();

        } catch (Exception e) {
            response.setContentType("text/plain");
            response.getWriter().write("Lỗi khi tạo file Excel: " + e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy dữ liệu từ form
        String emailSaving = request.getParameter("emailSaving");
        String nameSaving = request.getParameter("nameSaving");
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
            request.setAttribute("emailSaving", emailSaving);
            request.setAttribute("nameSaving", nameSaving);
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
