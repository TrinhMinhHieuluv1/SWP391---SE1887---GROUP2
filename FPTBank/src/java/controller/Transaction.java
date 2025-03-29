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
import java.util.List;
import java.util.Random;
import model.Customer;
import model.TransactionHistory;

/**
 *
 * @author ACER
 */
@WebServlet(name = "Transaction", urlPatterns = {"/transaction"})
public class Transaction extends HttpServlet {

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
            out.println("<title>Servlet Transaction</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Transaction at " + request.getContextPath() + "</h1>");
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

// Kiểm tra nếu chưa đăng nhập thì chuyển hướng đến trang login
        if (uidObj == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int uid = uidObj;

// Lấy dữ liệu từ form
        String number_raw = request.getParameter("number");
        String amount_raw = request.getParameter("amount");
        String note_raw = request.getParameter("note");
        String action = request.getParameter("action");
        CustomerDAO dao = new CustomerDAO();
        String error = "";
        Customer customer = dao.selectCustomerByConditions(uid, "", "", "");
        request.setAttribute("customer", customer);
        if ("continue".equals(action)) {
            // Các kiểm tra dữ liệu từ form
            if (number_raw == null || number_raw.trim().isEmpty()) {
                error = "Please input account to transfer";
            } else if (amount_raw == null || amount_raw.trim().isEmpty()) {
                error = "Please input amount payment";
            } else if (note_raw == null || note_raw.trim().isEmpty()) {
                error = "Please input note";
            } else if (!dao.isFieldExistsToUpdate("Phone", number_raw, uid)) {
                error = "Account not exist in system";
            } else if (number_raw.equals(customer.getPhone())) {
                error = "Don't fill out your phone";
            }else{
                String amountt = amount_raw.replace(",", "");
                double amount = Double.parseDouble(amountt);
                BigDecimal amounnt = BigDecimal.valueOf(amount);
                if(amounnt.compareTo(customer.getBalance()) > 0){
                error = "Your balance is not enough to transfer";
                }
            }

            if (!error.isEmpty()) {
                request.setAttribute("error", error);
                request.getRequestDispatcher("transaction.jsp").forward(request, response);
                return;
            }
            
            // Xử lý giao dịch khi không có lỗi
            Customer transferor = customer; // vì uid là của người chuyển
            Customer receivecustomer = dao.selectCustomerByConditions(0, "", number_raw, "");
            request.setAttribute("receivecustomer", receivecustomer);
            request.setAttribute("transferor", transferor);
            request.setAttribute("number", number_raw);
            request.setAttribute("amount", amount_raw);
            request.setAttribute("note", note_raw);
            request.getRequestDispatcher("confirmtransaction.jsp").forward(request, response);
        } else {
            // Nếu action không bằng "continue", forward về trang giao dịch mặc định
            request.getRequestDispatcher("transaction.jsp").forward(request, response);
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
