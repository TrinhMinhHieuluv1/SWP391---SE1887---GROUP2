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
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author HP
 */
@WebServlet(name = "changepassword", urlPatterns = {"/change-password"})
public class ChangePassword extends HttpServlet {

   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("change-password.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String old_password = request.getParameter("password");
        String new_password = request.getParameter("new-password");
        UserDAO udao = new UserDAO();
        if (udao.checkAuthen(username, old_password) == null) {
            String err = "Username or password is incorrect. Please try again!";
            request.setAttribute("err", err);
            request.getRequestDispatcher("change-password.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            User account = (User) session.getAttribute("account");
            account.setPassword(new_password);
            udao.updateAUserByUserID(account);
            session.removeAttribute("account");
            response.sendRedirect("/timibank/login");
        }
    }

   

}
