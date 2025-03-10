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
import jakarta.servlet.http.HttpSession;
import java.time.Instant;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import model.Customer;
import model.Emails;
import org.apache.tomcat.jakartaee.commons.lang3.tuple.Pair;
import org.json.simple.JSONArray;

/**
 *
 * @author HP
 */
@WebServlet(name = "RegisterEmail", urlPatterns = {"/register-email"})
public class RegisterEmail extends HttpServlet {

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
            out.println("<title>Servlet RegisterEmail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterEmail at " + request.getContextPath() + "</h1>");
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
        CustomerDAO cdao = new CustomerDAO();
        JSONArray emailArray = new JSONArray();
        for (Customer customer : cdao.selectAllCustomer()) {
            emailArray.add(customer.getEmail());
        }
        request.setAttribute("emailArray", emailArray);
        request.getRequestDispatcher("register-email.jsp").forward(request, response);
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
        Emails sendEmailTools = new Emails();
        Random rd = new Random();

        StringBuilder pinCode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            pinCode.append(rd.nextInt(10));
        }
        String email = request.getParameter("email");
        sendEmailTools.sendMess(email.trim(), "Verification code from TimiBank", "This code to verify your email: " + pinCode);
        session.setAttribute("pinCode-" + email, Pair.of(email, pinCode));
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String email = request.getParameter("email");
                session.removeAttribute("pinCode-" + email);
            }
        };
        timer.schedule(timerTask, 1000*60*5);
        response.sendRedirect("/timibank/register-email-check?email=" + email);
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
