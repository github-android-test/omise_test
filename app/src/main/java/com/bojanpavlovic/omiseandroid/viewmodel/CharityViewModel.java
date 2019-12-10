package com.bojanpavlovic.omiseandroid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bojanpavlovic.omiseandroid.FragmentState;
import com.bojanpavlovic.omiseandroid.model.AttachStateContainer;
import com.bojanpavlovic.omiseandroid.model.CharityResponseModel;
import com.bojanpavlovic.omiseandroid.repository.Repository;

public class CharityViewModel extends AndroidViewModel {
    private MutableLiveData<CharityResponseModel> charityLiveData;
    private MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>();
    private MutableLiveData<AttachStateContainer>fragmentStateLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> exitStateLiveData = new MutableLiveData<>();
    private Repository repository;

    public CharityViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        repository = Repository.getINSTANCE(application);
        charityLiveData = new MutableLiveData<>();
    }

    // Api that exposes ViewModel to View
    public LiveData<CharityResponseModel> getCharityViewModel(){
        return charityLiveData;
    }

    public LiveData<CharityResponseModel> getCharities(){
        // Make initial data fetch
        charityLiveData = repository.getCharities();
        // Signalize view subscriber to show Loader
        isLoadingLiveData.postValue(true);
        // Init attached fragment state data
        fragmentStateLiveData.postValue(new AttachStateContainer());
        return charityLiveData;
    }

    public LiveData<Boolean> getLoaderState(){
        return isLoadingLiveData;
    }

    public void setLoaderState(Boolean aBoolean){
        isLoadingLiveData.postValue(aBoolean);
    }

    public LiveData<AttachStateContainer> getFragmentState(){
        return fragmentStateLiveData;
    }

    public LiveData<Boolean> getExitStateLiveData(){
        return exitStateLiveData;
    }

    // TODO Later modify to take more args
    public void onItemClicked(String name){
        fragmentStateLiveData.postValue(new AttachStateContainer(FragmentState.ATTACHED_DONATIONS, true));
    }

    public void onBackPressed(){
        // Go back to previous fragment/Exit app
       FragmentState lastState = fragmentStateLiveData.getValue().getNewState();
       if(lastState == FragmentState.ATTACHED_DONATIONS){
           // Go back to the beginning screen (charity list fragment)
           fragmentStateLiveData.postValue(new AttachStateContainer(FragmentState.ATTACHED_CHARITY, true));
       }else if(lastState == FragmentState.ATTACHED_CHARITY){
           // We are already at the beginning screen. Exit app
           exitStateLiveData.postValue(true);
       }
    }

}
