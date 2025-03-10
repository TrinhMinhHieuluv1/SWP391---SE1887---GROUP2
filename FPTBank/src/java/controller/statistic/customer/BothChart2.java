package controller.statistic.customer;

import dal.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author SCN
 */
@WebServlet(name = "BothChar2", urlPatterns = {"/admin/BothChart2"})
public class BothChart2 extends HttpServlet {

    private CustomerDAO cDao;

    public void init() throws ServletException {
        cDao = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json"); // Đặt kiểu dữ liệu trả về là JSON
        response.setCharacterEncoding("UTF-8");

        // bar chart ( credit score )
        ChartCalculator chart = new ChartCalculator();
        List<Customer> listOfCus = cDao.selectAllCustomer();

        if (listOfCus.isEmpty()) {
            response.getWriter().write("{\"error\": \"No data valiable !!\"}");
            return;
        }

        int numOfPoor = 0, numOfFair = 0, numOfGood = 0, numOfVeryGood = 0, numOfExcellent = 0;
        for (Customer c : listOfCus) {

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
        List<Integer> creditScoreData = new ArrayList<>();
        creditScoreData.add(numOfPoor);
        creditScoreData.add(numOfFair);
        creditScoreData.add(numOfGood);
        creditScoreData.add(numOfVeryGood);
        creditScoreData.add(numOfExcellent);

        List<Double> listPercentChar2 = chart.calPercentageOfChar2(creditScoreData, listOfCus.size());
        double percentPoor = listPercentChar2.get(0);
        double percentFair = listPercentChar2.get(1);
        double percentGood = listPercentChar2.get(2);
        double percentVeryGood = listPercentChar2.get(3);
        double percentExcellent = listPercentChar2.get(4);

        String jsonResponse = String.format(
                "{\"data2\": [%d, %d, %d, %d, %d], \"total_cus\": %d, \"percentages\": [%.2f, %.2f, %.2f, %.2f, %.2f]}",
                numOfPoor, numOfFair, numOfGood, numOfVeryGood, numOfExcellent, listOfCus.size(),
                percentPoor, percentFair, percentGood, percentVeryGood, percentExcellent
        );

        response.getWriter().write(jsonResponse); // Gửi dữ liệu JSON về client

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
