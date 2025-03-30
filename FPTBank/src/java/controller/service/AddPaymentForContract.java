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
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import model.Contract;
import model.LoanPayment;
import java.time.LocalDate;
import java.sql.Date;

/**
 *
 * @author HP
 */
@WebServlet(name = "AddPaymentForContract", urlPatterns = {"/add-payment-for-contract"})
public class AddPaymentForContract extends HttpServlet {

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
            out.println("<title>Servlet AddPaymentForContract</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddPaymentForContract at " + request.getContextPath() + "</h1>");
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
        LoanPaymentDAO lpdao = new LoanPaymentDAO();

        //Get contract need to add payment
        String ContractID_raw = request.getParameter("ContractID");
        int ContractID = 0;
        try {
            ContractID = Integer.parseInt(ContractID_raw);
        } catch (NumberFormatException e) {
        }

        //Add payment
        if (ContractID != 0) {
            Contract contract = ctdao.selectAContractByID(ContractID);
            //Add payment for contract don't use monthly payment
            if (!contract.isMonthlyPayment()) {
                LoanPayment lpToAdd = new LoanPayment(0, contract, Date.valueOf(LocalDate.now().plusMonths(contract.getPeriod())), null, contract.getAmount().multiply(BigDecimal.valueOf(1 + contract.getInterestRate()*contract.getPeriod()/1200)).setScale(0, RoundingMode.CEILING), null, "Pending");
                lpdao.addALoanPayment(lpToAdd);
            } //Add payment for contract use monthly payment
            else {
                if (contract.getMonthlyPaymentType().equals("Fixed")) {
                    for (int i = 1; i <= contract.getPeriod(); i++) {
                        BigDecimal total = contract.getAmount().multiply(BigDecimal.ONE.add(new BigDecimal(contract.getInterestRate() / 1200 * contract.getPeriod()))).setScale(0, RoundingMode.CEILING);
                        LoanPayment lpToAdd = new LoanPayment(0, contract,
                                Date.valueOf(LocalDate.now().plusMonths(i)),
                                null,
                                total.divide(BigDecimal.valueOf(contract.getPeriod()), 0, RoundingMode.CEILING),
                                null,
                                "Pending");
                        lpdao.addALoanPayment(lpToAdd);
                    }
                } else {
                    BigDecimal interestRatePerMonth = new BigDecimal(String.valueOf(contract.getInterestRate())).divide(new BigDecimal(1200), 10, RoundingMode.CEILING);
                    BigDecimal principalPerMonth = contract.getAmount().divide(new BigDecimal(contract.getPeriod()), 2, RoundingMode.CEILING);
                    BigDecimal remainingAmount = contract.getAmount();
                    for (int i = 1; i <= contract.getPeriod(); i++) {
                        BigDecimal interestForMonth = remainingAmount.multiply(interestRatePerMonth);
                        BigDecimal totalPayment = principalPerMonth.add(interestForMonth);
                        remainingAmount = remainingAmount.subtract(principalPerMonth);
                        LoanPayment lpToAdd = new LoanPayment(0, contract,
                                Date.valueOf(LocalDate.now().plusMonths(i)),
                                null,
                                totalPayment.setScale(0, RoundingMode.CEILING),
                                null,
                                "Pending");
                        lpdao.addALoanPayment(lpToAdd);
                    }
                }
            }
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
