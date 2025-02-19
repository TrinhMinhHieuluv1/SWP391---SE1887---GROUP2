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
import java.util.ArrayList;
import java.util.List;
import model.FAQ;

/**
 *
 * @author hungk
 */
@WebServlet(name = "faqServlet", urlPatterns = {"/faq"})
public class faqServlet extends HttpServlet {

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
            out.println("<title>Servlet faqServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet faqServlet at " + request.getContextPath() + "</h1>");
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
       // Lấy danh sách các loại FAQ
    List<String> faqTypes = faqDao.getFAQType(); 
    request.setAttribute("faqTypes", faqTypes);

    // Lấy FAQ cho từng loại và truyền xuống JSP
    List<List<FAQ>> faqData = new ArrayList<>();
    for (String type : faqTypes) {
        List<FAQ> faqList = faqDao.getFAQsByType1(type); // Truyền loại vào đây
        faqData.add(faqList);
    }
    
    String searchKeyword = request.getParameter("searchKeyword");
        // Định dạng lại chuỗi searchKeyword
        if (searchKeyword != null) {
            searchKeyword = searchKeyword.trim(); // Xóa dấu cách đầu và cuối
            searchKeyword = searchKeyword.replaceAll("\\s+", " "); // Thay thế nhiều dấu cách bằng một dấu cách
        }
        List<FAQ> list = new ArrayList<>();
        list = faqDao.searchFAQsByQuestion1(searchKeyword);
        request.setAttribute("listFAQ", list);

    request.setAttribute("faqData", faqData); // Truyền danh sách câu hỏi theo loại
        request.getRequestDispatcher("faq.jsp").forward(request, response);
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
