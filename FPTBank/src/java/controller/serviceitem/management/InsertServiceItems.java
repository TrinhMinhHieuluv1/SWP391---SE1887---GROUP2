package controller.serviceitem.management;

import controller.user.management.*;
import Tools.HashString;
import dal.ServiceItemDAO;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.User;
import java.text.SimpleDateFormat;
import java.sql.Date;

import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import model.ServiceItem;

@WebServlet(name = "InsertServiceItems", urlPatterns = {"/admin/insert_serviceItem"})
public class InsertServiceItems extends HttpServlet {

    private ServiceItemDAO sDao;

    public void init() throws ServletException {
        sDao = new ServiceItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("FormAddServiceItem.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServiceItem serviceItemToAdd = new ServiceItem();

        // ServiceItemName
        String serviceItemName = request.getParameter("serviceItemName");
        if (serviceItemName == null || serviceItemName.trim().isEmpty()) {
            request.getSession().setAttribute("error", "Service Item Name is required");
            request.getRequestDispatcher("FormAddServiceItem.jsp").forward(request, response);
            return;
        }
        serviceItemToAdd.setServiceItemName(serviceItemName.trim());
        serviceItemToAdd.setStatus(true);
        serviceItemToAdd.setServiceItemID(0);

        try {
            // MaxAmount
            String maxAmount = request.getParameter("maxAmount");
            if (maxAmount != null && !maxAmount.isEmpty()) {
                String cleanedMaxAmount = maxAmount.replace(".", "");
                serviceItemToAdd.setMaxAmount(new BigDecimal(cleanedMaxAmount));
            }

            // MaxPeriod
            String maxPeriod = request.getParameter("maxPeriod");
            if (maxPeriod != null && !maxPeriod.isEmpty()) {
                serviceItemToAdd.setMaxPeriod(Integer.parseInt(maxPeriod));
            }

            // MinCreditScore
            String minCreditScore = request.getParameter("minCreditScore");
            if (minCreditScore != null && !minCreditScore.isEmpty()) {
                serviceItemToAdd.setMinCreditScore(Integer.parseInt(minCreditScore));
            }

            // LatePaymentRate
            String latePaymentRate = request.getParameter("latePaymentRate");
            if (latePaymentRate != null && !latePaymentRate.isEmpty()) {
                serviceItemToAdd.setLatePaymentRate(Float.parseFloat(latePaymentRate));
            }

            // MinAmount
            String minAmount = request.getParameter("minAmount");
            if (minAmount != null && !minAmount.isEmpty()) {
                String cleanedMinAmount = minAmount.replace(".", "");
                serviceItemToAdd.setMinAmount(new BigDecimal(cleanedMinAmount));
            }

            // MinPeriod
            String minPeriod = request.getParameter("minPeriod");
            if (minPeriod != null && !minPeriod.isEmpty()) {
                serviceItemToAdd.setMinPeriod(Integer.parseInt(minPeriod));
            }

            // EarlyWithdrawRate
            String earlyWithdrawRate = request.getParameter("earlyWithdrawRate");
            if (earlyWithdrawRate != null && !earlyWithdrawRate.isEmpty()) {
                serviceItemToAdd.setEarlyWithdrawRate(Float.parseFloat(earlyWithdrawRate));
            }

            // InterestRate
            String interestRate = request.getParameter("interestRate");
            if (interestRate != null && !interestRate.isEmpty()) {
                serviceItemToAdd.setInterestRate(Float.parseFloat(interestRate));
            }

            // Type
            String type = request.getParameter("type");
            if (type == null || type.trim().isEmpty()) {
                request.getSession().setAttribute("error", "Type is required");
                request.getRequestDispatcher("FormAddServiceItem.jsp").forward(request, response);
                return;
            }
            serviceItemToAdd.setType(type.trim());

            // Check for Loan
            if (type.equalsIgnoreCase("Secured Loan") || type.equalsIgnoreCase("Unsecured Loan")) {
                StringBuilder errorMsg = new StringBuilder();
                if (minPeriod != null && !minPeriod.isEmpty()) {
                    errorMsg.append("Min Period is not allowed for Loan. ");
                }
                if (minAmount != null && !minAmount.isEmpty()) {
                    errorMsg.append("Min Amount is not allowed for Loan. ");
                }
                if (earlyWithdrawRate != null && !earlyWithdrawRate.isEmpty()) {
                    errorMsg.append("Early Withdraw Rate is not allowed for Loan. ");
                }
                if (errorMsg.length() > 0) {
                    request.getSession().setAttribute("error", errorMsg.toString());
                    request.getSession().setAttribute("serviceItemToAdd", serviceItemToAdd);
                    request.getRequestDispatcher("FormAddServiceItem.jsp").forward(request, response);
                    return;
                }
                // Ensure required fields for Saving
                if (maxPeriod == null || maxPeriod.isEmpty()) {
                    errorMsg.append("Max Period is required for Loan. ");
                }
                if (maxAmount == null || maxAmount.isEmpty()) {
                    errorMsg.append("Max Amount is required for Loan. ");
                }
                if (latePaymentRate == null || latePaymentRate.isEmpty()) {
                    errorMsg.append("Late Payment Rate is required for Loan. ");
                }
                if (minCreditScore == null || minCreditScore.isEmpty()) {
                    errorMsg.append("Min Credit Score is required for Loan. ");
                }
                if (errorMsg.length() > 0) {
                    request.getSession().setAttribute("error", errorMsg.toString());
                    request.getSession().setAttribute("serviceItemToAdd", serviceItemToAdd);
                    request.getRequestDispatcher("FormAddServiceItem.jsp").forward(request, response);
                    return;
                }
            }

            // Check for Saving
            if (type.equalsIgnoreCase("Saving")) {
                StringBuilder errorMsg = new StringBuilder();
                if (maxAmount != null && !maxAmount.isEmpty()) {
                    errorMsg.append("Max Amount is not allowed for Saving. ");
                }
                if (maxPeriod != null && !maxPeriod.isEmpty()) {
                    errorMsg.append("Max Period is not allowed for Saving. ");
                }
                if (minCreditScore != null && !minCreditScore.isEmpty()) {
                    errorMsg.append("Min Credit Score is not allowed for Saving. ");
                }
                if (latePaymentRate != null && !latePaymentRate.isEmpty()) {
                    errorMsg.append("Late Payment Rate is not allowed for Saving. ");
                }
                if (errorMsg.length() > 0) {
                    request.getSession().setAttribute("error", errorMsg.toString());
                    request.getSession().setAttribute("serviceItemToAdd", serviceItemToAdd);
                    request.getRequestDispatcher("FormAddServiceItem.jsp").forward(request, response);
                    return;
                }
                // Ensure required fields for Saving
                if (minPeriod == null || minPeriod.isEmpty()) {
                    errorMsg.append("Min Period is required for Saving. ");
                }
                if (minAmount == null || minAmount.isEmpty()) {
                    errorMsg.append("Min Amount is required for Saving. ");
                }
                if (earlyWithdrawRate == null || earlyWithdrawRate.isEmpty()) {
                    errorMsg.append("Early Withdraw Rate is required for Saving. ");
                }
                if (errorMsg.length() > 0) {
                    request.getSession().setAttribute("error", errorMsg.toString());
                    request.getSession().setAttribute("serviceItemToAdd", serviceItemToAdd);
                    request.getRequestDispatcher("FormAddServiceItem.jsp").forward(request, response);
                    return;
                }
            }

            int row = sDao.addServiceItemReturnRow(serviceItemToAdd);
            switch (row) {
                case 1:
                    request.getSession().setAttribute("message", "Insert Successfully !!");
                    request.getSession().removeAttribute("serviceItemToAdd");
                    break;
                case 2:
                    request.getSession().setAttribute("error", "ServiceItem has already exists. Please enter another name !!");
                    request.getSession().setAttribute("serviceItemToAdd", serviceItemToAdd);
                    break;
                case 3:
                    request.getSession().setAttribute("error", "Max amount and Max period of service item has already exists. Please enter another value !!");
                    request.getSession().setAttribute("serviceItemToAdd", serviceItemToAdd);
                    break;
                case 4:
                    request.getSession().setAttribute("error", "Min amount and Min period of service item has already exists. Please enter another value !!");
                    request.getSession().setAttribute("serviceItemToAdd", serviceItemToAdd);
                    break;
                case 0:
                    request.getSession().setAttribute("error", "Insert fail !!");
                    break;
            }
            request.getRequestDispatcher("FormAddServiceItem.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "Invalid number format in input");
            request.getSession().setAttribute("serviceItemToAdd", serviceItemToAdd);
            request.getRequestDispatcher("FormAddServiceItem.jsp").forward(request, response);
        }
    }
}
