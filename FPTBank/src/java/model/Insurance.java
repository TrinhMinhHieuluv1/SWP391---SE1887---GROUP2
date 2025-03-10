/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author ADMIN
 */
public class Insurance {
    private int InsuranceID, ProviderID;
   private String InsuranceName,Type;
   private float FeeRate,CoverageRate;
   private double MaxAmountOfLoan;
   private boolean Status;

    public Insurance() {
    }

    public Insurance(int InsuranceID, int ProviderID, String InsuranceName, String Type, float FeeRate, float CoverageRate, double MaxAmountOfLoan, boolean Status) {
        this.InsuranceID = InsuranceID;
        this.ProviderID = ProviderID;
        this.InsuranceName = InsuranceName;
        this.Type = Type;
        this.FeeRate = FeeRate;
        this.CoverageRate = CoverageRate;
        this.MaxAmountOfLoan = MaxAmountOfLoan;
        this.Status = Status;
    }

    public int getInsuranceID() {
        return InsuranceID;
    }

    public void setInsuranceID(int InsuranceID) {
        this.InsuranceID = InsuranceID;
    }

    public int getProviderID() {
        return ProviderID;
    }

    public void setProviderID(int ProviderID) {
        this.ProviderID = ProviderID;
    }

    public String getInsuranceName() {
        return InsuranceName;
    }

    public void setInsuranceName(String InsuranceName) {
        this.InsuranceName = InsuranceName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public float getFeeRate() {
        return FeeRate;
    }

    public void setFeeRate(float FeeRate) {
        this.FeeRate = FeeRate;
    }

    public float getCoverageRate() {
        return CoverageRate;
    }

    public void setCoverageRate(float CoverageRate) {
        this.CoverageRate = CoverageRate;
    }

    public double getMaxAmountOfLoan() {
        return MaxAmountOfLoan;
    }

    public void setMaxAmountOfLoan(double MaxAmountOfLoan) {
        this.MaxAmountOfLoan = MaxAmountOfLoan;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Insurance{" + "InsuranceID=" + InsuranceID + ", ProviderID=" + ProviderID + ", InsuranceName=" + InsuranceName + ", Type=" + Type + ", FeeRate=" + FeeRate + ", CoverageRate=" + CoverageRate + ", MaxAmountOfLoan=" + MaxAmountOfLoan + ", Status=" + Status + '}';
    }

    

}