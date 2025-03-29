package controller.service;

import dal.AssetDAO;
import dal.ContractDAO;
import dal.InsuranceDAO;
import dal.LoanPaymentDAO;
import dal.LoanTermDAO;
import dal.SalaryDAO;
import dal.ServiceItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import model.Asset;
import model.Contract;
import model.Customer;
import model.Insurance;
import model.LoanPayment;
import model.LoanTerm;
import model.Salary;
import model.ServiceItem;

/**
 *
 * @author HP
 */
@WebServlet(name = "CreateLoanRequest", urlPatterns = {"/create-loan-request"})
public class CreateLoanRequest extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateLoanContract</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateLoanContract at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AssetDAO adao = new AssetDAO();
        SalaryDAO sdao = new SalaryDAO();
        ServiceItemDAO sidao = new ServiceItemDAO();
        LoanTermDAO ltdao = new LoanTermDAO();

        //Resource for both Secured and Unsecured Loan
        String Type = request.getParameter("Type");
        Customer account = (Customer) session.getAttribute("account");
        List<ServiceItem> serviceItemList = sidao.selectAllServiceItem();
        request.setAttribute("serviceItemList", serviceItemList);

        if (account == null) {
            response.sendRedirect("/timibank/home?RoleErr=true");
            return;
        }
        //Resource for Secured Loan
        if (Type == null || Type.isEmpty() || Type.equals("Secured")) {
            List<Asset> assetList = adao.getAssetListForCustomer(account.getCustomerId());
            request.setAttribute("assetList", assetList);
            List<LoanTerm> loanTermList = ltdao.selectLoanTermListByCondition("Secured Loan");
            request.setAttribute("loanTermList", loanTermList);
        } //Resource for Unsecured Loan
        else {
            List<Salary> salaryList = sdao.getSalaryListForCustomer(account.getCustomerId());
            request.setAttribute("salaryList", salaryList);
            List<LoanTerm> loanTermList = ltdao.selectLoanTermListByCondition("Unsecured Loan");
            request.setAttribute("loanTermList", loanTermList);
        }

        request.getRequestDispatcher("createLoanRequest.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AssetDAO adao = new AssetDAO();
        SalaryDAO sdao = new SalaryDAO();
        InsuranceDAO idao = new InsuranceDAO();
        ServiceItemDAO sidao = new ServiceItemDAO();
        ContractDAO ctdao = new ContractDAO();
        LoanPaymentDAO lpdao = new LoanPaymentDAO();
        Customer account = (Customer) request.getSession().getAttribute("account");

        String Type = request.getParameter("Type");
        int AssetID = -1;
        int SalaryID = -1;
        Asset Asset = null;
        Salary Salary = null;
        if (Type.equals("Secured")) {
            //Get Asset
            Type = "Secured Loan";
            String AssetID_raw = request.getParameter("Asset").split("-")[0];
            try {
                AssetID = Integer.parseInt(AssetID_raw);
                Asset = adao.getAssetById(AssetID);
            } catch (NumberFormatException e) {
            }

        } else {
            //Get Salary
            Type = "Unsecured Loan";
            String SalaryID_raw = request.getParameter("Salary").split("-")[0];;
            try {
                SalaryID = Integer.parseInt(SalaryID_raw);
                Salary = sdao.getSalaryById(SalaryID);
            } catch (NumberFormatException e) {
            }
        }

        //Get Amount
        String Amount_raw = request.getParameter("Amount").replaceAll("\\.", "");
        BigDecimal Amount = BigDecimal.ZERO;
        try {
            Amount = BigDecimal.valueOf(Long.parseLong(Amount_raw));
        } catch (NumberFormatException e) {
        }

        //Get Service Item
        String ServiceItemID_raw = request.getParameter("ServiceItem").split("-")[0];
        int ServiceItemID = -1;
        ServiceItem ServiceItem = null;
        try {
            ServiceItemID = Integer.parseInt(ServiceItemID_raw);
            ServiceItem = sidao.selectAServiceItemByID(ServiceItemID);
        } catch (NumberFormatException e) {
        }

        //Get Period
        String Period_raw = request.getParameter("Period").split("-")[0];
        int Period = -1;
        try {
            Period = Integer.parseInt(Period_raw);
        } catch (NumberFormatException e) {
        }

        //Get Description
        String Description = request.getParameter("Description");

        //Get Monthly Payment
        String MonthlyPayment_raw = request.getParameter("MonthlyPayment");
        boolean MonthlyPayment;
        String MonthlyPaymentType = "";
        if (MonthlyPayment_raw != null) {
            MonthlyPayment = true;
            MonthlyPaymentType = request.getParameter("MonthlyPaymentType");
            if (!MonthlyPaymentType.equals("Fixed")) {
                MonthlyPaymentType = "Reducing Balance";
            }
        } else {
            MonthlyPayment = false;
        }

        //Get Insurance
        String InsuranceID_raw = request.getParameter("Insurance").split("-")[0];
        int InsuranceID = -1;
        Insurance Insurance = null;
        try {
            InsuranceID = Integer.parseInt(InsuranceID_raw);
            Insurance = idao.getInsuranceByID(InsuranceID);
        } catch (NumberFormatException e) {
        }

        //Insert Contract
        if (Type.equals("Secured Loan")) {
            Contract ContractToAdd
                    = new Contract(0, account,
                            Amount, Period,
                            ServiceItem.getLatePaymentRate(),
                            0,
                            ServiceItem.getInterestRate(),
                            Type, Description, Asset, null,
                            Insurance, MonthlyPayment, MonthlyPaymentType,
                            0, null, Insurance.getCoverageRate());
            Asset.setUsed(true);
            adao.updateAsset(Asset);
            ctdao.addAContract(ContractToAdd);
        } else {
            Contract ContractToAdd
                    = new Contract(0, account,
                            Amount, Period,
                            ServiceItem.getLatePaymentRate(),
                            0,
                            ServiceItem.getInterestRate(),
                            Type, Description, null, Salary,
                            Insurance, MonthlyPayment, MonthlyPaymentType,
                            0, null, Insurance.getCoverageRate());
            Salary.setUsed(true);
            sdao.updateSalary(Salary);
            ctdao.addAContract(ContractToAdd);
        }

        response.sendRedirect("/timibank/contract-management-for-customer?fromAdd=true");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
