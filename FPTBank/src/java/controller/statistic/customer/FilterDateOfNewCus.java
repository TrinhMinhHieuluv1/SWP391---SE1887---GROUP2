package controller.statistic.customer;

import dal.CustomerDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import model.Customer;
import org.json.simple.JSONObject;

/**
 *
 * @author SCN
 */
@WebServlet(name = "FilterDateOfNewCus", urlPatterns = {"/admin/filterDateNewCus"})
public class FilterDateOfNewCus extends HttpServlet {

    private CustomerDAO cDao;

    public void init() throws ServletException {
        cDao = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ChartCalculator chart = new ChartCalculator();

        // line chart ( new customer )
        List<Customer> listOfCus = cDao.selectAllCustomer();
        List<Customer> filteredCustomers = new ArrayList<>();
        String fromDateStr = request.getParameter("fromDate");
        String toDateStr = request.getParameter("toDate");

        LocalDate fromDate = null;
        LocalDate toDate = null;

        try {
            if (fromDateStr != null && toDateStr != null) {
                fromDate = LocalDate.parse(fromDateStr);
                toDate = LocalDate.parse(toDateStr);
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        // Kiểm tra nếu ngày bị null hoặc không hợp lệ, dùng khoảng mặc định
        if (fromDate == null) {
            fromDate = LocalDate.of(2024, 10, 1); // Ngày mặc định
        }
        if (toDate == null) {
            toDate = LocalDate.now(); // Lấy ngày hiện tại
        }

        for (Customer c : listOfCus) {
            if (c.isStatus() && c.getCreatedAt() != null) { // Kiểm tra Status và CreatedAt không null
                LocalDate createdAt = c.getCreatedAt().toLocalDate();

                if ((createdAt.isEqual(fromDate) || createdAt.isAfter(fromDate))
                        && (createdAt.isEqual(toDate) || createdAt.isBefore(toDate))) {

                    filteredCustomers.add(c);
                }
            }
        }

        Map<String, Integer> newCusMapByMonth = countCustomersByMonth(filteredCustomers);
        List<String> listChar1 = chart.calculateChar1(newCusMapByMonth);

        JSONObject jsonResponse = new JSONObject();
        if (newCusMapByMonth.isEmpty() || newCusMapByMonth == null) {
            jsonResponse.put("error", "No data available !!");
        } else {
            jsonResponse.put("labels01", listChar1.get(0));
            jsonResponse.put("data01", listChar1.get(1));
            jsonResponse.put("percentData01", listChar1.get(2));
        }

        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());

    }

    private static Map<String, Integer> countCustomersByMonth(List<Customer> filteredCustomers) {
        Map<String, Integer> customerCountByMonth = new HashMap<>();
        List<String> MONTH_ORDER = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

        for (Customer c : filteredCustomers) {
            if (c.getCreatedAt() != null) {
                LocalDate createdAt = c.getCreatedAt().toLocalDate();
                String monthShort = createdAt.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH); // "Jan", "Feb"
                String yearMonthKey = createdAt.getYear() + "-" + monthShort; // "2024-Oct", "2025-Jan"

                customerCountByMonth.put(yearMonthKey, customerCountByMonth.getOrDefault(yearMonthKey, 0) + 1);
            }
        }

        List<String> sortedKeys = new ArrayList<>(customerCountByMonth.keySet());

        sortedKeys.sort(Comparator.comparing(key -> {
            String[] parts = key.split("-");
            int year = Integer.parseInt(parts[0]);
            int monthIndex = MONTH_ORDER.indexOf(parts[1]);
            return year * 12 + monthIndex;
        }));

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (String key : sortedKeys) {
            sortedMap.put(key, customerCountByMonth.get(key));
        }

        return sortedMap;
    }

}
