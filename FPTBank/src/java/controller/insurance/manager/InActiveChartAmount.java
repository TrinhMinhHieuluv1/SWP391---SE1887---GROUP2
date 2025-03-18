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
@WebServlet(name="InActiveChartAmount", urlPatterns={"/insurance/InactiveChartAmount"})
public class InActiveChartAmount extends HttpServlet {
   
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
            out.println("<title>Servlet InActiveChartAmount</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InActiveChartAmount at " + request.getContextPath () + "</h1>");
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
      response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        InsuranceDAO insudao = new InsuranceDAO();
        HttpSession session = request.getSession();
        int insuranceID = (int) session.getAttribute("uid");
        List<Insurance> insuranceList = insudao.getAllInsuranceByProviderID(insuranceID);
        int Inactive = 0;
        for (Insurance insurance : insuranceList) {
            if (!insurance.isStatus()) {
                Inactive++;
            }
        }
        if (Inactive == 0) {
            response.getWriter().write("{\"error\": \"No data valiable !!\"}");
            return;
        }

       //chart coverate
        int amount1 = 0;
        int amount2 = 0;
        int amount4 = 0;
        int amount6 = 0;
        int amount8 = 0;
        int amount10 = 0;
        for (Insurance insurance : insuranceList) {
            if(!insurance.isStatus()){
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
           
        }
        int totalCoverate = Inactive;
        String jsonResponse = String.format(
                "{\"data7\": [%d, %d, %d, %d, %d, %d], \"total_Amount\": %d}",
                amount1, amount2, amount4, amount6,amount8,amount10, totalCoverate
        );
        response.getWriter().write(jsonResponse); // Gửi dữ liệu JSON về client
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
