package com.bojanpavlovic.omiseandroid.interfaces;

import com.bojanpavlovic.omiseandroid.model.CharityResponseModel;
import com.bojanpavlovic.omiseandroid.model.DonationModel;
import com.bojanpavlovic.omiseandroid.model.DonationResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IRest {
    @GET("charities")
    Call<CharityResponseModel> getCharities();
    @POST("donations")
    Call<DonationResponseModel> setDonation(@Body DonationModel donationModel);
}
