package controller.user.management;

import controller.*;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CheckFieldExist", urlPatterns = {"/admin/checkFieldExist"})
public class CheckFieldExistBeforeInsert extends HttpServlet {

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

        boolean isExisted;

        // check Username exist or not
        String fieldToCheck1 = request.getParameter("username");
        if (fieldToCheck1 != null && !fieldToCheck1.isEmpty()) {
            isExisted = userDao.isFieldExistsToAdd("Username", fieldToCheck1);
            response.setContentType("text/plain");
            if (isExisted) {
                response.getWriter().write("exists");
            } else {
                response.getWriter().write("available");
            }
            return;
        }

         // check Email exist or not
        String fieldToCheck2 = request.getParameter("email");
        if (fieldToCheck2 != null && !fieldToCheck2.isEmpty()) {
            isExisted = userDao.isFieldExistsToAdd("Email", fieldToCheck2);
            response.setContentType("text/plain");
            if (isExisted) {
                response.getWriter().write("exists");
            } else {
                response.getWriter().write("available");
            }
            return;
        }

         // check Phone number exist or not
        String fieldToCheck3 = request.getParameter("phone");
        if (fieldToCheck3 != null && !fieldToCheck3.isEmpty()) {
            isExisted = userDao.isFieldExistsToAdd("Phone", fieldToCheck3);
            response.setContentType("text/plain");
            if (isExisted) {
                response.getWriter().write("exists");
            } else {
                response.getWriter().write("available");
            }
            return;
        }

        // check CCCD exist or not
        String fieldToCheck4 = request.getParameter("cccd");
        if (fieldToCheck4 != null && !fieldToCheck4.isEmpty()) {
            isExisted = userDao.isFieldExistsToAdd("CCCD", fieldToCheck4);
            response.setContentType("text/plain");
            if (isExisted) {
                response.getWriter().write("exists");
            } else {
                response.getWriter().write("available");
            }
        }

    }

}
