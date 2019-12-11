package com.bojanpavlovic.omiseandroid.model;

public class Charity {
    private int charityId;
    private String charityName;

    public Charity(){
        charityId = 0;
        charityName = "";
    }

    public int getCharityId() {
        return charityId;
    }

    public void setCharityId(int charityId) {
        this.charityId = charityId;
    }

    public String getCharityName() {
        return charityName;
    }

    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }
}
