/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Tools.SaveImage;
import dal.NewsCategoryDAO;
import dal.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.List;
import model.News;
import model.User;
import jakarta.servlet.annotation.MultipartConfig;

/**
 *
 * @author HP
 */
@WebServlet(name = "AddNews", urlPatterns = {"/seller/add-news"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddNews extends HttpServlet {

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
            out.println("<title>Servlet AddNews</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNews at " + request.getContextPath() + "</h1>");
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
        NewsCategoryDAO ncDAO = new NewsCategoryDAO();
        request.setAttribute("ncList", ncDAO.categoryList);
        request.getRequestDispatcher("add-news.jsp").forward(request, response);
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
        NewsCategoryDAO ncDAO = new NewsCategoryDAO();

        String Title = request.getParameter("Title");
        String Description = request.getParameter("Description");
        String urlImage = request.getParameter("url-image");
        String NewsCategoryID_raw = request.getParameter("NewsCategoryID");
        Part filePart = request.getPart("file-image");
        News newsToAdd = new News(0, (User) request.getSession().getAttribute("account"), Title, Description, "", true, null, 0, ncDAO.selectANewsCategoryByID(Integer.parseInt(NewsCategoryID_raw)));
        ndao.addANews(newsToAdd);
        List<News> newsList = ndao.selectAllNews();
        News newsToUpdate = newsList.get(newsList.size() - 1);
        String imagePath = "";
        if (urlImage != null && !urlImage.isEmpty()) {
            imagePath = si.saveImageByUrl(urlImage, "D:\\SWP391---SE1887---GROUP2\\FPTBank\\web\\image\\Image_For_News\\" + newsToUpdate.getNewsID(), newsToUpdate.getNewsID() + "");
            si.saveImageByUrl(urlImage, "D:\\SWP391---SE1887---GROUP2\\FPTBank\\build\\web\\image\\Image_For_News\\" + newsToUpdate.getNewsID(), newsToUpdate.getNewsID() + "");
        } else {
            imagePath = si.saveImageByFile(filePart, "D:\\SWP391---SE1887---GROUP2\\FPTBank\\web\\image\\Image_For_News\\" + newsToUpdate.getNewsID(), newsToUpdate.getNewsID() + "");
            si.saveImageByFile(filePart, "D:\\SWP391---SE1887---GROUP2\\FPTBank\\build\\web\\image\\Image_For_News\\" + newsToUpdate.getNewsID(), newsToUpdate.getNewsID() + "");
        }
        newsToUpdate.setImage(imagePath);
        ndao.updateANews(newsToUpdate);
        response.sendRedirect("/timibank/seller/news-management?fromAdd=true");
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
