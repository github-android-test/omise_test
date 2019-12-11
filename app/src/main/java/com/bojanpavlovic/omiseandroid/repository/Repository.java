package com.bojanpavlovic.omiseandroid.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.bojanpavlovic.omiseandroid.interfaces.ICharityResponse;
import com.bojanpavlovic.omiseandroid.interfaces.IDonationResponse;
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

    // For the sake of simplicity, we will not check if internet connection is available
    // Assume we are connected to internet !!!
    @Override
    public MutableLiveData<ICharityResponse> getCharities() {
        return retrofitAPI.getCharities();
    }

    @Override
    public MutableLiveData<IDonationResponse> setDonation(DonationModel donation) {
        return retrofitAPI.setDonation(donation);
    }

}
