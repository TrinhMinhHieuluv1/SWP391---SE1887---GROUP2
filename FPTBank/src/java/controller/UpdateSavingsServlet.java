/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CustomerDAO;
import dal.SavingsGoalsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import model.Customer;
import model.SavingsGoal;

/**
 *
 * @author tiend
 */
@WebServlet(name="UpdateSavingsServlet", urlPatterns={"/updateSavings"})
public class UpdateSavingsServlet extends HttpServlet {
        private SavingsGoalsDAO savingsGoalDAO = new SavingsGoalsDAO();
        private CustomerDAO customerDAO = new CustomerDAO();
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
            out.println("<title>Servlet UpdateSavingsServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateSavingsServlet at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
      try {
            // Get request parameters
            HttpSession session = request.getSession();
            Customer account = (Customer) session.getAttribute("account");
            int goalId = Integer.parseInt(request.getParameter("goalId"));
            String updateType = request.getParameter("updateType");
            double amount = Double.parseDouble(request.getParameter("amount").replace(".", ""));
            
            // Validate input
            if (amount <= 0) {
                request.setAttribute("errorMessage", "Amount must be greater than zero.");
                response.sendRedirect("goals");
                return;
            }
            
            // Get the savings goal
            SavingsGoal goal = savingsGoalDAO.findById(goalId);
            
            if (goal == null) {
                request.setAttribute("errorMessage", "Savings goal not found.");
                response.sendRedirect("goals");
                return;
            }
            
            // Update the savings amount based on the operation
            double newAmount = goal.getSavedAmount();
            
            switch (updateType) {
                case "add":
                    newAmount += amount;
                    if (newAmount > goal.getTargetAmount()) {
                        newAmount = goal.getTargetAmount(); // Cap at target amount
                    }
                    account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(amount)));
                    customerDAO.updateACustomer(account);
                    break;
                case "subtract":
                    newAmount -= amount;
                    if (newAmount < 0) {
                        newAmount = 0; // Don't allow negative savings
                    }
                       account.setBalance(account.getBalance().add(BigDecimal.valueOf(amount)));
                       customerDAO.updateACustomer(account);
                    break;
                case "set":
                    // Direct setting of amount
                    if (amount > goal.getTargetAmount()) {
                        newAmount = goal.getTargetAmount(); // Cap at target amount
                    } else {
                        newAmount = amount;
                    }
                    break;
                default:
                    request.setAttribute("errorMessage", "Invalid update operation.");
                    response.sendRedirect("goals");
                    return;
            }
            
            // Update in the database
            boolean success = savingsGoalDAO.updateSavedAmount(goalId, newAmount);
            
            if (success) {
                request.setAttribute("successMessage", "Savings amount updated successfully!");
            } else {
                request.setAttribute("errorMessage", "Error updating savings amount.");
            }
            
            // Redirect back to the goal or goals list
            String redirectTo = request.getParameter("redirectTo");
            if (redirectTo != null && redirectTo.equals("editPage")) {
                response.sendRedirect("goals?action=edit&id=" + goalId);
            } else {
                response.sendRedirect("goals");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid number format.");
            response.sendRedirect("goals");
        }
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
