/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Tools;

import dal.LoanDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import model.LoanAccount;
import model.MonthlyPayment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CreateExcelForCal", urlPatterns = {"/export"})
public class CreateExcelForCal extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateExcelForCal</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateExcelForCal at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
                            HttpSession session = request.getSession();

            String dateScheducle = request.getParameter("dateScheducle2");
            LoanDAO Ldao = new LoanDAO();

            int loanId = Ldao.getLoanIdByDisbursementDate(dateScheducle);
            Ldao.insertLoanRepaymentSchedule(loanId);

            LoanAccount loan = Ldao.getLoanById(loanId);

            List<MonthlyPayment> payments = Ldao.getAllLoanScheduleByLoanId(loanId);

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=khoanvay.xlsx");

            try (XSSFWorkbook workbook = new XSSFWorkbook(); ServletOutputStream out = response.getOutputStream()) {
                XSSFSheet sheet = workbook.createSheet("Khoản vay");

                // Tiêu đề chính
                XSSFRow row = sheet.createRow(2);
                row.createCell(0).setCellValue("THÔNG TIN KHOẢN VAY");

                // Tiêu đề cột
                String[] headers = {"Họ và tên", "EMAIL", "SỐ TIỀN VAY", "LÃI SUẤT VAY", "SỐ THÁNG VAY", "PHƯƠNG THỨC VAY", "Tổng tiền lãi", "Tổng số tiền phải trả", "NGÀY GIẢI NGÂN"};
                row = sheet.createRow(3);
                for (int i = 0; i < headers.length; i++) {
                    row.createCell(i).setCellValue(headers[i]);
                }
                CellStyle cellStyle = sheet.getWorkbook().createCellStyle();

                // Ghi thông tin khoản vay
                row = sheet.createRow(4);
                row.createCell(0).setCellValue(loan.getName());
                row.createCell(1).setCellValue(loan.getEmail());
                row.createCell(2).setCellValue(loan.getLoan_amount());
                row.createCell(3).setCellValue(loan.getInterest_rate());
                row.createCell(4).setCellValue(loan.getLoan_term());
                row.createCell(5).setCellValue(loan.getRepayment_method());
                row.createCell(6).setCellValue(loan.getTotal_interest());
                row.createCell(7).setCellValue(loan.getTotal_amount_to_pay());
// Giả sử 'row' đã được tạo từ Sheet
                Timestamp disbursementDate = loan.getDisbursement_date(); // Lấy Timestamp
                Date dateValue = new Date(disbursementDate.getTime()); // Chuyển Timestamp về Date

// Ghi vào ô trên hàng (row)
                Cell cell = row.createCell(8); // Cột thứ 8
                cell.setCellValue(dateValue); // Ghi Date vào Excel

// Định dạng ngày tháng trong Excel (dd/MM/yyyy)
                CreationHelper createHelper = sheet.getWorkbook().getCreationHelper();
                cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy")); // Hoặc "yyyy-MM-dd"
                cell.setCellStyle(cellStyle);

                // Tiêu đề chi tiết
                row = sheet.createRow(6);
                row.createCell(0).setCellValue("THÔNG TIN CHI TIẾT");
                String[] detailHeaders = {"STT", "Tháng", "Ngày thanh toán", "Số tiền gốc còn lại", "Tiền gốc", "Tiền lãi", "Tổng thanh toán"};
                row = sheet.createRow(7);
                for (int i = 0; i < detailHeaders.length; i++) {
                    row.createCell(i).setCellValue(detailHeaders[i]);
                }

                // Ghi dữ liệu thanh toán
                for (int i = 0; i < payments.size(); i++) {
                    MonthlyPayment payment = payments.get(i);
                    row = sheet.createRow(8 + i);
                    row.createCell(0).setCellValue(i + 1);
                    row.createCell(1).setCellValue(payment.getMonth());
                    row.createCell(2).setCellValue(payment.getPaymentDate());
                    row.createCell(3).setCellValue(payment.getRemainingPrincipal());
                    row.createCell(4).setCellValue(payment.getPrincipal());
                    row.createCell(5).setCellValue(payment.getInterest());
                    row.createCell(6).setCellValue(payment.getTotalPayment());

                }

                int columnCount = sheet.getRow(3).getLastCellNum();
                for (int i = 0; i < columnCount; i++) {
                    sheet.autoSizeColumn(i);
                }

                workbook.write(out);
                response.flushBuffer();

            }

        } catch (Exception e) {
            response.setContentType("text/plain");
            response.getWriter().write("Lỗi khi tạo file Excel: " + e.getMessage());
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
