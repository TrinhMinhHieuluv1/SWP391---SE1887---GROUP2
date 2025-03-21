/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Feedback;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ReclaimFeedback", urlPatterns = {"/reclaim"})
public class ReclaimFeedback extends HttpServlet {

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
            out.println("<title>Servlet ReclaimFeedback</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReclaimFeedback at " + request.getContextPath() + "</h1>");
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
        String fid_raw = request.getParameter("fid");
        String date_raw1 = request.getParameter("date_1");
        String date_raw2 = request.getParameter("date_2");
        String search = request.getParameter("search");
        String status = request.getParameter("status");
        String number = request.getParameter("pagesize");
        if (date_raw1 == null) {
            date_raw1 = "";
        }
        if (date_raw2 == null) {
            date_raw2 = "";
        }
        if (date_raw2 == null) {
            date_raw2 = "";
        }
        if (status == null) {
            status = "";
        }
        if (search == null) {
            search = "";
        }
        int fid = Integer.parseInt(fid_raw);
        int uid = (int) session.getAttribute("uid");
        FeedbackDAO dao = new FeedbackDAO();
        dao.updateStatus(fid, false);
        List<Feedback> list = dao.filterFeedback(date_raw1, date_raw2, status, search, uid);
        int page = 1;
        int pageSize = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (number != null && !number.trim().isEmpty()) {
            pageSize = Integer.parseInt(number);
        }

        int totalUsers = list.size();

        int totalPages = totalUsers % pageSize == 0 ? totalUsers / pageSize : (totalUsers / pageSize) + 1;
        int start = (page - 1) * pageSize;
        int end = page * pageSize > totalUsers ? totalUsers : page * pageSize;
        list = dao.getListByPage(list, start, end);
        request.setAttribute("listfeedback", list);
        String error = "You reclaimed a feedback!";
        request.setAttribute("error2", error);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("date_1", date_raw1);
        request.setAttribute("date_2", date_raw2);
        request.setAttribute("search", search);
        request.setAttribute("status", status);
        request.setAttribute("pagesize", pageSize);
        request.getRequestDispatcher("myfeedback").forward(request, response);
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
