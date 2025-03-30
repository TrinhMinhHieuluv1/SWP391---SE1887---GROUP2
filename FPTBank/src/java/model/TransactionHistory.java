/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author ACER
 */
public class TransactionHistory {
    private int TransactionID, Status;
    private Customer customer, receiver;
    private BigDecimal Amount, BalanceBefore, BalanceAfter;
    private String Transaction_type, Note;
    private Date CreatedAt;
    private boolean check;

    public TransactionHistory(int TransactionID, int Status, Customer customer, Customer receiver, BigDecimal Amount, BigDecimal BalanceBefore, BigDecimal BalanceAfter, String Transaction_type, String Note, Date CreatedAt, boolean check) {
        this.TransactionID = TransactionID;
        this.Status = Status;
        this.customer = customer;
        this.receiver = receiver;
        this.Amount = Amount;
        this.BalanceBefore = BalanceBefore;
        this.BalanceAfter = BalanceAfter;
        this.Transaction_type = Transaction_type;
        this.Note = Note;
        this.CreatedAt = CreatedAt;
        this.check = check;
    }

    public TransactionHistory(int TransactionID, int Status, Customer customer, Customer receiver, BigDecimal Amount, BigDecimal BalanceBefore, BigDecimal BalanceAfter, String Transaction_type, String Note, Date CreatedAt) {
        this.TransactionID = TransactionID;
        this.Status = Status;
        this.customer = customer;
        this.receiver = receiver;
        this.Amount = Amount;
        this.BalanceBefore = BalanceBefore;
        this.BalanceAfter = BalanceAfter;
        this.Transaction_type = Transaction_type;
        this.Note = Note;
        this.CreatedAt = CreatedAt;
    }

    public TransactionHistory(int Status, Customer customer, Customer receiver, BigDecimal Amount, BigDecimal BalanceBefore, BigDecimal BalanceAfter, String Transaction_type, String Note) {
        this.Status = Status;
        this.customer = customer;
        this.receiver = receiver;
        this.Amount = Amount;
        this.BalanceBefore = BalanceBefore;
        this.BalanceAfter = BalanceAfter;
        this.Transaction_type = Transaction_type;
        this.Note = Note;
    }

    public int getTransactionID() {
        return TransactionID;
    }
    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setTransactionID(int TransactionID) {
        this.TransactionID = TransactionID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal Amount) {
        this.Amount = Amount;
    }

    public BigDecimal getBalanceBefore() {
        return BalanceBefore;
    }

    public void setBalanceBefore(BigDecimal BalanceBefore) {
        this.BalanceBefore = BalanceBefore;
    }

    public BigDecimal getBalanceAfter() {
        return BalanceAfter;
    }

    public void setBalanceAfter(BigDecimal BalanceAfter) {
        this.BalanceAfter = BalanceAfter;
    }

    public String getTransaction_type() {
        return Transaction_type;
    }

    public void setTransaction_type(String Transaction_type) {
        this.Transaction_type = Transaction_type;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public Customer getReceiver() {
        return receiver;
    }

    public void setReceiver(Customer receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" + "TransactionID=" + TransactionID + ", Status=" + Status + ", customer=" + customer + ", receiver=" + receiver + ", Amount=" + Amount + ", BalanceBefore=" + BalanceBefore + ", BalanceAfter=" + BalanceAfter + ", Transaction_type=" + Transaction_type + ", Note=" + Note + ", CreatedAt=" + CreatedAt + '}';
    }

    
    
}
