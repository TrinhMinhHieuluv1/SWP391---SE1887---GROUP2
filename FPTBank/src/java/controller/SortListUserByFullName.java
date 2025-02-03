package controller;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author SCN
 */
@WebServlet(name = "SortListUserByFullName", urlPatterns = {"/admin/sort_fullname"})
public class SortListUserByFullName extends HttpServlet {

    private UserDAO uDao;

    public void init() throws ServletException {
        uDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String typeOfSort = request.getParameter("type");
        List<User> listUser;

        int page = 1; // trang đầu tiên
        int pageSize = 10; // 1 trang có 10 users

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        listUser = uDao.sortListUserByFullName(typeOfSort, page, pageSize);

        int totalUsers = uDao.getTotalUsers();
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        // số lượng của user theo từng role
        int numOfAdmin = uDao.getTotalUsersOfEachRole("Admin");
        int numOfSeller = uDao.getTotalUsersOfEachRole("Seller");
        int numOfManager = uDao.getTotalUsersOfEachRole("Manager");
        int numOfProviderInsurance = uDao.getTotalUsersOfEachRole("Provider Insurance");
        int numOfCustomer = uDao.getTotalUsersOfEachRole("Customer");

        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("numOfAdmin", numOfAdmin);
        request.setAttribute("numOfSeller", numOfSeller);
        request.setAttribute("numOfManager", numOfManager);
        request.setAttribute("numOfProviderInsurance", numOfProviderInsurance);
        request.setAttribute("numOfCustomer", numOfCustomer);

        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("listUsers", listUser);
        request.setAttribute("typeOfSort", typeOfSort);
        request.getRequestDispatcher("ManagementUsers.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
