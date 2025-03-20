/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.contract.manager;

import controller.sendMail;
import static controller.sendMail.guiMail;
import dal.ContractDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Contract;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="ShowContract", urlPatterns={"/manager/rejected"})
public class SendRejected extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet ShowContract</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowContract at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//      ContractDAO ctdao = new ContractDAO();
//        List<Contract> contractAll = ctdao.selectAllContract();
//        
//        int page = 1; // trang đầu tiên
//        int pageSize = 10; // 1 trang có 10 users
//        if (request.getParameter("page") != null) {
//            page = Integer.parseInt(request.getParameter("page"));
//        }
// List<Insurance> sortedList = a.getAllInsuranceByProviderIDByPage(insuranceID, page, pageSize);
//        int loanSize = a.getInsuranceByProviderID(insuranceID).size();
//
//        int totalPages = (int) Math.ceil((double) loanSize / pageSize);
//
//        // set phân trang
//        request.setAttribute("currentPage", page);
//        request.setAttribute("totalPages", totalPages);
//
//     request.setAttribute("contractList", contractAll);
//        request.getRequestDispatcher("manageContract.jsp").forward(request, response);
//        
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
                HttpSession session = request.getSession();

        int contractID = Integer.parseInt(request.getParameter("contractID"));
        String noidung = request.getParameter("noidung");
        String email = request.getParameter("emailcus");

        ContractDAO contractDAO = new ContractDAO();

        // Gửi email cho khách hàng
        boolean isMailSent = sendMail.guiMailRejected(email, noidung,contractID);

        // Cập nhật trạng thái hợp đồng thành "Rejected"
        boolean isUpdated = contractDAO.updateStatus(contractID, 4);

        if (isMailSent && isUpdated) {
            session.setAttribute("message", "Rejected successfully! Email sent.");
        } else {
            session.setAttribute("error", "Failed to reject contract.");
        }

        response.sendRedirect("contract-management-for-manager");
    }
       
    

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
