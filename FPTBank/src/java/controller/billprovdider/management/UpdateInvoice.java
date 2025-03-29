/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.billprovdider.management;

import dal.DetailBillDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import model.DetailBill;

/**
 *
 * @author ACER
 */
@WebServlet(name = "UpdateInvoice", urlPatterns = {"/bill_provider/updateinvoice"})
public class UpdateInvoice extends HttpServlet {

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
            out.println("<title>Servlet UpdateInvoice</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateInvoice at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        int uid = (int) session.getAttribute("uid");
        DetailBillDAO dao = new DetailBillDAO();

        String billID_raw = request.getParameter("billID");
        String title_raw = request.getParameter("title");
        String description_raw = request.getParameter("description");
        String startdate_raw = request.getParameter("startdate");
        String enddate_raw = request.getParameter("enddate");
        String total_raw = request.getParameter("total");
        String status_raw = request.getParameter("status");
        String action = request.getParameter("action");
        int billID = Integer.parseInt(billID_raw);
        DetailBill bill = dao.getDetailBillByID("BillID", billID);
        request.setAttribute("bill", bill);
        String error = "";
        if ("Update".equals(action)) {
            if (billID_raw == null || billID_raw.trim().isEmpty()) {
                error = "Please choose a bill";
            } else if (title_raw == null || title_raw.trim().isEmpty()) {
                error = "Please fill out the title";
            } else if (description_raw == null || description_raw.trim().isEmpty()) {
                error = "Please fill out the description";
            } else if (startdate_raw == null || startdate_raw.trim().isEmpty()) {
                error = "Please fill out the start date";
            } else if (enddate_raw == null || enddate_raw.trim().isEmpty()) {
                error = "Please fill out the end date";
            } else if (total_raw == null || total_raw.trim().isEmpty()) {
                error = "Please fill out the total or only number";
            }else if (isNumeric(total_raw) == false) {
                error = "Please fill out total or only number.";
            }
            else if (error.length() == 0) {
                DetailBill lastbill = dao.getLastDetailBill(bill.getCustomer().getCustomerId(), uid);
                total_raw = total_raw.replace(",", "");
                double total_raw2 = Double.parseDouble(total_raw);
                BigDecimal total = BigDecimal.valueOf(total_raw2);
                java.sql.Date startdate = java.sql.Date.valueOf(startdate_raw);
                java.sql.Date enddate = java.sql.Date.valueOf(enddate_raw);
                if (startdate.toLocalDate().isAfter(enddate.toLocalDate())) {
                    error = "Start Date must be before End Date. ";
                }
                LocalDate startlocaldate = startdate.toLocalDate();
                LocalDate endlocaldate = enddate.toLocalDate();

                int monthsBetween = Period.between(startlocaldate, endlocaldate).getMonths();
                int yearsBetween = Period.between(startlocaldate, endlocaldate).getYears();

                if ((yearsBetween * 12 + monthsBetween) < 1) {
                    error = "Bill for Electric must be at least 1 month. ";
                }
                if (error.length() == 0) {
                    int status = Integer.parseInt(status_raw);
                    bill.setTitle(title_raw);
                    bill.setDescription(description_raw);
                    bill.setStartDate(startdate);
                    bill.setEndDate(enddate);
                    bill.setTotal(total);
                    bill.setStatus(status);
                    dao.updateDetailBill(bill);
                    error = "Update successfully";
                    request.setAttribute("bill", bill);
                }
            }
        }
        request.setAttribute("error", error);
        request.getRequestDispatcher("updateinvoice.jsp").forward(request, response);

    }
    public boolean isNumeric(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false; // Chuỗi rỗng hoặc null
        }
        return str.matches("\\d{1,3}(?:,\\d{3})*"); // Cho phép số nguyên với dấu phẩy
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
