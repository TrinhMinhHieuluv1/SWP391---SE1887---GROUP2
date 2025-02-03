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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author HP
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

   

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fromRegister = request.getParameter("register");
        if (fromRegister != null && fromRegister.equals("true")) {
            String message = "You created an account successfully!";
            request.setAttribute("message", message);
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rem = request.getParameter("rem");
        Cookie cusername = new Cookie("cusername", username);
        Cookie cpassword = new Cookie("cpassword", password);
        Cookie crem = new Cookie("crem", rem);
        UserDAO udao = new UserDAO();
        User account = udao.checkAuthen(username, password);
        if (account == null) {
            String err = "Username or password is incorrect. Please try again!";
            request.setAttribute("err", err);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if ((rem != null) && (rem.equals("ON"))) {
                cusername.setMaxAge(60 * 60 * 24 * 7);
                cpassword.setMaxAge(60 * 60 * 24 * 7);
                crem.setMaxAge(60 * 60 * 24 * 7);
            } else {
                cusername.setMaxAge(0);
                cpassword.setMaxAge(0);
                crem.setMaxAge(0);
            }
            response.addCookie(cusername);
            response.addCookie(cpassword);
            response.addCookie(crem);
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            switch (account.getRoleID()) {
                case 1:
                    response.sendRedirect("/timibank/admin");
                    break;
                case 2:
                    response.sendRedirect("/timibank/seller");
                    break;
                case 3:
                    response.sendRedirect("/timibank/manager");
                    break;
                case 4:
                    response.sendRedirect("/timibank/insurance_provider");
                    break;
                case 5:
                    response.sendRedirect("/timibank/home");
                    break;
            }
        }
    }

   

}
