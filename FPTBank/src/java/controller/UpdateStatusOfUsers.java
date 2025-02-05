package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        String userId = request.getParameter("userID");
        String status = request.getParameter("status");

        Boolean isActive = false;

        try {
            if (userId != null && !userId.isEmpty() && status != null && !status.isEmpty()) {
                isActive = userDao.updateStatusOfUsers(Integer.parseInt(userId), Boolean.parseBoolean(status));
            }
        } catch (Exception e) {
            isActive = false;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String message = isActive ? "Update status successfully !!" : "Update status fail !!";
        response.getWriter().write("{\"success\": " + status + ", \"message\": \"" + message + "\"}");
    }

}
