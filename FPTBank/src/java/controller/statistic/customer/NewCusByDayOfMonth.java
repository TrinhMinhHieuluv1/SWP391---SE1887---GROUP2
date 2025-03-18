package controller.statistic.customer;

import dal.CustomerDAO;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import model.Customer;

@WebServlet(name = "NewCusByDayOfMonth", urlPatterns = {"/admin/NewCusByDayOfMonth"})
public class NewCusByDayOfMonth extends HttpServlet {

    private CustomerDAO cDao;

    public void init() throws ServletException {
        cDao = new CustomerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String monthSelected = request.getParameter("month"); // Ví dụ: "2024-Dec"

        String[] parts = monthSelected.split("-");
        int year = Integer.parseInt(parts[0]); 
        String monthShort = parts[1]; 

        // Chuyển tháng ngắn (Dec) thành số (12)
        Map<String, Integer> monthMap = new HashMap<>();
        monthMap.put("Jan", 1);
        monthMap.put("Feb", 2);
        monthMap.put("Mar", 3);
        monthMap.put("Apr", 4);
        monthMap.put("May", 5);
        monthMap.put("Jun", 6);
        monthMap.put("Jul", 7);
        monthMap.put("Aug", 8);
        monthMap.put("Sep", 9);
        monthMap.put("Oct", 10);
        monthMap.put("Nov", 11);
        monthMap.put("Dec", 12);
        int monthNum = monthMap.get(monthShort);

        // Lấy số ngày trong tháng
        YearMonth yearMonth = YearMonth.of(year, monthNum);
        int daysInMonth = yearMonth.lengthOfMonth();

        List<Customer> listOfCus = cDao.selectAllCustomer();

        // Lọc khách hàng theo tháng được chọn
        List<Customer> filteredCustomers = new ArrayList<>();
        LocalDate fromDate = LocalDate.of(year, monthNum, 1); // Ngày đầu tháng
        LocalDate toDate = fromDate.plusDays(daysInMonth - 1); // Ngày cuối tháng

        for (Customer c : listOfCus) {
            if (c.isStatus() && c.getCreatedAt() != null) {
                LocalDate createdAt = c.getCreatedAt().toLocalDate();
                if ((createdAt.isEqual(fromDate) || createdAt.isAfter(fromDate))
                        && (createdAt.isEqual(toDate) || createdAt.isBefore(toDate))) {
                    filteredCustomers.add(c);
                }
            }
        }

        // Đếm số khách hàng mới theo ngày
        Map<String, Integer> newCusMapByDay = countCustomersByDay(filteredCustomers);

        // Tạo dữ liệu cho biểu đồ
        ChartCalculator c = new ChartCalculator();
        List<String> listChar2 = c.calculateCharNewCusByDay(newCusMapByDay);
        String labels02 = "";
        String data02 = "";
        String percentData02 = "";

        if (listChar2 != null && !listChar2.isEmpty()) {
            labels02 = listChar2.get(0); // Ví dụ: '2024-12-01','2024-12-02',...
            data02 = listChar2.get(1);   // Ví dụ: 5,3,2,...
            percentData02 = listChar2.get(2); // Ví dụ: 25.00,15.00,...
        }

        // Kiểm tra nếu không có dữ liệu
        if (newCusMapByDay.isEmpty() || newCusMapByDay == null) {
            request.getSession().setAttribute("error", "No data available for this month!!");
        }

        request.getSession().setAttribute("labels02", labels02);
        request.getSession().setAttribute("data02", data02);
        request.getSession().setAttribute("percentData02", percentData02);

        request.getRequestDispatcher("ChartNewCusByDayOfMonth.jsp").forward(request, response);
    }

    private static Map<String, Integer> countCustomersByDay(List<Customer> filteredCustomers) {
        Map<String, Integer> customerCountByDay = new HashMap<>();

        for (Customer c : filteredCustomers) {
            if (c.getCreatedAt() != null) {
                LocalDate createdAt = c.getCreatedAt().toLocalDate();
                String dayKey = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                customerCountByDay.put(dayKey, customerCountByDay.getOrDefault(dayKey, 0) + 1);
            }
        }

        List<String> sortedKeys = new ArrayList<>(customerCountByDay.keySet());
        sortedKeys.sort((a, b) -> a.compareTo(b));

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (String key : sortedKeys) {
            sortedMap.put(key, customerCountByDay.get(key));
        }

        return sortedMap;
    }
}
