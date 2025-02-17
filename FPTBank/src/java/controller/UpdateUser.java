package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import model.User;

/**
 *
 * @author SCN
 */
@WebServlet(name = "UpdateUser", urlPatterns = {"/admin/update_user"})
public class UpdateUser extends HttpServlet {

    private UserDAO uDao;

    public void init() throws ServletException {
        uDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userID = Integer.parseInt(request.getParameter("id"));
        request.getSession().setAttribute("userID", userID);

        // Get list manager
        List<User> listManager = uDao.selectAllUsersByRole(3);

        // loại bỏ các manager bị inactive
        for (int i = 0; i < listManager.size(); i++) {
            if (!listManager.get(i).isStatus()) { // Nếu status = false
                listManager.remove(i);
                i--; // Giảm i để kiểm tra lại phần tử ở vị trí i sau khi xóa
            }
        }

        request.getSession().setAttribute("listManager", listManager);

        User user = uDao.selectAnUserByConditions(userID, "", "", "");
        request.setAttribute("user", user);

        request.getRequestDispatcher("FormUpdateUser.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userID = (int) request.getSession().getAttribute("userID");

        String roleID_raw = request.getParameter("role");
        int roleID = Integer.parseInt(roleID_raw);

        String managerId_raw = request.getParameter("managerid");

        User manager = null;
        if (managerId_raw != null && !managerId_raw.isEmpty()) {
            managerId_raw = managerId_raw.trim();
            int manageID = Integer.parseInt(managerId_raw);
            manager = uDao.getManagerForSeller(manageID);
            if (manager == null) {
                request.getSession().setAttribute("error", "ManagerID does not exist !!");
                response.sendRedirect("update_user?id=" + userID);
                return;
            }
        }

        // check for seller
        if (roleID == 2 && manager == null) {
            request.getSession().setAttribute("error", "Seller must have a manager !!");
            response.sendRedirect("update_user?id=" + userID);
            return;
        }
        if (roleID != 2 && manager != null) {
            request.getSession().setAttribute("error", "Only Sellers can have a manager !!");
            response.sendRedirect("update_user?id=" + userID);
            return;
        }

        User userNeedUpdate = uDao.selectAnUserByConditions(userID, "", "", "");
        userNeedUpdate.setRoleID(roleID);
        userNeedUpdate.setManager(manager);

        uDao.updateAUser(userNeedUpdate);
        request.getSession().setAttribute("message", "Update Successfully !!");
        response.sendRedirect("manage_users");

    }

}
