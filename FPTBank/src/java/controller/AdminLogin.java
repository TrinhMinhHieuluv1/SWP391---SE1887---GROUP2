package controller;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author SCN
 */
@WebServlet(name = "AdminLogin", urlPatterns = {"/admin/login"})
public class AdminLogin extends HttpServlet {

    private UserDAO userDao;

    public void init() throws ServletException {
        userDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDao.checkAuthen(userName, password);

        if (user != null && userDao.isAdmin(userName, password)) {
            request.getSession().setAttribute("admin", user);
            request.getSession().setAttribute("message", "Login Successfully !!");
            request.getRequestDispatcher("home.jsp").forward(request, response);

        } else {
            request.getSession().setAttribute("error", "Invalid username or password !!");
            response.sendRedirect("login");
        }

    }

}
