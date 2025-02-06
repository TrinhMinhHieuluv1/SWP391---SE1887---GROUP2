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

    private int CustomerId, CreditScore;
    private BigDecimal Balance;
    private User User;

    public Customer(int customerId, User user, int creditScore, BigDecimal Balance) {
        this.CustomerId = customerId;
        this.User = user;
        this.CreditScore = creditScore;
        this.Balance = Balance;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public int getCreditScore() {
        return CreditScore;
    }

    public void setCreditScore(int creditScore) {
        this.CreditScore = creditScore;
    }

    public void setCustomerId(int customerId) {
        this.CustomerId = customerId;

    }
    
    
    public Customer() {
    }

    public int getCustomerid() {
        return CustomerId;
    }

    public void setCustomerid(int customerId) {
        this.CustomerId = customerId;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        this.User = user;
    }

    public int getCreditscore() {
        return CreditScore;
    }

    public void setCreditscore(int creditScore) {
        this.CreditScore = creditScore;
    }

    public BigDecimal getBalance() {
        return Balance;
    }

    public void setBalance(BigDecimal Balance) {
        this.Balance = Balance;

    }

    @Override
    public String toString() {

        return "Customer{" + "customerid=" + CustomerId +", creditscore=" + CreditScore + ", balance=" + Balance +"Use:,"+User.toString() +'}';

    }
    
}

