package dal;

import java.util.List;
import model.News;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewsDAO extends DBContext {

    //Select all news
    public List<News> selectAllNews() {
        List<News> newsList = new ArrayList<>();
        UserDAO udao = new UserDAO();
        String sql = "SELECT * FROM [News]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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

    //Select a news by NewsID
    public News selectANewsByNewsID(int NewsID) {
        UserDAO udao = new UserDAO();
        String sql = "SELECT * FROM [News] WHERE NewsID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, NewsID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                News news= new News(rs.getInt("NewsID"),
                        udao.selectAnUserByConditions(rs.getInt("UserID"), "", "", ""),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Image"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"),
                        rs.getInt("NumberOfAccess"));
                return news;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    //Select news list by conditions (search, sort, filter)
    public List<News> selectNewsListByConditions(String searchKeyword, String sortBy, String filterStatus, String filterMine, int UserID) {
        List<News> newsList = new ArrayList<>();
        UserDAO udao = new UserDAO();
        String sql = "SELECT n.*, u.FullName FROM [News] n JOIN [User] u ON (n.UserID = u.UserID) WHERE (1=1)";
        
        //Search by keyword match to author's fullname or news's title
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql = sql + " AND ((FullName LIKE N'%" + searchKeyword + "%') OR (n.Title LIKE N'%" + searchKeyword + "%'))";
        }
        
        //Filter by news's status
        if (filterStatus != null && !filterStatus.isEmpty()) {
            if (filterStatus.equals("active")) {
                sql = sql + " AND (n.[Status]=1)";
            } else if (filterStatus.equals("inactive")){
                sql = sql + " AND (n.[Status]=0)";
            }
        }
        
        //Filter mine status
        if (filterMine != null && !filterMine.isEmpty()) {
            if (filterMine.equals("true")) {
                sql = sql + " AND (n.UserID=" + UserID + ")";
            }
        }
            
        //Sort by CreatedAt or NumberOfAccess
        if (sortBy != null && !sortBy.isEmpty()) {
            switch (sortBy) {
                case "CreatedAtASC":{
                    sql = sql + " ORDER BY n.CreatedAt";
                    break;
                }
                case "CreatedAtDESC":{
                    sql = sql + " ORDER BY n.CreatedAt DESC";
                    break;
                }
                case "NumberOfAccessASC":{
                    sql = sql + " ORDER BY n.NumberOfAccess";
                    break;
                }
                case "NumberOfAccessDESC":{
                    sql = sql + " ORDER BY n.NumberOfAccess DESC";
                    break;
                }
            }
        }
        System.out.println(sql);
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
 
    //Add a news
    public void addANews(News newsToAdd) {
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

    //Update a news
    public void updateANews(News newsToUpdate) {
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
