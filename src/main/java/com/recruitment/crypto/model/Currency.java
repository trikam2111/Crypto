package com.recruitment.crypto.model;

public class Currency {
    private String name;
    private Double rate;
    private Double amount;
    private static final Double fee = 0.01;
    private Double result;

    public Currency(String name, Double rate, Double amount){
        this.name = name;
        this.rate = rate;
        this.amount = amount;
        result = this.amount / (this.rate + this.rate * fee);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getResult() {
        return result;
    }

    public static Double getFee() { return fee; }
}
