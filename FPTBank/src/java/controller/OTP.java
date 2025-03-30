/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.billprovdider.management.sendMailbillProvider;
import dal.CustomerDAO;
import dal.TransactionHistoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Random;
import model.Customer;
import model.TransactionHistory;

/**
 *
 * @author ACER
 */
@WebServlet(name = "OTP", urlPatterns = {"/otp"})
public class OTP extends HttpServlet {

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
            out.println("<title>Servlet OTP</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OTP at " + request.getContextPath() + "</h1>");
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
        HttpSession userSession = request.getSession();
        Integer uidObj = (Integer) userSession.getAttribute("uid");

// Kiểm tra nếu chưa đăng nhập thì chuyển hướng đến trang login
        if (uidObj == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int uid = uidObj;
        String sessionOTP = (String) userSession.getAttribute("OTP");
        String number = request.getParameter("number");
        String note = request.getParameter("note");
        String amount = request.getParameter("amount");
        String receiveID = request.getParameter("receiverID");
        String transferID = request.getParameter("transferor");
        String action = request.getParameter("action");
        String OTP_raw = request.getParameter("otp"); // Lấy OTP mà người dùng nhập từ form
        String error = "";
        Integer solannhap = (Integer) userSession.getAttribute("solannhap");
        if (solannhap == null) {
            solannhap = 0; // Initialize if this is the user's first attempt
        }
        CustomerDAO dao = new CustomerDAO();
        TransactionHistoryDAO tdao = new TransactionHistoryDAO();
        Customer customer = dao.selectCustomerByConditions(uid, "", "", "");
// Nếu session chưa có OTP, tạo mới
        if (sessionOTP == null) {
            sessionOTP = generateRandomSixDigits();
            userSession.setAttribute("OTP", sessionOTP);

            boolean mailSent = sendMailbillProvider.guiMailforOTP(customer.getEmail(), sessionOTP);
            if (!mailSent) {
                error = "Failed to send OTP email";
            }
        }

        try {
            int receiveID_raw = Integer.parseInt(receiveID);
            int transferID_raw = Integer.parseInt(transferID);

            Customer receiver = dao.selectCustomerByConditions(receiveID_raw, "", "", "");
            Customer transferor = dao.selectCustomerByConditions(transferID_raw, "", "", "");

            if (receiver == null || transferor == null) {
                error = "Invalid receiver or transferor ID";
            } else {
                // Nếu người dùng nhấn Verify
                if ("verify".equals(action)) {
                    // Kiểm tra nếu đã vượt quá 3 lần thử
                    if (solannhap >= 3) {
                        error = "You have exceeded the maximum number of OTP attempts.";
                        request.setAttribute("message2", error);
                        request.setAttribute("customer", customer);
                        request.getRequestDispatcher("transaction").forward(request, response);
                        solannhap = 0;
                        userSession.setAttribute("solannhap", solannhap);
                        return;
                    }
                    if (OTP_raw == null || OTP_raw.isEmpty()) {
                        error = "Please fill out the OTP";
                        solannhap++;

                    } else if (!sessionOTP.equals(OTP_raw)) {
                        error = "OTP incorrect";
                        solannhap++;

                    } else {
                        try {
                            String amountt = amount.replace(",", "");
                            double amount_raw = Double.parseDouble(amountt);
                            BigDecimal amountValue = BigDecimal.valueOf(amount_raw);

                            BigDecimal receiverBefore = receiver.getBalance();
                            BigDecimal receiverAfter = receiverBefore.add(amountValue);
                            BigDecimal transferBefore = transferor.getBalance();
                            BigDecimal transferAfter = transferBefore.subtract(amountValue);
                            receiver.setBalance(receiverAfter);
                            transferor.setBalance(transferAfter);
                            dao.updateACustomer(receiver);
                            dao.updateACustomer(transferor);
                            if (transferAfter.compareTo(BigDecimal.ZERO) < 0) {
                                error = "Insufficient balance";
                            } else {
                                TransactionHistory receiverTransaction = new TransactionHistory(1, receiver, transferor, amountValue, receiverBefore, receiverAfter, "Receive money ", note);
                                TransactionHistory transferTransaction = new TransactionHistory(1, transferor, receiver, amountValue, transferBefore, transferAfter, "Transfer money ", note);

                                tdao.addTransaction(transferTransaction);
                                tdao.addTransaction(receiverTransaction);

                                error = "Transfer money successfully";

                                userSession.removeAttribute("OTP");
                                request.setAttribute("message", error);
                                request.getRequestDispatcher("transaction").forward(request, response);
                                return;
                            }
                        } catch (NumberFormatException e) {
                            error = "Invalid amount format";
                            request.setAttribute("error", error);
                        }
                    }
                     userSession.setAttribute("solannhap", solannhap);
                }
            }
        } catch (NumberFormatException e) {
            error = "Invalid ID format";
        }


        request.setAttribute("number", number);
        request.setAttribute("amount", amount);
        request.setAttribute("note", note);
        request.setAttribute("receiverID", receiveID);
        request.setAttribute("transferor", transferID);
        request.setAttribute("error", error);

        request.getRequestDispatcher("OTP.jsp").forward(request, response);

    }

    public static String generateRandomSixDigits() {
        Random random = new Random();
        int number = 100000 + random.nextInt(900000);
        return String.valueOf(number);
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
