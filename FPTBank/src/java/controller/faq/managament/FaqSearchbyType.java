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
@WebServlet(name = "FaqSearchbyType", urlPatterns = {"/seller/faq-search-type"})
public class FaqSearchbyType extends HttpServlet {

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
            out.println("<title>Servlet FaqSearchbyType</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FaqSearchbyType at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        String selectedType = request.getParameter("faqType"); // Chỉ nhận 1 giá trị do frontend chỉ cho chọn 1 checkbox

        FAQDAO dao = new FAQDAO();
        List<FAQ> filteredFAQs;

        int page = 1; // trang đầu tiên
        int pageSize = 10; // 1 trang có 10 FAQs
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int entries = (int) session.getAttribute("entries");
        if (entries != 10) {
            pageSize = entries;
        }
        
        String keywordFromFAQ = request.getParameter("keytype");
        if (keywordFromFAQ != null && !keywordFromFAQ.isEmpty()) {
          selectedType   = keywordFromFAQ;
        }

        if (selectedType != null && !selectedType.isEmpty()) {
            filteredFAQs = dao.getFAQsByType(selectedType, page, pageSize); // Lấy FAQs theo loại đã chọn và phân trang
        } 
        
        else {
            filteredFAQs = dao.getListFAQByPage(page, pageSize); // Nếu không chọn gì, lấy tất cả FAQs và phân trang
        }

        int totalFAQs;
        if (selectedType != null && !selectedType.isEmpty()) {
            totalFAQs = dao.countFAQByType(selectedType); // Lấy tổng số FAQs theo loại đã chọn
        } else {
            totalFAQs = dao.getAllFAQs().size(); // Lấy tổng số FAQs nếu không chọn loại nào
        }

        int totalPages = (int) Math.ceil((double) totalFAQs / pageSize);

// set phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("listFAQ", filteredFAQs);
        request.setAttribute("selectedType", selectedType);
        request.getRequestDispatcher("faq-management.jsp").forward(request, response);
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
