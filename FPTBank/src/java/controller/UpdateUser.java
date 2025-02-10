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
        User user = uDao.selectAnUserByConditions(userID, "", "", "");

        request.setAttribute("user", user);
        request.getRequestDispatcher("FormUpdateUser.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userID = Integer.parseInt(request.getParameter("userid"));
        
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
            request.getSession().setAttribute("error", "Seller must have a manager ID !!");
            response.sendRedirect("update_user?id=" + userID);
            return;
        }
        if (roleID != 2 && manager != null) {
            request.getSession().setAttribute("error", "Only Sellers can have a managerID !!");
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
