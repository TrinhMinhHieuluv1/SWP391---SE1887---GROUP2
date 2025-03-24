package controller.serviceitem.management;

import controller.user.management.*;
import controller.*;
import dal.ServiceItemDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.ServiceItem;
import model.User;
import utils.UserRoleUtils;

/**
 *
 * @author SCN
 */
@WebServlet(name = "FilterListServiceItemByStatus", urlPatterns = {"/admin/filter_sItemByStatus"})
public class FilterListServiceItemByStatus extends HttpServlet {

    private ServiceItemDAO sDao;

    public void init() throws ServletException {
        sDao = new ServiceItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idOfStatus = -1;
        if (request.getParameter("status") != null && !request.getParameter("status").isEmpty()) {
            idOfStatus = Integer.parseInt(request.getParameter("status"));
        }

        String statusAfterShowEntries_raw = request.getParameter("status");
        if (statusAfterShowEntries_raw != null && !statusAfterShowEntries_raw.isEmpty()) {
            int statusAfterShowEntries = Integer.parseInt(statusAfterShowEntries_raw);
            idOfStatus = statusAfterShowEntries;
        }

        int page = 1; // trang đầu tiên
        int pageSize;

        int entries = (int) request.getSession().getAttribute("entries");
        pageSize = entries;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<ServiceItem> listServiceItems;
        listServiceItems = sDao.filterListServiceItemsByStatus("Status", idOfStatus, page, pageSize);

        int totalUsersAfterFilter = sDao.getTotalServiceItemsAfterFilterStatus("Status", idOfStatus);
        int totalPages = (int) Math.ceil((double) totalUsersAfterFilter / pageSize);

        // số lượng của user theo từng role
        int totalSItems = sDao.selectAllServiceItem().size();
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

        // phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("statusOfSItem", idOfStatus);
        request.setAttribute("listSItems", listServiceItems);

        request.getRequestDispatcher("ManagementServiceItems.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
