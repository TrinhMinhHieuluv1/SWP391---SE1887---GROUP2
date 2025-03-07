/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class SavingsAccount {
    private int id,term;
    private String name,email,termUnit;
    private double  deposit_amount,interest_rate,interest_per_term,total_amount_at_maturity;
    private Timestamp createDate;
    private boolean status;

    public SavingsAccount(int term, String name, String email, String termUnit, double deposit_amount, double interest_rate, double interest_per_term, double total_amount_at_maturity, Timestamp createDate, boolean status) {
        this.term = term;
        this.name = name;
        this.email = email;
        this.termUnit = termUnit;
        this.deposit_amount = deposit_amount;
        this.interest_rate = interest_rate;
        this.interest_per_term = interest_per_term;
        this.total_amount_at_maturity = total_amount_at_maturity;
        this.createDate = createDate;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public SavingsAccount(int id, int term, String name, String email, String termUnit, double deposit_amount, double interest_rate, double interest_per_term, double total_amount_at_maturity, Timestamp createDate, boolean status) {
        this.id = id;
        this.term = term;
        this.name = name;
        this.email = email;
        this.termUnit = termUnit;
        this.deposit_amount = deposit_amount;
        this.interest_rate = interest_rate;
        this.interest_per_term = interest_per_term;
        this.total_amount_at_maturity = total_amount_at_maturity;
        this.createDate = createDate;
        this.status = status;
    }

   
    
    
    public SavingsAccount() {
    }
    
    
    public SavingsAccount(int term, String name, String email, String termUnit, double deposit_amount, double interest_rate, double interest_per_term, double total_amount_at_maturity, Timestamp createDate) {
        this.term = term;
        this.name = name;
        this.email = email;
        this.termUnit = termUnit;
        this.deposit_amount = deposit_amount;
        this.interest_rate = interest_rate;
        this.interest_per_term = interest_per_term;
        this.total_amount_at_maturity = total_amount_at_maturity;
        this.createDate = createDate;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    



    

    public double getInterest_per_term() {
        return interest_per_term;
    }

    public void setInterest_per_term(double interest_per_term) {
        this.interest_per_term = interest_per_term;
    }

    public double getTotal_amount_at_maturity() {
        return total_amount_at_maturity;
    }

    public void setTotal_amount_at_maturity(double total_amount_at_maturity) {
        this.total_amount_at_maturity = total_amount_at_maturity;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTermUnit() {
        return termUnit;
    }

    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit;
    }

    public double getDeposit_amount() {
        return deposit_amount;
    }

    public void setDeposit_amount(double deposit_amount) {
        this.deposit_amount = deposit_amount;
    }

    public double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(double interest_rate) {
        this.interest_rate = interest_rate;
    }

    @Override
    public String toString() {
        return "SavingsAccount{" + "id=" + id + ", term=" + term + ", name=" + name + ", email=" + email + ", termUnit=" + termUnit + ", deposit_amount=" + deposit_amount + ", interest_rate=" + interest_rate + ", interest_per_term=" + interest_per_term + ", total_amount_at_maturity=" + total_amount_at_maturity + ", createDate=" + createDate + ", status=" + status + '}';
    }
   
}
