package controller;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.User;

/**
 *
 * @author SCN
 */
@WebServlet(name = "FilterListUserByRoleName", urlPatterns = {"/admin/filter_roleName"})
public class FilterListUserByRoleName extends HttpServlet {

    private UserDAO uDao;

    public void init() throws ServletException {
        uDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idOfRole = Integer.parseInt(request.getParameter("id"));
        List<User> listUser;

        int page = 1; // trang đầu tiên
        int pageSize = 10; // 1 trang có 10 users

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        listUser = uDao.filterListUser("RoleID", idOfRole, page, pageSize);

        int totalUsersAfterFilter = uDao.getTotalUsers("RoleID", idOfRole);
        int totalPages = (int) Math.ceil((double) totalUsersAfterFilter / pageSize);

        
        // tính số lượng của user theo từng role
        int totalUsers = uDao.getTotalUsers(null, null);
        int numOfAdmin = uDao.getTotalUsers("RoleID", 1);
        int numOfSeller = uDao.getTotalUsers("RoleID", 2);
        int numOfManager = uDao.getTotalUsers("RoleID", 3);
        int numOfProviderInsurance = uDao.getTotalUsers("RoleID", 4);
        int numOfCustomer = uDao.getTotalUsers("RoleID", 5);

        // số lượng users hiển thị trên thanh search
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("numOfAdmin", numOfAdmin);
        request.setAttribute("numOfSeller", numOfSeller);
        request.setAttribute("numOfManager", numOfManager);
        request.setAttribute("numOfProviderInsurance", numOfProviderInsurance);
        request.setAttribute("numOfCustomer", numOfCustomer);

        // phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("idOfRole", idOfRole);

        request.setAttribute("listUsers", listUser);
        request.getRequestDispatcher("ManagementUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
