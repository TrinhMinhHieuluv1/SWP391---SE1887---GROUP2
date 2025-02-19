/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import dal.FeedbackDAO;
import dal.ServiceDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Service;

/**
 *
 * @author ACER
 */
@WebServlet(name = "Contact", urlPatterns = {"/contact"})
public class Contact extends HttpServlet {

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
            out.println("<title>Servlet Contact</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Contact at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String text = request.getParameter("message");
        String serviceid = request.getParameter("serviceid");
        String rating_star = request.getParameter("stars");
        int uid = (int) session.getAttribute("uid");
        CustomerDAO cdao = new CustomerDAO();
        FeedbackDAO fdao = new FeedbackDAO();
        UserDAO dao = new UserDAO();
        ServiceDAO sdao = new ServiceDAO();
        List<Service> lists = sdao.selectALLService();
        request.setAttribute("listservice", lists);
        String error = "";
        if(!isValidEmail(email)){
            error ="";
        }else
        if (serviceid == null || serviceid.trim().isEmpty()) {
            error = "Please select service you want to feedback";
        } else if (rating_star == null || rating_star.isEmpty()) {
            error = "Please rating stars";
        } else if (text.isEmpty() && text.isBlank()) {
            error = "Please to fill in feedback content";
        } else {
            try {
                int sid = Integer.parseInt(serviceid);
                int stars = Integer.parseInt(rating_star);
                fdao.addFeedback(uid, sid, stars, text, null, true);
                error = "Thank you for sending feedback, and wait to 24-48 hours to response";
            } catch (NumberFormatException e) {
                error = "Can't add feedback";
            }
        }
        request.setAttribute("error", error);
        request.getRequestDispatcher("addfeedback.jsp").forward(request, response);
    }

    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return email.matches(emailRegex);
    }

    public static boolean isValidString2(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        // Tách tên thành các từ (loại bỏ khoảng trắng dư thừa)
        String[] words = name.trim().split("\\s+");

        if (words.length < 2) {
            return false;
        }

        String regexFull = "^[A-ZÀ-Ỹ][a-zà-ỹ]+$";

        String regexInitial = "^[A-ZÀ-Ỹ]$";

        for (String word : words) {
            if (word.length() == 1) {
                if (!word.matches(regexInitial)) {
                    return false;
                }
            } else {
                if (!word.matches(regexFull)) {
                    return false;
                }
            }
        }
        return true;
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
