/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
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
@WebServlet(name = "FilterFeedback", urlPatterns = {"/filterfeedback"})
public class FilterFeedback extends HttpServlet {

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
            out.println("<title>Servlet FilterFeedback</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterFeedback at " + request.getContextPath() + "</h1>");
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
        String date_raw = request.getParameter("date");
        String search = request.getParameter("search");
        CustomerDAO cdao = new CustomerDAO();
        FeedbackDAO dao = new FeedbackDAO();
        String error = "";
        if (date_raw == null && (search.isEmpty() || search == null)) {
            error = "Can't filter";
            request.setAttribute("error", error);
            request.getRequestDispatcher("feedback.jsp").forward(request, response);
            return;
        }
        if (date_raw != null && (search == null || search.isEmpty())) {
            try {
                if (date_raw == null || date_raw.trim().isEmpty()) {
                    throw new NumberFormatException();
                }

                String[] parts = date_raw.trim().split("-");

                if (parts.length != 3) {
                    throw new NumberFormatException();
                }

                int year = Integer.parseInt(parts[0].trim());
                int month = Integer.parseInt(parts[1].trim());
                int date = Integer.parseInt(parts[2].trim());

                int uid = (int) session.getAttribute("uid");
                int cid = cdao.getCustomerIdByUserId(uid);

                List<Feedback> list = dao.getFeedbacksFromDate(date, month, year, cid);
                session.setAttribute("listfeedback", list);

                request.getRequestDispatcher("feedback.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                error = "Can't filter";
                request.setAttribute("error", error);
                request.getRequestDispatcher("feedback.jsp").forward(request, response);
            }

        } else if (search != null && !search.isEmpty()) {
            List<Feedback> list2 = dao.searchFeedbackByMessage(search);
            session.setAttribute("listfeedback", list2);
            request.getRequestDispatcher("feedback.jsp").forward(request, response);
        }

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
