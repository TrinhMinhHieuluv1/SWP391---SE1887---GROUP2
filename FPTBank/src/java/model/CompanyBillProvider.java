/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.User;

/**
 *
 * @author ACER
 */
public class CompanyBillProvider {
    private int CompanyID;
    private String CompanyName;
    private User User;

    public CompanyBillProvider(int CompanyID, String CompanyName, User User) {
        this.CompanyID = CompanyID;
        this.CompanyName = CompanyName;
        this.User = User;
    }

    public int getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(int CompanyID) {
        this.CompanyID = CompanyID;
    }
    
    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
    }

    @Override
    public String toString() {
        return "CompanyBillProvider{" + "CompanyID=" + CompanyID + ", CompanyName=" + CompanyName + ", User=" + User + '}';
    }

    
}
