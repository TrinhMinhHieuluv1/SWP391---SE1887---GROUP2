/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.faq.managament;

import dal.FAQDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import model.FAQ;

/**
 *
 * @author hungk
 */
@WebServlet(name = "faqServlet", urlPatterns = {"/faq"})
public class faqServlet extends HttpServlet {
    
    
private List<Integer> calculatePageSize() {
                 
     FAQDAO faqDAO = new FAQDAO();

        List<Integer> listOfPageSize = new ArrayList<>();
        int totalFaq = faqDAO.getAllFAQs().size();

        double[] percentages = {0.1, 0.3, 0.5, 0.7, 1.0}; // 10%,30%,50%,70%,100% of total users
        for (double percentage : percentages) {
            listOfPageSize.add((int) Math.ceil(totalFaq * percentage));
        }

        return listOfPageSize;
    }
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
            out.println("<title>Servlet faqServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet faqServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
         List<Integer> listOfPageSize = calculatePageSize();
        
        
         HttpSession session = request.getSession();    
        FAQDAO dao = new FAQDAO();
        List<FAQ> list = dao.getAllFAQs();
        int page = 1; // trang đầu tiên
        int pageSize = listOfPageSize.get(0); // gán mặc định trang 1 là 10%
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        
        // get entries from drownlist with user option
        String entries_raw = request.getParameter("entries");
        if (entries_raw != null && !entries_raw.isEmpty()) {
            pageSize = Integer.parseInt(entries_raw);
        }
        String keyword = request.getParameter("searchKey");
      
         if (request.getParameter("searchKey") != null && !request.getParameter("searchKey").isEmpty()) {
            request.getSession().setAttribute("entries", pageSize);
            String encodedKeyword = URLEncoder.encode(request.getParameter("searchKey"), "UTF-8");
            response.sendRedirect("faq-servlet-search?key=" + encodedKeyword);
            return;
        }
        
          String searchType = request.getParameter("searchType");
        if (searchType != null && !searchType.isEmpty()) {
            request.getSession().setAttribute("entries", pageSize);
            response.sendRedirect("faq-servlet-type?keytype=" + searchType);
            return;
        }
      
        FAQDAO faqDAO = new FAQDAO();
        ArrayList<FAQ> listFAQ = faqDAO.getListFAQByPage(page, pageSize);

        int totalUsers = faqDAO.getAllFAQs().size();

        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);
 // set phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
          request.setAttribute("listFAQ", listFAQ);
                  request.getSession().setAttribute("listOfPageSize", listOfPageSize);

          request.getSession().setAttribute("entries", pageSize);
        request.getRequestDispatcher("faq.jsp").forward(request, response);
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
