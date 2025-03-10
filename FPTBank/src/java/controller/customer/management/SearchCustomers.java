package controller.customer.management;

import controller.user.management.*;
import controller.*;
import dal.CustomerDAO;
import dal.UserDAO;
import java.io.IOException;
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
@WebServlet(name = "SearchCustomer", urlPatterns = {"/admin/search_customers"})
public class SearchCustomers extends HttpServlet {

    private CustomerDAO cDao;

    public void init() throws ServletException {
        cDao = new CustomerDAO();
    }

    public String normalizeStringOfKeyWordCus (String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return "";
        }
        return keyword.trim().replaceAll("\\s+", " ");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword_raw = request.getParameter("keyWord");

        int page = 1; // trang đầu tiên
        int pageSize;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int entries = (int) request.getSession().getAttribute("entries");
        pageSize = entries;

        // get value keyword được gửi từ svlet manage_users
        String keywordFromManageUser = request.getParameter("key");
        if (keywordFromManageUser != null && !keywordFromManageUser.isEmpty()) {
            keyword_raw = keywordFromManageUser;
        }

        String keyword = normalizeStringOfKeyWordCus(keyword_raw);

        List<Customer> listCustomer;
        if (keyword != null && !keyword.isEmpty()) {
            listCustomer = cDao.searchCustomers(keyword, page, pageSize);
        } else {
            // Nếu không có từ khóa, trả về toàn bộ danh sách
            response.sendRedirect("manage_customers");
            return;
        }

        int totalCusAfterSearching = cDao.getTotalCustomerAfterSearching(keyword);
        int totalPages = (int) Math.ceil((double) totalCusAfterSearching / pageSize);

        int totalCustomers = cDao.selectAllCustomer().size();
        request.setAttribute("totalCustomers", totalCustomers);

        // set phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.setAttribute("keyWord", keyword);
        request.setAttribute("listCustomers", listCustomer);

        request.getRequestDispatcher("ManagementCustomers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
