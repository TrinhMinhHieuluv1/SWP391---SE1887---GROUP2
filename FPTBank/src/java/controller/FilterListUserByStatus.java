package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "FillterListUserByStatus", urlPatterns = {"/admin/filter_byStatus"})
public class FilterListUserByStatus extends HttpServlet {

    private UserDAO uDao;

    public void init() throws ServletException {
        uDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idOfStatus = Integer.parseInt(request.getParameter("status"));
        List<User> listUser;

        int page = 1; // trang đầu tiên
        int pageSize = 10; // 1 trang có 10 users

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        listUser = uDao.filterListUserByStatus(idOfStatus, page, pageSize);
        int totalUsers1 = uDao.getTotalUsersAfterFilteringByStatus(idOfStatus);
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
        request.setAttribute("statusOfUser", idOfStatus);
        

        request.setAttribute("listUsers", listUser);
        request.getRequestDispatcher("ManagementUsers.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
