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
@WebServlet(name="Login", urlPatterns={"/login"})
public class Login extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet Login</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String fromRegister = request.getParameter("register");
        if (fromRegister!=null && fromRegister.equals("true")){
            String message = "You created an account successfully!";
            request.setAttribute("message", message);
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        if (account == null){
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
<<<<<<< HEAD
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
=======
            if (adao.checkAdmin(username, password) != null){
                session.setAttribute("account", adao.checkAdmin(username, password));
                response.sendRedirect("/timibank/admin");
            } else if (mdao.checkManager(username, password) != null){
                session.setAttribute("account", mdao.checkManager(username, password));
                response.sendRedirect("/timibank/manager");
            } else if (sdao.checkSeller(username, password) != null){
                session.setAttribute("account", cdao.checkCustomer(username, password));
                response.sendRedirect("/timibank/index.jsp");
>>>>>>> 62a24bf698a52faed55d68b8c1589fac04fd037e
            }
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
