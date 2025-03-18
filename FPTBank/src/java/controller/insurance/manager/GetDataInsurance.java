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
import java.util.List;
import model.Insurance;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "GetDataInsurance", urlPatterns = {"/insurance/getdatainsurance"})
public class GetDataInsurance extends HttpServlet {

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
            out.println("<title>Servlet GetDataInsurance</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetDataInsurance at " + request.getContextPath() + "</h1>");
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
        InsuranceDAO insudao = new InsuranceDAO();
        HttpSession session = request.getSession();
        int insuranceID = (int) session.getAttribute("uid");
        List<Insurance> insuranceList = insudao.getAllInsuranceByProviderID(insuranceID);

        //chart type
        int seLoan = 0;
        int unseLoan = 0;
        for (Insurance insurance : insuranceList) {
            if (insurance.getType().equalsIgnoreCase("Secured Loan")) {
                seLoan++;
            } else {
                unseLoan++;
            }
        }
        int totalType = seLoan + unseLoan;
        double percentSeloan = totalType > 0 ? ((double) seLoan / totalType) * 100 : 0;
        double percentUnseloan = totalType > 0 ? ((double) unseLoan / totalType) * 100 : 0;
        request.getSession().setAttribute("percentSeloan", percentSeloan);
        request.getSession().setAttribute("percentUnseloan", percentUnseloan);

        //chart status          
        int active = 0;
        int inActive = 0;
        for (Insurance insurance : insuranceList) {
            if (insurance.isStatus()) {
                active++;
            } else {
                inActive++;
            }
        }
        int totalStatus = active + inActive;
        double percentActive = totalStatus > 0 ? ((double) active / totalStatus) * 100 : 0;
        double percentInActive = totalStatus > 0 ? ((double) inActive / totalStatus) * 100 : 0;
        request.getSession().setAttribute("percentActive", percentActive);
        request.getSession().setAttribute("percentInActive", percentInActive);

        //chart feerate        
        int feerate10 = 0;
        int feerate20 = 0;
        int feerate30 = 0;
        int feerate100 = 0;
        for (Insurance insurance : insuranceList) {
            if (insurance.getFeeRate() <= 5) {
                feerate10++;
            } else if (insurance.getFeeRate() > 5 && insurance.getFeeRate() <= 10) {
                feerate20++;
            } else if (insurance.getFeeRate() > 10 && insurance.getFeeRate() <= 15) {
                feerate30++;
            } else {
                feerate100++;
            }
        }
        int totalFeerate = feerate10 + feerate20 + feerate30 + feerate100;

        request.getSession().setAttribute("feerate10", feerate10);
        request.getSession().setAttribute("feerate20", feerate20);
        request.getSession().setAttribute("feerate30", feerate30);
        request.getSession().setAttribute("feerate100", feerate100);
        request.getSession().setAttribute("totalFeerate", totalFeerate);

        //chart coverate
        int coverate30 = 0;
        int coverate50 = 0;
        int coverate60 = 0;
        int coverate70 = 0;
        int coverate80 = 0;
        int coverate100 = 0;
        for (Insurance insurance : insuranceList) {
            if (insurance.getCoverageRate()<= 30) {
                coverate30++;
            } else if (insurance.getCoverageRate()> 30 && insurance.getCoverageRate() <= 50) {
                coverate50++;
            } else if (insurance.getCoverageRate()> 50 && insurance.getCoverageRate() <= 60) {
                coverate60++;
            }  else if (insurance.getCoverageRate()> 60 && insurance.getCoverageRate() <= 70) {
                coverate70++;
            }  else if (insurance.getCoverageRate()> 70 && insurance.getCoverageRate() <= 80) {
                coverate80++;
            }  else {
                coverate100++;
            } 
        }
        int totalCoverate = coverate30 + coverate50 + coverate60 + coverate70+coverate80+coverate100;


        request.getSession().setAttribute("coverate30", coverate30);
        request.getSession().setAttribute("coverate50", coverate50);
        request.getSession().setAttribute("coverate60", coverate60);
        request.getSession().setAttribute("coverate70", coverate70);
        request.getSession().setAttribute("coverate80", coverate80);
        request.getSession().setAttribute("coverate100", coverate100);
        request.getSession().setAttribute("totalCoverate", totalCoverate);
        
        
        
        
         //chart amount
        int amount1 = 0;
        int amount2 = 0;
        int amount4 = 0;
        int amount6 = 0;
        int amount8 = 0;
        int amount10 = 0;
        for (Insurance insurance : insuranceList) {
            if (insurance.getMaxAmountOfLoan()<= 50000000) {
                amount1++;
            } else if (insurance.getMaxAmountOfLoan()> 50000000 && insurance.getMaxAmountOfLoan() <= 100000000) {
                amount2++;
            } else if (insurance.getMaxAmountOfLoan()> 100000000 && insurance.getMaxAmountOfLoan() <= 300000000) {
                amount4++;
            }  else if (insurance.getMaxAmountOfLoan()> 300000000 && insurance.getMaxAmountOfLoan() <= 500000000) {
                amount6++;
            }  else if (insurance.getMaxAmountOfLoan()> 500000000 && insurance.getMaxAmountOfLoan() <= 1000000000) {
                amount8++;
            }  else {
                amount10++;
            } 
        }
        int ToltalAmount = amount1 + amount2 + amount4 + amount6+amount8+amount10;


        request.getSession().setAttribute("amount1", amount1);
        request.getSession().setAttribute("amount2", amount2);
        request.getSession().setAttribute("amount4", amount4);
        request.getSession().setAttribute("amount6", amount6);
        request.getSession().setAttribute("amount8", amount8);
        request.getSession().setAttribute("amount10", amount10);
        request.getSession().setAttribute("ToltalAmount", ToltalAmount);

        request.getRequestDispatcher("chartInsurance.jsp").forward(request, response);

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
