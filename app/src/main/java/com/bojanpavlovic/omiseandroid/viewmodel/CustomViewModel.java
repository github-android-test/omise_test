package com.bojanpavlovic.omiseandroid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bojanpavlovic.omiseandroid.FragmentState;
import com.bojanpavlovic.omiseandroid.interfaces.ICharityResponse;
import com.bojanpavlovic.omiseandroid.interfaces.IDonationResponse;
import com.bojanpavlovic.omiseandroid.model.AttachStateContainer;
import com.bojanpavlovic.omiseandroid.model.Charity;
import com.bojanpavlovic.omiseandroid.model.DonationModel;
import com.bojanpavlovic.omiseandroid.model.DonationResponseModel;
import com.bojanpavlovic.omiseandroid.repository.Repository;

public class CustomViewModel extends AndroidViewModel {
    private MutableLiveData<AttachStateContainer>fragmentStateLiveData = new MutableLiveData<>();
    private MutableLiveData<ICharityResponse> charityLiveData;
    private MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> exitStateLiveData = new MutableLiveData<>();
    private MutableLiveData<IDonationResponse> donationLiveData;
    private Repository repository;
    private Charity selectedCharity;
    // Holds response from server after donation call.
    // Used in Success screen
    private DonationResponseModel donationResponsedata;

    public CustomViewModel(@NonNull Application application) {
        super(application);
        // Initialize repository
        repository = Repository.getINSTANCE(application);
        charityLiveData = new MutableLiveData<>();
        donationLiveData = new MutableLiveData<>();
        /**
         * Initialize charity object. It holds chosen charity info when we make transition
         * from charity screen to donation screen
         */
        selectedCharity = new Charity();
    }

    /**
     * Api that exposes ViewModel to View
     */
    public LiveData<ICharityResponse> getCharityLiveData(){
        return charityLiveData;
    }

    public LiveData<ICharityResponse> getCharities(){
        // Make initial data fetch
        charityLiveData = repository.getCharities();
        // Signalize view subscriber to show Loader
        isLoadingLiveData.postValue(true);
        // Init attached fragment state data
        fragmentStateLiveData.postValue(new AttachStateContainer());
        return charityLiveData;
    }

    public LiveData<IDonationResponse> makeDonation(DonationModel donation){
        donationLiveData = repository.setDonation(donation);
        // TODO Test loader - delete later
        isLoadingLiveData.postValue(true);
        return donationLiveData;
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

    /**
     * Used for app navigation
     * Changes attached fragment with desired one
     * @param nextState desired screen
     */
    public void changeScreen(FragmentState nextState){
        fragmentStateLiveData.postValue(new AttachStateContainer(nextState, true));
    }

    public LiveData<IDonationResponse> getDonationLiveData(){
        return donationLiveData;
    }

    public void setDonationResponseData(DonationResponseModel data){
        donationResponsedata = data;
    }

    public DonationResponseModel getDonationResponseData(){
        return donationResponsedata;
    }

    public void onBackPressed(){
       FragmentState lastState = fragmentStateLiveData.getValue().getNewState();
       if(lastState == FragmentState.ATTACHED_DONATIONS){
           // Go back to the beginning screen (charity list fragment)
           fragmentStateLiveData.postValue(new AttachStateContainer(FragmentState.ATTACHED_CHARITY, true));
       }else if(lastState == FragmentState.ATTACHED_CHARITY){
           // We are already at the beginning screen. Exit app
           exitStateLiveData.postValue(true);
       }else if(lastState == FragmentState.ATTACHED_SUCCESS){
           // Also go back to Home screen
           fragmentStateLiveData.postValue(new AttachStateContainer(FragmentState.ATTACHED_CHARITY, true));
       }
    }

}
