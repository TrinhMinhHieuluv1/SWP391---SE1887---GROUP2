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
import model.User;

@WebServlet(name = "UpdateStatusOfUsers", urlPatterns = {"/admin/updateStatus"})
public class UpdateStatusOfUsers extends HttpServlet {

    private UserDAO userDao;

    public void init() throws ServletException {
        userDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String status = request.getParameter("status");
        Boolean checkUpdate = false;
        try {
            String userId_raw = request.getParameter("userID");
            int userID = Integer.parseInt(userId_raw);
            Boolean isActive = false;

            if (status != null && !status.isEmpty()) {
                User user = userDao.selectAnUserByConditions(userID, "", "", "");
                if (status.equalsIgnoreCase("true")) {
                    isActive = true;
                }
                user.setStatus(isActive);
                userDao.updateAUser(user);
                checkUpdate = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String message = checkUpdate ? "Update status successfully !!" : "Update status fail !!";
        response.getWriter().write("{\"success\": " + status + ", \"message\": \"" + message + "\"}");
    }

}
