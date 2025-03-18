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
import java.util.List;
import model.Asset;
import model.Customer;
import model.PdfLis;

/**
 *
 * @author tiend
 */
@WebServlet(name = "SearchAsset", urlPatterns = {"/searchAsset"})
public class SearchAsset extends HttpServlet {

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
        response.sendRedirect("myassetsalary");
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
        AssetDAO dao = new AssetDAO();
        try {
            List<Asset> data = dao.getAssetsByCondition(account.getCustomerId(), name, opStatus, opUse, date);
            if (data != null) {
                descriptionSetup(data);
                PdfDAO pdfDAO = new PdfDAO();
                for (Asset asset : data) {
                    List<PdfLis> listPDF = pdfDAO.getpdfByAssetId(asset.getId());
                    asset.setListpdf(listPDF);
                }
            }
            request.setAttribute("data", data);
            request.getRequestDispatcher("myAsset-Salary.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                result.append(de.trim()).append("<br>-");
            }
            result.deleteCharAt(result.toString().length() - 1);
            asset.setDescription(result.toString());
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
