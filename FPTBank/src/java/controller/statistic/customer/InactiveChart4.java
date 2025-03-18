package controller.statistic.customer;

import dal.CustomerDAO;
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

/**
 *
 * @author SCN
 */
@WebServlet(name = "InactiveChart4", urlPatterns = {"/admin/InactiveChart4"})
public class InactiveChart4 extends HttpServlet {

    private CustomerDAO cDao;

    public void init() throws ServletException {
        cDao = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json"); // Đặt kiểu dữ liệu trả về là JSON
        response.setCharacterEncoding("UTF-8");

        // bar chart ( balance )
        ChartCalculator chart = new ChartCalculator();
        List<Customer> listOfCus = cDao.selectAllCustomer();

        int countInactive = 0;
        for (Customer l : listOfCus) {
            if (!l.isStatus()) {
                countInactive++;
            }
        }

        if (countInactive == 0) {
            response.getWriter().write("{\"error\": \"No data valiable !!\"}");
            return;
        }

        int numOfBasic = 0, numOfEmerging = 0, numOfMiddle = 0, numOfUpper = 0, numOfVip = 0;
        for (Customer c : listOfCus) {
            if (!c.isStatus()) {
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

        List<Double> listPercentChar4 = chart.calPercentageOfChar4(balanceOfData, countInactive);
        double percentBasic = listPercentChar4.get(0);
        double percentEmerging = listPercentChar4.get(1);
        double percentMiddle = listPercentChar4.get(2);
        double percentUpper = listPercentChar4.get(3);
        double percentVip = listPercentChar4.get(4);

       
        String jsonResponse = String.format(
                "{\"data4\": [%d, %d, %d, %d, %d], \"total_cus\": %d, \"percentages4\": [%.2f, %.2f, %.2f, %.2f, %.2f]}",
                numOfBasic, numOfEmerging, numOfMiddle, numOfUpper, numOfVip, countInactive,
                percentBasic, percentEmerging, percentMiddle, percentUpper, percentVip
        );

        response.getWriter().write(jsonResponse); // Gửi dữ liệu JSON về client

        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
