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
import java.util.ArrayList;
import java.util.HashSet;
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
        String name = request.getParameter("name") != null ? request.getParameter("name") : "";
        String opStatus = request.getParameter("opStatus") != null ? request.getParameter("opStatus") : "all";
        String opUse = request.getParameter("opUse") != null ? request.getParameter("opUse") : "all";
        String date = request.getParameter("opDate") != null ? request.getParameter("opDate") : "asc";
        HttpSession session = request.getSession();
        Customer account = (Customer) session.getAttribute("account");
        AssetDAO dao = new AssetDAO();
        try {
            int totalUsers = dao.getAssetsByCondition(account.getCustomerId(), name, opStatus, opUse, date).size();
            
            List<Integer> listOfPageSize = removeDuplicates(calculatePageSize(totalUsers));
            int pageSize = request.getParameter("pageSize") != null ? Integer.parseInt(request.getParameter("pageSize")) : 1;
            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            List<Asset> data = dao.getAssetsByConditionAndPaging(account.getCustomerId(), normalizeString(name), opStatus, opUse, date, page, pageSize);
            System.out.println("sizzzz" + listOfPageSize.size());
            if (data != null) {
                descriptionSetup(data);
                PdfDAO pdfDAO = new PdfDAO();
                for (Asset asset : data) {
                    List<PdfLis> listPDF = pdfDAO.getpdfByAssetId(asset.getId());
                    asset.setListpdf(listPDF);
                }
            }
            int totalPages = (int) Math.ceil((double) totalUsers / pageSize);
            request.setAttribute("listSize", listOfPageSize);
            request.setAttribute("name", name);
            request.setAttribute("opStatus", opStatus);
            request.setAttribute("opUse", opUse);
            request.setAttribute("opDate", date);
            request.setAttribute("currentPage", page);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("data", data);
            request.getRequestDispatcher("myAsset-Salary.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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

    }

    public static List<Integer> removeDuplicates(List<Integer> list) {
        // Sử dụng HashSet để xóa giá trị trùng lặp
        HashSet<Integer> set = new HashSet<>(list);

        // Chuyển đổi lại HashSet thành List
        return new ArrayList<>(set);

    }

    private List<Integer> calculatePageSize(int total) {
        List<Integer> listOfPageSize = new ArrayList<>();
        int totalUsers = total;

        double[] percentages = {0.15, 0.35, 0.7, 1.0};
        for (double percentage : percentages) {
            listOfPageSize.add((int) Math.ceil(totalUsers * percentage));
        }

        return listOfPageSize;
    }

    public String normalizeString(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return "";
        }
        return keyword.trim().replaceAll("\\s+", " ");
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
