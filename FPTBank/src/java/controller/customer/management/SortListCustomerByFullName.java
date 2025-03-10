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
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.User;
import utils.UserRoleUtils;

/**
 *
 * @author SCN
 */
@WebServlet(name = "SortListCusByFullName", urlPatterns = {"/admin/sortListCus_fullname"})
public class SortListCustomerByFullName extends HttpServlet {

    private CustomerDAO cDao;

    public void init() throws ServletException {
        cDao = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String typeOfSort = request.getParameter("type1");

        // get value typeOfSort được gửi từ svlet manage_users
        String typeOfSortFromManageUser = request.getParameter("typeOfSort");
        if (typeOfSortFromManageUser != null && !typeOfSortFromManageUser.isEmpty()) {
            typeOfSort = typeOfSortFromManageUser;
        }

        int page = 1; // trang đầu tiên
        int pageSize;

        int entries = (int) request.getSession().getAttribute("entries");
        pageSize = entries;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Customer> listCustomer;
        listCustomer = cDao.sortListCustomer("FullName", typeOfSort, page, pageSize);

        int totalCustomers = cDao.selectAllCustomer().size();
        int totalPages = (int) Math.ceil((double) totalCustomers / pageSize);
        request.setAttribute("totalCustomers", totalCustomers);

        // set phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.setAttribute("listCustomers", listCustomer);
        request.setAttribute("typeOfSortByName", typeOfSort);
        request.getRequestDispatcher("ManagementCustomers.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
