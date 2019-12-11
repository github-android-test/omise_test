package com.bojanpavlovic.omiseandroid.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bojanpavlovic.omiseandroid.FragmentState;
import com.bojanpavlovic.omiseandroid.R;
import com.bojanpavlovic.omiseandroid.model.DonationResponseModel;
import com.bojanpavlovic.omiseandroid.viewmodel.CharityViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuccessFragment extends Fragment implements View.OnClickListener {
    private TextView responseTextView;
    private Button backButton;
    private CharityViewModel viewModel;
    private DonationResponseModel donationResponseData;

    public SuccessFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_success, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        donationResponseData = viewModel.getDonationResponseData();
        initUI(view);
    }

    @Override
    public void onClick(View v) {
        // Go to home screen
        viewModel.changeScreen(FragmentState.ATTACHED_CHARITY);
    }

    private void initUI(final View view){
        responseTextView = view.findViewById(R.id.response_text);
        // If success flag is true, hide error msg
        // otherwise show error text
        if(donationResponseData.getSuccess()){
            responseTextView.setVisibility(View.INVISIBLE);
        }else{
            responseTextView.setText(donationResponseData.getErrorMsg());
        }
        backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener(this);
    }

    private void initViewModel(){
        viewModel = ViewModelProviders.of(requireActivity()).get(CharityViewModel.class);
    }
}
