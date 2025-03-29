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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Customer;
import model.SavingsGoal;

/**
 *
 * @author tiend
 */
@WebServlet(name = "SavingsGoalServlet", urlPatterns = {"/goals"})
public class SavingsGoalServlet extends HttpServlet {

    private SavingsGoalsDAO savingsGoalDAO = new SavingsGoalsDAO();
    private CustomerDAO cdao = new CustomerDAO();

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
            out.println("<title>Servlet SavingsGoalServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SavingsGoalServlet at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteSavingsGoal(request, response);
                break;
            default:
                listSavingsGoals(request, response);
                break;
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
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "create":
                createSavingsGoal(request, response);
                break;
            case "update":
                updateSavingsGoal(request, response);
                break;
            default:
                listSavingsGoals(request, response);
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/savingsGoalForm.jsp").forward(request, response);
    }

    /**
     * Show the form to edit an existing savings goal
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            SavingsGoal goal = savingsGoalDAO.findById(id);

            if (goal == null) {
                request.setAttribute("errorMessage", "Savings goal not found.");
                listSavingsGoals(request, response);
                return;
            }

            request.setAttribute("goal", goal);
            request.getRequestDispatcher("/editSavingGoal.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid goal ID.");
            listSavingsGoals(request, response);
        }
    }

    /**
     * Create a new savings goal from form data
     */
    private void createSavingsGoal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Customer account = (Customer) session.getAttribute("account");
            // Get form data
            String goalName = request.getParameter("goalName");
            double targetAmount = Double.parseDouble(request.getParameter("targetAmount").replace(".", ""));

            // Validate required fields
            if (goalName == null || goalName.trim().isEmpty() || targetAmount <= 0) {
                request.setAttribute("errorMessage", "Goal name and target amount are required.");
                request.getRequestDispatcher("/savingsGoalForm.jsp").forward(request, response);
                return;
            }

            // Create a new goal object
            SavingsGoal goal = new SavingsGoal();
            goal.setGoalName(goalName);
            goal.setTargetAmount(targetAmount);
            String deadlineString = request.getParameter("deadline");
            if (deadlineString != null && !deadlineString.isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date deadline = dateFormat.parse(deadlineString);

                    // Validate deadline is not in the past
                    Date today = new Date();
                    if (deadline.before(today)) {
                        request.setAttribute("errorMessage", "Deadline cannot be in the past.");
                        request.getRequestDispatcher("/savingsGoalForm.jsp").forward(request, response);
                        return;
                    }

                    goal.setDeadline(deadline);
                } catch (Exception e) {
                    request.setAttribute("errorMessage", "Invalid date format for deadline.");
                    request.getRequestDispatcher("/savingsGoalForm.jsp").forward(request, response);
                    return;
                }
            }

            // Set user ID (default to 1 for now, would be from session in a real app)
            goal.setUserId(account.getCustomerId());

            // Save to database
            SavingsGoal savedGoal = savingsGoalDAO.create(goal);

            if (savedGoal != null) {
                request.setAttribute("successMessage", "Savings goal created successfully!");
                response.sendRedirect("goals");
            } else {
                request.setAttribute("errorMessage", "Error creating savings goal. Please try again.");
                request.getRequestDispatcher("/savingsGoalForm.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid number format in the form.");
            request.getRequestDispatcher("/savingsGoalForm.jsp").forward(request, response);
        }
    }

    /**
     * Update an existing savings goal from form data
     */
    private void updateSavingsGoal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get form data
              HttpSession session = request.getSession();
            Customer account = (Customer) session.getAttribute("account");
            int id = Integer.parseInt(request.getParameter("id"));
            String goalName = request.getParameter("goalName");
            double targetAmount = Double.parseDouble(request.getParameter("targetAmount").replace(".", ""));
            double savedAmount = Double.parseDouble(request.getParameter("savedAmount").replace(".", ""));

            // Validate required fields
            if (goalName == null || goalName.trim().isEmpty() || targetAmount <= 0) {
                request.setAttribute("errorMessage", "Goal name and target amount are required.");
                showEditForm(request, response);
                return;
            }

            // Get the existing goal
            SavingsGoal existingGoal = savingsGoalDAO.findById(id);

            if (existingGoal == null) {
                request.setAttribute("errorMessage", "Savings goal not found.");
                listSavingsGoals(request, response);
                return;
            }

            // Validate saved amount
            if (savedAmount < 0) {
                request.setAttribute("errorMessage", "Saved amount cannot be negative.");
                showEditForm(request, response);
                return;
            }

            if (savedAmount > targetAmount) {
                request.setAttribute("errorMessage", "Saved amount cannot exceed target amount.");
                showEditForm(request, response);
                return;
            }

            // Update goal properties
            existingGoal.setGoalName(goalName);
            existingGoal.setTargetAmount(targetAmount);
            existingGoal.setSavedAmount(savedAmount);

            // Set deadline if provided
            String deadlineString = request.getParameter("deadline");
            if (deadlineString != null && !deadlineString.isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date deadline = dateFormat.parse(deadlineString);
                    existingGoal.setDeadline(deadline);
                } catch (Exception e) {
                    request.setAttribute("errorMessage", "Invalid date format for deadline.");
                    showEditForm(request, response);
                    return;
                }
            } else {
                existingGoal.setDeadline(null); // Remove deadline if empty
            }

            // Save to database
            boolean updated = savingsGoalDAO.update(existingGoal);

            if (updated) {
                request.setAttribute("successMessage", "Savings goal updated successfully!");
                response.sendRedirect("goals");
            } else {
                request.setAttribute("errorMessage", "Error updating savings goal. Please try again.");
                showEditForm(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid number format in the form.");
            showEditForm(request, response);
        }
    }

    /**
     * Delete a savings goal
     */
    private void deleteSavingsGoal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
             HttpSession session = request.getSession();
            Customer account = (Customer) session.getAttribute("account");
            int id = Integer.parseInt(request.getParameter("id"));
            SavingsGoal sv = savingsGoalDAO.findById(id);
            account.setBalance(account.getBalance().add(BigDecimal.valueOf(sv.getSavedAmount())));
            cdao.updateACustomer(account);
            boolean deleted = savingsGoalDAO.delete(id);

            if (deleted) {
                request.setAttribute("successMessage", "Savings goal deleted successfully!");
            } else {
                request.setAttribute("errorMessage", "Error deleting savings goal.");
            }

            response.sendRedirect("goals");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid goal ID.");
            listSavingsGoals(request, response);
        }
    }

    /**
     * Display a single savings goal
     */
    private void viewSavingsGoal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            SavingsGoal goal = savingsGoalDAO.findById(id);

            if (goal == null) {
                request.setAttribute("errorMessage", "Savings goal not found.");
                listSavingsGoals(request, response);
                return;
            }

            request.setAttribute("goal", goal);
            request.getRequestDispatcher("/viewSavingsGoal.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid goal ID.");
            listSavingsGoals(request, response);
        }
    }

    /**
     * List all savings goals for a user
     */
    private void listSavingsGoals(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Default to user ID 1 for demonstration (would be from session in a real app)
        HttpSession session = request.getSession();
        Customer account = (Customer) session.getAttribute("account");
        int userId = account.getCustomerId();

        List<SavingsGoal> goals = savingsGoalDAO.findAllByUser(userId);
        request.setAttribute("goals", goals);
        request.getRequestDispatcher("/viewSavingsGoals.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
