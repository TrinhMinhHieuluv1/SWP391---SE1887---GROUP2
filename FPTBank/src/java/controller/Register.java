

package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.sql.Date;
import model.User;

/**
 *
 * @author HP
 */
@WebServlet(name="Register", urlPatterns={"/register"})
public class Register extends HttpServlet {
   
  

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    } 

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dob_raw = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        Date dob = Date.valueOf(dob_raw);
        User userToAdd = new User(0, username, password, name, phone, email, dob, (gender.equals("Male")), "", 5, true, null);
        UserDAO udao = new UserDAO();
        udao.addAUser(userToAdd);
        response.sendRedirect("/timibank/login?register=true");
    }

 

}
