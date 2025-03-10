/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dal.AssetDAO;
import dal.CustomerDAO;
import dal.PdfDAO;
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

/**
 *
 * @author tiend
 */
@WebServlet(name = "SortAssetServlet", urlPatterns = {"/manager/sort"})
public class SortAssetServlet extends HttpServlet {

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
            out.println("<title>Servlet SortAssetServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SortAssetServlet at " + request.getContextPath() + "</h1>");
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
        AssetDAO dao = new AssetDAO();
        PdfDAO pdfDAO = new PdfDAO();
        String sortOrder = request.getParameter("sortOrder");
        String sortDate = request.getParameter("sortDate");
        String status = request.getParameter("status");
        String use = request.getParameter("used");
        String search = request.getParameter("search");
        try {
            List<Asset> data = new ArrayList<>();

            if (sortDate != null) {
                data = dao.getAssetsSortedByDate(sortDate);
            }
            if (status != null) {
                data = dao.getAssetsByStatus(status);
            }
            if (use != null) {
                boolean used = Boolean.parseBoolean(use);
                data = dao.getAssetsByUsed(used);
            }
            if (search != null && !search.isEmpty()) {
                search = normalizeString(search);
                data = dao.searchAssetsByDescription(search);
            }
            descriptionSetup(data);
            Map<Customer, List<Asset>> customerAssetsMap = createCustomerAssetsMap(data, pdfDAO);
            if (sortOrder != null) {
                data = dao.selectAllAssets();
                Map<Customer, List<Asset>> customerAssetsMap2 = createCustomerAssetsMap(data, pdfDAO);
                customerAssetsMap = sortCustomerAssetsMap(customerAssetsMap2, sortOrder);
            }
            request.setAttribute("customerAssetsMap", customerAssetsMap);
            request.getRequestDispatcher("manageAsset.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private Map<Customer, List<Asset>> createCustomerAssetsMap(List<Asset> data, PdfDAO pdfDAO) {
        Map<Customer, List<Asset>> customerAssetsMap = new HashMap<>();
        for (Asset asset : data) {
            List<PdfLis> listPDF = pdfDAO.getpdfByAssetId(asset.getId());
            asset.setListpdf(listPDF);
            Customer owner = asset.getCustomer();
            customerAssetsMap.putIfAbsent(owner, new ArrayList<>());
            customerAssetsMap.get(owner).add(asset);
        }
        return customerAssetsMap;
    }

    public void descriptionSetup(List<Asset> data) {
        for (Asset asset : data) {
            StringBuilder result = new StringBuilder();
            String descript = asset.getDescription();
            String[] des = descript.split("\n");
            for (String de : des) {
                result.append(de.trim()).append("<br>-");
            }
            result.deleteCharAt(result.toString().length() - 1);
            asset.setDescription(result.toString());
        }
    }

    public String normalizeString(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return "";
        }
        return keyword.trim().replaceAll("\\s+", " ");
    }

    private Map<Customer, List<Asset>> sortCustomerAssetsMap(Map<Customer, List<Asset>> customerAssetsMap, String sortOrder) {
        List<Map.Entry<Customer, List<Asset>>> customerList = new ArrayList<>(customerAssetsMap.entrySet());

        // Sắp xếp theo sortOrder
        if ("asc".equals(sortOrder)) {
            customerList.sort(Comparator.comparingInt(entry -> entry.getValue().size()));
        } else if ("desc".equals(sortOrder)) {
            customerList.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));
        }

        // Tạo lại HashMap từ danh sách đã sắp xếp
        Map<Customer, List<Asset>> sortedCustomerAssetsMap = new LinkedHashMap<>();
        for (Map.Entry<Customer, List<Asset>> entry : customerList) {
            sortedCustomerAssetsMap.put(entry.getKey(), entry.getValue());
        }

        return sortedCustomerAssetsMap;
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
