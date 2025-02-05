/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.User1DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.User;
import java.sql.Date;

/**
 *
 * @author ACER
 */
@WebServlet(name="UpdateProfile", urlPatterns={"/updateprofile"})
public class UpdateProfile extends HttpServlet {

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
            out.println("<title>Servlet UpdateProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfile at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User1DAO dao = new User1DAO();
        int uID = (int) session.getAttribute("uid");
        String fullname = request.getParameter("fullname");
        String cccd = request.getParameter("cccd");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String dob_raw = request.getParameter("dob");
        String address = request.getParameter("address");
        User user = dao.getUserProfileByID(uID);
        Date dob = Date.valueOf(dob_raw);
        String error = "";
        User1DAO udao = new User1DAO();
        if (isValidString(fullname) == false) {
            error = "Wrong type in fullname, only contain A-a and not null";
        } else if (isNumeric2(cccd) == false) {
            error = "Wrong type in cccd, only contain number 0-9 and must be 12 numbers and not null";
        } else if (isValidGender(gender) == false) {
            error = "Wrong type in gender only Male or Female";
        } else if (isValidEmail(email) == false) {
            error = "Wrong type in email, example like abc@gmail.com";
        } else if (isNumeric(phone) == false) {
            error = "Wrong type in cccd, only contain number 0-9 and must be 10 numbers and not null"; 
        }else if(isValidString(address) == false){
            error = "Wrong type in address, only contain A-a and not null";
        } 
        else if (udao.isEmailUsed(email, uID) == true) {
            error = "Email is used";
        } else if (udao.isPhoneUsed(phone, uID) == true) {
            error = "Phone is used";
        } else if (udao.isCCCDUsedExceptUser(cccd, uID) == true){
            error = "CCCD is used";        
        } else {
            udao.updateUser(uID, fullname, phone, email, cccd, dob, gender.equals("Male"),address);
            error = "Update Successfully, Please to back to your profile";
            User account = dao.getUserByID(uID);
            session.setAttribute("account",account);
        }
        request.setAttribute("error", error);
        request.getRequestDispatcher("updateprofile.jsp").forward(request, response);
    }

    public static boolean isValidString(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        // Tách tên thành các từ (loại bỏ khoảng trắng dư thừa)
        String[] words = name.trim().split("\\s+");

        // Giả sử tên phải có ít nhất 2 từ (có thể thay đổi theo yêu cầu)
        if (words.length < 2) {
            return false;
        }

        // Regex cho từ có nhiều hơn 1 ký tự:
        // - Ký tự đầu tiên: chữ in hoa (bao gồm chữ có dấu từ À đến Ỹ)
        // - Các ký tự sau: chữ thường (bao gồm chữ có dấu từ à đến ỹ)
        String regexFull = "^[A-ZÀ-Ỹ][a-zà-ỹ]+$";
        // Regex cho từ chỉ có 1 ký tự (phải là chữ in hoa)
        String regexInitial = "^[A-ZÀ-Ỹ]$";

        // Kiểm tra từng từ theo tiêu chí đã nêu
        for (String word : words) {
            if (word.length() == 1) {
                if (!word.matches(regexInitial)) {
                    return false;
                }
            } else {
                if (!word.matches(regexFull)) {
                    return false;
                }
            }
        }
        return true;
    }

    

    public boolean isValidGender(String gender) {
        // Kiểm tra nếu giới tính là "male" hoặc "female"
        return gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female");
    }

    public boolean isNumeric(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false; // Chuỗi rỗng hoặc null
        }

        // Kiểm tra chuỗi bắt đầu bằng "0" và có chính xác 10 chữ số
        return str.matches("^0\\d{9}$");
    }

    public boolean isNumeric2(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false; // Chuỗi rỗng hoặc null
        }

        // Kiểm tra chuỗi bắt đầu bằng "0" và có chính xác 10 chữ số
        return str.matches("^0\\d{11}$");
    }

    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false; // Nếu email null hoặc rỗng
        }

        // Biểu thức chính quy để kiểm tra email hợp lệ
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return email.matches(emailRegex);
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
        processRequest(request, response);
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
