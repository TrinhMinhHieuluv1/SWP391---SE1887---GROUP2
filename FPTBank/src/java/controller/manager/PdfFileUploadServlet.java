/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author tiend
 */
@WebServlet(name = "PdfFileUploadServlet", urlPatterns = {"/uploadFile"})
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50
)
public class PdfFileUploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "assetPDF";

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
            out.println("<title>Servlet PdfFileUploadServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PdfFileUploadServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        Part filePart = request.getPart("file"); // Nhận tệp PDF từ form
        String fileName = filePart.getSubmittedFileName();


        String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf(".")); // Tên file không có đuôi
        String fileExtension = fileName.substring(fileName.lastIndexOf(".")); // Đuôi file
        String uniqueFileName = fileNameWithoutExtension + fileExtension; // Tên file mới
        String mimeType = filePart.getContentType();
        if (!mimeType.equals("application/pdf")) {
            response.getWriter().println("Lỗi: Chỉ cho phép tải lên file PDF.");
            return;
        }
        if (!fileName.toLowerCase().endsWith(".pdf")) {
            response.getWriter().println("Lỗi: Chỉ cho phép tải lên file PDF.");
            return;
        }
        if (filePart.getSize() > 0) {
            // Lưu tệp vào thư mục uploads
            String pathHost = getServletContext().getRealPath("");
            String finalPath = pathHost.replace("build\\", "");
            String uploadPath = finalPath + UPLOAD_DIR;

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
            }

            filePart.write(uploadPath + File.separator + uniqueFileName); // Lưu tệp

            response.getWriter().println("Tệp đã được tải lên thành công: " + uniqueFileName);
        } else {
            response.getWriter().println("Tệp không hợp lệ hoặc vượt quá dung lượng cho phép.");
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
