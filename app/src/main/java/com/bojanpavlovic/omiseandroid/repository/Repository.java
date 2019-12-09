package com.bojanpavlovic.omiseandroid.repository;

import android.app.Application;

import com.bojanpavlovic.omiseandroid.interfaces.IRepository;
import com.bojanpavlovic.omiseandroid.model.CharityResponseModel;

public class Repository implements IRepository {
    private static Repository INSTANCE = null;

    private Repository(Application application){
        // TODO Here initialize Retrofit

    }

    public static Repository getINSTANCE(Application application){
        if(INSTANCE == null)
            INSTANCE = new Repository(application);
        return INSTANCE;
    }

    @Override
    public CharityResponseModel getCharities() {
        // TODO Implement calling RetrofitAPI via Retrofit
        return null;
    }

    @Override
    public void setDonation() {
        // TODO Implement calling RetrofitAPI via Retrofit
    }

}
