package com.bojanpavlovic.omiseandroid.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.bojanpavlovic.omiseandroid.interfaces.IRepository;
import com.bojanpavlovic.omiseandroid.model.CharityResponseModel;
import com.bojanpavlovic.omiseandroid.model.DonationModel;
import com.bojanpavlovic.omiseandroid.model.DonationResponseModel;

public class Repository implements IRepository {
    private static Repository INSTANCE = null;
    private RetrofitAPI retrofitAPI;

    private Repository(Application application){
        // On Repository init(occurs only once), also init retrofit
        retrofitAPI = new RetrofitAPI(application);
    }

    public static Repository getINSTANCE(Application application){
        if(INSTANCE == null)
            INSTANCE = new Repository(application);
        return INSTANCE;
    }

    @Override
    public MutableLiveData<CharityResponseModel> getCharities() {
        return retrofitAPI.getCharities();
    }

    @Override
    public MutableLiveData<DonationResponseModel> setDonation(DonationModel donation) {
        return retrofitAPI.setDonation(donation);
    }

}
