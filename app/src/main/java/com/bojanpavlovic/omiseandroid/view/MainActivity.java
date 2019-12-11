package com.bojanpavlovic.omiseandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.bojanpavlovic.omiseandroid.FragmentState;
import com.bojanpavlovic.omiseandroid.R;
import com.bojanpavlovic.omiseandroid.model.AttachStateContainer;
import com.bojanpavlovic.omiseandroid.viewmodel.CharityViewModel;

public class MainActivity extends AppCompatActivity {

    private CharityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attach starting fragment
        attachInitialFragment();

        viewModel = ViewModelProviders.of(this).get(CharityViewModel.class);

        viewModel.getFragmentState().observe(this, new Observer<AttachStateContainer>() {
            @Override
            public void onChanged(AttachStateContainer stateContainer) {
                replaceFragment(stateContainer);
            }
        });

        // Observes for exit app signal
        viewModel.getExitStateLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                    finish();
            }
        });
    }

    private void attachInitialFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Attach charity list fragment
        transaction.add(R.id.main_layout, new CharityListFragment());
        transaction.commit();
    }

    private void replaceFragment(AttachStateContainer stateContainer){
        if(!stateContainer.isTriggeredManually())
            return;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(stateContainer.getNewState() == FragmentState.ATTACHED_CHARITY){
            // Attach charity list fragment
            transaction.replace(R.id.main_layout, new CharityListFragment());
            transaction.commit();
        }else if(stateContainer.getNewState() == FragmentState.ATTACHED_DONATIONS){
            // Attach Donations fragment
            transaction.replace(R.id.main_layout, new DonationsFragment());
            transaction.commit();
        }else{
            // Attach Success fragment
            transaction.replace(R.id.main_layout, new SuccessFragment());
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        viewModel.onBackPressed();
    }
}
