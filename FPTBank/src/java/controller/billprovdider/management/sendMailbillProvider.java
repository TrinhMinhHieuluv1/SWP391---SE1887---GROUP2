/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.billprovdider.management;

/**
 *
 * @author ACER
 */
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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;
import model.Customer;

public class sendMailbillProvider {

    public static boolean guiMailforPaying(String email, int billID,  String tiltle, String description, Date startdate, Date enddate, Date createAt, BigDecimal total, String CompanyName, Customer customer, Timestamp PaymentDate ) throws UnsupportedEncodingException {
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
            
           
            String subject = "Thank you for paying one bill from " + CompanyName;
            String emailContent = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "    <head>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <title>Invoice</title>\n"
                    + "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\n"
                    + "        <style>\n"
                    + "            body {\n"
                    + "                background-color: #e9f5e9;\n"
                    + "                font-family: Arial, sans-serif;\n"
                    + "            }\n"
                    + "            .invoice-box {\n"
                    + "                max-width: 800px;\n"
                    + "                margin: auto;\n"
                    + "                padding: 20px;\n"
                    + "                border: 1px solid #52b788;\n"
                    + "                box-shadow: 0 0 10px rgba(0, 128, 0, 0.15);\n"
                    + "                background-color: #ffffff;\n"
                    + "                border-radius: 10px;\n"
                    + "            }\n"
                    + "            .invoice-header {\n"
                    + "                display: flex;\n"
                    + "                justify-content: space-between;\n"
                    + "                align-items: center;\n"
                    + "            }\n"
                    + "            .invoice-header h2 {\n"
                    + "                color: #2d6a4f;\n"
                    + "            }\n"
                    + "            .table th {\n"
                    + "                background-color: #52b788;\n"
                    + "                color: white;\n"
                    + "            }\n"
                    + "            .total-section {\n"
                    + "                text-align: right;\n"
                    + "                font-weight: bold;\n"
                    + "            }\n"
                    + "            .total-section .total {\n"
                    + "                background-color: #40916c;\n"
                    + "                color: white;\n"
                    + "                padding: 10px;\n"
                    + "                border-radius: 5px;\n"
                    + "            }\n"
                    + "        </style>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <div class=\"invoice-box\">\n"
                    + "            <form action=\"billdetail\" method=\"get\">\n"
                    + "                <div class=\"invoice-header\">\n"
                    + "                    <img src=\"img/logo1.png\" alt=\"Plax\" width=\"200\">\n"
                    + "                    <div>\n"
                    + "                        <p><strong>Date:</strong>"+ createAt +"</p>\n"
                    + "                        <p><strong>Invoice #:</strong> " +billID +"</p>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "                <hr>\n"
                    + "                <div>\n"
                    + "                    <h4>"+customer.getFullName()+"</h4>\n"
                    + "                    <p>Number: "+customer.getPhone()+"<br>"+ customer.getAddress() +"<br>"+customer.getEmail()+"</p>\n"
                    + "                </div>\n"
                    + "                <table class=\"table table-bordered\">\n"
                    + "                    <thead>\n"
                    + "                        <tr>\n"
                    + "                            <th>#</th>\n"
                    + "                            <th>Title</th>\n"
                    + "                            <th>Description</th>\n"
                    + "                            <th>StartDate</th>\n"
                    + "                            <th>EndDate</th>\n"
                    + "                            <th>StatusOfBill</th>\n"
                    + "                            <th>PaymentDate</th>\n"
                    + "                        </tr>\n"
                    + "                    </thead>\n"
                    + "                    <tbody>\n"
                    + "                        <tr>\n"
                    + "                            <td>1</td>\n"
                    + "                            <td>"+ tiltle +"</td>\n"
                    + "                            <td>"+ description +"</td>\n"
                    + "                            <td>"+ startdate +"</td>\n"
                    + "                            <td>"+enddate+"</td>\n"
                    + "                            <td>Paid</td>\n"
                    + "                            <td>"+PaymentDate+"</td>\n"
                    + "                        </tr>\n"
                    + "                        \n"
                    + "                    </tbody>\n"
                    + "                </table>\n"
                    + "                <div class=\"total-section\">\n"
                    + "                    <p class=\"total\">Total: "+ total +"</p>\n"
                    + "                </div>\n"
                    + "            </form>\n"
                    + "        </div>\n"
                    + "    </body>\n"
                    + "</html>";

            // Đặt tiêu đề với UTF-8
            message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));

            // Đặt nội dung email với UTF-8
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(emailContent, "text/html; charset=UTF-8");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("mail is send" + System.currentTimeMillis());

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }
     public static boolean guiMailforCreatingBill(String email, int billID,  String tiltle, String description, Date startdate, Date enddate, Date createAt, BigDecimal total, String CompanyName, Customer customer) throws UnsupportedEncodingException {
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
            
           
            String subject = "You are recieve a bill which is need to pay from " + CompanyName;
            String emailContent = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "    <head>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <title>Invoice</title>\n"
                    + "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\n"
                    + "        <style>\n"
                    + "            body {\n"
                    + "                background-color: #e9f5e9;\n"
                    + "                font-family: Arial, sans-serif;\n"
                    + "            }\n"
                    + "            .invoice-box {\n"
                    + "                max-width: 800px;\n"
                    + "                margin: auto;\n"
                    + "                padding: 20px;\n"
                    + "                border: 1px solid #52b788;\n"
                    + "                box-shadow: 0 0 10px rgba(0, 128, 0, 0.15);\n"
                    + "                background-color: #ffffff;\n"
                    + "                border-radius: 10px;\n"
                    + "            }\n"
                    + "            .invoice-header {\n"
                    + "                display: flex;\n"
                    + "                justify-content: space-between;\n"
                    + "                align-items: center;\n"
                    + "            }\n"
                    + "            .invoice-header h2 {\n"
                    + "                color: #2d6a4f;\n"
                    + "            }\n"
                    + "            .table th {\n"
                    + "                background-color: #52b788;\n"
                    + "                color: white;\n"
                    + "            }\n"
                    + "            .total-section {\n"
                    + "                text-align: right;\n"
                    + "                font-weight: bold;\n"
                    + "            }\n"
                    + "            .total-section .total {\n"
                    + "                background-color: #40916c;\n"
                    + "                color: white;\n"
                    + "                padding: 10px;\n"
                    + "                border-radius: 5px;\n"
                    + "            }\n"
                    + "        </style>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <div class=\"invoice-box\">\n"
                    + "            <form action=\"billdetail\" method=\"get\">\n"
                    + "                <div class=\"invoice-header\">\n"
                    + "                    <img src=\"img/logo1.png\" alt=\"Plax\" width=\"200\">\n"
                    + "                    <div>\n"
                    + "                        <p><strong>Date:</strong>"+ createAt +"</p>\n"
                    + "                        <p><strong>Invoice #:</strong> " +billID +"</p>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "                <hr>\n"
                    + "                <div>\n"
                    + "                    <h4>"+customer.getFullName()+"</h4>\n"
                    + "                    <p>Number: "+customer.getPhone()+"<br>"+ customer.getAddress() +"<br>"+customer.getEmail()+"</p>\n"
                    + "                </div>\n"
                    + "                <table class=\"table table-bordered\">\n"
                    + "                    <thead>\n"
                    + "                        <tr>\n"
                    + "                            <th>#</th>\n"
                    + "                            <th>Title</th>\n"
                    + "                            <th>Description</th>\n"
                    + "                            <th>StartDate</th>\n"
                    + "                            <th>EndDate</th>\n"
                    + "                            <th>StatusOfBill</th>\n"
                    + "                        </tr>\n"
                    + "                    </thead>\n"
                    + "                    <tbody>\n"
                    + "                        <tr>\n"
                    + "                            <td>1</td>\n"
                    + "                            <td>"+ tiltle +"</td>\n"
                    + "                            <td>"+ description +"</td>\n"
                    + "                            <td>"+ startdate +"</td>\n"
                    + "                            <td>"+enddate+"</td>\n"
                    + "                            <td>UnPaid</td>\n"
                    + "                        </tr>\n"
                    + "                        \n"
                    + "                    </tbody>\n"
                    + "                </table>\n"
                    + "                <div class=\"total-section\">\n"
                    + "                    <p class=\"total\">Total: "+ total +"</p>\n"
                    + "                </div>\n"
                    + "            </form>\n"
                    + "        </div>\n"
                    + "    </body>\n"
                    + "</html>";

            // Đặt tiêu đề với UTF-8
            message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));

            // Đặt nội dung email với UTF-8
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(emailContent, "text/html; charset=UTF-8");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("mail is send" + System.currentTimeMillis());

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }
     public static boolean guiMailforPaying(String email, String a) throws UnsupportedEncodingException {
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
            
           
            String subject = "Your OTP :" + a;
            
            message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));

            // Đặt nội dung email với UTF-8
            MimeMultipart multipart = new MimeMultipart();
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("mail is send" + System.currentTimeMillis());

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }
}
