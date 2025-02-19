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
import java.util.ArrayList;
import java.util.List;
import model.FAQ;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "FAQmanagement", urlPatterns = {"/seller/faq-management"})
public class FAQmanagement extends HttpServlet {

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
            out.println("<title>Servlet FAQmanagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FAQmanagement at " + request.getContextPath() + "</h1>");
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
       request.setCharacterEncoding("UTF-8");
       response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();    
        FAQDAO dao = new FAQDAO();
        List<FAQ> list = dao.getAllFAQs();
        int page = 1; // trang đầu tiên
        int pageSize = 10; // 1 trang có 10 users
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        // get entries from drownlist with user option
        String entries_raw = request.getParameter("entries");
        if (entries_raw != null && !entries_raw.isEmpty()) {
            pageSize = Integer.parseInt(entries_raw);
        }
        String keyword = request.getParameter("searchKey");
        if (keyword != null && !keyword.isEmpty()) {
            request.getSession().setAttribute("entries", pageSize);
            response.sendRedirect("faq-search-question?key=" + keyword);
            return;
        }
        
         String searchType = request.getParameter("searchType");
        if (searchType != null && !searchType.isEmpty()) {
            request.getSession().setAttribute("entries", pageSize);
            response.sendRedirect("faq-search-type?keytype=" + searchType);
            return;
        }
        
        FAQDAO faqDAO = new FAQDAO();
        ArrayList<FAQ> listFAQ = faqDAO.getListFAQByPage(page, pageSize);

        int totalUsers = faqDAO.getAllFAQs().size();

        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);
        // set phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
          request.setAttribute("listFAQ", listFAQ);
          request.getSession().setAttribute("entries", pageSize);
        // Chuyển tiếp request đến trang JSP
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
