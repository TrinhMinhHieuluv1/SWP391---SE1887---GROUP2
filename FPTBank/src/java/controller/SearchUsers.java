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
@WebServlet(name = "SearchUsers", urlPatterns = {"/admin/search_users"})
public class SearchUsers extends HttpServlet {

    private UserDAO userDao;

    public void init() throws ServletException {
        userDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword").trim();
        List<User> listUsers;

        int page = 1; // trang đầu tiên
        int pageSize = 10; // 1 trang có 10 users

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        if (keyword != null && !keyword.isEmpty()) {
            // Truy vấn cơ sở dữ liệu với từ khóa
            listUsers = userDao.searchUsers(keyword, page, pageSize);
        } else {
            // Nếu không có từ khóa, trả về toàn bộ danh sách
            response.sendRedirect("manage_users");
            return;
        }

        int totalUsersAfterSearching = userDao.getTotalUsersAfterSearching(keyword);
        int totalPages = (int) Math.ceil((double) totalUsersAfterSearching / pageSize);
        
        int totalUsers = userDao.getTotalUsers();

        // số lượng của user theo từng role
        int numOfAdmin = userDao.getTotalUsersOfEachRole("Admin");
        int numOfSeller = userDao.getTotalUsersOfEachRole("Seller");
        int numOfManager = userDao.getTotalUsersOfEachRole("Manager");
        int numOfProviderInsurance = userDao.getTotalUsersOfEachRole("Provider Insurance");
        int numOfCustomer = userDao.getTotalUsersOfEachRole("Customer");

        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("numOfAdmin", numOfAdmin);
        request.setAttribute("numOfSeller", numOfSeller);
        request.setAttribute("numOfManager", numOfManager);
        request.setAttribute("numOfProviderInsurance", numOfProviderInsurance);
        request.setAttribute("numOfCustomer", numOfCustomer);

        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.setAttribute("keyword", keyword);
        request.setAttribute("listUsers", listUsers);
        request.getRequestDispatcher("ManagementUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
