/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.billprovdider.management;

import dal.CustomerDAO;
import dal.DetailBillDAO;
import dal.UserDAO;
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
import model.Customer;
import model.DetailBill;

/**
 *
 * @author ACER
 */
@WebServlet(name="CreateInvoice", urlPatterns={"/createinvoice"})
public class CreateInvoice extends HttpServlet {
   
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
            out.println("<title>Servlet CreateInvoice</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateInvoice at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
        int uid = (int) session.getAttribute("uid");
        String customerid = request.getParameter("customerid");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");
        String total_raw = request.getParameter("total");
        DetailBillDAO dao = new DetailBillDAO();
        CustomerDAO cdao = new CustomerDAO();
        UserDAO udao = new UserDAO();
        String error = "";
        try {
            int cid = Integer.parseInt(customerid);
            double total = Double.parseDouble(total_raw);
            BigDecimal totalamount = BigDecimal.valueOf(total);
            DetailBill bill = new DetailBill(title, description, startdate, enddate, totalamount, cdao.getCustomerByID(cid), udao.selectAnUserByConditions(uid, "", "", ""));
            dao.add(bill);
            error = "Add bill successfully";
            request.setAttribute("error", error);
        } catch (NumberFormatException e) {
            error = "Please fill out full information or type incorrect";
        }

        List<Customer> listC = cdao.selectAllCustomer();
        request.setAttribute("error", error);
        request.setAttribute("listC", listC);
        request.getRequestDispatcher("createinvoice.jsp").forward(request, response);
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
