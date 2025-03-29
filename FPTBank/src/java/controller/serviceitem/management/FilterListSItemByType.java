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
import java.util.List;
import model.ServiceItem;
import model.User;
import utils.UserRoleUtils;

/**
 *
 * @author SCN
 */
@WebServlet(name = "FilterListSItemByType", urlPatterns = {"/admin/filter_typeSItem"})
public class FilterListSItemByType extends HttpServlet {

    private ServiceItemDAO sDao;

    public void init() throws ServletException {
        sDao = new ServiceItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = "";
        if (request.getParameter("type1") != null && !request.getParameter("type1").isEmpty()) {
            type = request.getParameter("type1");
        }

        String typeAfterShowEntries = request.getParameter("typeOfServiceItem");
        if (typeAfterShowEntries != null && !typeAfterShowEntries.isEmpty()) {
            type = typeAfterShowEntries;
        }

        int page = 1; // trang đầu tiên
        int pageSize;

        int entries = (int) request.getSession().getAttribute("entries");
        pageSize = entries;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<ServiceItem> listServiceItems;
        listServiceItems = sDao.filterListServiceItems("Type", type, page, pageSize);

        int totalSItemAfterFilter = sDao.getTotalServiceItemsAfterFilterType("Type", type);
        int totalPages = (int) Math.ceil((double) totalSItemAfterFilter / pageSize);

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

        // set phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.setAttribute("typeOfServiceItem", type);
        request.setAttribute("listSItems", listServiceItems);
        request.getRequestDispatcher("ManagementServiceItems.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
