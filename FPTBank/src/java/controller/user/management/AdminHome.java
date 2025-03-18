package controller.user.management;

import controller.statistic.contract.CalculateBounceRate;
import controller.statistic.contract.CalculateTotalOfRevenue;
import controller.statistic.contract.ChartContractCalculator;
import controller.statistic.feedback.ChartCalculatorHomePage;
import controller.statistic.feedback.GetListOfStar;
import controller.statistic.feedback.GetTotalOfNewCus;
import dal.ContractDAO;
import dal.CustomerDAO;
import dal.FeedbackDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Feedback;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Map;
import model.Contract;

/**
 *
 * @author SCN
 */
@WebServlet(name = "AdminLogin", urlPatterns = {"/admin/home"})
public class AdminHome extends HttpServlet {
    
    private FeedbackDAO fDao;
    private CustomerDAO cDao;
    private ContractDAO contractDao;
    
    public void init() throws ServletException {
        fDao = new FeedbackDAO();
        cDao = new CustomerDAO();
        contractDao = new ContractDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ChartContractCalculator chart = new ChartContractCalculator();

        // feed back
        List<Feedback> listOfFeedBack = fDao.selectAllFeedback();
        GetListOfStar getStar = new GetListOfStar();
        List<Integer> listOfStar = getStar.getListOfStar(listOfFeedBack);
        request.getSession().setAttribute("listOfStar", listOfStar);
        
        int totalFeedback = listOfStar.get(0) + listOfStar.get(1) + listOfStar.get(2) + listOfStar.get(3) + listOfStar.get(4);
        
        DecimalFormat df = new DecimalFormat("#.##");
        double percent1star = totalFeedback > 0 ? Double.parseDouble(df.format(((double) listOfStar.get(0) / totalFeedback) * 100)) : 0;
        double percent2star = totalFeedback > 0 ? Double.parseDouble(df.format(((double) listOfStar.get(1) / totalFeedback) * 100)) : 0;
        double percent3star = totalFeedback > 0 ? Double.parseDouble(df.format(((double) listOfStar.get(2) / totalFeedback) * 100)) : 0;
        double percent4star = totalFeedback > 0 ? Double.parseDouble(df.format(((double) listOfStar.get(3) / totalFeedback) * 100)) : 0;
        double percent5star = totalFeedback > 0 ? Double.parseDouble(df.format(((double) listOfStar.get(4) / totalFeedback) * 100)) : 0;
        
        request.getSession().setAttribute("percent1", percent1star);
        request.getSession().setAttribute("percent2", percent2star);
        request.getSession().setAttribute("percent3", percent3star);
        request.getSession().setAttribute("percent4", percent4star);
        request.getSession().setAttribute("percent5", percent5star);
        
        ChartCalculatorHomePage c = new ChartCalculatorHomePage();
        String dataOfChartFb = c.calDataChartFeedBack(listOfStar);
        request.getSession().setAttribute("dataOfChartFb", dataOfChartFb);
        //========================================================================
        
        // total of new customer
        List<Customer> listOfCus = cDao.selectAllCustomer();
        GetTotalOfNewCus getNewCus = new GetTotalOfNewCus();
        int totalOfNewCus = getNewCus.getTotalNumberOfNewCus(listOfCus);
        request.getSession().setAttribute("totalOfNewCus", totalOfNewCus);
        
        //========================================================================
        
        // total of contract
        List<Contract> listContract = contractDao.selectAllContract();
        int totalOfContract = listContract.size();
        request.getSession().setAttribute("totalOfContract", totalOfContract);
        
        //========================================================================
        
        // total of Revenue
        CalculateTotalOfRevenue cal = new CalculateTotalOfRevenue();
        BigDecimal totalOfSaving = cal.getTotalRevenueOfSaving(listContract);
        BigDecimal totalOfLoan = cal.getTotalRevenueOfLoan(listContract);
        BigDecimal totalRevenue = totalOfLoan.add(totalOfSaving);
        
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedTotalRevenue = formatter.format(totalRevenue);
        request.getSession().setAttribute("totalRevenue", formattedTotalRevenue);
        
        //========================================================================
        
        // bounce rate (rate of contract was rejected)
        CalculateBounceRate calBounce = new CalculateBounceRate();
        double bounceRate = calBounce.getBounceRate(listContract);
        request.getSession().setAttribute("bounceRate", bounceRate);
        
        //========================================================================
        
        // contract for each month
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
            if (contract.getStatusID() != 3 && contract.getCreateAt() != null) {
                LocalDate createdAt = contract.getCreateAt().toLocalDate();
                if ((createdAt.isEqual(fromDate) || createdAt.isAfter(fromDate))
                        && (createdAt.isEqual(toDate) || createdAt.isBefore(toDate))) {
                    filteredContract.add(contract);
                }
            }
        }
        
        Map<String, Integer> contractMapByMonth = chart.countContractByMonth(filteredContract);
        List<String> listDataContract = chart.calculateCharContract(contractMapByMonth);
        String labelsContract = "";
        String dataContract = "";
        String percentDataContract = "";
        
        if (listDataContract != null && !listDataContract.isEmpty()) {
            labelsContract = listDataContract.get(0);
            dataContract = listDataContract.get(1);
            percentDataContract = listDataContract.get(2);
            
        }
        
        if (contractMapByMonth.isEmpty() || contractMapByMonth == null) {
            request.getSession().setAttribute("error", "No data available !!");
        }
        
                
        request.getSession().setAttribute("urlToServletContract", "allTypeContract");
        request.getSession().setAttribute("titleOfX", "All Of Contract");
        request.getSession().setAttribute("labelsContract", labelsContract);
        request.getSession().setAttribute("dataContract", dataContract);
        request.getSession().setAttribute("percentDataContract", percentDataContract);
        

        //========================================================================
        
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
        String labelsSavingContract = "";
        String dataSavingContract = "";
        String percentDataSavingContract = "";
        
        if (listDataSavingContract != null && !listDataSavingContract.isEmpty()) {
            labelsSavingContract = listDataSavingContract.get(0);
            dataSavingContract = listDataSavingContract.get(1);
            percentDataSavingContract = listDataSavingContract.get(2);
            
        }
        
        if (revenueByMonth.isEmpty() || revenueByMonth == null) {
            request.getSession().setAttribute("error", "No data available !!");
        }
        
        request.getSession().setAttribute("labelsSavingContract", labelsSavingContract);
        request.getSession().setAttribute("dataSavingContract", dataSavingContract);
        request.getSession().setAttribute("percentDataSavingContract", percentDataSavingContract);
        //========================================================================

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
        String labelsLoanContract = "";
        String dataLoanContract = "";
        String percentDataLoanContract = "";
        
        if (listDataLoanContract != null && !listDataLoanContract.isEmpty()) {
            labelsLoanContract = listDataLoanContract.get(0);
            dataLoanContract = listDataLoanContract.get(1);
            percentDataLoanContract = listDataLoanContract.get(2);
            
        }
        
        if (revenueLoanByMonth.isEmpty() || revenueLoanByMonth == null) {
            request.getSession().setAttribute("error", "No data available !!");
        }
        
        request.getSession().setAttribute("labelsLoanContract", labelsLoanContract);
        request.getSession().setAttribute("dataLoanContract", dataLoanContract);
        request.getSession().setAttribute("percentDataLoanContract", percentDataLoanContract);
        
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
}
