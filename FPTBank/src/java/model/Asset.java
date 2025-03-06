/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class Asset {

    private int Id; // SalaryId
    private Customer Customer; // Customer
    private String Image; // Image
    private String Title;
    private String Description; // Description
    private BigDecimal Value; // Value
    private String Comments;
    private BigDecimal ValuationAmount;
    private boolean Used;
    private String Status;
    private Date CreatedAt;
    private List<PdfLis> listpdf = new ArrayList<>();

    public Asset() {
        this.Used = false;
        this.Status = "Pending";
        this.CreatedAt = new Date();
    }

    public Asset(Customer Customer, String Image, String Title, String Description, BigDecimal Value) {
        this.Customer = Customer;
        this.Image = Image;
        this.Title = Title;
        this.Description = Description;
        this.Value = Value;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public List<PdfLis> getListpdf() {
        return listpdf;
    }

    public String getListpdfJs() {
        Gson gson = new Gson();
        return gson.toJson(listpdf); // Trả về chuỗi JSON
    }

    public void setListpdf(List<PdfLis> listpdf) {
        this.listpdf = listpdf;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer Customer) {
        this.Customer = Customer;
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

    public String getListpdfAsJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this.listpdf);
    }

    @Override
    public String toString() {
        return "Asset{" + "Id=" + Id + ", Customer=" + Customer + ", Image=" + Image + ", Description=" + Description + ", Value=" + Value + ", Comments=" + Comments + ", ValuationAmount=" + ValuationAmount + ", Used=" + Used + ", Status=" + Status + ", CreatedAt=" + CreatedAt + '}';
    }

}
