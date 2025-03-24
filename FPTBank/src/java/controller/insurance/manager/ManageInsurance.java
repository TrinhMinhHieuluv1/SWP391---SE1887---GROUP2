package controller.insurance.manager;

import dal.InsuranceDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Insurance;

/**
 * Servlet to manage insurance records, including search, sort, filter, and pagination.
 */
@WebServlet(name = "ManageInsurance", urlPatterns = {"/insurance/manageinsurance"})
public class ManageInsurance extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        InsuranceDAO insuranceDAO = new InsuranceDAO();
        int insuranceProviderID = (int) session.getAttribute("uid");

        // Get parameters for pagination, search, sort, and filter
        String searchIsu = request.getParameter("searchIsu");
        String typeIns = request.getParameter("typeIns");
        String status = request.getParameter("status");
        String sortFee = request.getParameter("sortFee");
        String coverageRate = request.getParameter("CoverageRate");
        String maxAmountOfLoan = request.getParameter("MaxAmountOfLoan");
        String pageStr = request.getParameter("page");
        String pageSizeStr = request.getParameter("pageSize");

        // Default values
        int page = 1;
        int pageSize = 10; // Default page size

        // Parse page and pageSize
        if (pageStr != null && !pageStr.isEmpty()) {
            page = Integer.parseInt(pageStr);
        }
        if (pageSizeStr != null && !pageSizeStr.isEmpty()) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        // Format search keyword
        if (searchIsu != null) {
            searchIsu = searchIsu.trim().replaceAll("\\s+", " ");
        }

        try {
            int totalInsu = 0;
            List<Insurance> sortedList = new ArrayList<>();

            // Apply filters, sorting, and search
            if (typeIns != null && !typeIns.isEmpty()) {
                sortedList = insuranceDAO.searchByTypeByPage(insuranceProviderID, typeIns, page, pageSize);
                totalInsu = insuranceDAO.getTotalAfterSearchByType(insuranceProviderID, typeIns);
                request.setAttribute("typeIns", typeIns);
            } else if (status != null && !status.isEmpty()) {
                sortedList = insuranceDAO.findByStatusByPage(insuranceProviderID, "true".equals(status), page, pageSize);
                totalInsu = insuranceDAO.getTotalAfterSearchStatus(insuranceProviderID, status);
                request.setAttribute("status", status);
            } else if (searchIsu != null && !searchIsu.isEmpty()) {
                sortedList = insuranceDAO.searchByNameByPage(insuranceProviderID, searchIsu, page, pageSize);
                totalInsu = insuranceDAO.getTotalAfterSearchByName(insuranceProviderID, searchIsu);
                request.setAttribute("searchIsu", searchIsu);
            } else if (sortFee != null && !sortFee.isEmpty()) {
                boolean isDescending = "desc".equals(sortFee);
                sortedList = insuranceDAO.sortByFeeRate(insuranceProviderID, page, pageSize, isDescending);
                totalInsu = insuranceDAO.getInsuranceByProviderID(insuranceProviderID).size();
                request.setAttribute("sortFee", sortFee);
            } else if (coverageRate != null && !coverageRate.isEmpty()) {
                boolean isDescending = "desc".equals(coverageRate);
                sortedList = insuranceDAO.sortByCoverageRate(insuranceProviderID, page, pageSize, isDescending);
                totalInsu = insuranceDAO.getInsuranceByProviderID(insuranceProviderID).size();
                request.setAttribute("CoverageRate", coverageRate);
            } else if (maxAmountOfLoan != null && !maxAmountOfLoan.isEmpty()) {
                sortedList = insuranceDAO.sortByMaxAmountOfLoan(insuranceProviderID, page, pageSize, "false".equals(maxAmountOfLoan));
                totalInsu = insuranceDAO.getInsuranceByProviderID(insuranceProviderID).size();
                request.setAttribute("MaxAmountOfLoan", maxAmountOfLoan);
            } else {
                sortedList = insuranceDAO.getAllInsuranceByProviderIDByPage(insuranceProviderID, page, pageSize);
                totalInsu = insuranceDAO.getInsuranceByProviderID(insuranceProviderID).size();
            }

            // Calculate total pages
            int totalPages = (int) Math.ceil((double) totalInsu / pageSize);

            // Set attributes for JSP
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("typeIns", typeIns);
            request.setAttribute("searchIsu", searchIsu);
            request.setAttribute("CoverageRate", coverageRate);
            request.setAttribute("MaxAmountOfLoan", maxAmountOfLoan);
            request.setAttribute("sortFee", sortFee);
            request.setAttribute("status", status);
            request.setAttribute("ListInsu", sortedList);

            // Forward to JSP
            request.getRequestDispatcher("manageInsurance.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        InsuranceDAO insuranceDAO = new InsuranceDAO();

        // Get parameters for status update
        String active = request.getParameter("active");
        String inactive = request.getParameter("inactive");

        // Update status
        if (active != null) {
            int id = Integer.parseInt(active);
            boolean updated = insuranceDAO.updateStatus(id, false);
            if (updated) {
                session.setAttribute("message", "Insurance Inactivated!");
            }
        } else if (inactive != null) {
            int id = Integer.parseInt(inactive);
            boolean updated = insuranceDAO.updateStatus(id, true);
            if (updated) {
                session.setAttribute("message", "Insurance Activated!");
            }
        }

        // Redirect back to the manage insurance page
        response.sendRedirect("manageinsurance");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet to manage insurance records with search, sort, filter, and pagination.";
    }
}