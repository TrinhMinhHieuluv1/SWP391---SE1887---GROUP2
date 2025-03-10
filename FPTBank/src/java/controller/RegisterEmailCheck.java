/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import org.apache.tomcat.jakartaee.commons.lang3.tuple.Pair;

/**
 *
 * @author HP
 */
@WebServlet(name = "RegisterEmailCheck", urlPatterns = {"/register-email-check"})
public class RegisterEmailCheck extends HttpServlet {

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
            out.println("<title>Servlet RegisterEmailCheck</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterEmailCheck at " + request.getContextPath() + "</h1>");
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
        request.setAttribute("emailr", request.getParameter("email"));
        request.setAttribute("registerEmail", true);
        request.getRequestDispatcher("pincode.jsp").forward(request, response);
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
        String email = request.getParameter("emailr");
        Pair pinCode = (Pair) request.getSession().getAttribute("pinCode-" + email);
        String pinCodeToVerify = request.getParameter("pin1")
                + request.getParameter("pin2")
                + request.getParameter("pin3")
                + request.getParameter("pin4")
                + request.getParameter("pin5")
                + request.getParameter("pin6");
        if (email.equals((String) pinCode.getLeft()) && pinCodeToVerify.equals(pinCode.getRight().toString())) {
            Customer account = (Customer) request.getSession().getAttribute("account");
            account.setEmail(email);
            CustomerDAO cdao = new CustomerDAO();
            cdao.updateACustomer(account);
            response.sendRedirect("/timibank/login?fromRegister=true");
        } else {
            String err = "Pin code is wrong or has expired";
            request.setAttribute("emailr", email);
            request.setAttribute("registerEmail", true);
            request.setAttribute("err", err);
            request.getRequestDispatcher("pincode.jsp").forward(request, response);
        }
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
