/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Tools.HashString;
import dal.CustomerDAO;
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
import java.sql.Date;
import model.Customer;
import model.User;
import org.json.simple.JSONArray;

/**
 *
 * @author HP
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

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
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
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
        CustomerDAO cDAO = new CustomerDAO();
        JSONArray usernameArray = new JSONArray();
        JSONArray phoneArray = new JSONArray();
        JSONArray cccdArray = new JSONArray();
        for (Customer customer : cDAO.selectAllCustomer()) {
            usernameArray.add(customer.getUsername());
            phoneArray.add(customer.getPhone());
            cccdArray.add(customer.getCCCD());
        }
        request.setAttribute("usernameArray", usernameArray);
        request.setAttribute("phoneArray", phoneArray);
        request.setAttribute("cccdArray", cccdArray);
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        HashString hs = new HashString();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = hs.hashString(password);
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dob_raw = request.getParameter("dob");
        String image = request.getParameter("image");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String CCCD = request.getParameter("CCCD");
        Date dob = null;
        try {
            dob = Date.valueOf(dob_raw);
        } catch (Exception e) {
        }
        Customer customerToAdd = new Customer(0, 100, 5, username, password, name, image, phone, "", address, CCCD, dob, null, gender.equals("Male"), true, BigDecimal.ZERO);
        CustomerDAO cdao = new CustomerDAO();
        response.getWriter().print(cdao.addACustomer(customerToAdd));
        session.setAttribute("account", cdao.selectCustomerByConditions(0, username, "", ""));
        response.sendRedirect("/timibank/register-email");
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
