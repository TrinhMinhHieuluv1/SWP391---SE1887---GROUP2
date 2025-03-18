package controller.statistic.customer;

import dal.CustomerDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
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

@WebServlet(name = "GetDataOfCustomers", urlPatterns = {"/admin/getData_Cus"})
public class GetDataOfCustomers extends HttpServlet {

    private CustomerDAO cDao;

    public void init() throws ServletException {
        cDao = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ChartCalculator chart = new ChartCalculator();

        // pie chart ( status )
        List<Customer> listOfCus = cDao.selectAllCustomer();
        int countActive = 0;
        int countInactive = 0;
        for (Customer l : listOfCus) {
            if (l.isStatus()) {
                countActive++;
            } else {
                countInactive++;
            }
        }

        int totalOfCustomer = countActive + countInactive;

        double percentOfActive = totalOfCustomer > 0 ? ((double) countActive / totalOfCustomer) * 100 : 0;
        double percentOfInactive = totalOfCustomer > 0 ? ((double) countInactive / totalOfCustomer) * 100 : 0;

        request.getSession().setAttribute("numOfActive", countActive);
        request.getSession().setAttribute("numOfInactive", countInactive);
        request.getSession().setAttribute("percentOfActive", percentOfActive);
        request.getSession().setAttribute("percentOfInactive", percentOfInactive);

        // pie chart (Age)
        int count18to24age = 0, count25to34age = 0, count35to60age = 0, count60age = 0;
        for (Customer c : listOfCus) {
            if (c.isStatus()) {
                int ageOfCus = c.getAge();
                if (ageOfCus >= 18 && ageOfCus <= 24) {
                    count18to24age++;
                } else if (ageOfCus >= 25 && ageOfCus <= 34) {
                    count25to34age++;
                } else if (ageOfCus >= 35 && ageOfCus <= 60) {
                    count35to60age++;
                } else if (ageOfCus > 60) {
                    count60age++;
                }
            }
        }

        int total = count18to24age + count25to34age + count35to60age + count60age;

        double percent18to24age = total > 0 ? ((double) count18to24age / total) * 100 : 0;
        double percent25to34age = total > 0 ? ((double) count25to34age / total) * 100 : 0;
        double percent35to60age = total > 0 ? ((double) count35to60age / total) * 100 : 0;
        double percent60age = total > 0 ? ((double) count60age / total) * 100 : 0;

        request.getSession().setAttribute("numOf18to24age", count18to24age);
        request.getSession().setAttribute("numOf25to34age", count25to34age);
        request.getSession().setAttribute("numOf35to60age", count35to60age);
        request.getSession().setAttribute("numOf60age", count60age);

        request.getSession().setAttribute("percent18to24age", percent18to24age);
        request.getSession().setAttribute("percent25to34age", percent25to34age);
        request.getSession().setAttribute("percent35to60age", percent35to60age);
        request.getSession().setAttribute("percent60age", percent60age);

        // bar chart ( credit score )
        int numOfPoor = 0, numOfFair = 0, numOfGood = 0, numOfVeryGood = 0, numOfExcellent = 0;
        for (Customer c : listOfCus) {
            if (c.isStatus()) {
                int creditScoreOfCus = c.getCreditScore();
                if (creditScoreOfCus >= 300 && creditScoreOfCus <= 499) {
                    numOfPoor++;
                } else if (creditScoreOfCus >= 500 && creditScoreOfCus <= 599) {
                    numOfFair++;
                } else if (creditScoreOfCus >= 600 && creditScoreOfCus <= 699) {
                    numOfGood++;
                } else if (creditScoreOfCus >= 700 && creditScoreOfCus <= 799) {
                    numOfVeryGood++;
                } else {
                    numOfExcellent++;
                }
            }
        }
        List<Integer> creditScoreData = new ArrayList<>();
        creditScoreData.add(numOfPoor);
        creditScoreData.add(numOfFair);
        creditScoreData.add(numOfGood);
        creditScoreData.add(numOfVeryGood);
        creditScoreData.add(numOfExcellent);

        List<Double> listPercentChar2 = chart.calPercentageOfChar2(creditScoreData, countActive);
        double percentPoor = listPercentChar2.get(0);
        double percentFair = listPercentChar2.get(1);
        double percentGood = listPercentChar2.get(2);
        double percentVeryGood = listPercentChar2.get(3);
        double percentExcellent = listPercentChar2.get(4);

        String data2 = chart.calDataChart2(creditScoreData);

        request.getSession().setAttribute("percentPoor", percentPoor);
        request.getSession().setAttribute("percentFair", percentFair);
        request.getSession().setAttribute("percentGood", percentGood);
        request.getSession().setAttribute("percentVeryGood", percentVeryGood);
        request.getSession().setAttribute("percentExcellent", percentExcellent);
        request.getSession().setAttribute("data2", data2);
        request.getSession().setAttribute("total_cus", countActive);

        // bar chart ( balance )
        int numOfBasic = 0, numOfEmerging = 0, numOfMiddle = 0, numOfUpper = 0, numOfVip = 0;
        for (Customer c : listOfCus) {
            if (c.isStatus()) {
                BigDecimal balanceOfCus = c.getBalance();

                if (balanceOfCus.compareTo(new BigDecimal("50000000")) < 0) {
                    numOfBasic++;
                } else if (balanceOfCus.compareTo(new BigDecimal("50000000")) >= 0 && balanceOfCus.compareTo(new BigDecimal("200000000")) <= 0) {
                    numOfEmerging++;
                } else if (balanceOfCus.compareTo(new BigDecimal("200000000")) > 0 && balanceOfCus.compareTo(new BigDecimal("500000000")) <= 0) {
                    numOfMiddle++;
                } else if (balanceOfCus.compareTo(new BigDecimal("500000000")) > 0 && balanceOfCus.compareTo(new BigDecimal("1000000000")) <= 0) {
                    numOfUpper++;
                } else {
                    numOfVip++;
                }
            }
        }
        List<Integer> balanceOfData = new ArrayList<>();
        balanceOfData.add(numOfBasic);
        balanceOfData.add(numOfEmerging);
        balanceOfData.add(numOfMiddle);
        balanceOfData.add(numOfUpper);
        balanceOfData.add(numOfVip);

        List<Double> listPercentChar4 = chart.calPercentageOfChar4(balanceOfData, countActive);
        double percentBasic = listPercentChar4.get(0);
        double percentEmerging = listPercentChar4.get(1);
        double percentMiddle = listPercentChar4.get(2);
        double percentUpper = listPercentChar4.get(3);
        double percentVip = listPercentChar4.get(4);

        String data4 = chart.calDataChart4(balanceOfData);

        request.getSession().setAttribute("percentBasic", percentBasic);
        request.getSession().setAttribute("percentEmerging", percentEmerging);
        request.getSession().setAttribute("percentMiddle", percentMiddle);
        request.getSession().setAttribute("percentUpper", percentUpper);
        request.getSession().setAttribute("percentVip", percentVip);
        request.getSession().setAttribute("data4", data4);
        request.getSession().setAttribute("totalCus2", countActive);

        // line chart ( new customer )
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
        String labels01 = "";
        String data01 = "";
        String percentData01 = "";

        if (listChar1 != null && !listChar1.isEmpty()) {
            labels01 = listChar1.get(0);
            data01 = listChar1.get(1);
            percentData01 = listChar1.get(2);

        }

        if (newCusMapByMonth.isEmpty() || newCusMapByMonth == null) {
            request.getSession().setAttribute("error", "No data available !!");
        }

        request.getSession().setAttribute("labels01", labels01);
        request.getSession().setAttribute("data01", data01);
        request.getSession().setAttribute("percentData01", percentData01);

        request.getRequestDispatcher("charts-chartjs.jsp").forward(request, response);

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
