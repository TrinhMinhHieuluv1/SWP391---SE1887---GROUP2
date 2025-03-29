/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.insurance.manager;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="GeneratePDFCoverRate", urlPatterns={"/insurance/ChartToPDFServletCover"})
public class GeneratePDFCoverRate extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet GeneratePDFCoverRate</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GeneratePDFCoverRate at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
                 HttpSession session = request.getSession();

        String imageBase64 = request.getParameter("imageCover");
        if (imageBase64 == null || !imageBase64.startsWith("data:image/png;base64,")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid image data");
            return;
        }

        imageBase64 = imageBase64.replace("data:image/png;base64,", "");
        byte[] imageBytes = Base64.getDecoder().decode(imageBase64);
        InputStream inputStream = new ByteArrayInputStream(imageBytes);
        BufferedImage bufferedImage = ImageIO.read(inputStream);

       // Lấy dữ liệu từ tham số form thay vì session
    int coverate30 = Integer.parseInt(request.getParameter("coverate30"));
    int coverate50 = Integer.parseInt(request.getParameter("coverate50"));
    int coverate60 = Integer.parseInt(request.getParameter("coverate60"));
    int coverate70 = Integer.parseInt(request.getParameter("coverate70"));
    int coverate80 = Integer.parseInt(request.getParameter("coverate80"));
    int coverate100 = Integer.parseInt(request.getParameter("coverate100"));
    int totalCoverate = Integer.parseInt(request.getParameter("totalCoverate"));
        String statusFilter = request.getParameter("statusFilterCover");
         if (statusFilter == null) {
            statusFilter = "BothChartCover";
        }
                  session.setAttribute("statusFilterCover", statusFilter);


        // Tính phần trăm
        DecimalFormat df = new DecimalFormat("#.##");
        String percent1 = df.format((coverate30 * 100.0) / totalCoverate) + "%";
        String percent2 = df.format((coverate50 * 100.0) / totalCoverate) + "%";
        String percent4 = df.format((coverate60 * 100.0) / totalCoverate) + "%";
        String percent6 = df.format((coverate70 * 100.0) / totalCoverate) + "%";
        String percent8 = df.format((coverate80 * 100.0) / totalCoverate) + "%";
        String percent10 = df.format((coverate100 * 100.0) / totalCoverate) + "%";

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=chart.pdf");
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            // Thêm tiêu đề
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            document.add(new Paragraph("                         Statistic of Insurance Cover Rate", boldFont));
            document.add(new Paragraph("                                   Status:" + statusFilter, boldFont)); // Hiển thị trạng thái đang chọn
            document.add(new Paragraph("\n"));

            // Thêm dữ liệu dạng phần trăm 
            document.add(new Paragraph("Very Low (<30%):         " + coverate30 + " (" + percent1 + ")"));
            document.add(new Paragraph("Low (30%-50%):           " + coverate50 + " (" + percent2 + ")"));
            document.add(new Paragraph("Medium-Low (50%-60%):    " + coverate60 + " (" + percent4 + ")"));
            document.add(new Paragraph("Medium (60%-70%):        " + coverate70 + " (" + percent6 + ")"));
            document.add(new Paragraph("High (70%-80%):          " + coverate80 + " (" + percent8 + ")"));
            document.add(new Paragraph("Very High (>80%):        " + coverate100 + " (" + percent10 + ")"));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("                       Chart of Insurance Cover Rate", boldFont));
            document.add(new Paragraph("                                Status:" + statusFilter, boldFont)); // Hiển thị trạng thái đang chọn

            // Chèn ảnh biểu đồ vào PDF
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(imageBytes);
            image.scaleToFit(500, 400);
            document.add(image);

            document.close();
        } catch (DocumentException ex) {
            Logger.getLogger(GeneratePDFServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
