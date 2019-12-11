package com.bojanpavlovic.omiseandroid.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.bojanpavlovic.omiseandroid.R;
import com.bojanpavlovic.omiseandroid.interfaces.ICharityResponse;
import com.bojanpavlovic.omiseandroid.interfaces.IDonationResponse;
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

    public MutableLiveData<ICharityResponse> getCharities(){
        final MutableLiveData<ICharityResponse> charityLiveData = new MutableLiveData<>();
        Call<CharityResponseModel> call = iRest.getCharities();
        call.enqueue(new Callback<CharityResponseModel>() {
            @Override
            public void onResponse(Call<CharityResponseModel> call, Response<CharityResponseModel> response) {
                if(response.code() == 200){
                    charityLiveData.postValue(response.body());
                }else{
                    ICharityResponse errorModel = new CharityResponseModel();
                    errorModel.setError();
                    errorModel.setErrorResponse("error");
                    charityLiveData.postValue(errorModel);
                }
            }

            @Override
            public void onFailure(Call<CharityResponseModel> call, Throwable t) {
                ICharityResponse errorModel = new CharityResponseModel();
                errorModel.setError();
                errorModel.setErrorResponse(t.getMessage());
                charityLiveData.postValue(errorModel);
            }
        });
        return charityLiveData;
    }

    public MutableLiveData<IDonationResponse> setDonation(DonationModel donation){
        final MutableLiveData<IDonationResponse> donationLiveData = new MutableLiveData<>();
        Call<DonationResponseModel> call = iRest.setDonation(donation);
        call.enqueue(new Callback<DonationResponseModel>() {
            @Override
            public void onResponse(Call<DonationResponseModel> call, Response<DonationResponseModel> response) {
                if(response.code() == 201){
                    // Response OK
                    donationLiveData.postValue(response.body());
                }else{
                    IDonationResponse errorModel = new DonationResponseModel();
                    errorModel.setError();
                    errorModel.setErrorResponse("error");
                    donationLiveData.postValue(errorModel);
                }
            }

            @Override
            public void onFailure(Call<DonationResponseModel> call, Throwable t) {
                IDonationResponse errorModel = new DonationResponseModel();
                errorModel.setError();
                errorModel.setErrorResponse(t.getMessage());
                donationLiveData.postValue(errorModel);
            }
        });
        return donationLiveData;
    }


}
