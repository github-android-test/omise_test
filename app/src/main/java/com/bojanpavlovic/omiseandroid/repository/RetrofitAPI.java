package com.bojanpavlovic.omiseandroid.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.bojanpavlovic.omiseandroid.R;
import com.bojanpavlovic.omiseandroid.interfaces.IRest;
import com.bojanpavlovic.omiseandroid.model.CharityResponseModel;
import com.bojanpavlovic.omiseandroid.model.DonationModel;
import com.bojanpavlovic.omiseandroid.model.DonationResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {
    private Retrofit retrofit;
    private IRest iRest;

    public RetrofitAPI(Application application){
        // Initializes Retrofit2 instance
        initRetrofit(application);
        iRest = retrofit.create(IRest.class);
    }

    private void initRetrofit(Application application){
        retrofit = new Retrofit.Builder()
                .baseUrl(application.getString(R.string.API_BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public MutableLiveData<CharityResponseModel> getCharities(){
        final MutableLiveData<CharityResponseModel> charityLiveData = new MutableLiveData<>();
        Call<CharityResponseModel> call = iRest.getCharities();
        call.enqueue(new Callback<CharityResponseModel>() {
            @Override
            public void onResponse(Call<CharityResponseModel> call, Response<CharityResponseModel> response) {
                // TODO Add checking response code here !!!
                charityLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CharityResponseModel> call, Throwable t) {
                // TODO Handle failure !!!
            }
        });
        return charityLiveData;
    }

    public MutableLiveData<DonationResponseModel> setDonation(DonationModel donation){
        final MutableLiveData<DonationResponseModel> donationLiveData = new MutableLiveData<>();
        Call<DonationResponseModel> call = iRest.setDonation(donation);
        call.enqueue(new Callback<DonationResponseModel>() {
            @Override
            public void onResponse(Call<DonationResponseModel> call, Response<DonationResponseModel> response) {
                // TODO Add checking response code here !!!
                donationLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<DonationResponseModel> call, Throwable t) {
                // TODO Handle failure !!!
            }
        });
        return donationLiveData;
    }


}
