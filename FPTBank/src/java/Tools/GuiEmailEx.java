/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Tools;

import static controller.sendMail.guiMail;
import dal.LoanDAO;
import dal.SavingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "GuiEmailEx", urlPatterns = {"/seller/emailEx"})
public class GuiEmailEx extends HttpServlet {

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
            out.println("<title>Servlet GuiEmailEx</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GuiEmailEx at " + request.getContextPath() + "</h1>");
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

        String saveid = request.getParameter("saveID");
        int id = Integer.parseInt(saveid);
        SavingDAO savdao = new SavingDAO();
        String emailnhan = savdao.getEmailByID(id);

        boolean ismail = guiMail(emailnhan);
        if (ismail) {
            session.setAttribute("message", "Send email successfully!");

        }

        savdao.updateStatus(id, true);
        response.sendRedirect("showcalsaving");
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

        HttpSession session = request.getSession();

        String loanID = request.getParameter("loanID");
        int id = Integer.parseInt(loanID);
        SavingDAO savdao = new SavingDAO();
        LoanDAO loandao = new LoanDAO();
        String emailnhan = loandao.getEmailByID(id);
        boolean ismail = guiMail(emailnhan);
        if (ismail) {
            session.setAttribute("message", "Send email successfully!");

        }
        loandao.updateStatus(id, true);
        response.sendRedirect("showcalloan");

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
