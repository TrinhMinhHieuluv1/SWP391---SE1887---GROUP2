/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.SavingsGoal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import microsoft.sql.Types;
/**
 *
 * @author tiend
 */
public class SavingsGoalsDAO extends DBContext{
    
    public SavingsGoal create(SavingsGoal goal){
        String sql = "INSERT INTO SavingsGoals (CustomerID,goal_name,target_amount, saved_amount, created_at, deadline) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;
        
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, goal.getUserId());
            pstmt.setString(2, goal.getGoalName());
            pstmt.setDouble(3, goal.getTargetAmount());
            pstmt.setDouble(4, goal.getSavedAmount());
            pstmt.setTimestamp(5,  new java.sql.Timestamp(goal.getCreatedDate().getTime()));
            
            if (goal.getDeadline() != null) {
                pstmt.setTimestamp(6,  new java.sql.Timestamp(goal.getDeadline().getTime()));
            } else {
                pstmt.setNull(6, Types.DATETIME);
            }
            
            
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new Exception("Creating savings goal failed, no rows affected.");
            }
            
           
            return goal;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error creating savings goal: " + e.getMessage());
            return null;
        } finally {
            closeResources(pstmt, generatedKeys);
        }
    }

  
    public SavingsGoal findById(int id) {
        String sql = "SELECT * FROM SavingsGoals WHERE goal_id = ?";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractSavingsGoalFromResultSet(rs);
            }
            
            return null;
        } catch (Exception e) {
            System.err.println("Error finding savings goal by ID: " + e.getMessage());
            return null;
        } finally {
            closeResources(pstmt, rs);
        }
    }

   
    public List<SavingsGoal> findAllByUser(int userId) {
        String sql = "SELECT * FROM SavingsGoals WHERE CustomerID = ? ORDER BY created_at DESC";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        List<SavingsGoal> goals = new ArrayList<>();
        
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userId);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                goals.add(extractSavingsGoalFromResultSet(rs));
            }
            
            return goals;
        } catch (Exception e) {
            System.err.println("Error finding all savings goals: " + e.getMessage());
            return goals;
        } finally {
            closeResources(pstmt, rs);
        }
    }


    public boolean update(SavingsGoal goal) {
        String sql = "UPDATE SavingsGoals SET goal_name = ?, target_amount = ?, saved_amount = ?, " +
                     "deadline = ? WHERE goal_id = ?";
        
        PreparedStatement pstmt = null;
        
        try {
            pstmt = connection.prepareStatement(sql);
            
            pstmt.setString(1, goal.getGoalName());
            pstmt.setDouble(2, goal.getTargetAmount());
            pstmt.setDouble(3, goal.getSavedAmount());
            
            if (goal.getDeadline() != null) {
                pstmt.setTimestamp(4, new java.sql.Timestamp(goal.getDeadline().getTime()));
            } else {
                pstmt.setNull(4, Types.DATETIME);
            }
            
            pstmt.setInt(5, goal.getId());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            System.err.println("Error updating savings goal: " + e.getMessage());
            return false;
        } finally {
            closeResources(pstmt, null);
        }
    }


    public boolean delete(int id) {
        String sql = "DELETE FROM SavingsGoals WHERE goal_id = ?";
        
        PreparedStatement pstmt = null;
        
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            System.err.println("Error deleting savings goal: " + e.getMessage());
            return false;
        } finally {
            closeResources(pstmt, null);
        }
    }


    public boolean updateSavedAmount(int goalId, double newAmount) {
        String sql = "UPDATE SavingsGoals SET saved_amount = ? WHERE goal_id = ?";
        
        PreparedStatement pstmt = null;
        
        try {
            pstmt = connection.prepareStatement(sql);
            
            pstmt.setDouble(1, newAmount);
            pstmt.setInt(2, goalId);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            System.err.println("Error updating saved amount: " + e.getMessage());
            return false;
        } finally {
            closeResources(pstmt, null);
        }
    }
    
    /**
     * Extract a SavingsGoal object from a ResultSet
     */
    private SavingsGoal extractSavingsGoalFromResultSet(ResultSet rs) throws Exception {
        SavingsGoal goal = new SavingsGoal();
        
        goal.setId(rs.getInt("goal_id"));
        goal.setGoalName(rs.getString("goal_name"));
        goal.setTargetAmount(rs.getDouble("target_amount"));
        goal.setSavedAmount(rs.getDouble("saved_amount"));
        goal.setCreatedDate(rs.getTimestamp("created_at"));
        
        java.sql.Timestamp deadline = rs.getTimestamp("deadline");
        if (deadline != null) {
            goal.setDeadline(deadline);
        }
        
        goal.setUserId(rs.getInt("CustomerID"));
        
        return goal;
    }
    
    /**
     * Close database resources
     */
    private void closeResources(PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.err.println("Error closing result set: " + e.getMessage());
            }
        }
        
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (Exception e) {
                System.err.println("Error closing prepared statement: " + e.getMessage());
            }
        }
    }
    
}
