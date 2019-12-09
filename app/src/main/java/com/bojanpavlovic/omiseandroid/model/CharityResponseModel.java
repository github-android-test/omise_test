package com.bojanpavlovic.omiseandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharityResponseModel {
    @SerializedName("total")
    private int total;
    @SerializedName("data")
    private List<CharityItem> charityItemList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CharityItem> getCharityItemList() {
        return charityItemList;
    }

    public void setCharityItemList(List<CharityItem> charityItemList) {
        this.charityItemList = charityItemList;
    }
}
