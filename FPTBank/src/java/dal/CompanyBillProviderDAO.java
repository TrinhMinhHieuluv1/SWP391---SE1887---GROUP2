/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.CompanyBillProvider;
import model.User;
/**
 *
 * @author ACER
 */
public class CompanyBillProviderDAO extends DBContext{
    public CompanyBillProvider getCompanyById(String x,int companyID) {
        String sql = "SELECT * FROM CompanyBillProvider WHERE "+x+"  = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, companyID);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                UserDAO dao = new UserDAO();
                User a = dao.selectAnUserByConditions(rs.getInt("ProviderID"), "", "", "");
                return new CompanyBillProvider(
                    rs.getInt("CompanyID"),
                    rs.getString("CompanyName"),
                    a
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    public static void main(String[] args) {
        CompanyBillProviderDAO dao = new CompanyBillProviderDAO();
        CompanyBillProvider company = dao.getCompanyById("ProviderID", 9);
        System.out.println(company);
    }
}
