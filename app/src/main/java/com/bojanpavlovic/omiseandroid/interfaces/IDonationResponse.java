package com.bojanpavlovic.omiseandroid.interfaces;

public interface IDonationResponse {
    boolean isError();
    void setError();
    String getErrorResponse();
    void setErrorResponse(String errorResponse);
}
