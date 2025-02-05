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
    private User user;

    public Customer(int CustomerId, int CreditScore, BigDecimal Balance, int userID) {
        this.CustomerId = CustomerId;
        this.CreditScore = CreditScore;
        this.Balance = Balance;
        this.user.setUserID(userID);
    }

    public Customer(int CustomerId, User user, int CreditScore, BigDecimal Balance) {
        this.CustomerId = CustomerId;
        this.CreditScore = CreditScore;
        this.Balance = Balance;
        this.user = user;
    }
    
    public Customer() {
    }

    public int getCustomerid() {
        return CustomerId;
    }

    public void setCustomerid(int CustomerId) {
        this.CustomerId = CustomerId;
    }

    public int getUserid() {
        return user.getUserID();
    }

    public void setUserid(int userID) {
        this.user.setUserID(userID);
    }

    public int getCreditscore() {
        return CreditScore;
    }

    public void setCreditscore(int CreditScore) {
        this.CreditScore = CreditScore;
    }

    public BigDecimal getBalance() {
        return Balance;
    }

    public void setBalance(BigDecimal Balance) {
        this.Balance = Balance;
    }

    @Override
    public String toString() {
        return "Customer{" + "CustomerId=" + CustomerId + ", CreditScore=" + CreditScore + ", Balance=" + Balance + ", user=" + user + '}';
    }

    
    
}
