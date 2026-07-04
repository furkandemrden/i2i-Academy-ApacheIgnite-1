package com.furkan;

public class Subscriber {

    private Integer customerId;
    private Double dataUsage;
    private Integer smsUsage;
    private Integer callUsage;

    public Subscriber(Integer customerId, Double dataUsage, Integer smsUsage, Integer callUsage) {
        this.customerId = customerId;
        this.dataUsage = dataUsage;
        this.smsUsage = smsUsage;
        this.callUsage = callUsage;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Double getDataUsage() {
        return dataUsage;
    }

    public void setDataUsage(Double dataUsage) {
        this.dataUsage = dataUsage;
    }

    public Integer getSmsUsage() {
        return smsUsage;
    }

    public void setSmsUsage(Integer smsUsage) {
        this.smsUsage = smsUsage;
    }

    public Integer getCallUsage() {
        return callUsage;
    }

    public void setCallUsage(Integer callUsage) {
        this.callUsage = callUsage;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "customerId=" + customerId +
                ", dataUsage=" + dataUsage +
                ", smsUsage=" + smsUsage +
                ", callUsage=" + callUsage +
                '}';
    }
}