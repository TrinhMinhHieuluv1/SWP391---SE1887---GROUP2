package model;

import java.math.BigDecimal;

public class ServiceItem {
    private int ServiceItemID;
    private String ServiceItemName;
    private BigDecimal MaxAmount;
    private int MaxPeriod;
    private int MinCreditScore;
    private float LatePaymentRate;
    private BigDecimal MinAmount;
    private int MinPeriod;
    private float EarlyWithdrawRate;
    private float InterestRate;
    private String Type;
    private boolean Status;

    public ServiceItem() {
    }

    public ServiceItem(int ServiceItemID, String ServiceItemName, BigDecimal MaxAmount, int MaxPeriod, int MinCreditScore, float LatePaymentRate, BigDecimal MinAmount, int MinPeriod, float EarlyWithdrawRate, float InterestRate, String Type, boolean Status) {
        this.ServiceItemID = ServiceItemID;
        this.ServiceItemName = ServiceItemName;
        this.MaxAmount = MaxAmount;
        this.MaxPeriod = MaxPeriod;
        this.MinCreditScore = MinCreditScore;
        this.LatePaymentRate = LatePaymentRate;
        this.MinAmount = MinAmount;
        this.MinPeriod = MinPeriod;
        this.EarlyWithdrawRate = EarlyWithdrawRate;
        this.InterestRate = InterestRate;
        this.Type = Type;
        this.Status = Status;
    }

    public int getServiceItemID() {
        return ServiceItemID;
    }

    public void setServiceItemID(int ServiceItemID) {
        this.ServiceItemID = ServiceItemID;
    }

    public String getServiceItemName() {
        return ServiceItemName;
    }

    public void setServiceItemName(String ServiceName) {
        this.ServiceItemName = ServiceName;
    }

    public BigDecimal getMaxAmount() {
        return MaxAmount;
    }

    public void setMaxAmount(BigDecimal MaxAmount) {
        this.MaxAmount = MaxAmount;
    }

    public int getMaxPeriod() {
        return MaxPeriod;
    }

    public void setMaxPeriod(int MaxPeriod) {
        this.MaxPeriod = MaxPeriod;
    }

    public int getMinCreditScore() {
        return MinCreditScore;
    }

    public void setMinCreditScore(int MinCreditScore) {
        this.MinCreditScore = MinCreditScore;
    }

    public float getLatePaymentRate() {
        return LatePaymentRate;
    }

    public void setLatePaymentRate(float LatePaymentRate) {
        this.LatePaymentRate = LatePaymentRate;
    }

    public BigDecimal getMinAmount() {
        return MinAmount;
    }

    public void setMinAmount(BigDecimal MinAmount) {
        this.MinAmount = MinAmount;
    }

    public int getMinPeriod() {
        return MinPeriod;
    }

    public void setMinPeriod(int MinPeriod) {
        this.MinPeriod = MinPeriod;
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

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "ServiceItem{" + "ServiceItemID=" + ServiceItemID + ", ServiceItemName=" + ServiceItemName + ", MaxAmount=" + MaxAmount + ", MaxPeriod=" + MaxPeriod + ", MinCreditScore=" + MinCreditScore + ", LatePaymentRate=" + LatePaymentRate + ", MinAmount=" + MinAmount + ", MinPeriod=" + MinPeriod + ", EarlyWithdrawRate=" + EarlyWithdrawRate + ", InterestRate=" + InterestRate + ", Type=" + Type + ", Status=" + Status + '}';
    }
    
}
