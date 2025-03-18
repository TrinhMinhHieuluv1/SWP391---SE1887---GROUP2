package controller.customer.management;

import dal.CustomerDAO;
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
import model.Customer;
import model.User;
import utils.UserRoleUtils;

@WebServlet(name = "ManagementCustomer", urlPatterns = {"/admin/manage_customers"})
public class ManagementCustomer extends HttpServlet {

    private CustomerDAO cDao;

    public void init() throws ServletException {
        cDao = new CustomerDAO();
    }

    private boolean handleRequestParameterCus(HttpServletRequest request, HttpServletResponse response, String parameterName, String redirectUrl, int pageSize) throws IOException {
        String parameterValue = request.getParameter(parameterName);
        if (parameterValue != null && !parameterValue.isEmpty()) {
            request.getSession().setAttribute("entries", pageSize);
            response.sendRedirect(redirectUrl + parameterValue);
            return true;
        }
        return false;
    }

    private List<Integer> calculatePageSize() {
        List<Integer> listOfPageSize = new ArrayList<>();
        int totalCustomers = cDao.selectAllCustomer().size();

        double[] percentages = {0.1, 0.3, 0.5, 0.7, 1.0}; // 10%,30%,50%,70%,100% of total users
        for (double percentage : percentages) {
            listOfPageSize.add((int) Math.ceil(totalCustomers * percentage));
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

        if (handleRequestParameterCus(request, response, "typeOfSortByName", "sortListCus_fullname?typeOfSort=", pageSize)) {
            return;
        }
        if (handleRequestParameterCus(request, response, "typeOfSortByDate", "sortListCus_dateCreated?typeOfSort=", pageSize)) {
            return;
        }
        if (handleRequestParameterCus(request, response, "status", "filterListCus_byStatus?status=", pageSize)) {
            return;
        }
        if (handleRequestParameterCus(request, response, "typeOfSortByCreditScore", "sortListCus_CreditScore?typeOfSort=", pageSize)) {
            return;
        }

        if (request.getParameter("keyWord") != null && !request.getParameter("keyWord").isEmpty()) {
            request.getSession().setAttribute("entries", pageSize);
            String encodedKeyword = URLEncoder.encode(request.getParameter("keyWord"), "UTF-8");
            response.sendRedirect("search_customers?key=" + encodedKeyword);
            return;
        }

        ArrayList<Customer> listCustomers = cDao.getListCustomerByPage(page, pageSize);

        int totalCustomers = cDao.selectAllCustomer().size();
        int totalPages = (int) Math.ceil((double) totalCustomers / pageSize);

        request.setAttribute("totalCustomers", totalCustomers);

        // set phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // lưu entries vào trong session
        request.getSession().setAttribute("entries", pageSize);

        // lưu pagesize for each percent
        request.getSession().setAttribute("listOfPageSize", listOfPageSize);
        request.setAttribute("listCustomers", listCustomers);
        request.getRequestDispatcher("ManagementCustomers.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
