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
import model.Feedback;

/**
 *
 * @author ACER
 */
@WebServlet(name = "AddResponse", urlPatterns = {"/seller/addresponse"})
public class AddResponse extends HttpServlet {

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
            out.println("<title>Servlet AddResponse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddResponse at " + request.getContextPath() + "</h1>");
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
        FeedbackDAO dao = new FeedbackDAO();
        String fid_raw = request.getParameter("FID");

        Feedback feedback = null;
        String error = "";

        if (fid_raw != null && !fid_raw.isEmpty()) {
            try {
                int fid = Integer.parseInt(fid_raw);
                feedback = dao.findFBByfID(fid);

                if (feedback == null) {
                    error = "Feedback not found.";
                }
            } catch (NumberFormatException e) {
                error = "Invalid Feedback ID.";
            }
        } else {
            error = "Feedback ID is missing.";
        }

        request.setAttribute("error", error);
        request.setAttribute("feedbackre", feedback);
        request.getRequestDispatcher("addresponse.jsp").forward(request, response);
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
        FeedbackDAO dao = new FeedbackDAO();
        String fid_raw = request.getParameter("FID");
        String text = request.getParameter("response");

        String error = "";
//        PrintWriter out = response.getWriter();
//        out.print(text);
        if (fid_raw == null || fid_raw.isEmpty() || text == null || text.trim().isEmpty()) {
            error = "Response cannot be empty.";
        } else {
            try {
                int fid = Integer.parseInt(fid_raw);
                boolean updated = dao.updateResponse(fid, text);
                if (updated) {
                    error = "Response added successfully.";
                } else {
                    error = "Failed to add response.";
                }
            } catch (NumberFormatException e) {
                error = "Invalid Feedback ID format.";
            }
        }

        response.sendRedirect("managefeedback?message=" + error);
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
