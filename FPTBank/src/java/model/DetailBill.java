/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.Date;
import model.Customer;
import java.sql.Timestamp;
import model.User;

/**
 *
 * @author ACER
 */
public class DetailBill {
    private int BillID, Status, StatusOfBill;
    private String Title, Description;
    private Date  StartDate, EndDate, CreatedAt;
    private Timestamp PaymentDate;
    private BigDecimal Total;
    private Customer Customer;
    private User Provider;

    public DetailBill(int BillID, int Status, int StatusOfBill, String Title, String Description, Date StartDate, Date EndDate, Date CreatedAt, Timestamp PaymentDate, BigDecimal Total, Customer Customer, User Provider) {
        this.BillID = BillID;
        this.Status = Status;
        this.StatusOfBill = StatusOfBill;
        this.Title = Title;
        this.Description = Description;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.CreatedAt = CreatedAt;
        this.PaymentDate = PaymentDate;
        this.Total = Total;
        this.Customer = Customer;
        this.Provider = Provider;
    }

    

    

    public DetailBill(String Title, String Description, Date StartDate, Date EndDate, BigDecimal Total, Customer Customer, User Provider) {
        this.Title = Title;
        this.Description = Description;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.Total = Total;
        this.Customer = Customer;
        this.Provider = Provider;
    }

    public Timestamp getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(Timestamp PaymentDate) {
        this.PaymentDate = PaymentDate;
    }

    
    
    public int getStatusOfBill() {
        return StatusOfBill;
    }

    public void setStatusOfBill(int StatusOfBill) {
        this.StatusOfBill = StatusOfBill;
    }

    public int getBillID() {
        return BillID;
    }

    public void setBillID(int BillID) {
        this.BillID = BillID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal Total) {
        this.Total = Total;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer Customer) {
        this.Customer = Customer;
    }

    public User getProvider() {
        return Provider;
    }

    public void setProvider(User Provider) {
        this.Provider = Provider;
    }

    @Override
    public String toString() {
        return "DetailBill{" + "BillID=" + BillID + ", Status=" + Status + ", StatusOfBill=" + StatusOfBill + ", Title=" + Title + ", Description=" + Description + ", StartDate=" + StartDate + ", EndDate=" + EndDate + ", CreatedAt=" + CreatedAt + ", Total=" + Total + ", Customer=" + Customer + ", Provider=" + Provider + '}';
    }

    

    
}
