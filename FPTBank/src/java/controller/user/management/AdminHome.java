package controller.user.management;

import controller.*;
import controller.statistic.feedback.ChartCalculatorHomePage;
import controller.statistic.feedback.GetListOfStar;
import controller.statistic.feedback.GetTotalOfNewCus;
import dal.CustomerDAO;
import dal.FeedbackDAO;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Feedback;
import model.User;
import java.text.DecimalFormat;

/**
 *
 * @author SCN
 */
@WebServlet(name = "AdminLogin", urlPatterns = {"/admin/home"})
public class AdminHome extends HttpServlet {

    private FeedbackDAO fDao;

    public void init() throws ServletException {
        fDao = new FeedbackDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().setAttribute("message", "Login Successfully !!");

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

        // new customer
        CustomerDAO cDao = new CustomerDAO();
        List<Customer> listOfCus = cDao.selectAllCustomer();
        GetTotalOfNewCus getNewCus = new GetTotalOfNewCus();
        int totalOfNewCus = getNewCus.getTotalNumberOfNewCus(listOfCus);
        request.getSession().setAttribute("totalOfNewCus", totalOfNewCus);

        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
