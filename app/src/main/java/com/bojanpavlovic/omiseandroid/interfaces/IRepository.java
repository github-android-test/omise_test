package com.bojanpavlovic.omiseandroid.interfaces;

import androidx.lifecycle.MutableLiveData;

import com.bojanpavlovic.omiseandroid.model.CharityResponseModel;
import com.bojanpavlovic.omiseandroid.model.DonationModel;
import com.bojanpavlovic.omiseandroid.model.DonationResponseModel;

public interface IRepository {
    // Defines what calls should be made in Repository
    MutableLiveData<ICharityResponse> getCharities();
    MutableLiveData<IDonationResponse> setDonation(DonationModel donation);
}
