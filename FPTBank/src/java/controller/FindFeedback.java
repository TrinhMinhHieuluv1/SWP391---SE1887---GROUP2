/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Feedback;

/**
 *
 * @author ADMIN
 */
public class FindFeedback extends HttpServlet {

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
            out.println("<title>Servlet ShowFB</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowFB at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("about").forward(request, response);
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
        String idParam = request.getParameter("id"); // Lấy giá trị "id" từ query string
        String dateParam = request.getParameter("date"); // Lấy giá trị "date" từ query string

        try {
            if (idParam != null) {
                // Tìm feedback theo ID
                int id = Integer.parseInt(idParam);
                Feedback feedback = dao.findFBByID(id);

                // Đưa feedback vào request attribute
                if (feedback != null) {
                    request.setAttribute("list", List.of(feedback)); // Đặt 1 phản hồi vào danh sách
                } else {
                    request.setAttribute("list", List.of()); // Đặt danh sách trống nếu không tìm thấy
                }
            } else if (dateParam != null) {
                // Tìm feedback theo ngày
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateParam);

                List<Feedback> feedbackList = dao.findFBByDate(date);
                request.setAttribute("list", feedbackList); // Đưa danh sách phản hồi vào request attribute
            } else {
                // Nếu không có tham số, gửi danh sách trống
                request.setAttribute("list", List.of());
            }

            // Chuyển tiếp request tới JSP
            request.getRequestDispatcher("about").forward(request, response);
           
        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request parameters.");
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

