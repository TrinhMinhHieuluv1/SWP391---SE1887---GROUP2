/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.insurance.manager;

import dal.InsuranceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Insurance;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateInsurance", urlPatterns = {"/insurance/updateinsurance"})
public class UpdateInsurance extends HttpServlet {

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
            out.println("<title>Servlet UpdateInsurance</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateInsurance at " + request.getContextPath() + "</h1>");
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

        InsuranceDAO a = new InsuranceDAO();
        String isuid = request.getParameter("isuid");
        String type = request.getParameter("type");
        String feerate = request.getParameter("feerate").replaceAll("[^\\d,.]", "");
        String coverage = request.getParameter("coverage").replaceAll("[^\\d,.]", "");
        String maxamount = request.getParameter("maxamount").replaceAll("[^\\d.,]", "").replaceAll("(\\d)[.,](?=\\d{3})", "$1");

// Chuyển đổi dữ liệu từ String sang các kiểu số
        int isuidi = Integer.parseInt(isuid);
        float feeratef = Float.parseFloat(feerate);
        float coveragef = Float.parseFloat(coverage);
        double maxamountd = Double.parseDouble(maxamount);

// Kiểm tra điều kiện feerate và coverage phải < 100
        if (feeratef >= 30 || coveragef >= 100) {
            session.setAttribute("error", "Update failed! FeeRate must be be less than 30% and CoverageRate must be less than 100%.");
            response.sendRedirect("manageinsurance"); // Quay lại trang hiển thị với thông báo lỗi
            return; // Thoát khỏi hàm để không thực hiện cập nhật
        }

// Nếu hợp lệ, tiếp tục cập nhật
        Insurance insurance = new Insurance();
        insurance.setInsuranceID(isuidi); // ID của bản ghi cần cập nhật
        insurance.setType(type);
        insurance.setFeeRate(feeratef); // Giá trị mới cho FeeRate
        insurance.setCoverageRate(coveragef); // Giá trị mới cho CoverageRate
        insurance.setMaxAmountOfLoan(maxamountd); // Giá trị mới cho MaxAmountOfLoan

// Gọi hàm updateInsurance và kiểm tra kết quả
        boolean isUpdated = a.updateInsurance(insurance);
        if (isUpdated) {
            session.setAttribute("message", "Update Insurance Successfully!");
        }
        request.getRequestDispatcher("manageInsurance.jsp").forward(request, response);
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

        HttpSession session = request.getSession();

        InsuranceDAO a = new InsuranceDAO();
        int insuranceID = (int) session.getAttribute("uid");
        String insuranceName = request.getParameter("insuranceName1");
        String type = request.getParameter("type1");
        String feerate = request.getParameter("feerate1").replaceAll("[^\\d,.]", "");
        String coverage = request.getParameter("coverage1").replaceAll("[^\\d,.]", "");
        String maxamount = request.getParameter("maxamount1").replaceAll("[^\\d.]", "");

// Chuyển đổi dữ liệu từ String sang các kiểu số
        float feeratef = Float.parseFloat(feerate);
        float coveragef = Float.parseFloat(coverage);
        double maxamountd = Double.parseDouble(maxamount);

        if (insuranceName != null) {
            insuranceName = insuranceName.trim(); // Xóa dấu cách đầu và cuối
            insuranceName = insuranceName.replaceAll("\\s+", " "); // Thay thế nhiều dấu cách bằng một dấu cách
        }

// Kiểm tra điều kiện feerate và coverage phải < 100
        if (feeratef >= 30 || coveragef >= 100) {
            session.setAttribute("error", "Add failed! FeeRate must be less than 30% and CoverageRate must be less than 100%.");
            response.sendRedirect("manageinsurance"); // Quay lại trang hiển thị với thông báo lỗi
            return; // Thoát khỏi hàm để không thực hiện cập nhật
        }

// Kiểm tra xem insuranceName đã tồn tại chưa
        if (a.isInsuranceNameExists(insuranceName)) {
            session.setAttribute("error", "Add failed! Insurance Name already exists.");
            response.sendRedirect("manageinsurance"); // Quay lại trang hiển thị với thông báo lỗi
            return; // Thoát khỏi hàm để không thực hiện cập nhật
        }

// Nếu hợp lệ, tiếp tục cập nhật
        Insurance insurance = new Insurance();

        insurance.setProviderID(insuranceID); // ID của bản ghi cần cập nhật
        insurance.setInsuranceName(insuranceName);
        insurance.setType(type);
        insurance.setFeeRate(feeratef); // Giá trị mới cho FeeRate
        insurance.setCoverageRate(coveragef); // Giá trị mới cho CoverageRate
        insurance.setMaxAmountOfLoan(maxamountd); // Giá trị mới cho MaxAmountOfLoan
        insurance.setStatus(true); // Trạng thái hoạt động

// Gọi hàm updateInsurance và kiểm tra kết quả
        boolean isAdd = a.addInsurance(insurance);
        if (isAdd) {
            session.setAttribute("message", "Add Insurance Successfully!");
        } else {
            session.setAttribute("error", "Add failed! Please try again.");
        }
        response.sendRedirect("manageinsurance");
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
