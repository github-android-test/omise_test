package com.bojanpavlovic.omiseandroid.interfaces;

import com.bojanpavlovic.omiseandroid.model.CharityResponseModel;

public interface IRepository {
    // Defines what calls should be made in Repository
    CharityResponseModel getCharities();
    void setDonation();

}
