package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Contract {

    private int ContractID;
    private Customer Customer;
    private BigDecimal Amount;
    private int Period;
    private float LatePaymentRate;
    private float EarlyWithdrawRate;
    private float InterestRate;
    private String Type;
    private String Description;
    private Asset Asset;
    private Salary Salary;
    private Insurance Insurance;
    private boolean MonthlyPayment;
    private String MonthlyPaymentType;
    private int StatusID;
    private Date CreateAt;
    private float InsuranceCoverage;

    public Contract() {
    }

    public Contract(int ContractID, Customer Customer, BigDecimal Amount, int Period, float LatePaymentRate, float EarlyWithdrawRate, float InterestRate, String Type, String Description, Asset Asset, Salary Salary, Insurance Insurance, boolean MonthlyPayment, String MonthlyPaymentType, int StatusID, Date CreateAt, float InsuranceCoverage) {
        this.ContractID = ContractID;
        this.Customer = Customer;
        this.Amount = Amount;
        this.Period = Period;
        this.LatePaymentRate = LatePaymentRate;
        this.EarlyWithdrawRate = EarlyWithdrawRate;
        this.InterestRate = InterestRate;
        this.Type = Type;
        this.Description = Description;
        this.Asset = Asset;
        this.Salary = Salary;
        this.Insurance = Insurance;
        this.MonthlyPayment = MonthlyPayment;
        this.MonthlyPaymentType = MonthlyPaymentType;
        this.StatusID = StatusID;
        this.CreateAt = CreateAt;
        this.InsuranceCoverage = InsuranceCoverage;
    }

    public int getContractID() {
        return ContractID;
    }

    public void setContractID(int ContractID) {
        this.ContractID = ContractID;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer Customer) {
        this.Customer = Customer;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal Amount) {
        this.Amount = Amount;
    }

    public int getPeriod() {
        return Period;
    }

    public void setPeriod(int Period) {
        this.Period = Period;
    }

    public float getLatePaymentRate() {
        return LatePaymentRate;
    }

    public void setLatePaymentRate(float LatePaymentRate) {
        this.LatePaymentRate = LatePaymentRate;
    }

    public float getEarlyWithdrawRate() {
        return EarlyWithdrawRate;
    }

    public void setEarlyWithdrawRate(float EarlyWithdrawRate) {
        this.EarlyWithdrawRate = EarlyWithdrawRate;
    }

    public float getInterestRate() {
        return InterestRate;
    }

    public void setInterestRate(float InterestRate) {
        this.InterestRate = InterestRate;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Asset getAsset() {
        return Asset;
    }

    public void setAsset(Asset Asset) {
        this.Asset = Asset;
    }

    public Salary getSalary() {
        return Salary;
    }

    public void setSalary(Salary Salary) {
        this.Salary = Salary;
    }

    public Insurance getInsurance() {
        return Insurance;
    }

    public void setInsurance(Insurance Insurance) {
        this.Insurance = Insurance;
    }

    public boolean isMonthlyPayment() {
        return MonthlyPayment;
    }

    public void setMonthlyPayment(boolean MonthlyPayment) {
        this.MonthlyPayment = MonthlyPayment;
    }

    public String getMonthlyPaymentType() {
        return MonthlyPaymentType;
    }

    public void setMonthlyPaymentType(String MonthlyPaymentType) {
        this.MonthlyPaymentType = MonthlyPaymentType;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
    }

    public Date getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(Date CreateAt) {
        this.CreateAt = CreateAt;
    }

    public float getInsuranceCoverage() {
        return InsuranceCoverage;
    }

    public void setInsuranceCoverage(float InsuranceCoverage) {
        this.InsuranceCoverage = InsuranceCoverage;
    }

}
