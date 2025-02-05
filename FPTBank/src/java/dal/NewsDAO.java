package dal;

import java.util.List;
import model.News;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.User;

public class NewsDAO extends DBContext{
    
    public List<News> selectAllNews(){
        List<News> newsList = new ArrayList<>();
        UserDAO udao = new UserDAO();
        String sql = "SELECT * FROM [News]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                News newsToAdd = new News(rs.getInt("NewsID"), 
                                        udao.selectAnUserByConditions(rs.getInt("UserID"), "", "", ""), 
                                        rs.getString("Title"), 
                                        rs.getString("Description"), 
                                        rs.getString("Image"), 
                                        rs.getBoolean("Status"),
                                        rs.getDate("CreatedAt"), 
                                        rs.getInt("NumberOfAccess"));
                newsList.add(newsToAdd);
            }
        } catch (SQLException e) {
        }
        return newsList;
    }
    
    public List<News> selectANewsByConditions(int NewsID, int UserID){
        List<News> newsList = new ArrayList<>();
        UserDAO udao = new UserDAO();
        String sql = "SELECT * FROM [News] WHERE 1=1";
        if (NewsID != 0) {
            sql = sql + " AND NewsID=" + NewsID;
        }
        if (UserID != 0) {
            sql = sql + " AND UserID=" + UserID;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                News newsToAdd = new News(rs.getInt("NewsID"), 
                                        udao.selectAnUserByConditions(rs.getInt("UserID"), "", "", ""), 
                                        rs.getString("Title"), 
                                        rs.getString("Description"), 
                                        rs.getString("Image"), 
                                        rs.getBoolean("Status"),
                                        rs.getDate("CreatedAt"), 
                                        rs.getInt("NumberOfAccess"));
                newsList.add(newsToAdd);
            }
        } catch (SQLException e) {
        }
        return newsList;
    }
    
    public void addANews(News newsToAdd){
        String sql = "INSERT INTO [News](UserID, Title, Description, Image) "
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, newsToAdd.getUser().getUserID());
            st.setString(2, newsToAdd.getTitle());
            st.setString(3, newsToAdd.getDescription());
            st.setString(4, newsToAdd.getImage());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void updateANews(News newsToUpdate){
        String sql = "UPDATE [News] SET Title=?, Description=?, Image=?, Status=?, NumberOfAccess=? WHERE NewsID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newsToUpdate.getTitle());
            st.setString(2, newsToUpdate.getDescription());
            st.setString(3, newsToUpdate.getImage());
            st.setBoolean(4, newsToUpdate.isStatus());
            st.setInt(5, newsToUpdate.getNumberOfAccess());
            st.setInt(6, newsToUpdate.getNewsID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
