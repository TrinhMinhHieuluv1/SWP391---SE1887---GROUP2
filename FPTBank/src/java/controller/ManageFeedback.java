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
import java.util.ArrayList;
import java.util.List;
import model.Feedback;
import org.apache.catalina.ha.ClusterSession;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ManageFeedback", urlPatterns = {"/seller/managefeedback"})
public class ManageFeedback extends HttpServlet {

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
            out.println("<title>Servlet ManageFeedback</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageFeedback at " + request.getContextPath() + "</h1>");
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
        String search_raw = request.getParameter("searchKeyword");
        String status_raw = request.getParameter("filterStatus");
        String status_res = request.getParameter("statusresponse");
        String number = request.getParameter("pagesize");
        String date_1 = request.getParameter("date1");
        String date_2 = request.getParameter("date2");
        if(search_raw == null){
            search_raw = "";
        }
        if(status_raw == null){
            status_raw = "";
        }
        if(status_res == null){
            status_res = "";
        }
        if(date_1 == null){
            date_1 = "";
        }
        if(date_2 == null){
            date_2 = "";
        }
        FeedbackDAO dao = new FeedbackDAO();
        List<Feedback> list = dao.filterFeedback2(search_raw, status_raw, status_res,date_1,date_2);
        int page = 1;
        int pageSize = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(number == null || number.trim().isEmpty()){
            pageSize = 10;
        }else{
            int numberinpage = Integer.parseInt(number);
            pageSize = numberinpage;
        }
        int totalfeedback = list.size();
        List<Integer> listint = new ArrayList<>();
        listint.add((int) Math.ceil((double) totalfeedback / 100 * 10));
        listint.add((int) Math.ceil((double) totalfeedback / 100 * 30));
        listint.add((int) Math.ceil((double) totalfeedback / 100 * 50));
        listint.add((int) Math.ceil((double) totalfeedback / 100 * 70));
        listint.add((int) Math.ceil((double) totalfeedback / 100 * 100));
        int totalPages = totalfeedback % pageSize == 0? totalfeedback/pageSize: (totalfeedback/pageSize) + 1;
        int start = (page - 1)*pageSize;
        int end = page*pageSize > totalfeedback ? totalfeedback : page*pageSize;
        list = dao.getListByPage(list, start, end);
        request.setAttribute("listint", listint);
        request.setAttribute("searchKeyword", search_raw);
        request.setAttribute("filterStatus", status_raw);
        request.setAttribute("statusresponse", status_res);
        request.setAttribute("currentPage", page);
        request.setAttribute("pagesize", number);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("date1", date_1);
        request.setAttribute("date2", date_2);
        request.setAttribute("listfeedback", list);
        request.getRequestDispatcher("feedbacks-management.jsp").forward(request, response);
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
