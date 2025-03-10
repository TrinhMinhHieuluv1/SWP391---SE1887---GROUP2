/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class ServiceDAO extends DBContext {

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

    public List<Service> selectALLService() {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM Service";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Service service = new Service(
                        rs.getInt("ServiceID"),
                        rs.getString("ServiceName"),
                        rs.getString("Description"),
                        rs.getBoolean("Status") // Lấy trạng thái của dịch vụ
                );
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }
}
