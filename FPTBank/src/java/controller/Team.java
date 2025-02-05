/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.User;

/**
 *
 * @author hungk
 */
@WebServlet(name = "Team", urlPatterns = {"/about"})
public class Team extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Team</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Team at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<User> teamMembers = userDAO.selectAllUsersByRole(2); // Lấy user có roleID = 2
   int teamSeller = userDAO.countUsersByRole(2); // seller
        int teamManage = userDAO.countUsersByRole(3);//mânger
        int teamCustomer = userDAO.countUsersByRole(5);// customer
        request.setAttribute("teamSeller", teamSeller);
        request.setAttribute("teamManage", teamManage);
        request.setAttribute("teamCustomer", teamCustomer);
        request.setAttribute("teamMembers", teamMembers);
        request.getRequestDispatcher("about.jsp").forward(request, response); // Đẩy sang JSP
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         UserDAO userDAO = new UserDAO();
        List<User> teamMembers = userDAO.selectAllUsersByRole(2); // Lấy user có roleID = 2
   int teamSeller = userDAO.countUsersByRole(2); // seller
        int teamManage = userDAO.countUsersByRole(3);//mânger
        int teamCustomer = userDAO.countUsersByRole(5);// customer
        request.setAttribute("teamSeller", teamSeller);
        request.setAttribute("teamManage", teamManage);
        request.setAttribute("teamCustomer", teamCustomer);
        request.setAttribute("teamMembers", teamMembers);
        request.getRequestDispatcher("about.jsp").forward(request, response); // Đẩy sang JSP
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
