/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.billprovdider.management.sendMailbillProvider;
import dal.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import model.Customer;
import java.util.Random;
import model.TransactionHistory;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ConfirmTransaction", urlPatterns = {"/confirmtransaction"})
public class ConfirmTransaction extends HttpServlet {

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
            out.println("<title>Servlet ConfirmTransaction</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmTransaction at " + request.getContextPath() + "</h1>");
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
        Integer uidObj = (Integer) session.getAttribute("uid");
        if (uidObj == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String OTP_raw = request.getParameter("otp");
        String phone = request.getParameter("number");
        String amount_raw = request.getParameter("amount");
        String note = request.getParameter("note");
        if(amount_raw == null || amount_raw.isEmpty()){
            amount_raw = "0";
        }
        int uid = uidObj;
        String error = "";
        CustomerDAO dao = new CustomerDAO();
        Customer customertranfer = dao.selectCustomerByConditions(uid, "", "", "");
        Customer receiver = dao.selectCustomerByConditions(0, "", phone, "");
        String OTP = generateRandomSixDigits();
        boolean email =  sendMailbillProvider.guiMailforOTP(customertranfer.getEmail(), OTP);
        if(email){
            if(OTP_raw.equals(OTP)){
                double amount = Double.parseDouble(amount_raw);
                BigDecimal amountt = BigDecimal.valueOf(amount);
                BigDecimal before = customertranfer.getBalance();
                BigDecimal after = before.subtract(amountt);
                TransactionHistory transaction = new TransactionHistory(1, customertranfer, amountt, before, after, "Tranfer to" + receiver.getFullName(), note);
            }else{
                error = "OTP falied";
                
            }
        }else{
            error = "Don't send email";
        }
        request.setAttribute("error", error);
        request.getRequestDispatcher("confirmtransaction.jsp").forward(request, response);
    }

    

        public static String generateRandomSixDigits() {
            Random random = new Random();
            int number = 100000 + random.nextInt(900000); 
            return String.valueOf(number);
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
