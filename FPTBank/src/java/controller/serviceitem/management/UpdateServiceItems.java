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

@WebServlet(name = "UpdateServiceItems", urlPatterns = {"/admin/update_serviceItem"})
public class UpdateServiceItems extends HttpServlet {

    private ServiceItemDAO sDao;

    public void init() throws ServletException {
        sDao = new ServiceItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int serviceItemID = Integer.parseInt(request.getParameter("id"));
        request.getSession().setAttribute("serviceItemID", serviceItemID);
        ServiceItem serviceItem = sDao.selectAServiceItemByID(serviceItemID);

        if (serviceItem.getType().equalsIgnoreCase("Saving")) {
            request.getSession().setAttribute("serviceItem", serviceItem);
            response.sendRedirect("FormUpdateServiceItemSaving.jsp");
        } else {
            request.getSession().setAttribute("serviceItem", serviceItem);
            response.sendRedirect("FormUpdateServiceItemLoan.jsp");
        }
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
            request.getRequestDispatcher("FormUpdateServiceItemSaving.jsp").forward(request, response);
            return;
        }

        serviceItem.setServiceItemName(serviceItemName.trim());

        try {
            // MinAmount
            String minAmount = request.getParameter("minAmount");
            if (minAmount != null && !minAmount.isEmpty()) {
                String cleanedMinAmount = minAmount.replace(".", "");
                serviceItem.setMinAmount(new BigDecimal(cleanedMinAmount));
            }

            // MinPeriod
            String minPeriod = request.getParameter("minPeriod");
            if (minPeriod != null && !minPeriod.isEmpty()) {
                serviceItem.setMinPeriod(Integer.parseInt(minPeriod));
            }

            // EarlyWithdrawRate
            String earlyWithdrawRate = request.getParameter("earlyWithdrawRate");
            if (earlyWithdrawRate != null && !earlyWithdrawRate.isEmpty()) {
                serviceItem.setEarlyWithdrawRate(Float.parseFloat(earlyWithdrawRate));
            }

            // InterestRate
            String interestRate = request.getParameter("interestRate");
            if (interestRate != null && !interestRate.isEmpty()) {
                serviceItem.setInterestRate(Float.parseFloat(interestRate));
            }

            // Cập nhật vào database
            sDao.updateAServiceItem(serviceItem);

            // Thông báo thành công
            request.getSession().setAttribute("message", "Update Successfully!");
            response.sendRedirect("manage_serviceItems");
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "Invalid number format in input");
            if (serviceItem.getType().equalsIgnoreCase("Saving")) {
                request.getRequestDispatcher("FormUpdateServiceItemSaving.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("FormUpdateServiceItemLoan.jsp").forward(request, response);
            }
        }
    }
}
