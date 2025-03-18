package controller.customer.management;

import controller.user.management.*;
import controller.*;
import dal.CustomerDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import model.User;

@WebServlet(name = "UpdateStatusOfCus", urlPatterns = {"/admin/updateStatusOfCus"})
public class UpdateStatusOfCustomers extends HttpServlet {

    private CustomerDAO cDao;

    public void init() throws ServletException {
        cDao = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String status = request.getParameter("status");
        Boolean checkUpdate = false;
        try {
            String cusID_raw = request.getParameter("cusID");
            int cusID = Integer.parseInt(cusID_raw);
            Boolean isActive = false;

            if (status != null && !status.isEmpty()) {
                Customer customer = cDao.selectCustomerByConditions(cusID, "", "", "");
                if (status.equalsIgnoreCase("true")) {
                    isActive = true;
                }
                customer.setStatus(isActive);
                cDao.updateCustomer(customer);
                checkUpdate = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String message = checkUpdate ? "Update status successfully !!" : "Update status fail !!";
        response.getWriter().write("{\"success\": " + status + ", \"message\": \"" + message + "\"}");
    }

}
