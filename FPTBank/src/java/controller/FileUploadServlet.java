/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AssetDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import model.Asset;

/**
 *
 * @author tiend
 */
@WebServlet(name = "FileUploadServlet", urlPatterns = {"/uploadFile"})
@MultipartConfig(
    maxFileSize = 1048576 * 100, // 1MB (1MB = 1024 * 1024 bytes)
    maxRequestSize = 2097152 * 100 // 2MB (2MB = 2 * 1024 * 1024 bytes)
)
public class FileUploadServlet extends HttpServlet {

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
            out.println("<title>Servlet FileUploadServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FileUploadServlet at " + request.getContextPath() + "</h1>");
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
        Part filePart = request.getPart("file"); // Nhận file
        String fileName = filePart.getSubmittedFileName(); // Lấy tên file
        if (!fileName.endsWith(".pdf")) {
            response.getWriter().println("Error: Only PDF files are allowed.");
            return;
        }
        // Lưu file vào thư mục assetPDF/
        String uploadPath = getServletContext().getRealPath("") + "../assetPDF/"; // Đường dẫn đến thư mục assetPDF
        File uploadsDir = new File(uploadPath);
        if (!uploadsDir.exists()) {
            uploadsDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
        }
        filePart.write(uploadPath + fileName); // Ghi file vào thư mục
        
        //DAO lưu tên vào cssdl
        AssetDAO dao = new AssetDAO();
        Asset asset = dao.getAssetById(2);
        asset.setPdfPath(fileName);
        dao.updateAsset(asset);
        response.sendRedirect("home");
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
