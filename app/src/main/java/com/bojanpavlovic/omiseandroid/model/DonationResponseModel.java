package com.bojanpavlovic.omiseandroid.model;

import com.bojanpavlovic.omiseandroid.interfaces.IDonationResponse;
import com.google.gson.annotations.SerializedName;

public class DonationResponseModel implements IDonationResponse {
    // In case of error response from server, properly set below fields
    //=======================
    private boolean isError = false;
    private String serverErrorMessage = "";
    //=======================

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
