package controller.user.management;

import controller.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "AdminLogout", urlPatterns = {"/admin/logout"})
public class AdminLogout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRoleID() == 1) {
                session.removeAttribute("account");
            }
        }
        request.getSession().setAttribute("message", "Logout Successfully !!");
        response.sendRedirect("logout");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
