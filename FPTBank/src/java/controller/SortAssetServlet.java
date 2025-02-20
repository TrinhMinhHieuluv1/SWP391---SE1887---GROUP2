/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AssetDAO;
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
import java.util.List;
import model.Asset;

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
        String sortOrder = request.getParameter("sortOrder");
        String sortDate = request.getParameter("sortDate");
        String status = request.getParameter("status");
        String use = request.getParameter("used");
        String search = request.getParameter("search");
        try {
            List<Asset> data = new ArrayList<>();
            if (sortOrder != null) {
                data = dao.getAssetsSortedByValue(sortOrder);
                request.setAttribute("data", data);
            }
            if (sortDate != null) {
                data = dao.getAssetsSortedByDate(sortDate);
                request.setAttribute("data", data);
            }
            if (status != null) {
                data = dao.getAssetsByStatus(status);
                request.setAttribute("data", data);
            }
            if (use != null) {
                boolean used = Boolean.parseBoolean(use);
                data = dao.getAssetsByUsed(used);
                request.setAttribute("data", data);
            }
            if (search != null && !search.isEmpty()) {
                search = normalizeString(search);
                data = dao.searchAssetsByDescription(search);
                request.setAttribute("data", data);
            }
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
            String uploadPath = getServletContext().getRealPath("assetPDF");
            File uploadDir = new File(uploadPath);
            String[] filenames = uploadDir.list((dir, name) -> name.toLowerCase().endsWith(".pdf"));
            String assetId = "assetid";
            List<String> filteredList = new ArrayList<>();
            for (String filename : filenames) {
                if (filename.contains(assetId)) {
                    filename = filename.replaceAll(".pdf", "");
                    filename = filename.replaceAll("\\d.*", "");
                    filteredList.add(filename);
                }
            }
            request.setAttribute("filenames", filteredList);
            request.getRequestDispatcher("manageAsset.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
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
