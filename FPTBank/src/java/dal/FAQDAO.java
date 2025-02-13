/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.FAQ;

/**
 *
 * @author 
 */
public class FAQDAO extends DBContext {

    public List<FAQ> getFAQsByType(String type) {
        List<FAQ> faqList = new ArrayList<>();
        String sql = "SELECT * FROM FAQ WHERE type = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, type);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int faqID = rs.getInt("faqID");
                String faqType = rs.getString("type");
                String question = rs.getString("question");
                String answer = rs.getString("answer");

                FAQ faq = new FAQ(faqID, faqType, question, answer);
                faqList.add(faq);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return faqList;
    }
    public List<FAQ> getAllFAQs() {
    List<FAQ> faqList = new ArrayList<>();
    String sql = "SELECT * FROM FAQ"; // Lấy tất cả thông tin từ bảng FAQ

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            int faqID = rs.getInt("faqID");
            String faqType = rs.getString("type");
            String question = rs.getString("question");
            String answer = rs.getString("answer");

            FAQ faq = new FAQ(faqID, faqType, question, answer);
            faqList.add(faq);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return faqList;
}
    
    public List<FAQ> searchFAQsByQuestion(String keyword) {
    List<FAQ> faqList = new ArrayList<>();
    String sql = "SELECT * FROM FAQ WHERE question LIKE ?"; // Tìm kiếm theo cột question

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, "%" + keyword + "%"); // Sử dụng LIKE để tìm kiếm phần tử chứa keyword
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            int faqID = rs.getInt("faqID");
            String faqType = rs.getString("type");
            String question = rs.getString("question");
            String answer = rs.getString("answer");

            FAQ faq = new FAQ(faqID, faqType, question, answer);
            faqList.add(faq);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return faqList;
}
    
    
    public static void main(String[] args) {
        FAQDAO faqDAO = new FAQDAO();
        List<FAQ> bankFAQs = faqDAO.getFAQsByType("bank");

        for (FAQ faq : bankFAQs) {
            System.out.println("Câu hỏi: " + faq.getQuestion());
            System.out.println("Trả lời: " + faq.getAnswer());
            System.out.println("---------------------------");
        }
    }

}
