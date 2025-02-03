package controller;

import dal.UserDAO;
import java.io.IOException;
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
@WebServlet(name = "InsertUser", urlPatterns = {"/admin/insert_users"})
public class InsertUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("FormAddUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("fullname");
        String gender = request.getParameter("gender");

        String dob_raw = request.getParameter("dob");
        Date dob = Date.valueOf(dob_raw);

        String phone = request.getParameter("phonenumber");
        String email = request.getParameter("email");
        String cccd = request.getParameter("card");

        String roleID_raw = request.getParameter("role");
        int roleID = Integer.parseInt(roleID_raw);

        User userToAdd = new User(0, username, password, name, phone, email, dob, (gender.equals("Male")), cccd, roleID, true, null);
        UserDAO udao = new UserDAO();
        int row = udao.addUserReturnRow(userToAdd);

        if (row > 0) {
            request.getSession().setAttribute("message", "Insert Successfully !!");
        } else {
            request.getSession().setAttribute("error", "Insert fail !!");
        }

        response.sendRedirect("insert_users");

    }

}
