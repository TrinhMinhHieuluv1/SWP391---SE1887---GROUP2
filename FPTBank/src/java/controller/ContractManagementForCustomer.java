/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import model.Customer;

/**
 *
 * @author HP
 */
@WebServlet(name = "ContractManagementForCustomer", urlPatterns = {"/contract-management-for-customer"})
public class ContractManagementForCustomer extends HttpServlet {

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
            out.println("<title>Servlet ContractManagementForCustomer</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContractManagementForCustomer at " + request.getContextPath() + "</h1>");
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
        Customer account = (Customer) session.getAttribute("account");
        ContractDAO ctdao = new ContractDAO();

        //Show message after a news updated
        String fromAdd = request.getParameter("fromAdd");
        if (fromAdd != null && fromAdd.equals("true")) {
            String message = "Add successfully!";
            request.setAttribute("message", message);
        }

        // Get parameter
        String sortBy = request.getParameter("sortBy");
        String filterStatus_raw = request.getParameter("filterStatus");
        int filterStatus = 0;
        try {
            filterStatus = Integer.parseInt(filterStatus_raw);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        String filterType = request.getParameter("filterType");
        
        String filterMonthlyPayment = request.getParameter("filterMonthlyPayment");

        // Get news list by conditions
        List<Contract> contractListBeforePagition = ctdao.selectContractListWithConditions(account.getCustomerId(), filterType, filterMonthlyPayment, filterStatus, sortBy);

        //Pagination
        String pageSize_raw = request.getParameter("pageSize");
        int pageSize;
        if (contractListBeforePagition.size() <= 100) {
            pageSize = 10;
        } else {
            pageSize = (int) Math.round((double) contractListBeforePagition.size() / 100) * 10;
        }
        try {
            pageSize = Integer.parseInt(pageSize_raw);
        } catch (NumberFormatException e) {
        }
        int totalPages = (int) Math.ceil((double) contractListBeforePagition.size() / pageSize);
        int page = 1;
        String pageNum_raw = request.getParameter("page");
        if (pageNum_raw != null && !pageNum_raw.isEmpty()) {
            try {
                page = Integer.parseInt(pageNum_raw);
                if (page < 1) {
                    page = 1;
                }
                if (page > totalPages) {
                    page = totalPages;
                }
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        List<Contract> contractList = contractListBeforePagition.subList((page - 1) * pageSize, Math.min(contractListBeforePagition.size(), page * pageSize));
        int[] pageSizeArray = {(int) Math.round((double) contractListBeforePagition.size() / 100) * 10,
            (int) Math.round((double) contractListBeforePagition.size() / 100) * 20,
            (int) Math.round((double) contractListBeforePagition.size() / 100) * 30,
            (int) Math.round((double) contractListBeforePagition.size() / 100) * 40,
            (int) Math.round((double) contractListBeforePagition.size() / 100) * 50};
        request.setAttribute("numberOfContract", contractListBeforePagition.size());
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("pageSizeArray", pageSizeArray);
        request.setAttribute("contractList", contractList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("filterStatus", filterStatus);
        request.setAttribute("filterType", filterType);
        request.setAttribute("filterMonthlyPayment", filterMonthlyPayment);
        request.getRequestDispatcher("contractManagementForCustomer.jsp").forward(request, response);
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
