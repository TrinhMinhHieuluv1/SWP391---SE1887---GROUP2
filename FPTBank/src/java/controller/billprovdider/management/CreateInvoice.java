/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.billprovdider.management;

import dal.CustomerDAO;
import dal.DetailBillDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import model.Customer;
import model.DetailBill;

/**
 *
 * @author ACER
 */
@WebServlet(name = "CreateInvoice", urlPatterns = {"/bill_provider/createinvoice"})
public class CreateInvoice extends HttpServlet {

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
            out.println("<title>Servlet CreateInvoice</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateInvoice at " + request.getContextPath() + "</h1>");
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
        String customerid = request.getParameter("customerid");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String startdate_raw = request.getParameter("startdate");
        String enddate_raw = request.getParameter("enddate");
        String total_raw = request.getParameter("total");
        String action = request.getParameter("action");
        DetailBillDAO dao = new DetailBillDAO();
        CustomerDAO cdao = new CustomerDAO();
        UserDAO udao = new UserDAO();
        String error = "";
        if ("Add".equals(action)) {
            if (customerid == null || customerid.isEmpty()) {
                error = "Please choose a customer. ";
            } else if (title == null || title.isEmpty()) {
                error = "Please fill out title. ";
            } else if (description == null || description.isEmpty()) {
                error = "Please fill out description. ";
            } else if (startdate_raw == null || startdate_raw.isEmpty()) {
                error = "Please fill out startdate. ";
            } else if (enddate_raw == null || enddate_raw.isEmpty()) {
                error = "Please fill out enddate. ";
            } else if (isNumeric(total_raw) == false) {
                error = "Please fill out total or only number.";
            } else if (error.length() == 0) { // Chỉ xử lý nếu không có lỗi
                int cid = Integer.parseInt(customerid);
                DetailBill lastbill = dao.getLastDetailBill(cid, uid);
                total_raw = total_raw.replace(",", "");
                System.out.println(total_raw); 
               double total = Double.parseDouble(total_raw);
                BigDecimal totalamount = BigDecimal.valueOf(total);
                java.sql.Date startdate = java.sql.Date.valueOf(startdate_raw);
                java.sql.Date enddate = java.sql.Date.valueOf(enddate_raw);

                if (startdate.toLocalDate().isAfter(enddate.toLocalDate())) {
                    error = "Start Date must be before End Date. ";
                }

                if (lastbill != null && startdate.toLocalDate().isBefore(lastbill.getEndDate().toLocalDate())) {
                    error = "Customer paid for this month. ";
                }

                LocalDate startlocaldate = startdate.toLocalDate();
                LocalDate endlocaldate = enddate.toLocalDate();

                int monthsBetween = Period.between(startlocaldate, endlocaldate).getMonths();
                int yearsBetween = Period.between(startlocaldate, endlocaldate).getYears();

                if ((yearsBetween * 12 + monthsBetween) < 1) {
                    error = "Bill for Electric must be at least 1 month. ";
                }

                if (error.length() == 0) {
                    DetailBill bill = new DetailBill(title, description, startdate, enddate, totalamount,
                            cdao.getCustomerByID(cid),
                            udao.selectAnUserByConditions(uid, "", "", ""));
                    dao.add(bill);
                    error = "Add bill successfully.";
                }
            }
        }
        List<Customer> listC = cdao.selectAllCustomer();
        request.setAttribute("error", error);
        request.setAttribute("listC", listC);
        request.getRequestDispatcher("createinvoice.jsp").forward(request, response);
    }

    public boolean isNumeric(String str) {
    if (str == null || str.trim().isEmpty()) {
        return false; // Chuỗi rỗng hoặc null
    }
    return str.matches("\\d+(,\\d+)?"); // Cho phép số nguyên hoặc số thập phân với dấu ,
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
