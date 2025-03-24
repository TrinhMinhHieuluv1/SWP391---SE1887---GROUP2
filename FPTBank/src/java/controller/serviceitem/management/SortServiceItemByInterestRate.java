package controller.serviceitem.management;

import controller.user.management.*;
import controller.*;
import dal.ServiceItemDAO;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.ServiceItem;
import model.User;
import utils.UserRoleUtils;

/**
 *
 * @author SCN
 */
@WebServlet(name = "SortServiceItemByInterestRate", urlPatterns = {"/admin/sortInterestRate"})
public class SortServiceItemByInterestRate extends HttpServlet {

    private ServiceItemDAO sDao;

    public void init() throws ServletException {
        sDao = new ServiceItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String typeOfSort = request.getParameter("type2");

        // get value typeOfSort được gửi từ svlet manage_users
        String typeOfSortFromManageUser = request.getParameter("typeOfSort");
        if (typeOfSortFromManageUser != null && !typeOfSortFromManageUser.isEmpty()) {
            typeOfSort = typeOfSortFromManageUser;
        }

        int page = 1; // trang đầu tiên
        int pageSize;

        int entries = (int) request.getSession().getAttribute("entries");
        pageSize = entries;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<ServiceItem> listServiceItem;
        listServiceItem = sDao.sortServiceItemsByInterestRate(typeOfSort, page, pageSize);

        int totalSItems = sDao.selectAllServiceItem().size();
        int totalPages = (int) Math.ceil((double) totalSItems / pageSize);

        // số lượng của user theo từng role
        int cntUnLoan = 0, cntSaving = 0, cntSeLoan = 0;
        List<ServiceItem> listSItems2 = sDao.selectAllServiceItem();
        for (ServiceItem serviceItem : listSItems2) {
            if (serviceItem.getType().equalsIgnoreCase("Saving")) {
                cntSaving++;
            } else if (serviceItem.getType().equalsIgnoreCase("Unsecured Loan")) {
                cntUnLoan++;
            } else {
                cntSeLoan++;
            }
        }

        request.setAttribute("cntSaving", cntSaving);
        request.setAttribute("cntSeLoan", cntSeLoan);
        request.setAttribute("cntUnLoan", cntUnLoan);

        request.setAttribute("totalSItems", totalSItems);

        // set phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.setAttribute("listSItems", listServiceItem);
        request.setAttribute("typeOfSortByInterestRate", typeOfSort);
        request.getRequestDispatcher("ManagementServiceItems.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
