package controller.user.management;

import controller.*;
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
@WebServlet(name = "SortListUserByDateCreated", urlPatterns = {"/admin/sort_dateCreated"})
public class SortListUserByDateCreated extends HttpServlet {

    private UserDAO uDao;

    public void init() throws ServletException {
        uDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String typeOfSort = request.getParameter("type2");

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

        List<User> listUser;
        listUser = uDao.sortListUser("CreatedAt", typeOfSort, page, pageSize);
        int totalUsers = uDao.getTotalUsers(null, null);
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        // số lượng của user theo từng role
        UserRoleUtils.setUserCountsForEachRole(request, uDao);
        request.setAttribute("totalUsers", totalUsers);

        // set phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.setAttribute("listUsers", listUser);
        request.setAttribute("typeOfSortByDate", typeOfSort);
        request.getRequestDispatcher("ManagementUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
