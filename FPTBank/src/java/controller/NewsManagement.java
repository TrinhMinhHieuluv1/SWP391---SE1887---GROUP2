/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.NewsCategoryDAO;
import dal.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.News;
import model.User;

/**
 *
 * @author HP
 */
@WebServlet(name = "NewsManagement", urlPatterns = {"/seller/news-management"})
public class NewsManagement extends HttpServlet {

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
            out.println("<title>Servlet NewsManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewsManagement at " + request.getContextPath() + "</h1>");
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
        NewsDAO ndao = new NewsDAO();
        NewsCategoryDAO ncDAO = new NewsCategoryDAO();
        User account = (User) session.getAttribute("account");

        //Show message after a news updated
        String fromUpdate = request.getParameter("fromUpdate");
        if (fromUpdate != null && fromUpdate.equals("true")) {
            String message = "Update successfully!";
            request.setAttribute("message", message);
        }

        String fromAdd = request.getParameter("fromAdd");
        if (fromAdd != null && fromAdd.equals("true")) {
            String message = "Add successfully!";
            request.setAttribute("message", message);
        }

        // Get parameter
        String searchKeyword = request.getParameter("searchKeyword");
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            searchKeyword = searchKeyword.trim();
        }
        String sortBy = request.getParameter("sortBy");
        String filterMine = request.getParameter("filterMine");
        String filterStatus = request.getParameter("filterStatus");
        String filterNewsCategoryID_raw = request.getParameter("filterNewsCategoryID");
        int filterNewsCategoryID = 0;
        try {
            filterNewsCategoryID = Integer.parseInt(filterNewsCategoryID_raw);
        } catch (NumberFormatException e) {
        }

        // Get news list by conditions
        List<News> newsListBeforePagition = ndao.selectNewsListByConditions(searchKeyword, sortBy, filterStatus, filterMine, filterNewsCategoryID, account.getUserID());

        //Pagination
        String pageSize_raw = request.getParameter("pageSize");
        int pageSize = 10;
        try {
            pageSize = Integer.parseInt(pageSize_raw);
        } catch (NumberFormatException e) {
        }
        int totalPages = (int) Math.ceil((double) newsListBeforePagition.size() / pageSize);
        int page = 1;
        String pageNum_raw = request.getParameter("page");
        if (pageNum_raw != null && !pageNum_raw.isEmpty()) {
            try {
                page = Integer.parseInt(pageNum_raw);
                if (page < 1) {
                    page = 1;
                }
                if (page > totalPages) {
                    page = totalPages;
                }
            } catch (NumberFormatException e) {
                page = 1;
            }
        }
        
        List<News> newsList = newsListBeforePagition.subList((page - 1) * pageSize, Math.min(newsListBeforePagition.size(), page * pageSize));
        request.setAttribute("ncList", ncDAO.selectAllNewsCategory());  
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("newsList", newsList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchKeyword);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("filterMine", filterMine);
        request.setAttribute("filterStatus", filterStatus);
        request.setAttribute("filterNewsCategoryID", filterNewsCategoryID   );
        request.getRequestDispatcher("news-management.jsp").forward(request, response);
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
