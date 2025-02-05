/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.math.BigDecimal;
import java.util.Date;

public class Asset {

    private int id; // AssetId
    private int customerId; // CustomerId
    private String image; // Image
    private String description; // Description
    private BigDecimal value; // Value
    private boolean verification; // Verification
    private boolean status; // Status
    private Date createdAt; // CreatedAt

    // Constructor
    
    public Asset() {
    }

    public Asset(int customerId, String image, String description, BigDecimal value) {
        this.customerId = customerId;
        this.image = image;
        this.description = description;
        this.value = value;
        this.verification = false; // default
        this.status = false; // default
        this.createdAt = new Date(); // default to current date
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public boolean isVerification() {
        return verification;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Asset{" + "id=" + id + ", customerId=" + customerId + ", image=" + image + ", description=" + description + ", value=" + value + ", verification=" + verification + ", status=" + status + ", createdAt=" + createdAt + '}';
    }
    
}

