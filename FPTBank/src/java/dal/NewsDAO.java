package dal;

import java.util.List;
import model.News;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewsDAO extends DBContext {

    UserDAO udao = new UserDAO();
    NewsCategoryDAO ncDAO = new NewsCategoryDAO();

    //Select all news
    public List<News> selectAllNews() {
        List<News> newsList = new ArrayList<>();
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
                        rs.getInt("NumberOfAccess"),
                        ncDAO.selectANewsCategoryByID(rs.getInt("NewsCategoryID")));
                newsList.add(newsToAdd);
            }
        } catch (SQLException e) {
        }
        return newsList;
    }

    //Select a news by NewsID
    public News selectANewsByNewsID(int NewsID) {
        String sql = "SELECT * FROM [News] WHERE NewsID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, NewsID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                News news = new News(rs.getInt("NewsID"),
                        udao.selectAnUserByConditions(rs.getInt("UserID"), "", "", ""),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Image"),
                        rs.getBoolean("Status"),
                        rs.getDate("CreatedAt"),
                        rs.getInt("NumberOfAccess"),
                        ncDAO.selectANewsCategoryByID(rs.getInt("NewsCategoryID")));
                return news;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //Select news list by conditions (search, sort, filter)
    public List<News> selectNewsListByConditions(String searchKeyword, String sortBy, String filterStatus, String filterMine, int filterNewsCategoryID, int UserID) {
        List<News> newsList = new ArrayList<>();
        String sql = "SELECT n.*, u.FullName FROM [News] n JOIN [User] u ON (n.UserID = u.UserID) WHERE (1=1)";

        //Search by keyword match to author's fullname or news's title
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql = sql + " AND ((u.FullName LIKE N'%" + searchKeyword + "%') OR (n.Title LIKE N'%" + searchKeyword + "%'))";
        }

        //Filter by news's status
        if (filterStatus != null && !filterStatus.isEmpty()) {
            if (filterStatus.equals("active")) {
                sql = sql + " AND (n.[Status]=1)";
            } else if (filterStatus.equals("inactive")) {
                sql = sql + " AND (n.[Status]=0)";
            }
        }

        //Filter mine status
        if (filterMine != null && !filterMine.isEmpty()) {
            if (filterMine.equals("true")) {
                sql = sql + " AND (n.[UserID]=" + UserID + ")";
            }
        }

        if (filterNewsCategoryID != 0) {
            sql = sql + " AND (n.[NewsCategoryID]=" + filterNewsCategoryID + ")";
        }

        //Sort by CreatedAt or NumberOfAccess
        if (sortBy != null && !sortBy.isEmpty()) {
            switch (sortBy) {
                case "CreatedAtASC": {
                    sql = sql + " ORDER BY n.CreatedAt";
                    break;
                }
                case "CreatedAtDESC": {
                    sql = sql + " ORDER BY n.CreatedAt DESC";
                    break;
                }
                case "NumberOfAccessASC": {
                    sql = sql + " ORDER BY n.NumberOfAccess";
                    break;
                }
                case "NumberOfAccessDESC": {
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
                        rs.getInt("NumberOfAccess"),
                        ncDAO.selectANewsCategoryByID(rs.getInt("NewsCategoryID")));
                newsList.add(newsToAdd);
            }
        } catch (SQLException e) {
        }
        System.out.println(newsList.size());
        return newsList;
    }

    //Add a news
    public void addANews(News newsToAdd) {
        String sql = "INSERT INTO [News](UserID, Title, Description, Image, NewsCategoryID) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, newsToAdd.getUser().getUserID());
            st.setString(2, newsToAdd.getTitle());
            st.setString(3, newsToAdd.getDescription());
            st.setString(4, newsToAdd.getImage());
            st.setInt(5, newsToAdd.getNewsCategory().getNewsCategoryID());
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

    public List<News> selectNewsListByConditionsAndPageSize(String searchKeyword, String sortBy, String filterStatus, String filterMine, int filterNewsCategoryID, int UserID, int page, int size) {
        List<News> newsList = new ArrayList<>();
        String sql = "SELECT n.*, u.FullName FROM [News] n JOIN [User] u ON (n.UserID = u.UserID) WHERE (1=1)";

        //Search by keyword match to author's fullname or news's title
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql = sql + " AND ((u.FullName LIKE N'%" + searchKeyword + "%') OR (n.Title LIKE N'%" + searchKeyword + "%'))";
        }

        //Filter by news's status
        if (filterStatus != null && !filterStatus.isEmpty()) {
            if (filterStatus.equals("active")) {
                sql = sql + " AND (n.[Status]=1)";
            } else if (filterStatus.equals("inactive")) {
                sql = sql + " AND (n.[Status]=0)";
            }
        }

        //Filter mine status
        if (filterMine != null && !filterMine.isEmpty()) {
            if (filterMine.equals("true")) {
                sql = sql + " AND (n.[UserID]=" + UserID + ")";
            }
        }

        if (filterNewsCategoryID != 0) {
            sql = sql + " AND (n.[NewsCategoryID]=" + filterNewsCategoryID + ")";
        }

        //Sort by CreatedAt or NumberOfAccess
        if (sortBy != null && !sortBy.isEmpty()) {
            switch (sortBy) {
                case "CreatedAtASC": {
                    sql = sql + " ORDER BY n.CreatedAt";
                    break;
                }
                case "CreatedAtDESC": {
                    sql = sql + " ORDER BY n.CreatedAt DESC";
                    break;
                }
                case "NumberOfAccessASC": {
                    sql = sql + " ORDER BY n.NumberOfAccess";
                    break;
                }
                case "NumberOfAccessDESC": {
                    sql = sql + " ORDER BY n.NumberOfAccess DESC";
                    break;
                }
            }
        }
        int offset = (page - 1) * size;
        if (page > 0) {
            sql = sql + " OFFSET " + offset + " ROWS FETCH NEXT " + size + " ROWS ONLY";
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
                        rs.getInt("NumberOfAccess"),
                        ncDAO.selectANewsCategoryByID(rs.getInt("NewsCategoryID")));
                newsList.add(newsToAdd);
            }
        } catch (SQLException e) {
        }
        System.out.println(newsList.size());
        return newsList;
    }

    public int getTotalNewsCount() {
        String query = "SELECT COUNT(*) FROM [News]";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
