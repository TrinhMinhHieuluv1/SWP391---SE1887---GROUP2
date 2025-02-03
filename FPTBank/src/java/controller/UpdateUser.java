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
        User user = uDao.getUserByUserID(userID);

        request.setAttribute("user", user);
        request.getRequestDispatcher("FormUpdateUser.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userID = Integer.parseInt(request.getParameter("userid"));
        
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

        User userToUpdate = new User(userID, username, password, name, phone, email, dob, (gender.equals("Male")), cccd, roleID, true, null);
        UserDAO udao = new UserDAO();
        int row = udao.updateUserByUserId2(userToUpdate, userID);
        
        if (row > 0) {
            request.getSession().setAttribute("message", "Update Successfully !!");
        } else {
            request.getSession().setAttribute("error", "Update fail !!");
        }

        response.sendRedirect("manage_users");
        
        

    }

}
