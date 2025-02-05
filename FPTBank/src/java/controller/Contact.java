/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import dal.FeedbackDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
        String name = request.getParameter("FullName");
        String email = request.getParameter("email");
        String text = request.getParameter("message");
        int uid = (int) session.getAttribute("uid");
        CustomerDAO cdao = new CustomerDAO();
        FeedbackDAO fdao = new FeedbackDAO();
        int cid = cdao.getCustomerIdByUserId(uid);
        UserDAO dao = new UserDAO();
        String error = "";
        if (!isValidString(name)) {
            error = "Wrong type in fullname, only contain A-a and not null";
        }else if (!isValidEmail(email)) {
            error = "Wrong type in email, example like abc@gmail.com";
        }  else {
            fdao.addFeedback(cid, text, true);
            error = "Thank you for sending feedback, and wait to 24-48 hours to response";
        }
        request.setAttribute("error", error);
        request.getRequestDispatcher("contact.jsp").forward(request, response);  
    }

    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false; // Nếu email null hoặc rỗng
        }

        // Biểu thức chính quy để kiểm tra email hợp lệ
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return email.matches(emailRegex);
    }

    public static boolean isValidString(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        // Tách tên thành các từ (loại bỏ khoảng trắng dư thừa)
        String[] words = name.trim().split("\\s+");

        // Giả sử tên phải có ít nhất 2 từ (có thể thay đổi theo yêu cầu)
        if (words.length < 2) {
            return false;
        }

        // Regex cho từ có nhiều hơn 1 ký tự:
        // - Ký tự đầu tiên: chữ in hoa (bao gồm chữ có dấu từ À đến Ỹ)
        // - Các ký tự sau: chữ thường (bao gồm chữ có dấu từ à đến ỹ)
        String regexFull = "^[A-ZÀ-Ỹ][a-zà-ỹ]+$";
        // Regex cho từ chỉ có 1 ký tự (phải là chữ in hoa)
        String regexInitial = "^[A-ZÀ-Ỹ]$";

        // Kiểm tra từng từ theo tiêu chí đã nêu
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
