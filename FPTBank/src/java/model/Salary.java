/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

public class Salary {

    private int Id; // SalaryId
    private int CustomerId; // CustomerId
    private String Image; // Image
    private String Description; // Description
    private BigDecimal Value; // Value
    private String Comments;
    private BigDecimal ValuationAmount;
    private boolean Used;
    private String Status;
    private Date CreatedAt;
    private String PdfPath;
    public Salary() {
        this.Used = false;
        this.Status = "Pending";
        this.PdfPath="";
        this.CreatedAt = new Date();
    }

    public Salary(int CustomerId, String Image, String Description, BigDecimal Value, String Comments, BigDecimal ValuationAmount) {
        this.CustomerId = CustomerId;
        this.Image = Image;
        this.Description = Description;
        this.Value = Value;
        this.Comments = Comments;
        this.ValuationAmount = ValuationAmount;

    }

    // Getters and Setters
    public int getId() {

        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        this.CustomerId = customerId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public BigDecimal getValue() {
        return Value;
    }

    public void setValue(BigDecimal value) {
        this.Value = value;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.CreatedAt = createdAt;

    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    public BigDecimal getValuationAmount() {
        return ValuationAmount;
    }

    public void setValuationAmount(BigDecimal ValuationAmount) {
        this.ValuationAmount = ValuationAmount;
    }

    public boolean isUsed() {
        return Used;
    }

    public void setUsed(boolean Used) {
        this.Used = Used;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getPdfPath() {
        return PdfPath;
    }

    public void setPdfPath(String PdfPath) {
        this.PdfPath = PdfPath;
    }

    @Override
    public String toString() {
        return "Salary{" + "Id=" + Id + ", CustomerId=" + CustomerId + ", Image=" + Image + ", Description=" + Description + ", Value=" + Value + ", Comments=" + Comments + ", ValuationAmount=" + ValuationAmount + ", Used=" + Used + ", Status=" + Status + ", CreatedAt=" + CreatedAt + ", PdfPath=" + PdfPath + '}';
    }

}
