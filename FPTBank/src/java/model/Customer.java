/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author ACER
 */
public class Customer {
    private int customerId, creditScore;
    private BigDecimal balance;
    private User user;

    public Customer(int customerId, User user, int creditScore, BigDecimal Balance) {
        this.customerId = customerId;
        this.user = user;
        this.creditScore = creditScore;
        this.balance = Balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    
    public Customer() {
    }

    public int getCustomerid() {
        return customerId;
    }

    public void setCustomerid(int customerId) {
        this.customerId = customerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCreditscore() {
        return creditScore;
    }

    public void setCreditscore(int creditScore) {
        this.creditScore = creditScore;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal Balance) {
        this.balance = Balance;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerid=" + customerId +", creditscore=" + creditScore + ", balance=" + balance +"Use:,"+user.toString() +'}';
    }
    
}

