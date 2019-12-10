package com.bojanpavlovic.omiseandroid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoaderViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> loaderStateLiveData;

    public LoaderViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getLoaderStateLiveData(){
        return loaderStateLiveData;
    }

}
