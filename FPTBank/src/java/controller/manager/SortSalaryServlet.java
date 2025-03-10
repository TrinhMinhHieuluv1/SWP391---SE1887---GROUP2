/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dal.PdfDAO;
import dal.SalaryDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Asset;
import model.Customer;
import model.PdfLis;
import model.Salary;

/**
 *
 * @author tiend
 */
@WebServlet(name = "SortSalaryServlet", urlPatterns = {"/manager/sortSala"})
public class SortSalaryServlet extends HttpServlet {

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
            out.println("<title>Servlet SortSalaryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SortSalaryServlet at " + request.getContextPath() + "</h1>");
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
        SalaryDAO dao = new SalaryDAO();
        PdfDAO pdfDAO = new PdfDAO();
        String sortOrder = request.getParameter("sortOrder");
        String sortDate = request.getParameter("sortDate");
        String status = request.getParameter("status");
        String use = request.getParameter("used");
        String search = request.getParameter("search");
        try {
            List<Salary> data = new ArrayList<>();
            if (sortDate != null) {
                data = dao.getSalarySortedByDate(sortDate);
                request.setAttribute("data", data);
            }
            if (status != null) {
                data = dao.getSalaryByStatus(status);
                request.setAttribute("data", data);
            }
            if (use != null) {
                boolean used = Boolean.parseBoolean(use);
                data = dao.getSalaryByUse(used);
                request.setAttribute("data", data);
            }
            if (search != null && !search.isEmpty()) {
                data = dao.searchSalaryByDescription(normalizeString(search));
                request.setAttribute("data", data);
            }
            descriptionSetup(data);
            Map<Customer, List<Salary>> customerSalarysMap = createCustomerSalarysMap(data, pdfDAO);
            if (sortOrder != null) {
                data = dao.selectAllSalary();
                Map<Customer, List<Salary>> customerSalaryMap2 = createCustomerSalarysMap(data, pdfDAO);
                customerSalarysMap = sortCustomerSalarysMap(customerSalaryMap2, sortOrder);
            }
            request.setAttribute("customerSalarysMap", customerSalarysMap);
            request.getRequestDispatcher("manageSalary.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Map<Customer, List<Salary>> createCustomerSalarysMap(List<Salary> data, PdfDAO pdfDAO) {
        Map<Customer, List<Salary>> customerSalaryMap = new HashMap<>();
        for (Salary salary : data) {
            List<PdfLis> listPDF = pdfDAO.getpdfBySalaryId(salary.getId());
            salary.setListpdf(listPDF);
            Customer owner = salary.getCustomer();
            customerSalaryMap.putIfAbsent(owner, new ArrayList<>());
            customerSalaryMap.get(owner).add(salary);
        }
        return customerSalaryMap;
    }

    private Map<Customer, List<Salary>> sortCustomerSalarysMap(Map<Customer, List<Salary>> customerAssetsMap, String sortOrder) {
        List<Map.Entry<Customer, List<Salary>>> customerList = new ArrayList<>(customerAssetsMap.entrySet());

        // Sắp xếp theo sortOrder
        if ("asc".equals(sortOrder)) {
            customerList.sort(Comparator.comparingInt(entry -> entry.getValue().size()));
        } else if ("desc".equals(sortOrder)) {
            customerList.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));
        }

        // Tạo lại HashMap từ danh sách đã sắp xếp
        Map<Customer, List<Salary>> sortedCustomerSalarysMap = new LinkedHashMap<>();
        for (Map.Entry<Customer, List<Salary>> entry : customerList) {
            sortedCustomerSalarysMap.put(entry.getKey(), entry.getValue());
        }

        return sortedCustomerSalarysMap;
    }

    public void descriptionSetup(List<Salary> data) {
        for (Salary sala : data) {
            StringBuilder result = new StringBuilder();
            String descript = sala.getDescription();
            String[] des = descript.split("\n");
            for (String de : des) {
                result.append(de.trim()).append("<br>-");
            }
            result.deleteCharAt(result.toString().length() - 1);
            sala.setDescription(result.toString());
        }
    }

    public String normalizeString(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return "";
        }
        return keyword.trim().replaceAll("\\s+", " ");
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
