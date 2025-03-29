/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tiend
 */

import java.util.Date;

/**
 * Represents a savings goal in the personal finance application.
 */
public class SavingsGoal {
    private int id;
    private String goalName;
    private double targetAmount;
    private double savedAmount;
    private Date createdDate;
    private Date deadline;
    private int userId;

    /**
     * Default constructor
     */
    public SavingsGoal() {
        this.createdDate = new Date();
        this.savedAmount = 0.0;
    }

    /**
     * Constructor with basic required fields
     */
    public SavingsGoal(String goalName, double targetAmount, int userId) {
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.userId = userId;
        this.savedAmount = 0.0;
        this.createdDate = new Date();
    }

    /**
     * Constructor with all fields
     */
    public SavingsGoal(int id, String goalName, double targetAmount, double savedAmount, 
                       Date createdDate, Date deadline, int userId) {
        this.id = id;
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.savedAmount = savedAmount;
        this.createdDate = createdDate;
        this.deadline = deadline;
        this.userId = userId;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public double getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(double savedAmount) {
        this.savedAmount = savedAmount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Business logic methods

    /**
     * Calculate the percentage completion of the savings goal
     * @return the percentage (0-100) of progress made
     */
    public int getPercentageComplete() {
        if (targetAmount <= 0) {
            return 0;
        }
        
        double percentage = (savedAmount / targetAmount) * 100;
        return (int) Math.min(100, percentage);
    }

    /**
     * Check if the goal is complete (saved amount >= target amount)
     * @return true if the goal is complete, false otherwise
     */
    public boolean isComplete() {
        return savedAmount >= targetAmount;
    }

    /**
     * Calculate the remaining amount needed to complete the goal
     * @return the amount still needed to reach the target
     */
    public double getRemainingAmount() {
        return Math.max(0, targetAmount - savedAmount);
    }

    /**
     * Add to the saved amount
     * @param amount the amount to add to savings
     */
    public void addToSavings(double amount) {
        if (amount > 0) {
            this.savedAmount += amount;
        }
    }

    @Override
    public String toString() {
        return "SavingsGoal{" +
               "id=" + id +
               ", goalName='" + goalName + '\'' +
               ", targetAmount=" + targetAmount +
               ", savedAmount=" + savedAmount +
               ", percentComplete=" + getPercentageComplete() + "%" +
               ", createdDate=" + createdDate +
               ", deadline=" + deadline +
               '}';
    }
}