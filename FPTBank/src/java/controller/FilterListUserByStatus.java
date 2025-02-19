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
import utils.UserRoleUtils;

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
        int pageSize = 10; // 1 trang có 10 users

        int entries = (int) request.getSession().getAttribute("entries");
        if (entries != 10) {
            pageSize = entries;
        }

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<User> listUser;
        listUser = uDao.filterListUser("Status", idOfStatus, page, pageSize);
        int totalUsersAfterFilter = uDao.getTotalUsers("Status", idOfStatus);
        int totalPages = (int) Math.ceil((double) totalUsersAfterFilter / pageSize);

        // số lượng của user theo từng role
        int totalUsers = uDao.getTotalUsers(null, null);
        UserRoleUtils.setUserCountsForEachRole(request, uDao);
        request.setAttribute("totalUsers", totalUsers);

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
