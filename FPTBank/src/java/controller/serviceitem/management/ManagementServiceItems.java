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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import model.Service;
import model.ServiceItem;
import model.User;
import utils.UserRoleUtils;

/**
 *
 * @author SCN
 */
@WebServlet(name = "ManagementServiceItems", urlPatterns = {"/admin/manage_serviceItems"})
public class ManagementServiceItems extends HttpServlet {

    private ServiceItemDAO sDao;

    public void init() throws ServletException {
        sDao = new ServiceItemDAO();
    }

    private boolean handleRequestParameter(HttpServletRequest request, HttpServletResponse response, String parameterName, String redirectUrl, int pageSize) throws IOException {
        String parameterValue = request.getParameter(parameterName);
        if (parameterValue != null && !parameterValue.isEmpty()) {
            request.getSession().setAttribute("entries", pageSize);
            response.sendRedirect(redirectUrl + parameterValue);
            return true; // Nếu đã xử lý, trả về true
        }
        return false; // Nếu không xử lý, trả về false
    }

    private List<Integer> calculatePageSize() {
        List<Integer> listOfPageSize = new ArrayList<>();
        int totalSItems = sDao.selectAllServiceItem().size();

        double[] percentages = {0.1, 0.3, 0.5, 0.7, 1.0}; // 10%,30%,50%,70%,100% of total users
        for (double percentage : percentages) {
            listOfPageSize.add((int) Math.ceil(totalSItems * percentage));
        }

        return listOfPageSize;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // get page size for each percent (10%,30%,50%,70%)
        List<Integer> listOfPageSize = calculatePageSize();

        int page = 1; // trang đầu tiên
        int pageSize = listOfPageSize.get(0); // gán page size mặc định lúc đầu = 10% of total user

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // get entries from drownlist with user option
        String entries_raw = request.getParameter("entries");
        if (entries_raw != null && !entries_raw.isEmpty()) {
            pageSize = Integer.parseInt(entries_raw);
        }

        if (handleRequestParameter(request, response, "typeOfServiceItem", "filter_typeSItem?typeOfServiceItem=", pageSize)) {
            return;
        }
        if (handleRequestParameter(request, response, "status", "filter_sItemByStatus?status=", pageSize)) {
            return;
        }
        if (handleRequestParameter(request, response, "typeOfSortByInterestDate", "sortInterestRate?typeOfSort=", pageSize)) {
            return;
        }
        if (request.getParameter("keyword") != null && !request.getParameter("keyword").isEmpty()) {
            request.getSession().setAttribute("entries", pageSize);
            String encodedKeyword = URLEncoder.encode(request.getParameter("keyword"), "UTF-8");
            response.sendRedirect("search_serviceItem?key=" + encodedKeyword);
            return;
        }

        ArrayList<ServiceItem> listSItems = sDao.getListServiceItemsByPage(page, pageSize);
        int totalSItems = sDao.selectAllServiceItem().size();
        int totalPages = (int) Math.ceil((double) totalSItems / pageSize);

        // số lượng của user theo từng role
        int cntUnLoan = 0, cntSaving = 0, cntSeLoan = 0;
          List<ServiceItem> listSItems2 = sDao.selectAllServiceItem();
          for (ServiceItem serviceItem : listSItems2) {
            if(serviceItem.getType().equalsIgnoreCase("Saving")) {
                cntSaving++;
            } else if(serviceItem.getType().equalsIgnoreCase("Unsecured Loan")) {
                cntUnLoan++;
            } else{
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

        // lưu entries vào trong session
        request.getSession().setAttribute("entries", pageSize);

        // lưu pagesize for each percent
        request.getSession().setAttribute("listOfPageSize", listOfPageSize);
        request.setAttribute("listSItems", listSItems);
        request.getRequestDispatcher("ManagementServiceItems.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
