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
@WebServlet(name = "FilterListUserByRoleName", urlPatterns = {"/admin/filter_roleName"})
public class FilterListUserByRoleName extends HttpServlet {

    private UserDAO uDao;

    public void init() throws ServletException {
        uDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idOfRole = -1;
        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            idOfRole = Integer.parseInt(request.getParameter("id"));
        }

        String idOfRoleAfterShowEntries_raw = request.getParameter("idOfRole");
        if (idOfRoleAfterShowEntries_raw != null && !idOfRoleAfterShowEntries_raw.isEmpty()) {
            int idOfRoleAfterShowEntries = Integer.parseInt(idOfRoleAfterShowEntries_raw);
            idOfRole = idOfRoleAfterShowEntries;
        }

        int page = 1; // trang đầu tiên
        int pageSize;

        int entries = (int) request.getSession().getAttribute("entries");
        pageSize = entries;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<User> listUser;
        listUser = uDao.filterListUser("RoleID", idOfRole, page, pageSize);

        int totalUsersAfterFilter = uDao.getTotalUsers("RoleID", idOfRole);
        int totalPages = (int) Math.ceil((double) totalUsersAfterFilter / pageSize);

        // số lượng của user theo từng role
        int totalUsers = uDao.getTotalUsers(null, null);
        UserRoleUtils.setUserCountsForEachRole(request, uDao);
        request.setAttribute("totalUsers", totalUsers);

        // set phân trang
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
