/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Properties;
import util.Email;

/**
 *
 * @author tiend
 */
@WebServlet(name = "SendEmail", urlPatterns = {"/sendEmail"})
public class SendEmail extends HttpServlet {

    static final String from = "tiendung18112k4@gmail.com";
    static final String pass = "dmol dllu bnpj cous";

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
            out.println("<title>Servlet SendEmail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendEmail at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("timibank/forgotPass.jsp").forward(request, response);
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");// smtp host
        props.put("mail.smtp.port", "587");// TLS 587,SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });
        String toEmail = request.getParameter("email");
            MimeMessage msg = new MimeMessage(session);
    
        try {
                  msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                     // nguoi gui
                     msg.setFrom(from);
                     // nguoi nhan
                     msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail,false));
                     // tieu de
                     msg.setSubject("Reset PassWord");
                     // quy dinh ngay gui
                     msg.setSentDate(new Date());
                     // quy dinh email nhan phan hoi khi ma nguoi nhan nhan nut Reply   msg.setReplyTo(InternetAddress.parse(from,false));
                     
                     // Noi dung
                    msg.setContent("<!DOCTYPE html>\n"
                    + "<html lang=\"vi\">\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <title>Mã Code 6 Chữ Số</title>\n"
                    + "    <style>\n"
                    + "        body {\n"
                    + "            font-family: Arial, sans-serif;\n"
                    + "            display: flex;\n"
                    + "            justify-content: center;\n"
                    + "            align-items: center;\n"
                    + "            height: 100vh;\n"
                    + "            background-color: #f4f4f4;\n"
                    + "        }\n"
                    + "        .code {\n"
                    + "            display: flex;\n"
                    + "            gap: 10px; /* Khoảng cách giữa các ô */\n"
                    + "        }\n"
                    + "        .digit {\n"
                    + "            width: 50px; /* Chiều rộng của mỗi ô */\n"
                    + "            height: 50px; /* Chiều cao của mỗi ô */\n"
                    + "            font-size: 2em;\n"
                    + "            font-weight: bold;\n"
                    + "            display: flex;\n"
                    + "            justify-content: center;\n"
                    + "            align-items: center;\n"
                    + "            border: 2px solid #007bff;\n"
                    + "            border-radius: 10px;\n"
                    + "            background-color: #ffffff;\n"
                    + "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n"
                    + "        }\n"
                    + "    </style>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "    <div class=\"code\" id=\"code\"></div>\n"
                    + "\n"
                    + "    <script>\n"
                    + "        // Hàm tạo chuỗi 6 chữ số ngẫu nhiên\n"
                    + "        function generateRandomCode(length) {\n"
                    + "            let result = '';\n"
                    + "            for (let i = 0; i < length; i++) {\n"
                    + "                result += Math.floor(Math.random() * 10); // Sinh số ngẫu nhiên từ 0 đến 9\n"
                    + "            }\n"
                    + "            return result;\n"
                    + "        }\n"
                    + "\n"
                    + "        // Gọi hàm để tạo chuỗi 6 chữ số ngẫu nhiên\n"
                    + "        const codeString = generateRandomCode(6);\n"
                    + "\n"
                    + "        // Lấy thẻ div chứa mã code\n"
                    + "        const codeContainer = document.getElementById(\"code\");\n"
                    + "\n"
                    + "        // Chia mỗi chữ số vào các ô\n"
                    + "        for (let char of codeString) {\n"
                    + "            const digitElement = document.createElement(\"div\");\n"
                    + "            digitElement.className = \"digit\";\n"
                    + "            digitElement.textContent = char;\n"
                    + "            codeContainer.appendChild(digitElement);\n"
                    + "        }\n"
                    + "    </script>\n"
                    + "</body>\n"
                    + "</html>", "text/HTML; charset=UTF-8");
                     
                     // gui email
                     Transport.send(msg);
                     
                     System.out.println("Gui email thanh cong");
        } catch (Exception e) {
        }

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
