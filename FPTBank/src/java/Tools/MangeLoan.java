/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Tools;

import dal.LoanDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.LoanAccount;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "MangeLoan", urlPatterns = {"/seller/mangeloan"})
public class MangeLoan extends HttpServlet {

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
            out.println("<title>Servlet MangeLoan</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MangeLoan at " + request.getContextPath() + "</h1>");
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
    LoanDAO a = new LoanDAO();
    String sortOrderDate = request.getParameter("sortOrderDate");
    String status = request.getParameter("status");
    String searchDate = request.getParameter("searchDate");
    String searchSav = request.getParameter("searchSav");
    String sortOrder = request.getParameter("sortOrder");

    // Định dạng lại chuỗi searchKeyword
    if (searchSav != null) {
        searchSav = searchSav.trim(); // Xóa dấu cách đầu và cuối
        searchSav = searchSav.replaceAll("\\s+", " ");
    }

    int page = 1; // trang đầu tiên
    int pageSize = 5; // 1 trang có 10 users
    if (request.getParameter("page1") != null) {
        page = Integer.parseInt(request.getParameter("page1"));
    }

    try {
        int totalSav = 0;
        List<LoanAccount> sortedList = new ArrayList<>();

        if (sortOrderDate != null && !sortOrderDate.isEmpty()) {
            sortedList = a.sortByDateLoan(page, pageSize, "desc".equals(sortOrderDate));
            totalSav = a.getAllLoanAccounts().size();
            request.setAttribute("sortOrderDate", sortOrderDate);
        } else if (status != null && !status.isEmpty()) {
            sortedList = a.findByStatusLoan("true".equals(status), page, pageSize);
            totalSav = a.getTotalLoansByStatus(status);
            request.setAttribute("status", status);
        } else if (searchDate != null && !searchDate.isEmpty()) {
            sortedList = a.searchByDateLoan(searchDate, page, pageSize);
            totalSav = a.getTotalLoansAfterDateSearch(searchDate);
            request.setAttribute("searchDate", searchDate);
        } else if (searchSav != null && !searchSav.isEmpty()) {
            sortedList = a.searchByNameLoan(searchSav, page, pageSize);
            totalSav = a.getTotalLoansAfterSearching(searchSav);
            request.setAttribute("searchSav", searchSav);
        } else if (sortOrder != null && !sortOrder.isEmpty()) {
            sortedList = a.sortByAmountLoan(page, pageSize, "desc".equals(sortOrder));
            totalSav = a.getAllLoanAccounts().size();
            request.setAttribute("sortOrder", sortOrder);
        } else {
            sortedList = a.getAllLoanAccounts();
            totalSav = a.getAllLoanAccounts().size();
        }

        int totalPages = (int) Math.ceil((double) totalSav / pageSize);

        // set phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Truyền lại các tham số tìm kiếm vào JSP
        request.setAttribute("searchSav", searchSav);
        request.setAttribute("sortOrder", sortOrder);
        request.setAttribute("sortOrderDate", sortOrderDate);
        request.setAttribute("status", status);
        request.setAttribute("searchDate", searchDate);

        request.setAttribute("ListSav2", sortedList);

        request.getRequestDispatcher("loan-manage.jsp").forward(request, response);

    } catch (Exception e) {
        e.printStackTrace();
        // Xử lý ngoại lệ và hiển thị thông báo lỗi phù hợp
        request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
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
