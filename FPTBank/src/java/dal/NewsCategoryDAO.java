package dal;

import java.util.List;
import model.NewsCategory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewsCategoryDAO extends DBContext{
    
    public List<NewsCategory> categoryList = selectAllNewsCategory();
    
    public List<NewsCategory> selectAllNewsCategory(){
        List<NewsCategory> newsCategoryList = new ArrayList<>();
        String sql = "SELECT * FROM NewsCategory";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                NewsCategory newsCategoryToAdd = new NewsCategory(rs.getInt("NewsCategoryID"), rs.getString("NewsCategoryName"));
                newsCategoryList.add(newsCategoryToAdd);
            }
        } catch (SQLException e) {
        }
        return newsCategoryList;
    }
    
    public NewsCategory selectANewsCategoryByID(int newsCategoryIDToSearch){
        for (NewsCategory newsCategory : categoryList) {
            if (newsCategory.getNewsCategoryID() == newsCategoryIDToSearch){
                return newsCategory;
            }
        }
        return null;
    }
}
