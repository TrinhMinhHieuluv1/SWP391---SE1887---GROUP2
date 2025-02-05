package model;

import java.sql.Date;

public class User {
    
    private int UserID, RoleID;
    private String Username, Password, FullName, Phone, Email, CCCD, Image, Address;
    private Date DateOfBirth, CreatedAt;
    private boolean Gender, Status;
    private User Manager;

    public User() {
    }

    public User(int UserID, String Username, String Password, String FullName, String Image, String Phone, String Email, Date DateOfBirth, boolean Gender, String Address, String CCCD, int RoleID,  boolean Status, User Manager, Date CreatedAt) {
        this.UserID = UserID;
        this.RoleID = RoleID;
        this.Username = Username;
        this.Password = Password;
        this.FullName = FullName;
        this.Phone = Phone;
        this.Email = Email;
        this.CCCD = CCCD;
        this.Image = Image;
        this.Address = Address;
        this.DateOfBirth = DateOfBirth;
        this.CreatedAt = CreatedAt;
        this.Gender = Gender;
        this.Status = Status;
        this.Manager = Manager;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean Gender) {
        this.Gender = Gender;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public User getManager() {
        return Manager;
    }

    public void setManager(User Manager) {
        this.Manager = Manager;
    }
    
}
