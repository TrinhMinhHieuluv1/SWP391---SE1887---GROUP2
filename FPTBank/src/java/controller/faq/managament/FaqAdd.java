/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.faq.managament;

import dal.FAQDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.FAQ;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "FaqAdd", urlPatterns = {"/seller/faq-add"})
public class FaqAdd extends HttpServlet {

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
            out.println("<title>Servlet FaqAdd</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FaqAdd at " + request.getContextPath() + "</h1>");
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
        FAQDAO faqDao = new FAQDAO();
        List<String> listType = faqDao.getFAQType();      
        request.setAttribute("listType", listType);
        request.getRequestDispatcher("faq-add.jsp").forward(request, response);
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

        // Lấy dữ liệu từ form
        String type = request.getParameter("type");
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        HttpSession session = request.getSession();

        // Kiểm tra xem câu hỏi đã tồn tại chưa
        FAQDAO faqDao = new FAQDAO();
        if (faqDao.isQuestionExists(question)) {
            // Nếu câu hỏi đã tồn tại, chuyển hướng người dùng về trang quản lý FAQ với thông báo lỗi
            session.setAttribute("error", "Failed to add FAQ.Question is exist. Please try again.");
            response.sendRedirect("/timibank/seller/faq-management");
            
        } else {
            // Nếu câu hỏi chưa tồn tại, thêm FAQ vào cơ sở dữ liệu
            FAQ newFAQ = new FAQ();
            newFAQ.setType(type);
            newFAQ.setQuestion(question);
            newFAQ.setAnswer(answer);            
            boolean isAdded = faqDao.addFAQBoolean(newFAQ);
            
            if (isAdded) {
                session.setAttribute("message", "FAQ added successfully!");
            } else {
                session.setAttribute("message", "Failed to add FAQ. Please try again.");
            }
            request.setAttribute("question", question);
            // Chuyển hướng người dùng về trang quản lý FAQ
            response.sendRedirect("/timibank/seller/faq-management");
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
