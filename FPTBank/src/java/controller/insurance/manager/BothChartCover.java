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
@WebServlet(name="BothChartCover", urlPatterns={"/insurance/BothChartCover"})
public class BothChartCover extends HttpServlet {
   
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
            out.println("<title>Servlet BothChartCover</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BothChartCover at " + request.getContextPath () + "</h1>");
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
        
        if (insuranceList.isEmpty()) {
            response.getWriter().write("{\"error\": \"No data valiable !!\"}");
            return;
        }

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
        int totalCoverate = insuranceList.size();
        String jsonResponse = String.format(
                "{\"data21\": [%d, %d, %d, %d, %d, %d], \"total_Cover\": %d}",
                coverate30, coverate50, coverate60, coverate70,coverate80,coverate100, totalCoverate
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
