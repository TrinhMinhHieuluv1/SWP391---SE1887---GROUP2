/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Insurance;

public class InsuranceDAO extends DBContext {

    public List<Insurance> getAllInsurance() {
        List<Insurance> insuranceList = new ArrayList<>();
        String sql = "SELECT * FROM Insurance";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Insurance insurance = new Insurance(
                        rs.getInt("InsuranceID"),
                        rs.getInt("ProviderID"),
                        rs.getString("InsuranceName"),
                        rs.getString("Type"),
                        rs.getFloat("FeeRate"),
                        rs.getFloat("CoverageRate"),
                        rs.getDouble("MaxAmountOfLoan"),
                        rs.getBoolean("Status")
                );
                insuranceList.add(insurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceList;
    }

    public Insurance getInsuranceByID(int insuranceID) {
        String sql = "SELECT * FROM Insurance WHERE InsuranceID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, insuranceID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Insurance(
                        rs.getInt("InsuranceID"),
                        rs.getInt("ProviderID"),
                        rs.getString("InsuranceName"),
                        rs.getString("Type"),
                        rs.getFloat("FeeRate"),
                        rs.getFloat("CoverageRate"),
                        rs.getDouble("MaxAmountOfLoan"),
                        rs.getBoolean("Status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addInsurance(Insurance insurance) {
        String sql = "INSERT INTO Insurance (ProviderID, Type, FeeRate, CoverageRate, MaxAmountOfLoan, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, insurance.getProviderID());
            st.setString(2, insurance.getType());
            st.setFloat(3, insurance.getFeeRate());
            st.setFloat(4, insurance.getCoverageRate());
            st.setDouble(5, insurance.getMaxAmountOfLoan());
            st.setBoolean(6, insurance.isStatus());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateInsurance(Insurance insurance) {
        String sql = "UPDATE Insurance SET ProviderID=?, Type=?, FeeRate=?, CoverageRate=?, MaxAmountOfLoan=?, Status=? WHERE InsuranceID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, insurance.getProviderID());
            st.setString(2, insurance.getType());
            st.setFloat(3, insurance.getFeeRate());
            st.setFloat(4, insurance.getCoverageRate());
            st.setDouble(5, insurance.getMaxAmountOfLoan());
            st.setBoolean(6, insurance.isStatus());
            st.setInt(7, insurance.getInsuranceID());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteInsurance(int insuranceID) {
        String sql = "DELETE FROM Insurance WHERE InsuranceID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, insuranceID);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
