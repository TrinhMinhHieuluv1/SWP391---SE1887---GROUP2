package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class LoanPayment {

    private int LoanPaymentID;
    private Contract Contract;
    private Date PaymentDate;
    private Date PaidDate;
    private BigDecimal PaymentAmount;
    private BigDecimal LateAmount;
    private String PaymentStatus;

    public LoanPayment() {
    }

    public LoanPayment(int LoanPaymentID, Contract Contract, Date PaymentDate, Date PaidDate, BigDecimal PaymentAmount, BigDecimal LateAmount, String PaymentStatus) {
        this.LoanPaymentID = LoanPaymentID;
        this.Contract = Contract;
        this.PaymentDate = PaymentDate;
        this.PaidDate = PaidDate;
        this.PaymentAmount = PaymentAmount;
        this.LateAmount = LateAmount;
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

    public Date getPaidDate() {
        return PaidDate;
    }

    public void setPaidDate(Date PaidDate) {
        this.PaidDate = PaidDate;
    }

    public BigDecimal getLateAmount() {
        return LateAmount;
    }

    public void setLateAmount(BigDecimal LateAmount) {
        this.LateAmount = LateAmount;
    }

    
}
