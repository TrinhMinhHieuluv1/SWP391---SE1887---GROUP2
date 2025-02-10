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

    private UserDAO userDao;

    public void init() throws ServletException {
        userDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("FormAddUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String name = request.getParameter("fullname").trim();

        String gender = request.getParameter("gender");
        boolean isMale = gender.equals("1");

        String dob_raw = request.getParameter("dob");
        Date dob = Date.valueOf(dob_raw);

        String phone = request.getParameter("phonenumber").trim();
        String email = request.getParameter("email").trim();
        String cccd = request.getParameter("card").trim();

        String roleID_raw = request.getParameter("role");
        int roleID = Integer.parseInt(roleID_raw);

        String img = request.getParameter("img").trim();
        String address = request.getParameter("address").trim();

        String managerId_raw = request.getParameter("managerid");

        User manager = null;
        if (managerId_raw != null && !managerId_raw.isEmpty()) {
            managerId_raw = managerId_raw.trim();
            int manageID = Integer.parseInt(managerId_raw);
            manager = userDao.getManagerForSeller(manageID);
            if (manager == null) {
                request.getSession().setAttribute("error", "ManagerID does not exist !!");
                response.sendRedirect("insert_users");
                return;
            }
        }

        // check for seller
        if (roleID == 2 && manager == null) {
            request.getSession().setAttribute("error", "Seller must have a manager ID !!");
            response.sendRedirect("insert_users");
            return;
        }
        if (roleID != 2 && manager != null) {
            request.getSession().setAttribute("error", "Only Sellers can have a managerID !!");
            response.sendRedirect("insert_users");
            return;
        }
        User userToAdd = new User(0, username, password, name, img, phone, email, dob, isMale, address, cccd, roleID, true, manager, null);

        int row = userDao.addUserReturnRow(userToAdd);

        switch (row) {
            case 1 ->
                request.getSession().setAttribute("message", "Insert Successfully !!");
            case 0 ->
                request.getSession().setAttribute("error", "Insert fail !!");
            case 2 ->
                request.getSession().setAttribute("error", "Username has exsisted !!");
            case 3 ->
                request.getSession().setAttribute("error", "Identity card has exsisted !!");
            case 4 ->
                request.getSession().setAttribute("error", "Email has exsisted !!");
            case 5 ->
                request.getSession().setAttribute("error", "Phone number has exsisted !!");
            default -> {
            }
        }

        response.sendRedirect("insert_users");

    }

}
