/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author ACER
 */
public class Customer {

    private int CustomerId, CreditScore, RoleID;
    private String Username, Password, FullName, Image, Phone, Email, Address, CCCD;
    private Date DateOfBirth, CreatedAt;
    private boolean Gender, Status;
    private BigDecimal Balance;

    public Customer() {
    }

    public Customer(int CustomerId, int CreditScore, int RoleID, String Username, String Password, String FullName, String Image, String Phone, String Email, String Address, String CCCD, Date DateOfBirth, Date CreatedAt, boolean Gender, boolean Status, BigDecimal Balance) {
        this.CustomerId = CustomerId;
        this.CreditScore = CreditScore;
        this.RoleID = RoleID;
        this.Username = Username;
        this.Password = Password;
        this.FullName = FullName;
        this.Image = Image;
        this.Phone = Phone;
        this.Email = Email;
        this.Address = Address;
        this.CCCD = CCCD;
        this.DateOfBirth = DateOfBirth;
        this.CreatedAt = CreatedAt;
        this.Gender = Gender;
        this.Status = Status;
        this.Balance = Balance;
    }

    public Customer(int customerId, int creditScore, BigDecimal Balance) {
        this.CustomerId = customerId;
        this.CreditScore = creditScore;
        this.Balance = Balance;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }

    public int getCreditScore() {
        return CreditScore;
    }

    public void setCreditScore(int CreditScore) {
        this.CreditScore = CreditScore;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
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

    public BigDecimal getBalance() {
        return Balance;
    }

    public void setBalance(BigDecimal Balance) {
        this.Balance = Balance;
    }
 @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Customer customer = (Customer) obj;
    return Objects.equals(CustomerId, customer.CustomerId); // So sánh theo ID
}

@Override
public int hashCode() {
    return Objects.hash(CustomerId); // Sử dụng ID để tính toán hash
}
    @Override
    public String toString() {
        return "Customer{" + "CustomerId=" + CustomerId + ", CreditScore=" + CreditScore + ", RoleID=" + RoleID + ", Username=" + Username + ", Password=" + Password + ", FullName=" + FullName + ", Image=" + Image + ", Phone=" + Phone + ", Email=" + Email + ", Address=" + Address + ", CCCD=" + CCCD + ", DateOfBirth=" + DateOfBirth + ", CreatedAt=" + CreatedAt + ", Gender=" + Gender + ", Status=" + Status + ", Balance=" + Balance + '}';
    }

    public int getAge() {
        if (this.DateOfBirth == null) {
            return -1; // Trả về -1 nếu ngày sinh chưa được đặt
        }

        LocalDate birthDate = this.DateOfBirth.toLocalDate(); // Chuyển đổi từ Date sang LocalDate
        LocalDate currentDate = LocalDate.now(); // Lấy ngày hiện tại

        return Period.between(birthDate, currentDate).getYears(); // Tính số năm (tuổi)
    }

}
