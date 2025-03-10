/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.news_management;

import Tools.SaveImage;
import dal.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.News;
import jakarta.servlet.annotation.MultipartConfig;

/**
 *
 * @author HP
 */
@WebServlet(name = "UpdateNews", urlPatterns = {"/seller/update-news"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UpdateNews extends HttpServlet {

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
            out.println("<title>Servlet UpdateNews</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateNews at " + request.getContextPath() + "</h1>");
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
        NewsDAO ndao = new NewsDAO();
        String NewsID_raw = request.getParameter("NewsID");
        String changeStatus = request.getParameter("changeStatus");
        if (NewsID_raw != null && !NewsID_raw.isEmpty()) {
            int NewsID = Integer.parseInt(NewsID_raw);
            News newsToUpdate = ndao.selectANewsByNewsID(NewsID);
            if (changeStatus != null && changeStatus.equals("true")) {
                if (newsToUpdate.isStatus()) {
                    newsToUpdate.setStatus(false);
                } else {
                    newsToUpdate.setStatus(true);
                }
                ndao.updateANews(newsToUpdate);
                response.sendRedirect("/timibank/seller/news-management?fromUpdate=true");
                return;
            }
            request.setAttribute("newsToUpdate", newsToUpdate);
            request.getRequestDispatcher("update-news.jsp").forward(request, response);
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
        NewsDAO ndao = new NewsDAO();
        SaveImage si = new SaveImage();
        int NewsID=0;
        String NewsID_raw = request.getParameter("NewsID");
        try {
            NewsID = Integer.parseInt(NewsID_raw);
        } catch (NumberFormatException e) {
            response.getWriter().print(e);
            return;
        }
        News newsToUpdate = ndao.selectANewsByNewsID(NewsID);
        String urlImage = request.getParameter("url-image");
        Part filePart = request.getPart("file-image");
        String imagePath;
        if (urlImage != null && !urlImage.isEmpty()) {
            imagePath = si.saveImageByUrl(urlImage, "D:\\SWP391---SE1887---GROUP2\\FPTBank\\web\\img\\Image_For_News\\" + newsToUpdate.getNewsID(), newsToUpdate.getNewsID() + "");
            si.saveImageByUrl(urlImage, "D:\\SWP391---SE1887---GROUP2\\FPTBank\\build\\web\\img\\Image_For_News\\" + newsToUpdate.getNewsID(), newsToUpdate.getNewsID() + "");
        } else {
            imagePath = si.saveImageByFile(filePart, "D:\\SWP391---SE1887---GROUP2\\FPTBank\\web\\img\\Image_For_News\\" + newsToUpdate.getNewsID(), newsToUpdate.getNewsID() + "");
            si.saveImageByFile(filePart, "D:\\SWP391---SE1887---GROUP2\\FPTBank\\build\\web\\img\\Image_For_News\\" + newsToUpdate.getNewsID(), newsToUpdate.getNewsID() + "");
        }
        newsToUpdate.setTitle(request.getParameter("Title"));
        newsToUpdate.setDescription(request.getParameter("Description"));
        newsToUpdate.setImage(imagePath);
        ndao.updateANews(newsToUpdate);
        response.sendRedirect("/timibank/seller/news-management?fromUpdate=true");

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
