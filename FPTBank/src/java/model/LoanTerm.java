package model;

import java.math.BigDecimal;

public class LoanTerm {
     private int LoanTermID;
     private String Type;
     private BigDecimal MinAssetValue;
     private float PercentOfAsset;
     private BigDecimal MinSalary;
     private float NumberOfSalary;

    public LoanTerm() {
    }

    public LoanTerm(int LoanTermID, String Type, BigDecimal MinAssetValue, float PercentOfAsset, BigDecimal MinSalary, float NumberOfSalary) {
        this.LoanTermID = LoanTermID;
        this.Type = Type;
        this.MinAssetValue = MinAssetValue;
        this.PercentOfAsset = PercentOfAsset;
        this.MinSalary = MinSalary;
        this.NumberOfSalary = NumberOfSalary;
    }

    public int getLoanTermID() {
        return LoanTermID;
    }

    public void setLoanTermID(int LoanTermID) {
        this.LoanTermID = LoanTermID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public BigDecimal getMinAssetValue() {
        return MinAssetValue;
    }

    public void setMinAssetValue(BigDecimal MinAssetValue) {
        this.MinAssetValue = MinAssetValue;
    }

    public float getPercentOfAsset() {
        return PercentOfAsset;
    }

    public void setPercentOfAsset(float PercentOfAsset) {
        this.PercentOfAsset = PercentOfAsset;
    }

    public BigDecimal getMinSalary() {
        return MinSalary;
    }

    public void setMinSalary(BigDecimal MinSalary) {
        this.MinSalary = MinSalary;
    }

    public float getNumberOfSalary() {
        return NumberOfSalary;
    }

    public void setNumberOfSalary(float NumberOfSalary) {
        this.NumberOfSalary = NumberOfSalary;
    }

    @Override
    public String toString() {
        return "LoanTerm{" + "LoanTermID=" + LoanTermID + ", Type=" + Type + ", MinAssetValue=" + MinAssetValue + ", PercentOfAsset=" + PercentOfAsset + ", MinSalary=" + MinSalary + ", NumberOfSalary=" + NumberOfSalary + '}';
    }
     
}
