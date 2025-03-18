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
import org.json.simple.JSONObject;

/**
 *
 * @author SCN
 */
@WebServlet(name = "ChartRevenueLoan", urlPatterns = {"/admin/ChartRevenueLoan"})
public class ChartRevenueLoan extends HttpServlet {

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

        // Revenue from loan contract
        List<Contract> filteredLoanContract = new ArrayList<>();
        String fromDateLoanStr = request.getParameter("fromDateLoan");
        String toDateLoanStr = request.getParameter("toDateLoan");

        LocalDate fromDateLoan = null;
        LocalDate toDateLoan = null;

        try {
            if (fromDateLoanStr != null && toDateLoanStr != null) {
                fromDateLoan = LocalDate.parse(fromDateLoanStr);
                toDateLoan = LocalDate.parse(toDateLoanStr);
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        if (fromDateLoan == null) {
            fromDateLoan = LocalDate.of(2024, 10, 1); // Ngày mặc định
        }
        if (toDateLoan == null) {
            toDateLoan = LocalDate.now(); // Lấy ngày hiện tại
        }

        for (Contract contract : listContract) {
            if (contract.getStatusID() != 3 && contract.getCreateAt() != null) {
                if (contract.getType().equalsIgnoreCase("secured loan") || contract.getType().equalsIgnoreCase("Unsecured Loan")) {
                    LocalDate createdAt = contract.getCreateAt().toLocalDate();
                    if ((createdAt.isEqual(fromDateLoan) || createdAt.isAfter(fromDateLoan))
                            && (createdAt.isEqual(toDateLoan) || createdAt.isBefore(toDateLoan))) {
                        filteredLoanContract.add(contract);
                    }
                }

            }
        }

        Map<String, BigDecimal> revenueLoanByMonth = cal.calRevenueLoanByMonth(filteredLoanContract);
        List<String> listDataLoanContract = chart.calculateDataLoan(revenueLoanByMonth);

        JSONObject jsonResponse = new JSONObject();
        if (revenueLoanByMonth.isEmpty() || revenueLoanByMonth == null) {
            jsonResponse.put("error", "No data available !!");
        } else {
            jsonResponse.put("labels04", listDataLoanContract.get(0));
            jsonResponse.put("data04", listDataLoanContract.get(1));
            jsonResponse.put("percentData04", listDataLoanContract.get(2));
        }

        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
