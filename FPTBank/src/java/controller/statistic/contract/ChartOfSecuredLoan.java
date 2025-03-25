package controller.statistic.contract;

import dal.ContractDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
import model.Contract;
import org.json.simple.JSONObject;

/**
 *
 * @author SCN
 */
@WebServlet(name = "ChartOfSecuredLoan", urlPatterns = {"/admin/chartSecuredLoan"})
public class ChartOfSecuredLoan extends HttpServlet {

    private ContractDAO contractDao;

    public void init() throws ServletException {
        contractDao = new ContractDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ChartContractCalculator chart = new ChartContractCalculator();
        List<Contract> listContract = contractDao.selectAllContract();

        List<Contract> filteredContract = new ArrayList<>();
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
        if (fromDate == null) {
            fromDate = LocalDate.of(2024, 10, 1); // Ngày mặc định
        }
        if (toDate == null) {
            toDate = LocalDate.now(); // Lấy ngày hiện tại
        }

        for (Contract contract : listContract) {
            if (contract.getStatusID() != 3 && contract.getCreateAt() != null && contract.getType().equalsIgnoreCase("Secured Loan")) {
                LocalDate createdAt = contract.getCreateAt().toLocalDate();
                if ((createdAt.isEqual(fromDate) || createdAt.isAfter(fromDate))
                        && (createdAt.isEqual(toDate) || createdAt.isBefore(toDate))) {
                    filteredContract.add(contract);
                }
            }
        }

        Map<String, Integer> contractMapByMonth = countContractByMonth(filteredContract);
        List<String> listDataContract = chart.calculateCharContract(contractMapByMonth);

        JSONObject jsonResponse = new JSONObject();
        
        jsonResponse.put("fromDate",fromDate.toString());
        jsonResponse.put("toDate",toDate.toString());
        if (contractMapByMonth.isEmpty() || contractMapByMonth == null) {
            jsonResponse.put("error", "No data available !!");
        } else {
            jsonResponse.put("labels01", listDataContract.get(0));
            jsonResponse.put("data01", listDataContract.get(1));
            jsonResponse.put("percentData01", listDataContract.get(2));
        }

        jsonResponse.put("urlSvl", "chartSecuredLoan");
        jsonResponse.put("titleOfX", "Secured Loan Contract");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());

    }

    private static Map<String, Integer> countContractByMonth(List<Contract> filteredContract) {
        Map<String, Integer> contractCountByMonth = new HashMap<>();
        List<String> MONTH_ORDER = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

        for (Contract c : filteredContract) {
            LocalDate createAt = c.getCreateAt().toLocalDate();
            String monthShort = createAt.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            String yearMonthKey = createAt.getYear() + "-" + monthShort;
            contractCountByMonth.put(yearMonthKey, contractCountByMonth.getOrDefault(yearMonthKey, 0) + 1);
        }

        List<String> sortedKeys = new ArrayList<>(contractCountByMonth.keySet());
        sortedKeys.sort(Comparator.comparing(key -> {
            String[] parts = key.split("-");
            int year = Integer.parseInt(parts[0]);
            int monthIndex = MONTH_ORDER.indexOf(parts[1]);
            return year * 12 + monthIndex;
        }));

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (String key : sortedKeys) {
            sortedMap.put(key, contractCountByMonth.get(key));
        }
        return sortedMap;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
