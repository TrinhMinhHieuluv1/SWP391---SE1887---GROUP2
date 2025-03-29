package controller.service;

import dal.ServiceItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import model.ServiceItem;

/**
 *
 * @author HP
 */
@WebServlet(name = "getServiceItemList", urlPatterns = {"/get-service-item-list"})
public class getServiceItemList extends HttpServlet {

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
            out.println("<title>Servlet getServiceItemList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet getServiceItemList at " + request.getContextPath() + "</h1>");
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
        ServiceItemDAO sidao = new ServiceItemDAO();
        String Amount_raw = request.getParameter("Amount");
        Amount_raw = Amount_raw.replace(".", "");
        String Type = request.getParameter("Type");
        BigDecimal Amount = BigDecimal.ZERO;
        DecimalFormat df = new DecimalFormat("#,###");
        try {
            Amount = BigDecimal.valueOf(Long.parseLong(Amount_raw));
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        if (Type.equals("Secured")) {
            Type = "Secured Loan";
        } else if (Type.equals("Unsecured")) {
            Type = "Unsecured Loan";
        }
        List<ServiceItem> serviceItemList = sidao.getServiceItemList(Amount, Type);
        if (serviceItemList.size() > 0) {
            if (Type.equals("Saving")) {
                response.getWriter().println("<tr>\n"
                        + "     <th></th>\n"
                        + "     <th>Service Item Name</th>\n"
                        + "     <th>Min Amount</th>\n"
                        + "     <th>Min Period (months)</th>\n"
                        + "     <th>Early Withdraw Rate (%)</th>\n"
                        + "     <th>Interest Rate (%)</th>\n"
                        + "</tr>");
                for (ServiceItem serviceItem : serviceItemList) {
                    response.getWriter().println("<tr>\n"
                            + "<th><input name=\"choosenServiceItem\" type=\"radio\" "
                            + "value=\"" + serviceItem.getServiceItemID()
                            + "-" + serviceItem.getServiceItemName()
                            + "-" + df.format(serviceItem.getMinAmount())
                            + "-" + serviceItem.getMinPeriod()
                            + "-" + serviceItem.getEarlyWithdrawRate()
                            + "-" + serviceItem.getInterestRate() + "\"></th>\n"
                            + "<th>" + serviceItem.getServiceItemName() + "</th>\n"
                            + "<th>" + df.format(serviceItem.getMinAmount()) + "</th>\n"
                            + "<th>" + serviceItem.getMinPeriod() + "</th>\n"
                            + "<th>" + serviceItem.getEarlyWithdrawRate() + "</th>\n"
                            + "<th>" + serviceItem.getInterestRate() + "</th>\n"
                            + "</tr>");
                }
            } else {
                response.getWriter().println("<tr>\n"
                        + "<th></th>\n"
                        + "<th>Service Item Name</th>\n"
                        + "<th>Max Amount</th>\n"
                        + "<th>Max Period (months)</th>\n"
                        + "<th>Min Credit Score</th>\n"
                        + "<th>Late Payment Rate (%)</th>\n"
                        + "<th>Interest Rate (%)</th>\n"
                        + "</tr>");
                for (ServiceItem serviceItem : serviceItemList) {
                    response.getWriter().println("<tr>\n"
                            + "<th><input name=\"choosenServiceItem\" type=\"radio\" "
                            + "value=\"" + serviceItem.getServiceItemID()
                            + "-" + serviceItem.getServiceItemName()
                            + "-" + df.format(serviceItem.getMaxAmount())
                            + "-" + serviceItem.getMaxPeriod()
                            + "-" + serviceItem.getMinCreditScore()
                            + "-" + serviceItem.getLatePaymentRate()
                            + "-" + serviceItem.getInterestRate() + "\"></th>\n"
                            + "<th>" + serviceItem.getServiceItemName() + "</th>\n"
                            + "<th>" + df.format(serviceItem.getMaxAmount()) + "</th>\n"
                            + "<th>" + serviceItem.getMaxPeriod() + "</th>\n"
                            + "<th>" + serviceItem.getMinCreditScore() + "</th>\n"
                            + "<th>" + serviceItem.getLatePaymentRate() + "</th>\n"
                            + "<th>" + serviceItem.getInterestRate() + "</th>\n"
                            + "</tr>");
                }
            }
        } else {
            response.getWriter().println("<tr><h5 style=\"color: red\">We don't provide any loan item for your loan requirement. Try to change your requirement!</h5></tr>");
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
