/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;
import model.Customer;
import model.Emails;
import model.User;

import model.Emails;

import model.User;

/**
 *
 * @author tiend
 */
@WebServlet(name = "ForgotPass", urlPatterns = {"/forgotPass"})
public class ForgotPass extends HttpServlet {
   Random random = new Random();
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SendEmail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendEmail at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("forgotPass.jsp").forward(request, response);
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
        String emailr = request.getParameter("email");
        String code = getRandom();
        Emails email = new Emails();
        UserDAO userDAO = new UserDAO();
        CustomerDAO cusDAO = new CustomerDAO();
        Customer customer = cusDAO.selectCustomerByConditions(0,"","",emailr.trim());
        User user = userDAO.selectAnUserByConditions(0,"","",emailr.trim());
        
        if (user != null||customer!=null) {
            email.sendMess(emailr.trim(), "Recovery Password", code);
            request.setAttribute("emailr", emailr.trim());
            request.setAttribute("code", code);
            request.getRequestDispatcher("pincode.jsp").forward(request, response);
        }else{
            String err= "Not exist Email!";
            request.setAttribute("err", err);
            request.getRequestDispatcher("forgotPass.jsp").forward(request, response);
        }

    }

    public String getRandom() {
        StringBuilder randomNumbers = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            randomNumbers.append(random.nextInt(10));
        }
        return randomNumbers.toString();
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
