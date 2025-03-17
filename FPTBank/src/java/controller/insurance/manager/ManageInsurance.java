/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.insurance.manager;

import dal.InsuranceDAO;
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
import model.Insurance;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ManageInsurance", urlPatterns = {"/insurance/manageinsurance"})
public class ManageInsurance extends HttpServlet {

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
            out.println("<title>Servlet ManageInsurance</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageInsurance at " + request.getContextPath() + "</h1>");
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

        InsuranceDAO a = new InsuranceDAO();
       int insuranceID= (int) session.getAttribute("uid");

        String searchIsu = request.getParameter("searchIsu");
        String typeIns = request.getParameter("typeIns");
        String status = request.getParameter("status");
        String sortFee = request.getParameter("sortFee");
        String CoverageRate = request.getParameter("CoverageRate");
        String MaxAmountOfLoan = request.getParameter("MaxAmountOfLoan");

        int page = 1; // trang đầu tiên
        int pageSize = 10; // 1 trang có 10 users
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        // Định dạng lại chuỗi searchKeyword
        if (searchIsu != null) {
            searchIsu = searchIsu.trim(); // Xóa dấu cách đầu và cuối
            searchIsu = searchIsu.replaceAll("\\s+", " "); // Thay thế nhiều dấu cách bằng một dấu cách
        }

        try {
            int totalInsu = 0;
            List<Insurance> sortedList = new ArrayList<>();

            if (typeIns != null && !typeIns.isEmpty()) {
                if ("Secured Loan".equals(typeIns)) {
                    sortedList = a.searchByTypeByPage(insuranceID,typeIns, page, pageSize);  // Viết hàm này để sắp xếp giảm dần
                } else {
                    sortedList = a.searchByTypeByPage(insuranceID,typeIns, page, pageSize);   // Viết hàm này để sắp xếp tăng dần
                }
                totalInsu = a.getTotalAfterSearchByName(insuranceID,typeIns);
                request.setAttribute("typeIns", typeIns);

            } else if (status != null && !status.isEmpty()) {
                sortedList = a.findByStatusByPage(insuranceID,"true".equals(status), page, pageSize);
                totalInsu = a.getTotalAfterSearchStatus(insuranceID,status);
                request.setAttribute("status", status);

            } else if (searchIsu != null && !searchIsu.isEmpty()) {
                sortedList = a.searchByNameByPage(insuranceID,searchIsu, page, pageSize);
                totalInsu = a.getTotalAfterSearchByName(insuranceID,searchIsu);

                request.setAttribute("searchIsu", searchIsu);

            } else if (sortFee != null && !sortFee.isEmpty()) {
                boolean isDescending = "desc".equals(sortFee); // true nếu là "desc", false nếu là "asc"
                sortedList = a.sortByFeeRate(insuranceID,page, pageSize, isDescending);
                totalInsu = a.getInsuranceByProviderID(insuranceID).size();

                request.setAttribute("sortFee", sortFee);

            } else if (CoverageRate != null && !CoverageRate.isEmpty()) {
                boolean isDescending = "desc".equals(CoverageRate); // true nếu là "desc", false nếu là "asc"
                sortedList = a.sortByCoverageRate(insuranceID,page, pageSize, isDescending);
                totalInsu = a.getInsuranceByProviderID(insuranceID).size();

                request.setAttribute("CoverageRate", CoverageRate);
            } else if (MaxAmountOfLoan != null && !MaxAmountOfLoan.isEmpty()) {
                sortedList = a.sortByMaxAmountOfLoan(insuranceID,page, pageSize, "false".equals(MaxAmountOfLoan));
                totalInsu = a.getInsuranceByProviderID(insuranceID).size();

                request.setAttribute("MaxAmountOfLoan", MaxAmountOfLoan);

            } else {
                sortedList = a.getAllInsuranceByProviderIDByPage(insuranceID, page, pageSize);
                totalInsu = a.getInsuranceByProviderID(insuranceID).size();
            }

            int totalPages = (int) Math.ceil((double) totalInsu / pageSize);

            // set phân trang
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("typeIns", typeIns);
            request.setAttribute("searchIsu", searchIsu);
            request.setAttribute("CoverageRate", CoverageRate);
            request.setAttribute("MaxAmountOfLoan", MaxAmountOfLoan);
            request.setAttribute("sortFee", sortFee);
            request.setAttribute("status", status);
            request.setAttribute("ListInsu", sortedList);
            request.getRequestDispatcher("manageInsurance.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response); // Chuyển hướng đến trang lỗi
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
        HttpSession session = request.getSession();

// Lấy giá trị từ button "Active" hoặc "Inactive"
        String active = request.getParameter("active");
        String inactive = request.getParameter("inactive");

        InsuranceDAO a = new InsuranceDAO();

        if (active != null) {
            // Nếu bấm vào button "Active", cập nhật trạng thái thành false (Inactive)
            int id = Integer.parseInt(active);
            boolean updated = a.updateStatus(id, false); // Cập nhật trạng thái thành false
            if (updated) {
                session.setAttribute("message", "Insurance Inactivated!");
            }
        } else if (inactive != null) {
            // Nếu bấm vào button "Inactive", cập nhật trạng thái thành true (Active)
            int id = Integer.parseInt(inactive);
            boolean updated = a.updateStatus(id, true); // Cập nhật trạng thái thành true
            if (updated) {
                session.setAttribute("message", "Insurance Activated!");
            }
        }

// Chuyển hướng người dùng trở lại trang hiển thị danh sách bảo hiểm
        response.sendRedirect("showinsurance");

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
