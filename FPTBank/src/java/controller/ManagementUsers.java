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
import utils.UserRoleUtils;

/**
 *
 * @author SCN
 */
@WebServlet(name = "ManagementUsers", urlPatterns = {"/admin/manage_users"})
public class ManagementUsers extends HttpServlet {

    private UserDAO uDao;

    public void init() throws ServletException {
        uDao = new UserDAO();
    }

    private boolean handleRequestParameter(HttpServletRequest request, HttpServletResponse response, String parameterName, String redirectUrl, int pageSize) throws IOException {
        String parameterValue = request.getParameter(parameterName);
        if (parameterValue != null && !parameterValue.isEmpty()) {
            request.getSession().setAttribute("entries", pageSize);
            response.sendRedirect(redirectUrl + parameterValue);
            return true; // Nếu đã xử lý, trả về true
        }
        return false; // Nếu không xử lý, trả về false
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int page = 1; // trang đầu tiên
        int pageSize = 10; // 1 trang có 10 users
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // get entries from drownlist with user option
        String entries_raw = request.getParameter("entries");
        if (entries_raw != null && !entries_raw.isEmpty()) {
            pageSize = Integer.parseInt(entries_raw);
        }

        if (handleRequestParameter(request, response, "typeOfSortByName", "sort_fullname?typeOfSort=", pageSize)) {
            return;
        }
        if (handleRequestParameter(request, response, "typeOfSortByDate", "sort_dateCreated?typeOfSort=", pageSize)) {
            return;
        }
        if (handleRequestParameter(request, response, "status", "filter_byStatus?status=", pageSize)) {
            return;
        }
        if (handleRequestParameter(request, response, "idOfRole", "filter_roleName?idOfRole=", pageSize)) {
            return;
        }
        if (handleRequestParameter(request, response, "keyword", "search_users?key=", pageSize)) {
            return;
        }

        ArrayList<User> listUsers = uDao.getListUserByPage(page, pageSize);
        int totalUsers = uDao.getTotalUsers(null, null);
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        // số lượng của user theo từng role
        UserRoleUtils.setUserCountsForEachRole(request, uDao);
        request.setAttribute("totalUsers", totalUsers);

        // set phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // lưu entries vào trong session
        request.getSession().setAttribute("entries", pageSize);
        request.setAttribute("listUsers", listUsers);
        request.getRequestDispatcher("ManagementUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
