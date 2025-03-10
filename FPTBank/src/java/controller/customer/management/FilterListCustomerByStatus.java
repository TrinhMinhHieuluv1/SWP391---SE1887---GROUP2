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
import java.util.List;
import model.Customer;
import model.User;
import utils.UserRoleUtils;

/**
 *
 * @author SCN
 */
@WebServlet(name = "FillterListCusByStatus", urlPatterns = {"/admin/filterListCus_byStatus"})
public class FilterListCustomerByStatus extends HttpServlet {

    private CustomerDAO cDao;

    public void init() throws ServletException {
        cDao = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idOfStatus = -1;
        if (request.getParameter("status") != null && !request.getParameter("status").isEmpty()) {
            idOfStatus = Integer.parseInt(request.getParameter("status"));
        }

        String statusAfterShowEntries_raw = request.getParameter("status");
        if (statusAfterShowEntries_raw != null && !statusAfterShowEntries_raw.isEmpty()) {
            int statusAfterShowEntries = Integer.parseInt(statusAfterShowEntries_raw);
            idOfStatus = statusAfterShowEntries;
        }

        int page = 1; // trang đầu tiên
        int pageSize;

        int entries = (int) request.getSession().getAttribute("entries");
        pageSize = entries;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Customer> listCustomer;
        listCustomer = cDao.filterListUser("Status", idOfStatus, page, pageSize);

        int totalCustomersAfterFilter = cDao.getTotalCustomer("Status", idOfStatus);
        int totalPages = (int) Math.ceil((double) totalCustomersAfterFilter / pageSize);

        // số lượng của user theo từng role
        int totalCustomers = cDao.selectAllCustomer().size();
        request.setAttribute("totalCustomers", totalCustomers);

        // phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("statusOfUser", idOfStatus);
        request.setAttribute("listCustomers", listCustomer);

        request.getRequestDispatcher("ManagementCustomers.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
