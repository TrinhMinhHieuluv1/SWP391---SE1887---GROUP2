/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.FAQ;

/**
 *
 * @author
 */
public class FAQDAO extends DBContext {

  
    public List<FAQ> getFAQsByType1(String type) {
        List<FAQ> faqList = new ArrayList<>();
        String sql = "SELECT faqID, type, question, answer FROM FAQ WHERE type = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, type);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                FAQ faq = new FAQ(rs.getInt("faqID"), rs.getString("type"),
                                  rs.getString("question"), rs.getString("answer"));
                faqList.add(faq);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return faqList;
    }
    
    public List<FAQ> getFAQsByType(String type, int page, int pageSize) {
    List<FAQ> faqList = new ArrayList<>();
    String sql = "SELECT * FROM FAQ WHERE type = ? ORDER BY faqID "
            + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY"; // Lấy dữ liệu theo loại và phân trang

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, type); // Lọc theo loại
        st.setInt(2, (page - 1) * pageSize); // Tính toán OFFSET
        st.setInt(3, pageSize); // Số lượng bản ghi trên mỗi trang
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
    
    
    public int countFAQByType(String type) {
    int count = 0;
    String sql = "SELECT COUNT(*) AS count FROM FAQ WHERE type = ?";

    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, type);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            count = rs.getInt("count");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return count;
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

    public List<FAQ> searchFAQsByQuestion(String keyword, int page, int pageSize) {
        List<FAQ> faqList = new ArrayList<>();
        String sql = "SELECT * FROM FAQ WHERE question LIKE ? ORDER BY faqID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";// Tìm kiếm theo cột question

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + keyword + "%"); // Sử dụng LIKE để tìm kiếm phần tử chứa keyword
            st.setInt(2, (page - 1) * pageSize);
            st.setInt(3, pageSize);
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

    public List<FAQ> searchFAQsByQuestion1(String keyword) {
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

    public int getTotalFAQAfterSearching(String keyword) {
        String sql = """
         SELECT COUNT(*) FROM [dbo].[FAQ] 
         WHERE question LIKE ? 
           
    """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

   
      public List<String> getFAQType() {
        List<String> typeList = new ArrayList<>();
        String sql = "SELECT DISTINCT type FROM FAQ"; // Dùng DISTINCT để loại bỏ trùng

        try (
            PreparedStatement st = connection.prepareStatement(sql); 
            ResultSet rs = st.executeQuery()) 
        {

            while (rs.next()) {
                typeList.add(rs.getString("type"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeList;
    }
    
    

    public FAQ getFAQByID(int id) {
        FAQ faq = null;
        String sql = "SELECT * FROM FAQ WHERE faqID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id); // Thiết lập giá trị cho tham số id

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                faq = new FAQ();
                faq.setFaqID(rs.getInt("faqID"));
                faq.setType(rs.getString("type"));
                faq.setQuestion(rs.getString("question"));
                faq.setAnswer(rs.getString("answer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return faq;
    }

    // Add an FAQ
    public void addFAQ(FAQ faqToAdd) {
        String sql = "INSERT INTO [FAQ](type, question, answer) VALUES (?, ?, ?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, faqToAdd.getType());
            st.setString(2, faqToAdd.getQuestion());
            st.setString(3, faqToAdd.getAnswer());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean addFAQBoolean(FAQ faqToAdd) {
        String sql = "INSERT INTO [FAQ](type, question, answer) VALUES (?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, faqToAdd.getType());
            st.setString(2, faqToAdd.getQuestion());
            st.setString(3, faqToAdd.getAnswer());

            int rowsInserted = st.executeUpdate(); // Trả về số dòng bị ảnh hưởng
            return rowsInserted > 0; // Nếu > 0, nghĩa là thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Nếu có lỗi, trả về false
    }

    // Update an FAQ
    public void updateFAQ(FAQ faqToUpdate) {
        String sql = "UPDATE [FAQ] SET type = ?, question = ?, answer = ? WHERE faqID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, faqToUpdate.getType());
            st.setString(2, faqToUpdate.getQuestion());
            st.setString(3, faqToUpdate.getAnswer());
            st.setInt(4, faqToUpdate.getFaqID()); // Giả sử FAQ có một trường id để xác định
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Update an FAQ
    public boolean updateFAQBoolean(FAQ faqToUpdate) {
        String sql = "UPDATE [FAQ] SET type = ?, question = ?, answer = ? WHERE faqID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, faqToUpdate.getType());
            st.setString(2, faqToUpdate.getQuestion());
            st.setString(3, faqToUpdate.getAnswer());
            st.setInt(4, faqToUpdate.getFaqID());

            int rowsUpdated = st.executeUpdate(); // Trả về số dòng bị ảnh hưởng
            return rowsUpdated > 0; // Nếu > 0, nghĩa là update thành công
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật FAQ: " + e.getMessage());
        }
        return false;
    }



    public boolean isQuestionExists(String question) {
    String sql = "SELECT COUNT(*) FROM FAQ WHERE question = ?"; // Đếm số lượng bản ghi có cùng question

    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, question); // Truyền tham số question vào câu truy vấn
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1); // Lấy giá trị COUNT(*)
            return count > 0; // Trả về true nếu câu hỏi đã tồn tại
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Xử lý ngoại lệ nếu có lỗi
    }

    return false; // Trả về false nếu không có lỗi và không tìm thấy question
}
    
    
    public ArrayList<FAQ> getListFAQByPage(int page, int pageSize) {
        ArrayList<FAQ> listFAQ = new ArrayList<>();

        String sql = "select * from [FAQ] order by [faqID] offset ? rows fetch next ? rows only";
        // offset ? rows:    Bá»� qua má»™t sá»‘ dÃ²ng dá»±a trÃªn sá»‘ trang.
        // fetch next ? rows only:  Láº¥y tiáº¿p sá»‘ dÃ²ng tÆ°Æ¡ng á»©ng vá»›i pageSize.

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (page - 1) * pageSize);
            stmt.setInt(2, pageSize);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FAQ faq = new FAQ(rs.getInt("faqID"), rs.getString("type"), rs.getString("question"), rs.getString("answer"));
                listFAQ.add(faq);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listFAQ;
    }

}
