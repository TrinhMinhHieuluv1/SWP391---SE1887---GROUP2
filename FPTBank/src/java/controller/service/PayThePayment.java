/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.service;

import dal.CustomerDAO;
import dal.LoanPaymentDAO;
import dal.TransactionHistoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import model.Customer;
import model.LoanPayment;
import model.TransactionHistory;

/**
 *
 * @author HP
 */
@WebServlet(name = "PayThePayment", urlPatterns = {"/pay-the-payment"})
public class PayThePayment extends HttpServlet {

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
            out.println("<title>Servlet PayThePayment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PayThePayment at " + request.getContextPath() + "</h1>");
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
        TransactionHistoryDAO tdao = new TransactionHistoryDAO();
        CustomerDAO cdao = new CustomerDAO();
        Customer customer = (Customer) session.getAttribute("account");
        String LoanPaymentID_raw = request.getParameter("LoanPaymentID");
        int LoanPaymentID = Integer.parseInt(LoanPaymentID_raw);
        LoanPaymentDAO lpdao = new LoanPaymentDAO();
        LoanPayment lp = lpdao.selectALoanPaymentByID(LoanPaymentID);
        lp.setPaidDate(Date.valueOf(LocalDate.now()));
        lp.setPaymentStatus("Complete");
        lpdao.updateALoanPayment(lp);
        BigDecimal balancebefore = customer.getBalance();
        BigDecimal balanceafter = balancebefore.subtract(lp.getPaymentAmount());
        customer.setBalance(balanceafter);
        TransactionHistory history = new TransactionHistory(1, customer, customer, lp.getPaymentAmount(), balancebefore, balanceafter, "Pay the payment", "Pay the payment #" + lp.getLoanPaymentID());
        cdao.updateACustomer(customer);
        tdao.addTransaction(history);
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
