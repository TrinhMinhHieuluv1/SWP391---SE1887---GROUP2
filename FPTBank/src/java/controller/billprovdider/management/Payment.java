/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.billprovdider.management;

import dal.CompanyBillProviderDAO;
import dal.CustomerDAO;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.CompanyBillProvider;
import model.Customer;

/**
 *
 * @author ACER
 */
@WebServlet(name = "Payment", urlPatterns = {"/payment"})
public class Payment extends HttpServlet {

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
            out.println("<title>Servlet Payment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Payment at " + request.getContextPath() + "</h1>");
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
        CompanyBillProviderDAO cdao = new CompanyBillProviderDAO();
        CustomerDAO udao = new CustomerDAO();
        String billID_raw = request.getParameter("billID");
        String providerID_raw = request.getParameter("providerID");
        String total_raw = request.getParameter("total");
        String action = request.getParameter("action");
        double totall = Double.parseDouble(total_raw);
        int billID = Integer.parseInt(billID_raw);
        int providerID = Integer.parseInt(providerID_raw);
        model.DetailBill bill = dao.getDetailBillByID("BillID", billID);
        CompanyBillProvider company = cdao.getCompanyById("ProviderID", providerID);
        Customer customer = udao.selectCustomerByConditions(uid, "", "", "");
        String error = "";
        if ("Paid".equals(action)) {
            BigDecimal total = BigDecimal.valueOf(totall);
            BigDecimal balance = customer.getBalance();

            if (total.compareTo(balance) > 0) {
                error = "Your balance is enough to paid";
            } else {
                LocalDateTime now = LocalDateTime.now();
                Timestamp paymentTimestamp = Timestamp.valueOf(now);
                balance = balance.subtract(total);
                bill.setStatusOfBill(0);
                bill.setPaymentDate(paymentTimestamp);
                customer.setBalance(balance);
                
                boolean mail = sendMailbillProvider.guiMailforPaying(customer.getEmail(), bill.getBillID(), bill.getTitle(), bill.getDescription(), bill.getStartDate(), bill.getEndDate(), bill.getEndDate(), bill.getTotal(), company.getCompanyName(), customer, paymentTimestamp);
                if(mail){
                    error = "Paid successfully and send invoice to email";
                    dao.updateDetailBill(bill);
                    udao.updateACustomer(customer);
                }else {
                    error = "Don't send email";
                }
            }
        }
        request.setAttribute("error", error);
        request.setAttribute("customer", customer);
        request.setAttribute("bill", bill);
        request.setAttribute("company", company);
        request.getRequestDispatcher("payment.jsp").forward(request, response);
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
