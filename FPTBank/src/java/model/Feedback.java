/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Feedback {
      int feedbackID,StarScore;
      String message,response;
      boolean status;
      Date createdAt;
      Service service;
      Customer Customer;
    
      public Feedback() {
    }

    public Feedback(int feedbackID, int StarScore, String message, String response, boolean status, Date createdAt, Service service, Customer Customer) {
        this.feedbackID = feedbackID;
        this.StarScore = StarScore;
        this.message = message;
        this.response = response;
        this.status = status;
        this.createdAt = createdAt;
        this.service = service;
        this.Customer = Customer;
    }

    public Feedback(int feedbackID, int StarScore, String message, String response, boolean status, Date createdAt, Customer Customer) {
        this.feedbackID = feedbackID;
        this.StarScore = StarScore;
        this.message = message;
        this.response = response;
        this.status = status;
        this.createdAt = createdAt;
        this.Customer = Customer;
    }

    

    

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getStarScore() {
        return StarScore;
    }

    public void setStarScore(int StarScore) {
        this.StarScore = StarScore;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer Customer) {
        this.Customer = Customer;
    }

    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
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
        return "Feedback{" + "feedbackID=" + feedbackID + ", StarScore=" + StarScore + ", message=" + message + ", response=" + response + ", status=" + status + ", createdAt=" + createdAt + ", service=" + service + ", Customer=" + Customer + '}';
    }

   

    
    
}
