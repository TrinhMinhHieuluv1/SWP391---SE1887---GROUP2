/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.billprovdider.management;

import controller.vnpay.com.Config;
import dal.CompanyBillProviderDAO;
import dal.CustomerDAO;
import dal.DetailBillDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dal.TransactionHistoryDAO;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import model.CompanyBillProvider;
import model.Customer;
import model.TransactionHistory;

/**
 *
 * @author ACER
 */
@WebServlet(name = "Payment", urlPatterns = {"/payment"})
public class Payment extends HttpServlet {

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
            out.println("<title>Servlet Payment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Payment at " + request.getContextPath() + "</h1>");
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
        int uid = (int) session.getAttribute("uid");
        DetailBillDAO dao = new DetailBillDAO();
        CompanyBillProviderDAO cdao = new CompanyBillProviderDAO();
        CustomerDAO udao = new CustomerDAO();
        TransactionHistoryDAO tdao = new TransactionHistoryDAO();
        String billID_raw = request.getParameter("billID");
        String providerID_raw = request.getParameter("providerID");
        String total_raw = request.getParameter("total");
        String action = request.getParameter("action");
        String paymentmethod = request.getParameter("paymentmethod");
        if (total_raw == null || total_raw.isEmpty()) {
            total_raw = "0";
        }
        if (billID_raw == null || billID_raw.isEmpty()) {
            billID_raw = "0";
        }
        if (providerID_raw == null || providerID_raw.isEmpty()) {
            providerID_raw = "0";
        }
        double totall = Double.parseDouble(total_raw);
        int billID = Integer.parseInt(billID_raw);
        int providerID = Integer.parseInt(providerID_raw);
        model.DetailBill bill = dao.getDetailBillByID("BillID", billID);
        CompanyBillProvider company = cdao.getCompanyById("ProviderID", providerID);
        Customer customer = udao.selectCustomerByConditions(uid, "", "", "");
        String error = "";
        if ("Paid".equals(action) && "balance".equals(paymentmethod)) {
            BigDecimal total = BigDecimal.valueOf(totall);
            BigDecimal balance = customer.getBalance();

            if (total.compareTo(balance) > 0) {
                error = "Your balance is enough to paid";
            } else {
                LocalDateTime now = LocalDateTime.now();
                Timestamp paymentTimestamp = Timestamp.valueOf(now);
                BigDecimal balanceBefore = customer.getBalance(); 
                BigDecimal balanceAfter = balanceBefore.subtract(total);
                bill.setStatusOfBill(0);
                bill.setPaymentDate(paymentTimestamp);
                customer.setBalance(balanceAfter);
                boolean mail = sendMailbillProvider.guiMailforPaying(customer.getEmail(), bill.getBillID(), bill.getTitle(), bill.getDescription(), bill.getStartDate(), bill.getEndDate(), bill.getEndDate(), bill.getTotal(), company.getCompanyName(), customer, paymentTimestamp);
                if (mail) {
                    error = "Paid successfully and send invoice to email";
                    dao.updateDetailBill(bill);
                    udao.updateACustomer(customer);
                    TransactionHistory transaction = new TransactionHistory(1, customer, total, balanceBefore, balanceAfter, "Bill Payment", "Bill Payment");
                    tdao.addTransaction(transaction);
                } else {
                    error = "Don't send email";
                }
            }
        } else if ("Paid".equals(action) && "transfer".equals(paymentmethod)) {
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String orderType = "other";
            long amount = (long) (totall * 100);
            String vnp_TxnRef = billID + "";
            String vnp_IpAddr = Config.getIpAddress(request);
            String vnp_TmnCode = Config.vnp_TmnCode;
            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
            vnp_Params.put("vnp_OrderType", orderType);

            String locate = request.getParameter("language");
            if (locate != null && !locate.isEmpty()) {
                vnp_Params.put("vnp_Locale", locate);
            } else {
                vnp_Params.put("vnp_Locale", "vn");
            }
            vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl + "?total=" + bill.getTotal() + "&billID=" + bill.getBillID() + "&providerID=" + bill.getProvider().getUserID());
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
            response.sendRedirect(paymentUrl);
            return;
        }
        request.setAttribute("error", error);
        request.setAttribute("customer", customer);
        request.setAttribute("bill", bill);
        request.setAttribute("company", company);
        request.getRequestDispatcher("payment.jsp").forward(request, response);
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
