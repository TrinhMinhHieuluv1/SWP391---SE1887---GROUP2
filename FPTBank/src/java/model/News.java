package model;

import java.sql.Date;

public class News {
    
    private int NewsID;
    private User User;
    private String Title, Description, Image;
    private boolean Status;
    private Date CreatedAt;
    private int NumberOfAccess;

    public News() {
    }

    public News(int NewsID, User User, String Title, String Description, String Image, boolean Status, Date CreatedAt, int NumberOfAccess) {
        this.NewsID = NewsID;
        this.User = User;
        this.Title = Title;
        this.Description = Description;
        this.Image = Image;
        this.Status = Status;
        this.CreatedAt = CreatedAt;
        this.NumberOfAccess = NumberOfAccess;
    }

    public int getNewsID() {
        return NewsID;
    }

    public void setNewsID(int NewsID) {
        this.NewsID = NewsID;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public int getNumberOfAccess() {
        return NumberOfAccess;
    }

    public void setNumberOfAccess(int NumberOfAccess) {
        this.NumberOfAccess = NumberOfAccess;
    }
  
}
