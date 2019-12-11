package com.bojanpavlovic.omiseandroid.model;

import com.google.gson.annotations.SerializedName;

public class DonationResponseModel {
    @SerializedName("success")
    private boolean success;
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("error_message")
    private String errorMsg;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
