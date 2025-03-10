package model;

import java.math.BigDecimal;
import java.sql.Date;

public class LoanPayment {
    
    private int LoanPaymentID;
    private Contract Contract;
    private Date PaymentDate;
    private BigDecimal PaymentAmount;
    private String PaymentStatus;

    public LoanPayment() {
    }

    public LoanPayment(int LoanPaymentID, Contract Contract, Date PaymentDate, BigDecimal PaymentAmount, String PaymentStatus) {
        this.LoanPaymentID = LoanPaymentID;
        this.Contract = Contract;
        this.PaymentDate = PaymentDate;
        this.PaymentAmount = PaymentAmount;
        this.PaymentStatus = PaymentStatus;
    }

    public int getLoanPaymentID() {
        return LoanPaymentID;
    }

    public void setLoanPaymentID(int LoanPaymentID) {
        this.LoanPaymentID = LoanPaymentID;
    }

    public Contract getContract() {
        return Contract;
    }

    public void setContract(Contract Contract) {
        this.Contract = Contract;
    }

    public Date getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(Date PaymentDate) {
        this.PaymentDate = PaymentDate;
    }

    public BigDecimal getPaymentAmount() {
        return PaymentAmount;
    }

    public void setPaymentAmount(BigDecimal PaymentAmount) {
        this.PaymentAmount = PaymentAmount;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }
    
}
