/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author tiend
 */
public class Email {
       public static final String from="tiendung18112k4@gmail.com";
       public static final String pass ="attp nvqi fkvf oudn";
       
       public  boolean sendMess(String from,String to,String subject,String content){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");// smtp host
        props.put("mail.smtp.port", "587");// TLS 587,SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        
        
        Authenticator  authen =  new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass); 
            }
        };
               Session session = Session.getInstance(props,authen);

        MimeMessage msg = new MimeMessage(session);
         try {       // kieu noi dung
                     msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                     // nguoi gui
                     msg.setFrom(from);
                     // nguoi nhan
                     msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
                     // tieu de
                     msg.setSubject(subject);
                     // quy dinh ngay gui
                     msg.setSentDate(new Date());
                     // quy dinh email nhan phan hoi khi ma nguoi nhan nhan nut Reply   msg.setReplyTo(InternetAddress.parse(from,false));
                     msg.setContent(content,"text/HTML; charset=UTF-8");
                     return true;
         }catch (Exception e) {
             System.out.println("Gap loi trong van qua tring gui email");
            e.printStackTrace();
            return false;
        }
         
         
         
       }
       
      
    
}
