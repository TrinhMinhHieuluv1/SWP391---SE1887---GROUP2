/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AssetDAO;
import dal.PdfDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Asset;
import model.Customer;
import model.PdfLis;

/**
 *
 * @author tiend
 */
@WebServlet(name = "MyAssetSalary", urlPatterns = {"/myassetsalary"})
public class MyAssetSalary extends HttpServlet {

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
            out.println("<title>Servlet MyAssetSalary</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyAssetSalary at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Customer account = (Customer) session.getAttribute("account");
        AssetDAO dao = new AssetDAO();
        List<Asset> data = dao.getAssetByCId(account.getCustomerId());
        descriptionSetup(data);
        PdfDAO pdfDAO = new PdfDAO();
        for (Asset asset : data) {
            List<PdfLis> listPDF = pdfDAO.getpdfByAssetId(asset.getId());
            asset.setListpdf(listPDF);
        }
        request.setAttribute("data", data);
        request.getRequestDispatcher("myAsset-Salary.jsp").forward(request, response);

    }

    public void descriptionSetup(List<Asset> data) {
        for (Asset asset : data) {
            StringBuilder result = new StringBuilder();
            String descript = asset.getDescription();
            String regex = "\n";
            if (!asset.getDescription().contains(regex)) {
                continue;
            }
            String[] des = descript.split(regex);
            for (String de : des) {
                
                result.append(de.trim()).append("<br>");
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
        HttpSession session = request.getSession();
        Customer account = (Customer) session.getAttribute("account");
        AssetDAO dao = new AssetDAO();
        List<Asset> data = dao.getAssetByCId(account.getCustomerId());
        PdfDAO pdfDAO = new PdfDAO();
        descriptionSetup(data);
        String ass = request.getParameter("ass");
        String assdelet = request.getParameter("assdelet");
        try {

            if (assdelet != null) {
                int assDelete = Integer.parseInt(assdelet);
                Asset adele = dao.getAssetById(assDelete);
                if (adele.getStatus().equalsIgnoreCase("Not Processed")) {
                    pdfDAO.deletePdfLisbyAId(assDelete);
                    dao.deleteAsset(assDelete);
                }
            }
            if (ass != null) {
                int assetid = Integer.parseInt(ass);
                Asset a = dao.getAssetById(assetid);

                if (a.getStatus().equalsIgnoreCase("Not Processed")) {
                    a.setStatus("Pending");
                    dao.updateAsset(a);
                } else if (a.getStatus().equalsIgnoreCase("Adjusting")) {
                    a.setStatus("Adjusted");
                    dao.updateAsset(a);
                }
            }

     
            response.sendRedirect("myassetsalary");
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
