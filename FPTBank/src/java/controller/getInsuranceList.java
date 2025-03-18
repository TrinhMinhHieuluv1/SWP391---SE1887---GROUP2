/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.InsuranceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import model.Insurance;

/**
 *
 * @author HP
 */
@WebServlet(name="getInsuranceList", urlPatterns={"/get-insurance-list"})
public class getInsuranceList extends HttpServlet {
   
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
            out.println("<title>Servlet getInsuranceList</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet getInsuranceList at " + request.getContextPath () + "</h1>");
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
        InsuranceDAO idao = new InsuranceDAO();
        String Amount_raw = request.getParameter("Amount");
        Amount_raw = Amount_raw.replace(".", "");
        String Type = request.getParameter("Type");
        BigDecimal Amount = BigDecimal.ZERO;
        DecimalFormat df = new DecimalFormat("#,###");
        try {
            Amount = BigDecimal.valueOf(Long.parseLong(Amount_raw));
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        if (Type.equals("Secured")) {
            Type = "Secured Loan";
        } else if (Type.equals("Unsecured")) {
            Type = "Unsecured Loan";
        }
        List<Insurance> insuranceList = idao.getInsuranceList(Amount, Type);
        if (insuranceList.size() > 0) {
            response.getWriter().println("<tr>\n" +
                                        "<th></th>\n" +
                                        "<th>Insurance Name</th>\n" +
                                        "<th>Fee Rate (%)</th>\n" +
                                        "<th>Coverage Rate (months)</th>\n" +
                                        "<th>Max Amount For Loan</th>\n" +
                                                                                "</tr>");
            for (Insurance insurance : insuranceList) {
                response.getWriter().println("<tr>\n"
                        + "<th><input name=\"choosenInsurance\" type=\"radio\" "
                                + "value=\"" + insurance.getInsuranceID()
                                + "-" + insurance.getInsuranceName()
                                + "-" + insurance.getFeeRate()
                                + "-" + insurance.getCoverageRate()
                                + "-" + df.format(BigDecimal.valueOf(insurance.getMaxAmountOfLoan()))
                                + "\"></th>\n"
                        + "<th>" + insurance.getInsuranceName() + "</th>\n"
                        + "<th>" + insurance.getFeeRate() + "</th>\n"
                        + "<th>" + insurance.getCoverageRate() +"</th>\n"
                        + "<th>" + df.format(BigDecimal.valueOf(insurance.getMaxAmountOfLoan())) + "</th>\n"
                        + "</tr>");
            }
        }
        else {
            response.getWriter().println("<tr><h5 style=\"color: red\">We don't provide any insurance for your loan requirement. Try to change your requirement!</h5></tr>");
        }
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
        processRequest(request, response);
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
