/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.service;

import dal.ContractDAO;
import dal.InsuranceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Contract;
import model.Insurance;

/**
 *
 * @author HP
 */
@WebServlet(name = "UpdateContractForCustomer", urlPatterns = {"/update-contract-for-customer"})
public class UpdateContractForCustomer extends HttpServlet {

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
            out.println("<title>Servlet UpdateContractForCustomer</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateContractForCustomer at " + request.getContextPath() + "</h1>");
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
        ContractDAO ctdao = new ContractDAO();

        String ContractID_raw = request.getParameter("ContractID");
        int ContractID = 0;
        if (ContractID_raw == null || ContractID_raw.isEmpty()) {
            response.sendRedirect("/timibank/contract-management-for-customer");
        } else {
            try {
                ContractID = Integer.parseInt(ContractID_raw);
            } catch (NumberFormatException e) {
                response.sendRedirect("/timibank/contract-management-for-customer");
            }
        }
        Contract contractToUpdate = ctdao.selectAContractByID(ContractID);
        if (contractToUpdate == null) {
            response.sendRedirect("/timibank/contract-management-for-customer");
        } else {
            request.setAttribute("contractToUpdate", contractToUpdate);
            request.getRequestDispatcher("updateContract.jsp").forward(request, response);
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
        InsuranceDAO idao = new InsuranceDAO();
        ContractDAO ctdao = new ContractDAO();
        
        //Get Contract
        String ContractID_raw = request.getParameter("ContractID");
        int ContractID = 0;
        try {
            ContractID = Integer.parseInt(ContractID_raw);
        } catch (NumberFormatException e) {
        }
        Contract contractToUpdate = ctdao.selectAContractByID(ContractID);
        
        //Get Description
        String Description = request.getParameter("Description");

        //Get Monthly Payment
        String MonthlyPayment_raw = request.getParameter("MonthlyPayment");
        boolean MonthlyPayment;
        String MonthlyPaymentType = "";
        if (MonthlyPayment_raw != null) {
            MonthlyPayment = true;
            MonthlyPaymentType = request.getParameter("MonthlyPaymentType");
            if (!MonthlyPaymentType.equals("Fixed")) {
                MonthlyPaymentType = "Reducing Balance";
            }
        } else {
            MonthlyPayment = false;
        }

        //Get Insurance
        String InsuranceID_raw = request.getParameter("Insurance").split("-")[0];
        int InsuranceID = -1;
        Insurance Insurance = null;
        try {
            InsuranceID = Integer.parseInt(InsuranceID_raw);
            Insurance = idao.getInsuranceByID(InsuranceID);
        } catch (NumberFormatException e) {
        }
        
        contractToUpdate.setDescription(Description);
        contractToUpdate.setMonthlyPayment(MonthlyPayment);
        if (MonthlyPayment) {
            contractToUpdate.setMonthlyPaymentType(MonthlyPaymentType);
        } else {
            contractToUpdate.setMonthlyPaymentType(null);
        }
        contractToUpdate.setInsurance(Insurance);
        contractToUpdate.setInsuranceCoverage(Insurance.getCoverageRate());
        ctdao.updateAContract(contractToUpdate);
        response.sendRedirect("/timibank/contract-management-for-customer?fromUpdate=true");
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
