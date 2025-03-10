/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import model.User;

/**
 *
 * @author ACER
 */
public class CompanyName {
     private String CompanyName;
    private User User;

    public CompanyName(String CompanyName, User User) {
        this.CompanyName = CompanyName;
        this.User = User;
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
        return "CompanyName{" + "CompanyName=" + CompanyName + ", User=" + User + '}';
    }
}
