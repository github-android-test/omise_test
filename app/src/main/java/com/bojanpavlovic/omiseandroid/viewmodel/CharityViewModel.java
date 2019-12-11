package com.bojanpavlovic.omiseandroid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bojanpavlovic.omiseandroid.FragmentState;
import com.bojanpavlovic.omiseandroid.model.AttachStateContainer;
import com.bojanpavlovic.omiseandroid.model.CharityResponseModel;
import com.bojanpavlovic.omiseandroid.model.Charity;
import com.bojanpavlovic.omiseandroid.model.DonationModel;
import com.bojanpavlovic.omiseandroid.model.DonationResponseModel;
import com.bojanpavlovic.omiseandroid.repository.Repository;

public class CharityViewModel extends AndroidViewModel {
    private MutableLiveData<AttachStateContainer>fragmentStateLiveData = new MutableLiveData<>();
    private MutableLiveData<CharityResponseModel> charityLiveData;
    private MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> exitStateLiveData = new MutableLiveData<>();
    private MutableLiveData<DonationResponseModel> donationLiveData = new MutableLiveData<>();
    private Repository repository;
    private Charity selectedCharity;

    public CharityViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        repository = Repository.getINSTANCE(application);
        charityLiveData = new MutableLiveData<>();
        /**
         * Initialize charity object. It holds chosen charity info when we make transition
         * from charity screen to donation screen
         */
        selectedCharity = new Charity();
    }

    /**
     * Api that exposes ViewModel to View
     */
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

    public void makeDonation(DonationModel donation){
        repository.setDonation(donation);
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

    public Charity getSelectedCharity(){
        return selectedCharity;
    }

    public void onItemClicked(int charityId, String charityName){
        // Save chosen charity data for later use
        selectedCharity.setCharityId(charityId);
        selectedCharity.setCharityName(charityName);
        // Move to donation screen
        fragmentStateLiveData.postValue(new AttachStateContainer(FragmentState.ATTACHED_DONATIONS, true));
    }

    public LiveData<DonationResponseModel> getDonationLiveData(){
        return donationLiveData;
    }



    public void onBackPressed(){
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
