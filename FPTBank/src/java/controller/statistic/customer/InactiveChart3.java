package controller.statistic.customer;

import dal.CustomerDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Customer;

/**
 *
 * @author SCN
 */
@WebServlet(name = "InactiveChart3", urlPatterns = {"/admin/InactiveChart3"})
public class InactiveChart3 extends HttpServlet {

    private CustomerDAO cDao;

    public void init() throws ServletException {
        cDao = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // pie chart (Age)
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

        int count18to24age = 0, count25to34age = 0, count35to60age = 0, count60age = 0;

        for (Customer c : listOfCus) {
            if (!c.isStatus()) {
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

        String jsonResponse = String.format(
                "{\"percentages3\": [%.2f, %.2f, %.2f, %.2f], \"data3\": [%d, %d, %d, %d]}",
                percent18to24age, percent25to34age, percent35to60age, percent60age, 
                count18to24age, count25to34age, count35to60age, count60age
        );

        response.getWriter().write(jsonResponse);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
