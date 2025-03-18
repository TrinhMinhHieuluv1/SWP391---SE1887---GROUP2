/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AssetDAO;
import dal.PdfDAO;
import dal.SalaryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Asset;
import model.Customer;
import model.PdfLis;
import model.Salary;

/**
 *
 * @author tiend
 */
@WebServlet(name = "SearchSalary", urlPatterns = {"/searchSalary"})
public class SearchSalary extends HttpServlet {

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
            out.println("<title>Servlet SearchAsset</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchAsset at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("mysalary");
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
        String name = request.getParameter("name");
        String opStatus = request.getParameter("opStatus");
        String opUse = request.getParameter("opUse");
        String date = request.getParameter("opDate");
        HttpSession session = request.getSession();
        Customer account = (Customer) session.getAttribute("account");
        SalaryDAO dao = new SalaryDAO();
        try {
            List<Salary> data = dao.getSalaryByCondition(account.getCustomerId(), name, opStatus, opUse, date);
            if (data != null) {
                descriptionSetup(data);
                PdfDAO pdfDAO = new PdfDAO();
                for (Salary salary : data) {
                    List<PdfLis> listPDF = pdfDAO.getpdfBySalaryId(salary.getId());
                    salary.setListpdf(listPDF);
                }
            }
            request.setAttribute("data", data);
            request.getRequestDispatcher("myAsset-Salary.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void descriptionSetup(List<Salary> data) {
        for (Salary salary : data) {
            StringBuilder result = new StringBuilder();
            String descript = salary.getDescription();
            String regex =  "\n";
            if (!salary.getDescription().contains(regex)) {
                continue;
            }
            String[] des = descript.split(regex);
            for (String de : des) {
                result.append(de.trim()).append("<br>-");
            }
            result.deleteCharAt(result.toString().length() - 1);
            salary.setDescription(result.toString());
        }
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
