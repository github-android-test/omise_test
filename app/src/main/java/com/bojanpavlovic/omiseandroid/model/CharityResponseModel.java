package com.bojanpavlovic.omiseandroid.model;

import com.bojanpavlovic.omiseandroid.interfaces.ICharityResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharityResponseModel implements ICharityResponse {
    // In case of error response from server, properly set below fields
    //=======================
    private boolean isError = false;
    private String serverErrorMessage = "";
    //=======================

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


    @Override
    public boolean isError() {
        return isError;
    }

    @Override
    public void setError() {
        isError = true;
    }

    @Override
    public String getErrorResponse() {
        return serverErrorMessage;
    }

    @Override
    public void setErrorResponse(String errorResponse) {
        serverErrorMessage = errorResponse;
    }
}
