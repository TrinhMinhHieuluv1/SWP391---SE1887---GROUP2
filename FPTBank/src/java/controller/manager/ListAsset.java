/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dal.AssetDAO;
import dal.PdfDAO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Asset;
import model.Customer;
import model.PdfLis;

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
        descriptionSetup(data);
        PdfDAO pdfDAO = new PdfDAO();

        Map<Customer, List<Asset>> customerAssetsMap = new HashMap<>();
        for (Asset asset : data) {
            List<PdfLis> listPDF = pdfDAO.getpdfByAssetId(asset.getId());
            asset.setListpdf(listPDF);
            Customer owner = asset.getCustomer();
            customerAssetsMap.putIfAbsent(owner, new ArrayList<>());
            customerAssetsMap.get(owner).add(asset);

        }

        request.setAttribute("customerAssetsMap", customerAssetsMap);
        request.getRequestDispatcher("manageAsset.jsp").forward(request, response);
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
            if (value != null && !value.isEmpty()) {
                   String loanAmountStr = value.replace(".", "");
                double va  = Double.parseDouble(loanAmountStr);
                a.setValuationAmount(BigDecimal.valueOf(va));
                dao.updateAsset(a);
            } else {
                double valu = Double.parseDouble(a.getValue().toString());
                a.setValuationAmount(BigDecimal.valueOf(valu));
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
            List<Asset> data = dao.selectAllAssets();
            descriptionSetup(data);
            PdfDAO pdfDAO = new PdfDAO();

            Map<Customer, List<Asset>> customerAssetsMap = new HashMap<>();
            for (Asset asset : data) {
                List<PdfLis> listPDF = pdfDAO.getpdfByAssetId(asset.getId());
                asset.setListpdf(listPDF);
                Customer owner = asset.getCustomer();
                customerAssetsMap.putIfAbsent(owner, new ArrayList<>());
                customerAssetsMap.get(owner).add(asset);

            }

            request.setAttribute("customerAssetsMap", customerAssetsMap);

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
