package controller.statistic.contract;

import dal.ContractDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Contract;
import model.Feedback;
import org.json.simple.JSONObject;

/**
 *
 * @author SCN
 */
@WebServlet(name = "ChartRevenueSaving", urlPatterns = {"/admin/ChartRevenueSaving"})
public class ChartRevenueSaving extends HttpServlet {

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
        CalculateTotalOfRevenue cal = new CalculateTotalOfRevenue();

        // Revenue from saving contract
        List<Contract> filteredSavingContract = new ArrayList<>();
        String fromDateSavingStr = request.getParameter("fromDateSaving");
        String toDateSavingStr = request.getParameter("toDateSaving");

        LocalDate fromDateSaving = null;
        LocalDate toDateSaving = null;

        try {
            if (fromDateSavingStr != null && toDateSavingStr != null) {
                fromDateSaving = LocalDate.parse(fromDateSavingStr);
                toDateSaving = LocalDate.parse(toDateSavingStr);
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        if (fromDateSaving == null) {
            fromDateSaving = LocalDate.of(2024, 10, 1); // Ngày mặc định
        }
        if (toDateSaving == null) {
            toDateSaving = LocalDate.now(); // Lấy ngày hiện tại
        }

        for (Contract contract : listContract) {
            if (contract.getStatusID() != 3 && contract.getCreateAt() != null && contract.getType().equalsIgnoreCase("Saving")) {
                LocalDate createdAt = contract.getCreateAt().toLocalDate();
                if ((createdAt.isEqual(fromDateSaving) || createdAt.isAfter(fromDateSaving))
                        && (createdAt.isEqual(toDateSaving) || createdAt.isBefore(toDateSaving))) {
                    filteredSavingContract.add(contract);
                }
            }
        }

        Map<String, BigDecimal> revenueByMonth = cal.calRevenueSavingByMonth(filteredSavingContract);
        List<String> listDataSavingContract = chart.calculateDataSaving(revenueByMonth);

        JSONObject jsonResponse = new JSONObject();
        if (revenueByMonth.isEmpty() || revenueByMonth == null) {
            jsonResponse.put("error", "No data available !!");
        } else {
            jsonResponse.put("labels03", listDataSavingContract.get(0));
            jsonResponse.put("data03", listDataSavingContract.get(1));
            jsonResponse.put("percentData03", listDataSavingContract.get(2));
        }

        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
