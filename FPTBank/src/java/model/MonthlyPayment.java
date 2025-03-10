/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class MonthlyPayment {
    private int month;
    private String remainingPrincipal;
    private String principal;
    private String interest;
    private String totalPayment;
    private String paymentDate; // Ngày thanh toán

    public MonthlyPayment() {
    }

    public MonthlyPayment(int month, String remainingPrincipal, String principal, String interest, String totalPayment, String paymentDate) {
        this.month = month;
        this.remainingPrincipal = remainingPrincipal;
        this.principal = principal;
        this.interest = interest;
        this.totalPayment = totalPayment;
        this.paymentDate = paymentDate;
    }

    // Getter methods
    public int getMonth() {
        return month;
    }

    public String getRemainingPrincipal() {
        return remainingPrincipal;
    }

    public String getPrincipal() {
        return principal;
    }

    public String getInterest() {
        return interest;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {
        return "MonthlyPayment{" + "month=" + month + ", remainingPrincipal=" + remainingPrincipal + ", principal=" + principal + ", interest=" + interest + ", totalPayment=" + totalPayment + ", paymentDate=" + paymentDate + '}';
    }
}