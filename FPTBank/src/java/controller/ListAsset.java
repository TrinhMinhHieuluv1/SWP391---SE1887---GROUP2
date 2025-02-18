/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AssetDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Asset;

/**
 *
 * @author tiend
 */
@WebServlet(name = "ListAsset", urlPatterns = {"/manager/listAsset"})
public class ListAsset extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListAsset</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListAsset at " + request.getContextPath() + "</h1>");
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
        List<Asset> data = dao.selectAllAssets();
        for (Asset asset : data) {
            StringBuilder result = new StringBuilder();
            String descript = asset.getDescription();
            String[] des = descript.split("\\.");
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
        request.setAttribute("data", data);
        request.getRequestDispatcher("manageAsset.jsp").forward(request, response);
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
        String assetId = request.getParameter("assetid");

        AssetDAO dao = new AssetDAO();
        try {
            int id = Integer.parseInt(assetId);
            Asset a = dao.getAssetById(id);
            String comment = request.getParameter("comment_" + id);
            String value = request.getParameter("valuationAmount_" + id);
            if (comment != null) {
                a.setComments(comment);
                dao.updateAsset(a);
            }
            if (value != null) {
                double va  = Double.parseDouble(value);
                a.setValuationAmount(BigDecimal.valueOf(va));
                dao.updateAsset(a);
            }

            switch (action) {
                case "Adjusting":
                    a.setStatus("Adjusting");
                    dao.updateAsset(a);
                    break;
                case "Approved":
                    a.setStatus("Approved");
                    dao.updateAsset(a);
                    break;
                default:
                    //
                    break;
            }
            String uploadPath = getServletContext().getRealPath("assetPDF");
            File uploadDir = new File(uploadPath);
            String[] filenames = uploadDir.list((dir, name) -> name.toLowerCase().endsWith(".pdf"));
            request.setAttribute("filenames", filenames);
            List<Asset> data = dao.selectAllAssets();
            request.setAttribute("data", data);
            request.getRequestDispatcher("manageAsset.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
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
