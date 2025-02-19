/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author ACER
 */
public class ServiceDAO extends DBContext{
    public Service getServiceByID(int serviceID) {
    String sql = "SELECT * FROM Service WHERE ServiceID = ?";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, serviceID);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new Service(
                rs.getInt("ServiceID"),
                rs.getString("ServiceName"),
                rs.getString("Description"),
                rs.getBoolean("Status") // Lấy trạng thái của dịch vụ
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
}
