package com.bojanpavlovic.omiseandroid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bojanpavlovic.omiseandroid.model.CharityResponseModel;
import com.bojanpavlovic.omiseandroid.repository.Repository;

public class CharityViewModel extends AndroidViewModel {
    private MutableLiveData<CharityResponseModel> charityLiveData;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private Repository repository;

    public CharityViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        repository = Repository.getINSTANCE(application);
        // Make initial data fetch
        charityLiveData = repository.getCharities();
        // Signalize view subscriber to show Loader
        isLoading.postValue(true);
    }

    // Api that exposes ViewModel to View
    public LiveData<CharityResponseModel> getCharityViewModel(){
        return repository.getCharities();
    }

    public LiveData<Boolean> getLoaderState(){
        return isLoading;
    }

    public void setLoaderState(Boolean aBoolean){
        isLoading.postValue(aBoolean);
    }

}
