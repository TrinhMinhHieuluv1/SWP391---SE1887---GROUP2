/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.NewsCategoryDAO;
import dal.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import model.News;

/**
 *
 * @author tiend
 */
@WebServlet(name = "NewDetail", urlPatterns = {"/newdetail"})
public class NewDetail extends HttpServlet {

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
            out.println("<title>Servlet NewDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewDetail at " + request.getContextPath() + "</h1>");
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
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
        String id_raw = request.getParameter("newId");
        NewsCategoryDAO dao = new NewsCategoryDAO();
        NewsDAO newsDAO = new NewsDAO();
        try {
            int id = Integer.parseInt(id_raw);

            News anew = newsDAO.selectANewsByNewsID(id);
            anew.setNumberOfAccess(anew.getNumberOfAccess() + 1);
            descriptionSetup(anew);
            newsDAO.updateANews(anew);
            List<News> list = newsDAO.selectNewsListByConditions("", "", "active", "", anew.getNewsCategory().getNewsCategoryID(), 0);
            list = getRandomNews(list, 3);
            request.setAttribute("newdetail", anew);
            request.setAttribute("data", list);
            request.getRequestDispatcher("newDetail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void descriptionSetup(News anew) {
            StringBuilder result = new StringBuilder();
            String descript = anew.getDescription();
            String regex = "\n";
            if (!anew.getDescription().contains(regex)) {
                return ;
            }
            String[] des = descript.split(regex);
            for (String de : des) {
                result.append(de.trim()).append("<br>");
            }
            result.deleteCharAt(result.toString().length() - 1);
            anew.setDescription(result.toString());
    }
    public static List<News> getRandomNews(List<News> newsList, int count) {
        // Kiểm tra xem danh sách có đủ tin tức hay không
        if (count > newsList.size()) {
            count = newsList.size();
        }

        // Xáo trộn danh sách
        Collections.shuffle(newsList);

        // Lấy ra 3 tin tức đầu tiên
        return newsList.subList(0, count);
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
        processRequest(request, response);
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
