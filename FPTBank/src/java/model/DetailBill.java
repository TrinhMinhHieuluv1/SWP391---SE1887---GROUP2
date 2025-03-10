/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import model.Customer;
import model.User;

/**
 *
 * @author ACER
 */
public class DetailBill {
     private int BillID, Status;
    private String Title, Description, StartDate, EndDate, CreatedAt;
    private BigDecimal Total;
    private Customer Customer;
    private User Provider;

    public DetailBill(int BillID, int Status, String Title, String Description, String StartDate, String EndDate, String CreatedAt, BigDecimal Total, Customer Customer, User Provider) {
        this.BillID = BillID;
        this.Status = Status;
        this.Title = Title;
        this.Description = Description;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.CreatedAt = CreatedAt;
        this.Total = Total;
        this.Customer = Customer;
        this.Provider = Provider;
    }

    

    public DetailBill(String Title, String Description, String StartDate, String EndDate, BigDecimal Total, Customer Customer, User Provider) {
       
        this.Title = Title;
        this.Description = Description;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.Total = Total;
        this.Customer = Customer;
        this.Provider = Provider;
    }

    public User getProvider() {
        return Provider;
    }

    public void setProvider(User Provider) {
        this.Provider = Provider;
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

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String CreatedAt) {
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

    @Override
    public String toString() {
        return "DetailBill{" + "BillID=" + BillID + ", Status=" + Status + ", Title=" + Title + ", Description=" + Description + ", StartDate=" + StartDate + ", EndDate=" + EndDate + ", CreatedAt=" + CreatedAt + ", Total=" + Total + ", Customer=" + Customer + '}';
    }
}
