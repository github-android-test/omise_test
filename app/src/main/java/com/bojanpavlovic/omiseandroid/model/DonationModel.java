package com.bojanpavlovic.omiseandroid.model;

public class DonationModel {
    private String name;
    private String token;
    private int amount;

    private String cardNumber;

    public DonationModel(){
        name = "";
        token = "";
        amount = 0;
        cardNumber = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCardNumberInTextFormat() {
        return cardNumber;
    }

    public void setCardNumberInTextFormat(String cardNumberInTextFormat) {
        this.cardNumber = cardNumberInTextFormat;
    }
}
