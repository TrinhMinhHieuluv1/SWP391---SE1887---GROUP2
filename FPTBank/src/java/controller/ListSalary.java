/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.Asset;
import model.Salary;

/**
 *
 * @author tiend
 */
@WebServlet(name = "ListSalary", urlPatterns = {"/manager/listSalary"})
public class ListSalary extends HttpServlet {

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
        SalaryDAO dao = new SalaryDAO();
        List<Salary> data = dao.selectAllSalary();
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
        String uploadPath = getServletContext().getRealPath("assetPDF");
        File uploadDir = new File(uploadPath);
        String[] filenames = uploadDir.list((dir, name) -> name.toLowerCase().endsWith(".pdf"));
        String salaId = "salaryid";
        List<String> filteredList = new ArrayList<>();
        for (String filename : filenames) {
            if (filename.contains(salaId)) {
                filename = filename.replaceAll(".pdf", "");
                filename = filename.replaceAll("\\d.*", "");
                filteredList.add(filename);
            }
        }
        request.setAttribute("filenames", filteredList);
        request.setAttribute("data", data);
        request.getRequestDispatcher("manageSalary.jsp").forward(request, response);
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
        String id = request.getParameter("salaryid");
        SalaryDAO dao = new SalaryDAO();
        try {
            int idd = Integer.parseInt(id);
            Salary a = dao.getSalaryById(idd);
            String comment = request.getParameter("comment_" + id);
            String value = request.getParameter("valuationAmount_" + id);
            if (comment != null) {
                a.setComments(comment);
                dao.updateSalary(a);
            }
            if (value != null && !value.isEmpty())  {
                double va  = Double.parseDouble(value);
                a.setValuationAmount(BigDecimal.valueOf(va));
                dao.updateSalary(a);
            }else{
                double valu = Double.parseDouble(a.getValue().toString());
                a.setValuationAmount(BigDecimal.valueOf(valu));
                dao.updateSalary(a);
            }
            switch (action) {
                case "Adjusting":
                    a.setStatus("Adjusting");
                    dao.updateSalary(a);
                    break;
                case "Approved":
                    a.setStatus("Approved");
                    dao.updateSalary(a);
                    break;
                default:
                    //
                    break;
            }
            List<Salary> data = dao.selectAllSalary();
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
            
            String uploadPath = getServletContext().getRealPath("assetPDF");
            File uploadDir = new File(uploadPath);
            String[] filenames = uploadDir.list((dir, name) -> name.toLowerCase().endsWith(".pdf"));
            String salaId = "salaryid";
            List<String> filteredList = new ArrayList<>();
            for (String filename : filenames) {
                if (filename.contains(salaId)) {
                    filename = filename.replaceAll(".pdf", "");
                    filename = filename.replaceAll("\\d.*", "");
                    filteredList.add(filename);
                }
            }
            request.setAttribute("filenames", filteredList);

            request.setAttribute("data", data);
            request.getRequestDispatcher("manageSalary.jsp").forward(request, response);
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
