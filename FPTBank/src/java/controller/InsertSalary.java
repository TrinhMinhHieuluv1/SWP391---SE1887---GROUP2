/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Tools.SaveImage;
import static controller.UpdateAsset.isValidImageFile;
import dal.AssetDAO;
import dal.PdfDAO;
import dal.SalaryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import model.Customer;
import model.PdfLis;
import model.Salary;
import utils.FileUploadHelper;

/**
 *
 * @author tiend
 */
@WebServlet(name = "InsertSalary", urlPatterns = {"/insertSalary"})
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50
)
public class InsertSalary extends HttpServlet {
    private static final String IMAGE_DIR = "uploads";
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
            out.println("<title>Servlet InsertAsset</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertAsset at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String value_raw = request.getParameter("value");
        String description = request.getParameter("description");
        Part filePartImage = request.getPart("fileImage");
        SalaryDAO dao = new SalaryDAO();
        try {
            HttpSession session = request.getSession();
            Customer account = (Customer) session.getAttribute("account");
            Salary a = new Salary();
            a.setCustomer(account);
            a.setTitle(normalizeString(name));
            a.setDescription(description);
            String valueR = value_raw.replace(".", "");
            double value = Double.parseDouble(valueR);
            a.setValue(BigDecimal.valueOf(value));

            if (!isValidImageFile(extractFileName(filePartImage))) {
                request.setAttribute("messType", "false");
                request.setAttribute("mess", "Chỉ được tải lên file ảnh có định dạng JPG, JPEG, PNG, GIF, WEBP!");
                request.getRequestDispatcher("addSalary.jsp").forward(request, response);
                return;
            }
            String pathHost = getServletContext().getRealPath("")+ File.separator + IMAGE_DIR;
            String imagePath = "uploads/" + FileUploadHelper.saveProfilePicture(filePartImage,pathHost, 1);
            FileUploadHelper.saveProfilePicture(filePartImage, pathHost, 2);
            a.setImage(imagePath);
            a.setStatus("Not Processed");

            boolean succ = dao.insertSalary(a);
            request.setAttribute("messType", "true");
            request.setAttribute("mess", "Successfully add new Salary ");
            request.getRequestDispatcher("addAsset.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        public String normalizeString(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return "";
        }
        return keyword.trim().replaceAll("\\s+", " ");
    }
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "1.jpg";
    }

    public static boolean isValidImageFile(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return false; // Tên file không hợp lệ
        }

        String[] allowedExtensions = {".jpg", ".jpeg", ".png", ".gif", ".webp"};

        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1) {
            return false; // Không có phần mở rộng hoặc chỉ có dấu "."
        }

        String fileExtension = fileName.substring(lastDotIndex).toLowerCase();
        return Arrays.asList(allowedExtensions).contains(fileExtension);
    }

    private byte[] processProfilePicture(Part filePart) throws IOException {
        if (filePart == null || filePart.getSize() <= 0) {
            return null;
        }
        try (InputStream inputStream = filePart.getInputStream()) {
            return inputStream.readAllBytes();
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
