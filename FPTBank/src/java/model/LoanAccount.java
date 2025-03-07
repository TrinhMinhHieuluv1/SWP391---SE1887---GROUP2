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
public class LoanAccount {
    private int id,loan_term;
    private String name,email,repayment_method;
    private double  loan_amount,  interest_rate;
    private Timestamp disbursement_date; 
    private double total_interest,total_amount_to_pay;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LoanAccount(int id, int loan_term, String name, String email, String repayment_method, double loan_amount, double interest_rate, Timestamp disbursement_date, double total_interest, double total_amount_to_pay, boolean status) {
        this.id = id;
        this.loan_term = loan_term;
        this.name = name;
        this.email = email;
        this.repayment_method = repayment_method;
        this.loan_amount = loan_amount;
        this.interest_rate = interest_rate;
        this.disbursement_date = disbursement_date;
        this.total_interest = total_interest;
        this.total_amount_to_pay = total_amount_to_pay;
        this.status = status;
    }

    public LoanAccount() {
    }

   

    public LoanAccount(int loan_term, String name, String email, String repayment_method, double loan_amount, double interest_rate, Timestamp disbursement_date, double total_interest, double total_amount_to_pay) {
        this.loan_term = loan_term;
        this.name = name;
        this.email = email;
        this.repayment_method = repayment_method;
        this.loan_amount = loan_amount;
        this.interest_rate = interest_rate;
        this.disbursement_date = disbursement_date;
        this.total_interest = total_interest;
        this.total_amount_to_pay = total_amount_to_pay;
    }

  
    
    
    
    

    public double getTotal_interest() {
        return total_interest;
    }

    public void setTotal_interest(double total_interest) {
        this.total_interest = total_interest;
    }

    public double getTotal_amount_to_pay() {
        return total_amount_to_pay;
    }

    public void setTotal_amount_to_pay(double total_amount_to_pay) {
        this.total_amount_to_pay = total_amount_to_pay;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoan_term() {
        return loan_term;
    }

    public void setLoan_term(int loan_term) {
        this.loan_term = loan_term;
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

    public String getRepayment_method() {
        return repayment_method;
    }

    public void setRepayment_method(String repayment_method) {
        this.repayment_method = repayment_method;
    }

    public double getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(double loan_amount) {
        this.loan_amount = loan_amount;
    }

    public double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(double interest_rate) {
        this.interest_rate = interest_rate;
    }

    public Timestamp getDisbursement_date() {
        return disbursement_date;
    }

    public void setDisbursement_date(Timestamp disbursement_date) {
        this.disbursement_date = disbursement_date;
    }

    @Override
    public String toString() {
        return "LoanAccount{" + "id=" + id + ", loan_term=" + loan_term + ", name=" + name + ", email=" + email + ", repayment_method=" + repayment_method + ", loan_amount=" + loan_amount + ", interest_rate=" + interest_rate + ", disbursement_date=" + disbursement_date + ", total_interest=" + total_interest + ", total_amount_to_pay=" + total_amount_to_pay + ", status=" + status + '}';
    }

  

}
