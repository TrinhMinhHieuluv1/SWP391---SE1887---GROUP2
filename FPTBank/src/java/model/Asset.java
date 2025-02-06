/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.math.BigDecimal;
import java.util.Date;

public class Asset {


    private int Id; // SalaryId
    private int CustomerId; // CustomerId
    private String Image; // Image
    private String Description; // Description
    private BigDecimal Value; // Value
    private boolean Verification; // Verification
    private boolean Status; // Status
    private Date CreatedAt; // CreatedAt



    // Constructor
    
    public Asset() {
    }

    public Asset(int customerId, String image, String description, BigDecimal value) {

        this.CustomerId = customerId;
        this.Image = image;
        this.Description = description;
        this.Value = value;
        this.Verification = false; // default
        this.Status = false; // default
        this.CreatedAt = new Date(); // default to current date

    }

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

    public boolean isVerification() {
        return Verification;
    }

    public void setVerification(boolean verification) {
        this.Verification = verification;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        this.Status = status;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.CreatedAt = createdAt;

    }

    @Override
    public String toString() {

        return "Asset{" + "id=" + Id + ", customerId=" + CustomerId + ", image=" + Image + ", description=" + Description + ", value=" + Value + ", verification=" + Verification + ", status=" + Status + ", createdAt=" + CreatedAt + '}';

    }
    
}

