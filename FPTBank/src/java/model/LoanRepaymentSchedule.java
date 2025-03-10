/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class LoanRepaymentSchedule {
    private int id;
    private LoanAccount loan_id;
    private int month;
    private Date payment_date;
    private double remaining_principal,principal_amount,interest_amount,total_payment ;

    public LoanRepaymentSchedule() {
    }

    public LoanRepaymentSchedule(int id, LoanAccount loan_id, int month, Date payment_date, double remaining_principal, double principal_amount, double interest_amount, double total_payment) {
        this.id = id;
        this.loan_id = loan_id;
        this.month = month;
        this.payment_date = payment_date;
        this.remaining_principal = remaining_principal;
        this.principal_amount = principal_amount;
        this.interest_amount = interest_amount;
        this.total_payment = total_payment;
    }

    public LoanRepaymentSchedule(LoanAccount loan_id, int month, Date payment_date, double remaining_principal, double principal_amount, double interest_amount, double total_payment) {
        this.loan_id = loan_id;
        this.month = month;
        this.payment_date = payment_date;
        this.remaining_principal = remaining_principal;
        this.principal_amount = principal_amount;
        this.interest_amount = interest_amount;
        this.total_payment = total_payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LoanAccount getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(LoanAccount loan_id) {
        this.loan_id = loan_id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public double getRemaining_principal() {
        return remaining_principal;
    }

    public void setRemaining_principal(double remaining_principal) {
        this.remaining_principal = remaining_principal;
    }

    public double getPrincipal_amount() {
        return principal_amount;
    }

    public void setPrincipal_amount(double principal_amount) {
        this.principal_amount = principal_amount;
    }

    public double getInterest_amount() {
        return interest_amount;
    }

    public void setInterest_amount(double interest_amount) {
        this.interest_amount = interest_amount;
    }

    public double getTotal_payment() {
        return total_payment;
    }

    public void setTotal_payment(double total_payment) {
        this.total_payment = total_payment;
    }

    @Override
    public String toString() {
        return "LoanRepaymentSchedule{" + "id=" + id + ", loan_id=" + loan_id + ", month=" + month + ", payment_date=" + payment_date + ", remaining_principal=" + remaining_principal + ", principal_amount=" + principal_amount + ", interest_amount=" + interest_amount + ", total_payment=" + total_payment + '}';
    }
}
