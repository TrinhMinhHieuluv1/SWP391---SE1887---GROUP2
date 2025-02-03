package controller;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author SCN
 */
@WebServlet(name = "ManagementUsers", urlPatterns = {"/admin/manage_users"})
public class ManagementUsers extends HttpServlet {

    private UserDAO userDao;

    public void init() throws ServletException {
        userDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        int page = 1; // trang đầu tiên
        int pageSize = 10; // 1 trang có 10 users
       
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        ArrayList<User> listUsers = userDao.getListUserByPage(page, pageSize);

        int totalUsers = userDao.getTotalUsers();
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);
        
        // số lượng của user theo từng role
        int numOfAdmin = userDao.getTotalUsersOfEachRole("Admin");
        int numOfSeller = userDao.getTotalUsersOfEachRole("Seller");
        int numOfManager = userDao.getTotalUsersOfEachRole("Manager");
        int numOfProviderInsurance = userDao.getTotalUsersOfEachRole("Provider Insurance");
        int numOfCustomer = userDao.getTotalUsersOfEachRole("Customer");
        
        
        request.setAttribute("numOfAdmin", numOfAdmin);
        request.setAttribute("numOfSeller", numOfSeller);
        request.setAttribute("numOfManager", numOfManager);
        request.setAttribute("numOfProviderInsurance", numOfProviderInsurance);
        request.setAttribute("numOfCustomer", numOfCustomer);
        
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("listUsers", listUsers);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("ManagementUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
