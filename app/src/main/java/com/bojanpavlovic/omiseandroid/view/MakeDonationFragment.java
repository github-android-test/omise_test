package com.bojanpavlovic.omiseandroid.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bojanpavlovic.omiseandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakeDonationFragment extends Fragment {


    public MakeDonationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_make_donation, container, false);
    }

}
