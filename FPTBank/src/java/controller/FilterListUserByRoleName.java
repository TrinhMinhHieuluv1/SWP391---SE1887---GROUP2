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

        listUser = uDao.filterListUserByRoleName(idOfRole, page, pageSize);

        int totalUsers1 = uDao.getTotalUsersAfterFilteringByRole(idOfRole);
        int totalPages = (int) Math.ceil((double) totalUsers1 / pageSize);

        
        // tính số lượng của user theo từng role
        int totalUsers2 = uDao.getTotalUsers();
        
        int numOfAdmin = uDao.getTotalUsersOfEachRole("Admin");
        int numOfSeller = uDao.getTotalUsersOfEachRole("Seller");
        int numOfManager = uDao.getTotalUsersOfEachRole("Manager");
        int numOfProviderInsurance = uDao.getTotalUsersOfEachRole("Provider Insurance");
        int numOfCustomer = uDao.getTotalUsersOfEachRole("Customer");

        // số lượng users hiển thị trên thanh search
        request.setAttribute("totalUsers", totalUsers2);
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
