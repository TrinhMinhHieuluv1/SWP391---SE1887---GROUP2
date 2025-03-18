/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.vnpay.com;

import controller.billprovdider.management.sendMailbillProvider;
import dal.CompanyBillProviderDAO;
import dal.CustomerDAO;
import dal.DetailBillDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import model.CompanyBillProvider;
import model.Customer;
import model.DetailBill;

/**
 *
 * @author ACER
 */
public class vnpayRefund extends HttpServlet {

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
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
           

                String billID_raw = request.getParameter("billID");
                int billID = 0;
                if (billID_raw != null && !billID_raw.isEmpty()) {
                    billID = Integer.parseInt(billID_raw);
                }
                String error = "";
                int uid = (int) session.getAttribute("uid");
                CustomerDAO cdao = new CustomerDAO();
                Customer customer = cdao.selectCustomerByConditions(uid, "", "", "");
                DetailBillDAO dao = new DetailBillDAO();
                DetailBill bill = dao.getDetailBillByID("BillID", billID);
                boolean transSuccess = false;
                LocalDateTime now = LocalDateTime.now();
                Timestamp paymentTimestamp = Timestamp.valueOf(now);
                CompanyBillProviderDAO cdaoo = new CompanyBillProviderDAO();
                CompanyBillProvider company = cdaoo.getCompanyById("ProviderID", bill.getProvider().getUserID());
                if ("00".equals(request.getParameter("vnp_ResponseCode"))) {
                    bill.setStatusOfBill(0);
                    bill.setPaymentDate(paymentTimestamp);
                    boolean mail = sendMailbillProvider.guiMailforPaying(customer.getEmail(), billID, bill.getTitle(), bill.getDescription(), bill.getStartDate(), bill.getEndDate(), bill.getCreatedAt(), bill.getTotal(), company.getCompanyName(), customer, paymentTimestamp);
                    if (mail) {
                        error = "Paid successfully and sent a email for customer";
                    } else {
                        error = "Don't send mail";
                    }
                    transSuccess = true;
                } else {
                    bill.setStatusOfBill(1);
                    error = "Paid failed";
                    System.out.println(error);
                }
                dao.updateDetailBill(bill);
                request.setAttribute("bill", bill);
                request.setAttribute("error", error);
                request.getRequestDispatcher("payment.jsp").forward(request, response);
           
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
//        //Command: refund
//        String vnp_RequestId = Config.getRandomNumber(8);
//        String vnp_Version = "2.1.0";
//        String vnp_Command = "refund";
//        String vnp_TmnCode = Config.vnp_TmnCode;
//        String vnp_TransactionType = request.getParameter("trantype");
//        String vnp_TxnRef = request.getParameter("order_id");
//        long amount = Integer.parseInt(request.getParameter("amount")) * 100;
//        String vnp_Amount = String.valueOf(amount);
//        String vnp_OrderInfo = "Hoan tien GD OrderId:" + vnp_TxnRef;
//        String vnp_TransactionNo = ""; //Assuming value of the parameter "vnp_TransactionNo" does not exist on your system.
//        String vnp_TransactionDate = request.getParameter("trans_date");
//        String vnp_CreateBy = request.getParameter("user");
//
//        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//        String vnp_CreateDate = formatter.format(cld.getTime());
//
//        String vnp_IpAddr = Config.getIpAddress(request);
//
//        JsonObject vnp_Params = new JsonObject();
//
//        vnp_Params.addProperty("vnp_RequestId", vnp_RequestId);
//        vnp_Params.addProperty("vnp_Version", vnp_Version);
//        vnp_Params.addProperty("vnp_Command", vnp_Command);
//        vnp_Params.addProperty("vnp_TmnCode", vnp_TmnCode);
//        vnp_Params.addProperty("vnp_TransactionType", vnp_TransactionType);
//        vnp_Params.addProperty("vnp_TxnRef", vnp_TxnRef);
//        vnp_Params.addProperty("vnp_Amount", vnp_Amount);
//        vnp_Params.addProperty("vnp_OrderInfo", vnp_OrderInfo);
//
//        if (vnp_TransactionNo != null && !vnp_TransactionNo.isEmpty()) {
//            vnp_Params.addProperty("vnp_TransactionNo", "{get value of vnp_TransactionNo}");
//        }
//
//        vnp_Params.addProperty("vnp_TransactionDate", vnp_TransactionDate);
//        vnp_Params.addProperty("vnp_CreateBy", vnp_CreateBy);
//        vnp_Params.addProperty("vnp_CreateDate", vnp_CreateDate);
//        vnp_Params.addProperty("vnp_IpAddr", vnp_IpAddr);
//
//        String hash_Data = String.join("|", vnp_RequestId, vnp_Version, vnp_Command, vnp_TmnCode,
//                vnp_TransactionType, vnp_TxnRef, vnp_Amount, vnp_TransactionNo, vnp_TransactionDate,
//                vnp_CreateBy, vnp_CreateDate, vnp_IpAddr, vnp_OrderInfo);
//
//        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hash_Data.toString());
//
//        vnp_Params.addProperty("vnp_SecureHash", vnp_SecureHash);
//
//        URL url = new URL(Config.vnp_ApiUrl);
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("POST");
//        con.setRequestProperty("Content-Type", "application/json");
//        con.setDoOutput(true);
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//        wr.writeBytes(vnp_Params.toString());
//        wr.flush();
//        wr.close();
//        int responseCode = con.getResponseCode();
//        System.out.println("nSending 'POST' request to URL : " + url);
//        System.out.println("Post Data : " + vnp_Params);
//        System.out.println("Response Code : " + responseCode);
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String output;
//        StringBuffer res = new StringBuffer();
//        while ((output = in.readLine()) != null) {
//            res.append(output);
//        }
//        in.close();
//        System.out.println(response.toString());

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
