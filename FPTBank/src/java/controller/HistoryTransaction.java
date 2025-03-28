/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.TransactionHistoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.TransactionHistory;

/**
 *
 * @author ACER
 */
@WebServlet(name = "HistoryTransaction", urlPatterns = {"/historytransaction"})
public class HistoryTransaction extends HttpServlet {

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
            out.println("<title>Servlet HistoryTransaction</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HistoryTransaction at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Integer uidObj = (Integer) session.getAttribute("uid");
        if (uidObj == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int uid = uidObj;

        String number = request.getParameter("pagesize");
        String date_1 = request.getParameter("date1");
        String date_2 = request.getParameter("date2");
        boolean check = false;
        
        if(date_1 == null){
            date_1 = "";
        }
        if(date_2 == null){
            date_2 = "";
        }
        
        TransactionHistoryDAO dao = new TransactionHistoryDAO();
        List<TransactionHistory> list = dao.filterListCustomer( date_1, date_2, uid);
        for(TransactionHistory transaction : list){
           check = transaction.getBalanceBefore().compareTo(transaction.getBalanceAfter()) > 0;
        }
        int page = 1;
        int pagesize = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(number == null || number.trim().isEmpty()){
            pagesize = 10;
        }else{
            int numberinpage = Integer.parseInt(number);
            pagesize = numberinpage;
        }
        int totalbill = list.size();
        List<Integer> listint = new ArrayList<>();
        listint.add((int) Math.ceil((double) totalbill / 100 * 10));
        listint.add((int) Math.ceil((double) totalbill / 100 * 30));
        listint.add((int) Math.ceil((double) totalbill / 100 * 50));
        listint.add((int) Math.ceil((double) totalbill / 100 * 70));
        listint.add((int) Math.ceil((double) totalbill / 100 * 100));
        int totalPages = totalbill % pagesize == 0 ? totalbill/pagesize : (totalbill/pagesize) + 1;
        int start = (page - 1)*pagesize;
        int end = page*pagesize > totalbill ? totalbill : page*pagesize;
        list = dao.getListByPage(list, start, end);
        request.setAttribute("listint", listint);
        request.setAttribute("currentPage", page);
        request.setAttribute("check", check);
        request.setAttribute("pagesize", number);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("date1", date_1);
        request.setAttribute("date2", date_2);
        request.setAttribute("listTr", list);
        request.setAttribute("listsize", list.size());
        request.getRequestDispatcher("transactionhistory.jsp").forward(request, response);
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
