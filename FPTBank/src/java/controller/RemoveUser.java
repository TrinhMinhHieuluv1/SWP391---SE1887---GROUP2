

package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author SCN
 */
@WebServlet(name="RemoveUser", urlPatterns={"/admin/remove_user"})
public class RemoveUser extends HttpServlet {
   
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("id"));
        UserDAO udao = new UserDAO();
        udao.removeUserByUserID(userID);
        
        request.getSession().setAttribute("message", "Delete Successfully !!");
        response.sendRedirect("manage_users");
        
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }


}
