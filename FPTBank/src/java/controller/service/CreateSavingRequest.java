/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.service;

import dal.ContractDAO;
import dal.ServiceItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import model.Contract;
import model.Customer;
import model.ServiceItem;
import org.apache.catalina.connector.Response;
import org.apache.poi.hpsf.Decimal;

/**
 *
 * @author HP
 */
@WebServlet(name = "CreateSavingRequest", urlPatterns = {"/create-saving-request"})
public class CreateSavingRequest extends HttpServlet {

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
            out.println("<title>Servlet CreateSavingRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateSavingRequest at " + request.getContextPath() + "</h1>");
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
        if (session.getAttribute("account") == null) {
            response.sendRedirect("/timibank/home?RoleErr=true");
            return;
        }
        request.getRequestDispatcher("createSavingRequest.jsp").forward(request, response);
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
        ServiceItemDAO sidao = new ServiceItemDAO();
        ContractDAO ctdao = new ContractDAO();
        HttpSession session = request.getSession();
        Customer account = (Customer) session.getAttribute("account");

        //Get Amount
        String Amount_raw = request.getParameter("Amount").replaceAll("\\.", "");
        BigDecimal Amount = BigDecimal.ZERO;
        try {
            Amount = BigDecimal.valueOf(Long.parseLong(Amount_raw));
        } catch (NumberFormatException e) {
        }

        //Get Service Item
        String ServiceItemID_raw = request.getParameter("ServiceItem").split("-")[0];
        int ServiceItemID = -1;
        ServiceItem ServiceItem = null;
        try {
            ServiceItemID = Integer.parseInt(ServiceItemID_raw);
            ServiceItem = sidao.selectAServiceItemByID(ServiceItemID);
        } catch (NumberFormatException e) {
        }

        //Get Period
        String Period_raw = request.getParameter("Period").split("-")[0];
        int Period = -1;
        try {
            Period = Integer.parseInt(Period_raw);
        } catch (NumberFormatException e) {
        }

        //Get Description
        String Description = request.getParameter("Description");

        //Insert Contract
        Contract contractToAdd
                = new Contract(0, account,
                        Amount, Period,
                        0, ServiceItem.getEarlyWithdrawRate(),
                        ServiceItem.getInterestRate(),
                        "Saving", Description, null, null, null, false, null, 0, null, 0);
        ctdao.addAContract(contractToAdd);
        response.sendRedirect("/timibank/contract-management-for-customer?fromAdd=true");
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
