package com.bojanpavlovic.omiseandroid.interfaces;

public interface ICharityResponse{
    boolean isError();
    void setError();
    String getErrorResponse();
    void setErrorResponse(String errorResponse);
}
