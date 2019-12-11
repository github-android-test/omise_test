package com.bojanpavlovic.omiseandroid.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bojanpavlovic.omiseandroid.R;
import com.bojanpavlovic.omiseandroid.model.Charity;
import com.bojanpavlovic.omiseandroid.model.DonationModel;
import com.bojanpavlovic.omiseandroid.model.DonationResponseModel;
import com.bojanpavlovic.omiseandroid.viewmodel.CharityViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationsFragment extends Fragment implements View.OnClickListener {
    private CharityViewModel viewModel;
    private Charity selectedCharity;
    private EditText cardNumberEditText;
    private EditText donationAmountEditText;
    private Button donateButton;

    public DonationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_make_donation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    private void initUI(View view){
        cardNumberEditText = view.findViewById(R.id.card_number_edit);
        donationAmountEditText = view.findViewById(R.id.amount_edit);
        donateButton = view.findViewById(R.id.donate_button);
        donateButton.setOnClickListener(this);

        viewModel = ViewModelProviders.of(requireActivity()).get(CharityViewModel.class);
        selectedCharity = viewModel.getSelectedCharity();

        viewModel.getDonationLiveData().observe(this, new Observer<DonationResponseModel>() {
            @Override
            public void onChanged(DonationResponseModel donationResponseModel) {
                // TODO Check response here !!!

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(isValidCardNumberLength() && isValidDonationLength()){
            // Make donation call to REST
            viewModel.makeDonation(prepareDonationData());
        }else if(!isValidCardNumberLength()){
            // User entered less than 16 digits
            Toast.makeText(requireActivity(),
                    requireActivity().getString(R.string.donation_invalid_card_length),
                    Toast.LENGTH_LONG).show();
        }else if(!isValidDonationLength()){
            // User entered invalid donation sum
            Toast.makeText(requireActivity(),
                    requireActivity().getString(R.string.donation_invalid_sum),
                    Toast.LENGTH_LONG).show();
        }
    }

    private boolean isValidDonationLength(){
        String donationAmountInTextFormat = donationAmountEditText.getText().toString().trim();
        /**
         * Length should be at least 1 cipher and value should be non 0
         * because there is no logic in donating 0
         */
        int donationNumberLength = donationAmountInTextFormat.length();
        if(donationNumberLength == 0)
            return false;

        int donation = Integer.valueOf(donationAmountInTextFormat);
        return donation > 0;
    }

    private boolean isValidCardNumberLength(){
        String cardNumberInTextFormat = cardNumberEditText.getText().toString();
        // Should be always 16
        int cardNumberLength = cardNumberInTextFormat.length();
        return cardNumberLength == 16;
    }

    // Prepared data container used in donation request body
    private DonationModel prepareDonationData(){
        DonationModel model = new DonationModel();
        String cardNumberText = cardNumberEditText.getText().toString();
        int amount = Integer.valueOf(donationAmountEditText.getText().toString());
        String token = getString(R.string.hardcoded_token);
        // Not sure if we need to send selected charity name ???
        String name = selectedCharity.getCharityName();

        model.setCardNumberInTextFormat(cardNumberText);
        model.setAmount(amount);
        model.setToken(token);
        model.setName(name);

        return model;
    }

}
