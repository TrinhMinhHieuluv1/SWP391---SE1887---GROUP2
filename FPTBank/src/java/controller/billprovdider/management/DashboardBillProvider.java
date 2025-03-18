/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.billprovdider.management;

import dal.CompanyBillProviderDAO;
import dal.DetailBillDAO;
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
import model.CompanyBillProvider;

/**
 *
 * @author ACER
 */
@WebServlet(name = "DashboardBillProvider", urlPatterns = {"/bill_provider/dashboardbillprovider"})
public class DashboardBillProvider extends HttpServlet {

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
            out.println("<title>Servlet DashboardBillProvider</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashboardBillProvider at " + request.getContextPath() + "</h1>");
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
        DetailBillDAO dao = new DetailBillDAO();
        CompanyBillProviderDAO cdao = new CompanyBillProviderDAO();
        HttpSession session = request.getSession();
        int uid = (int) session.getAttribute("uid");
        String year_raw = request.getParameter("year");
        String yearcustomer_raw = request.getParameter("yearcustomer");
        int year = 2025;
        int yearcustomer = 2025;
        if (year_raw != null && !year_raw.isEmpty()) {
            try {
                year = Integer.parseInt(year_raw);
            } catch (NumberFormatException e) {

                System.err.println("Invalid year format. Using default year: " + year_raw);
            }
        }// Xử lý yearcustomer
        if (yearcustomer_raw != null && !yearcustomer_raw.isEmpty()) {
            try {
                yearcustomer = Integer.parseInt(yearcustomer_raw);
            } catch (NumberFormatException e) {

                System.err.println("Invalid yearcustomer format. Using default yearcustomer: " + yearcustomer_raw);
            }
        }
        List<Integer> list = dao.getUniqueCustomerIDs();
        List<model.DetailBill> listbill = dao.filterList("", "", "", "", uid);
        CompanyBillProvider company = cdao.getCompanyById("ProviderID", uid);
        int totalCustomer = list.size();
        int totalBill = listbill.size();
        BigDecimal total = dao.getTotalSum(uid);
        //Chart for Revenue
        BigDecimal total1 = dao.getTotalByYearAndMonth(year, 1);
        BigDecimal total2 = dao.getTotalByYearAndMonth(year, 2);
        BigDecimal total3 = dao.getTotalByYearAndMonth(year, 3);
        BigDecimal total4 = dao.getTotalByYearAndMonth(year, 4);
        BigDecimal total5 = dao.getTotalByYearAndMonth(year, 5);
        BigDecimal total6 = dao.getTotalByYearAndMonth(year, 6);
        BigDecimal total7 = dao.getTotalByYearAndMonth(year, 7);
        BigDecimal total8 = dao.getTotalByYearAndMonth(year, 8);
        BigDecimal total9 = dao.getTotalByYearAndMonth(year, 9);
        BigDecimal total10 = dao.getTotalByYearAndMonth(year, 10);
        BigDecimal total11 = dao.getTotalByYearAndMonth(year, 11);
        BigDecimal total12 = dao.getTotalByYearAndMonth(year, 12);
        request.setAttribute("total1", total1);
        request.setAttribute("total2", total2);
        request.setAttribute("total3", total3);
        request.setAttribute("total4", total4);
        request.setAttribute("total5", total5);
        request.setAttribute("total6", total6);
        request.setAttribute("total7", total7);
        request.setAttribute("total8", total8);
        request.setAttribute("total9", total9);
        request.setAttribute("total10", total10);
        request.setAttribute("total11", total11);
        request.setAttribute("total12", total12);
        //Chart for Customer
        int totalcustomer1 = dao.getCustomerByYearAndMonth(yearcustomer, 1);
        int totalcustomer2 = dao.getCustomerByYearAndMonth(yearcustomer, 2);
        int totalcustomer3 = dao.getCustomerByYearAndMonth(yearcustomer, 3);
        int totalcustomer4 = dao.getCustomerByYearAndMonth(yearcustomer, 4);
        int totalcustomer5 = dao.getCustomerByYearAndMonth(yearcustomer, 5);
        int totalcustomer6 = dao.getCustomerByYearAndMonth(yearcustomer, 6);
        int totalcustomer7 = dao.getCustomerByYearAndMonth(yearcustomer, 7);
        int totalcustomer8 = dao.getCustomerByYearAndMonth(yearcustomer, 8);
        int totalcustomer9 = dao.getCustomerByYearAndMonth(yearcustomer, 9);
        int totalcustomer10 = dao.getCustomerByYearAndMonth(yearcustomer, 10);
        int totalcustomer11 = dao.getCustomerByYearAndMonth(yearcustomer, 11);
        int totalcustomer12 = dao.getCustomerByYearAndMonth(yearcustomer, 12);
        request.setAttribute("totalcustomer1", totalcustomer1);
        request.setAttribute("totalcustomer2", totalcustomer2);
        request.setAttribute("totalcustomer3", totalcustomer3);
        request.setAttribute("totalcustomer4", totalcustomer4);
        request.setAttribute("totalcustomer5", totalcustomer5);
        request.setAttribute("totalcustomer6", totalcustomer6);
        request.setAttribute("totalcustomer7", totalcustomer7);
        request.setAttribute("totalcustomer8", totalcustomer8);
        request.setAttribute("totalcustomer9", totalcustomer9);
        request.setAttribute("totalcustomer10", totalcustomer10);
        request.setAttribute("totalcustomer11", totalcustomer11);
        request.setAttribute("totalcustomer12", totalcustomer12);
        // Chart for Unpaid or Paid
        int billPaid = dao.getBillCountByStatusOfBill(0,uid);
        int billUnPaid = dao.getBillCountByStatusOfBill(1, uid);
        double Paidrate = (double) billPaid/listbill.size()*100;
        double UnPaidrate = (double) billUnPaid/listbill.size()*100;
        System.out.println(Paidrate);
        System.out.println(UnPaidrate);
        request.setAttribute("UnPaidrate", UnPaidrate);
        request.setAttribute("Paidrate", Paidrate);
        request.setAttribute("companyName", company.getCompanyName());
        request.setAttribute("total", total);
        request.setAttribute("totalCustomer", totalCustomer);
        request.setAttribute("totalbill", totalBill);
        request.getRequestDispatcher("dashboardbillprovider.jsp").forward(request, response);
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
