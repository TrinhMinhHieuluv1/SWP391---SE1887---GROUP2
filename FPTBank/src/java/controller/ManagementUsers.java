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

        // lấy typeOfSort (asc/desc) nếu ng dùng sort trc và show entries sau 
        String typeOfSortByName = request.getParameter("typeOfSortByName");
        if (typeOfSortByName != null && !typeOfSortByName.isEmpty()) {
            request.getSession().setAttribute("entries", pageSize);
            response.sendRedirect("sort_fullname?typeOfSort=" + typeOfSortByName);
            return;
        }

        // lấy typeOfSort (asc/desc) nếu ng dùng sort trc và show entries sau 
        String typeOfSortByDate = request.getParameter("typeOfSortByDate");
        if (typeOfSortByDate != null && !typeOfSortByDate.isEmpty()) {
            request.getSession().setAttribute("entries", pageSize);
            response.sendRedirect("sort_dateCreated?typeOfSort=" + typeOfSortByDate);
            return;
        }

        // lấy status (active/inactive) nếu ng dùng filter trc và show entries sau 
        String status_raw = request.getParameter("status");
        if (status_raw != null && !status_raw.isEmpty()) {
            int status = Integer.parseInt(status_raw);
            request.getSession().setAttribute("entries", pageSize);
            response.sendRedirect("filter_byStatus?status=" + status);
            return;
        }

        // lấy role user (1,2,3,4,5) nếu ng dùng filter trc và show entries sau 
        String idOfRole_raw = request.getParameter("idOfRole");
        if (idOfRole_raw != null && !idOfRole_raw.isEmpty()) {
            int idOfRole = Integer.parseInt(idOfRole_raw);
            request.getSession().setAttribute("entries", pageSize);
            response.sendRedirect("filter_roleName?idOfRole=" + idOfRole);
            return;
        }

        // lấy keyword nếu ng dùng search trc và show entries sau
        String keyword = request.getParameter("keyword");
        if (keyword != null && !keyword.isEmpty()) {
            request.getSession().setAttribute("entries", pageSize);
            response.sendRedirect("search_users?key=" + keyword);
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
