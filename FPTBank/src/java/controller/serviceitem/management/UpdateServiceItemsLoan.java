package controller.serviceitem.management;

import dal.ServiceItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import model.ServiceItem;

@WebServlet(name = "UpdateServiceItemsLoan", urlPatterns = {"/admin/update_serviceItemLoan"})
public class UpdateServiceItemsLoan extends HttpServlet {

    private ServiceItemDAO sDao;

    @Override
    public void init() throws ServletException {
        sDao = new ServiceItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = (Integer) request.getSession().getAttribute("serviceItemID");

        ServiceItem serviceItem = sDao.selectAServiceItemByID(id);

        // ServiceItemName
        String serviceItemName = request.getParameter("serviceItemName");
        if (serviceItemName == null || serviceItemName.trim().isEmpty()) {
            request.getSession().setAttribute("error", "Service Item Name is required");
            request.getRequestDispatcher("FormUpdateServiceItemLoan.jsp").forward(request, response);
            return;
        }
        serviceItem.setServiceItemName(serviceItemName.trim());

        try {
            // MaxAmount
            String maxAmount = request.getParameter("maxAmount");
            if (maxAmount != null && !maxAmount.isEmpty()) {
                String cleanedMaxAmount = maxAmount.replace(".", "");
                serviceItem.setMaxAmount(new BigDecimal(cleanedMaxAmount));
            }

            // MaxPeriod
            String maxPeriod = request.getParameter("maxPeriod");
            if (maxPeriod != null && !maxPeriod.isEmpty()) {
                serviceItem.setMaxPeriod(Integer.parseInt(maxPeriod));
            }

            // LatePaymentRate
            String latePaymentRate = request.getParameter("latePaymentRate");
            if (latePaymentRate != null && !latePaymentRate.isEmpty()) {
                serviceItem.setLatePaymentRate(Float.parseFloat(latePaymentRate));
            }

            // MinCreditScore
            String minCreditScore = request.getParameter("minCreditScore");
            if (minCreditScore != null && !minCreditScore.isEmpty()) {
                serviceItem.setMinCreditScore(Integer.parseInt(minCreditScore));
            }

            // InterestRate
            String interestRate = request.getParameter("interestRate");
            if (interestRate == null || interestRate.trim().isEmpty()) {
                request.getSession().setAttribute("error", "Interest Rate is required");
                request.getRequestDispatcher("FormUpdateServiceItemLoan.jsp").forward(request, response);
                return;
            }
            serviceItem.setInterestRate(Float.parseFloat(interestRate));

            // Cập nhật vào database
            sDao.updateAServiceItem(serviceItem);

            // Thông báo thành công
            request.getSession().setAttribute("message", "Update Successfully!");
            response.sendRedirect("manage_serviceItems");
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "Invalid number format in input");
            request.getSession().setAttribute("serviceItem", serviceItem);
            request.getRequestDispatcher("FormUpdateServiceItemLoan.jsp").forward(request, response);
        }
    }
}
