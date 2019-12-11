package com.bojanpavlovic.omiseandroid.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.bojanpavlovic.omiseandroid.interfaces.ICharityResponse;
import com.bojanpavlovic.omiseandroid.interfaces.IDonationResponse;
import com.bojanpavlovic.omiseandroid.interfaces.IRepository;
import com.bojanpavlovic.omiseandroid.model.DonationModel;

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

    // For the sake of simplicity, we will not check if internet connection is available
    // If network unavailable, network timeout will be triggered
    // after some time and error toast will be shown

    @Override
    public MutableLiveData<ICharityResponse> getCharities() {
        return retrofitAPI.getCharities();
    }

    @Override
    public MutableLiveData<IDonationResponse> setDonation(DonationModel donation) {
        return retrofitAPI.setDonation(donation);
    }

}
