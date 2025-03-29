/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.service;

import dal.ContractDAO;
import dal.LoanPaymentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Contract;
import model.Customer;
import model.LoanPayment;

/**
 *
 * @author HP
 */
@WebServlet(name = "LoanPaymentManagementForCustomer", urlPatterns = {"/loan-payment-management-for-customer"})
public class LoanPaymentManagementForCustomer extends HttpServlet {

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
            out.println("<title>Servlet LoanPaymentManagementForCustomer</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoanPaymentManagementForCustomer at " + request.getContextPath() + "</h1>");
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
        LoanPaymentDAO lpdao = new LoanPaymentDAO();
        ContractDAO ctdao = new ContractDAO();

        if (account == null) {
            response.sendRedirect("/timibank/home?RoleErr=true");
            return;
        }
        String groupByContract = request.getParameter("groupByContract");
        if (groupByContract != null && !groupByContract.isEmpty() && groupByContract.equals("true")) {
            request.setAttribute("groupByContract", "true");
            HashMap<Contract, List<LoanPayment>> contractMap = new HashMap<>();
            List<Contract> contractListDoing = ctdao.selectContractListWithConditions(account.getCustomerId(), null, null, 3, null);
            List<Contract> contractListComplete = ctdao.selectContractListWithConditions(account.getCustomerId(), null, null, 5, null);
            for (Contract contract : contractListDoing) {
                if (!contract.getType().equals("Saving")) {
                    contractMap.put(contract, lpdao.selectAllLoanPaymentWithConditions(contract.getContractID(), null, null));
                }
            }
            for (Contract contract : contractListComplete) {
                if (!contract.getType().equals("Saving")) {
                    contractMap.put(contract, lpdao.selectAllLoanPaymentWithConditions(contract.getContractID(), null, null));
                }
            }
            List<Map.Entry<Contract, List<LoanPayment>>> entryListBefore = new ArrayList<>(contractMap.entrySet());
            //Pagination
            String pageSize_raw = request.getParameter("pageSize");
            int pageSize;
            if (entryListBefore.size() <= 100) {
                pageSize = 10;
            } else {
                pageSize = (int) Math.round((double) entryListBefore.size() / 100) * 10;
            }
            try {
                pageSize = Integer.parseInt(pageSize_raw);
            } catch (NumberFormatException e) {
            }
            int totalPages = (int) Math.ceil((double) entryListBefore.size() / pageSize);
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

            List<Map.Entry<Contract, List<LoanPayment>>> entryList = entryListBefore.subList((page - 1) * pageSize, Math.min(entryListBefore.size(), page * pageSize));
            int[] pageSizeArray = {(int) Math.round((double) entryListBefore.size() / 100) * 10,
                (int) Math.round((double) entryListBefore.size() / 100) * 20,
                (int) Math.round((double) entryListBefore.size() / 100) * 30,
                (int) Math.round((double) entryListBefore.size() / 100) * 40,
                (int) Math.round((double) entryListBefore.size() / 100) * 50};
            request.setAttribute("numberOfContract", entryList.size());
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("pageSizeArray", pageSizeArray);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("entryList", entryList);
        } else {
            request.setAttribute("groupByContract", "false");
            String Status = request.getParameter("Status");
            String SortBy = request.getParameter("SortBy");
            List<LoanPayment> lpListBefore = lpdao.selectAllLoanPaymentWithConditions(0, Status, SortBy);
            //Pagination
            String pageSize_raw = request.getParameter("pageSize");
            int pageSize;
            if (lpListBefore.size() <= 100) {
                pageSize = 10;
            } else {
                pageSize = (int) Math.round((double) lpListBefore.size() / 100) * 10;
            }
            try {
                pageSize = Integer.parseInt(pageSize_raw);
            } catch (NumberFormatException e) {
            }
            int totalPages = (int) Math.ceil((double) lpListBefore.size() / pageSize);
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

            List<LoanPayment> lpList = lpListBefore.subList((page - 1) * pageSize, Math.min(lpListBefore.size(), page * pageSize));
            int[] pageSizeArray = {(int) Math.round((double) lpListBefore.size() / 100) * 10,
                (int) Math.round((double) lpListBefore.size() / 100) * 20,
                (int) Math.round((double) lpListBefore.size() / 100) * 30,
                (int) Math.round((double) lpListBefore.size() / 100) * 40,
                (int) Math.round((double) lpListBefore.size() / 100) * 50};
            request.setAttribute("numberOfLoanPayment", lpListBefore.size());
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("pageSizeArray", pageSizeArray);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("lpList", lpList);
        }
        request.getRequestDispatcher("loanPaymentManagementForCustomer.jsp").forward(request, response);

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
