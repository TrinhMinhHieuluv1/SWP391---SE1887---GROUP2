package controller.user.management;

import controller.*;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.User;
import utils.UserRoleUtils;

/**
 *
 * @author SCN
 */
@WebServlet(name = "SearchUsers", urlPatterns = {"/admin/search_users"})
public class SearchUsers extends HttpServlet {

    private UserDAO uDao;

    public void init() throws ServletException {
        uDao = new UserDAO();
    }

    public static String normalizeString(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return "";
        }
        return keyword.trim().replaceAll("\\s+", " ");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword_raw = request.getParameter("keyword");

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

        String keyword = normalizeString(keyword_raw);

        List<User> listUsers;
        if (keyword != null && !keyword.isEmpty()) {
            listUsers = uDao.searchUsers(keyword, page, pageSize);
        } else {
            // Nếu không có từ khóa, trả về toàn bộ danh sách
            response.sendRedirect("manage_users");
            return;
        }

        int totalUsersAfterSearching = uDao.getTotalUsersAfterSearching(keyword);
        int totalPages = (int) Math.ceil((double) totalUsersAfterSearching / pageSize);

        // số lượng của user theo từng role
        UserRoleUtils.setUserCountsForEachRole(request, uDao);
        int totalUsers = uDao.getTotalUsers(null, null);
        request.setAttribute("totalUsers", totalUsers);

        // set phân trang
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
