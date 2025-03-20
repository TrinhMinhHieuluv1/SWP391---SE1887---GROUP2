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
@WebServlet(name = "SearchServiceItems", urlPatterns = {"/admin/search_serviceItem"})
public class SearchServiceItems extends HttpServlet {

    private ServiceItemDAO sDao;

    public void init() throws ServletException {
        sDao = new ServiceItemDAO();
    }

    public static String normalizeString(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return "";
        }
        return keyword.trim().replaceAll("\\s+", " ");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword_raw = request.getParameter("keyword");

        int page = 1; // trang đầu tiên
        int pageSize;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int entries = (int) request.getSession().getAttribute("entries");
        pageSize = entries;

        // get value keyword được gửi từ svlet manage_users
        String keywordFromManageServiceItem = request.getParameter("key");
        if (keywordFromManageServiceItem != null && !keywordFromManageServiceItem.isEmpty()) {
            keyword_raw = keywordFromManageServiceItem;
        }

        String keyword = normalizeString(keyword_raw);

        List<ServiceItem> listServiceItems;
        if (keyword != null && !keyword.isEmpty()) {
            listServiceItems = sDao.searchServiceItems(keyword, page, pageSize);
        } else {
            // Nếu không có từ khóa, trả về toàn bộ danh sách
            response.sendRedirect("manage_serviceItems");
            return;
        }

        int totalServiceItemAfterSearching = sDao.getTotalServiceItemsAfterSearching(keyword);
        int totalPages = (int) Math.ceil((double) totalServiceItemAfterSearching / pageSize);

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

        request.setAttribute("keyword", keyword);
        request.setAttribute("listSItems", listServiceItems);

        request.getRequestDispatcher("ManagementServiceItems.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
