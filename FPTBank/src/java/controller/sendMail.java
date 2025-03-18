/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import consts.Mail;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;

public class sendMail {

    public static boolean guiSupport(String noidung, String tieuDe, String emailGui) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", Mail.HOST_NAME);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", Mail.TSL_PORT);

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Mail.APP_EMAIL, Mail.APP_PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Mail.EMAIL_SP));
            message.setSubject(emailGui);

            String emailContent = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "<head>\n"
                    + "  <meta charset=\"UTF-8\">\n"
                    + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "  <title>Email Form</title>\n"
                    + "  <style>\n"
                    + "    body {\n"
                    + "      font-family: Arial, sans-serif;\n"
                    + "      margin: 0;\n"
                    + "      padding: 0;\n"
                    + "      display: flex;\n"
                    + "      justify-content: center;\n"
                    + "      align-items: center;\n"
                    + "      min-height: 100vh;\n"
                    + "      background-color: #f4f4f9;\n"
                    + "    }\n"
                    + "    .form-container {\n"
                    + "      background: #DCDCDC;\n"
                    + "      padding: 20px;\n"
                    + "      border-radius: 8px;\n"
                    + "      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);\n"
                    + "      width: 100%;\n"
                    + "      max-width: 400px;\n"
                    + "    }\n"
                    + "    .form-container h2 {\n"
                    + "      margin-bottom: 20px;\n"
                    + "      font-size: 24px;\n"
                    + "      text-align: center;\n"
                    + "    }\n"
                    + "    .form-group {\n"
                    + "      margin-bottom: 15px;\n"
                    + "    }\n"
                    + "    .form-group label {\n"
                    + "      display: block;\n"
                    + "      margin-bottom: 5px;\n"
                    + "      font-weight: bold;\n"
                    + "    }\n"
                    + "    .form-group input, .form-group textarea, .form-group button {\n"
                    + "      width: 100%;\n"
                    + "      padding: 10px;\n"
                    + "      border: 1px solid #ddd;\n"
                    + "      border-radius: 4px;\n"
                    + "      font-size: 16px;\n"
                    + "    }\n"
                    + "    .form-group button {\n"
                    + "      background-color: #4CAF50;\n"
                    + "      color: #ffffff;\n"
                    + "      font-weight: bold;\n"
                    + "      cursor: pointer;\n"
                    + "      border: none;\n"
                    + "    }\n"
                    + "    .form-group button:hover {\n"
                    + "      background-color: #45a049;\n"
                    + "    }\n"
                    + "  </style>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "  <div class=\"form-container\">\n"
                    + "    <h2>Thư yêu cầu hỗ trợ</h2>\n"
                    + "    <table style=\"width: 100%; border-collapse: collapse; font-family: Arial, sans-serif; color: #333;\">\n"
                    + "    <tr>\n"
                    + "        <th style=\"padding: 8px; text-align: left; background-color: #008000;border: 2px solid #000; color: white;\">Người gửi:</th>\n"
                    + "        <td style=\"padding: 8px; border: 2px solid #000;\">" + emailGui + "</td>\n"
                    + "    </tr>\n"
                    + "    <tr>\n"
                    + "        <th style=\"padding: 8px; text-align: left; background-color: #008000;border: 2px solid #000; color: white;\">Tiêu đề:</th>\n"
                    + "        <td style=\"padding: 8px; border: 2px solid #000;\">" + tieuDe + "</td>\n"
                    + "    </tr>\n"
                    + "    <tr>\n"
                    + "        <th style=\"padding: 8px; text-align: left; background-color: #008000;border: 2px solid #000; color: white;\">Nội dung:</th>\n"
                    + "        <td style=\"padding: 8px; border: 2px solid #000;\">" + noidung + "</td>\n"
                    + "    </tr>\n"
                    + "</table>"
                    + "  </div>\n"
                    + "</body>\n"
                    + "</html>";

            message.setContent(emailContent, "text/html; charset=UTF-8");
            Transport.send(message);
            return true; // Gửi thành công
        } catch (MessagingException e) {
            e.printStackTrace();
            return false; // Gửi thất bại
        }
    }

    public static boolean guiMail(String email) throws UnsupportedEncodingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", Mail.HOST_NAME);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", Mail.TSL_PORT);

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("timibank.se1887@gmail.com", "dczj xqjz xmsa csdt");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            String subject = "Cảm ơn quý khách đã quan tâm đến dịch vụ của chúng tôi";
            String emailContent = "\n"
                    + "\n"
                    + "\n"
                    + "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "    <meta charset=\"utf-8\">\n"
                    + "\n"
                    + "    <head>\n"
                    + "        <title>Xuân an khang - Ngàn ưu đãi</title>\n"
                    + "        <link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\" rel=\"stylesheet\"/>\n"
                    + "        <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&amp;display=swap\" rel=\"stylesheet\"/>\n"
                    + "        <style>\n"
                    + "            body {\n"
                    + "                font-family: 'Roboto', sans-serif;\n"
                    + "                margin: 0;\n"
                    + "                padding: 0;\n"
                    + "                background-color: #f5f5f5;\n"
                    + "            }\n"
                    + "            .container {\n"
                    + "                max-width: 1200px;\n"
                    + "                margin: 0 auto;\n"
                    + "                padding: 20px;\n"
                    + "            }\n"
                    + "            .header {\n"
                    + "                background-color: #e0f7e0;\n"
                    + "                border-radius: 10px;\n"
                    + "                padding: 20px;\n"
                    + "                text-align: center;\n"
                    + "            }\n"
                    + "            .header img {\n"
                    + "                max-width: 100%;\n"
                    + "                height: auto;\n"
                    + "                border-radius: 10px;\n"
                    + "            }\n"
                    + "            .header h1 {\n"
                    + "                font-size: 24px;\n"
                    + "                font-weight: 700;\n"
                    + "                color: #333;\n"
                    + "                margin: 10px 0;\n"
                    + "            }\n"
                    + "            .header p {\n"
                    + "                font-size: 16px;\n"
                    + "                color: #666;\n"
                    + "            }\n"
                    + "            .info-boxes {\n"
                    + "                display: flex;\n"
                    + "                justify-content: space-between;\n"
                    + "                margin-top: 20px;\n"
                    + "            }\n"
                    + "            .info-box {\n"
                    + "                background-color: #fff;\n"
                    + "                border-radius: 10px;\n"
                    + "                padding: 20px;\n"
                    + "                text-align: center;\n"
                    + "                flex: 1;\n"
                    + "                margin: 0 10px;\n"
                    + "                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n"
                    + "            }\n"
                    + "            .info-box i {\n"
                    + "                font-size: 24px;\n"
                    + "                color: #4caf50;\n"
                    + "                margin-bottom: 10px;\n"
                    + "            }\n"
                    + "            .content {\n"
                    + "                background-color: #fff;\n"
                    + "                border-radius: 10px;\n"
                    + "                padding: 20px;\n"
                    + "                margin-top: 20px;\n"
                    + "                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n"
                    + "            }\n"
                    + "            join-button {\n"
                    + "                display: inline-block;\n"
                    + "                background-color: #4caf50; /* Màu xanh lá cây */\n"
                    + "                color: white;\n"
                    + "                padding: 12px 24px;\n"
                    + "                text-decoration: none;\n"
                    + "                border-radius: 5px;\n"
                    + "                font-size: 16px;\n"
                    + "                font-weight: 500;\n"
                    + "                margin-top: 15px;\n"
                    + "                transition: background-color 0.3s;\n"
                    + "            }\n"
                    + "\n"
                    + "            .join-button:hover {\n"
                    + "                background-color: #45a049; /* Màu xanh đậm hơn khi hover */\n"
                    + "            }\n"
                    + "            @media (max-width: 768px) {\n"
                    + "                .info-boxes {\n"
                    + "                    flex-direction: column;\n"
                    + "                }\n"
                    + "                .info-box {\n"
                    + "                    margin: 10px 0;\n"
                    + "                }\n"
                    + "            }\n"
                    + "        </style>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <div class=\"container\">\n"
                    + "            <div class=\"header\">\n"
                    + "                <img alt=\"Promotional banner with a gift box and text '17 triệu đồng'\" height=\"400\" src=\"https://www.vpbank.com.vn/-/media/b386fbc8a3e24da0837e0649ba050cce.png\" width=\"800\"/>\n"
                    + "                <h1>TimiBank – Ngàn ưu đãi</h1>\n"
                    + "                <p>Mua bảo hiểm FWD nhận voucher mua vàng PNJ</p>\n"
                    + "                <a href=\"http://localhost:8080/timibank/index.jsp\" class=\"join-button\">Tham gia ngay - Nhận quà liền tay</a>\n"
                    + "            </div>\n"
                    + "            <div class=\"info-boxes\">\n"
                    + "                <div class=\"info-box\">\n"
                    + "                    <i class=\"fas fa-calendar-alt\"></i>\n"
                    + "                    <h3>Thời gian ưu đãi</h3>\n"
                    + "                    <p>14/01/2025-31/03/2025</p>\n"
                    + "                </div>\n"
                    + "                <div class=\"info-box\">\n"
                    + "                    <i class=\"fas fa-gift\"></i>\n"
                    + "                    <h3>Đối tượng áp dụng</h3>\n"
                    + "                    <p>Khách hàng cá nhân là bên mua bảo hiểm FWD tại Vietcombank và đáp ứng đồng thời các điều kiện</p>\n"
                    + "                </div>\n"
                    + "                <div class=\"info-box\">\n"
                    + "                    <i class=\"fas fa-map-marker-alt\"></i>\n"
                    + "                    <h3>Địa điểm áp dụng</h3>\n"
                    + "                    <p>Hệ thống TiMiBank phân phối sản phẩm bảo hiểm FWD Việt Nam trên toàn quốc.</p>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "            <div class=\"content\">\n"
                    + "                <h2>Chương trình ưu đãi</h2>\n"
                    + "                <p>TiMiBank Việt Nam trân trọng thông báo triển khai chương trình khuyến mại \"Xuân an khang – Ngàn ưu đãi\" dành cho khách hàng mua bảo hiểm FWD để nhận ngay voucher mua vàng PNJ trị giá lên đến 17 triệu đồng:</p>\n"
                    + "                <ul>\n"
                    + "                    <li><strong>1. Thời gian khuyến mại:</strong> Từ ngày 14/01/2025 đến hết 31/03/2025.</li>\n"
                    + "                    <li><strong>2. Đối tượng áp dụng:</strong> Khách hàng cá nhân là bên mua bảo hiểm FWD tại TiMiBank và đáp ứng đồng thời các điều kiện:\n"
                    + "                        <ul>\n"
                    + "                            <li>- Hợp đồng bảo hiểm (HĐBH) được phát hành trong thời gian khuyến mại;</li>\n"
                    + "                            <li>- HĐBH có IP phát hành tối thiểu từ 16 triệu đồng/HĐBH trở lên (Không áp dụng cho khu vực Hà Nội, Hồ Chí Minh); tối thiểu từ 25 triệu đồng/HĐBH trở lên (Áp dụng cho tất cả khu vực);</li>\n"
                    + "                        </ul>\n"
                    + "                    </li>\n"
                    + "                </ul>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "    </body>\n"
                    + "</html>\n"
                    + "";

            // Đặt tiêu đề với UTF-8
            message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));

            // Đặt nội dung email với UTF-8
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(emailContent, "text/html; charset=UTF-8");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("mail được gửi" + System.currentTimeMillis());

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }

}
