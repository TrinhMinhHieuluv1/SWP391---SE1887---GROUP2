
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBContext {
    protected Connection connection;
    public DBContext()
    {
        try {
            // Edit URL , username, password to authenticate with your MS SQL Server
<<<<<<< HEAD
            String url = "jdbc:sqlserver://localhost:1433;databaseName=TimiBank";
=======
            String url = "jdbc:sqlserver://localhost:1433;databaseName=fix3";
>>>>>>> 62a24bf698a52faed55d68b8c1589fac04fd037e
            String username = "sa";
            String password = "oanh23062004";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
//            if (connection != null) {
//                System.out.println("Kết nối thành công!");
//            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        
    }
//    public void closeConnection() {
//        try {
//            if (connection != null && !connection.isClosed()) {
//                connection.close();
//                System.out.println("Kết nối đã đóng.");
//            }
//        } catch (SQLException ex) {
//            System.out.println("Lỗi khi đóng kết nối: " + ex.getMessage());
//        }
//    }
//
//    public static void main(String[] args) {
//        DBContext dbContext = new DBContext();
//        dbContext.closeConnection();
//    }
// 
}
