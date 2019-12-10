package com.bojanpavlovic.omiseandroid.view;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bojanpavlovic.omiseandroid.R;
import com.bojanpavlovic.omiseandroid.model.CharityItem;
import com.bojanpavlovic.omiseandroid.model.CharityResponseModel;
import com.bojanpavlovic.omiseandroid.viewmodel.CharityViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharityListFragment extends Fragment {
    private CharityViewModel viewModel;
    private CharityAdapter adapter;
    private ListView charityListView;
    private ProgressDialog progressDialog;

    public CharityListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_charity_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        charityListView = view.findViewById(R.id.list_view);
        initUI();
    }

    private void initUI(){
        // Get proper ViewModel
        viewModel = ViewModelProviders.of(requireActivity()).get(CharityViewModel.class);
        // Initialize adapter
        CharityResponseModel responseModel = viewModel.getCharityViewModel().getValue();
        List<CharityItem> list =  responseModel != null ? responseModel.getCharityItemList() : null;
        adapter = new CharityAdapter(requireContext(), R.layout.charity_item, list);
        // Set adapter
        charityListView.setAdapter(adapter);

        progressDialog = new ProgressDialog(requireActivity());
        progressDialog.setTitle("Loading ...");
        progressDialog.setCancelable(false);

        // Observe for data changes and update UI if needed
        viewModel.getCharityViewModel().observe(this, new Observer<CharityResponseModel>() {
            @Override
            public void onChanged(CharityResponseModel charityResponseModel) {
                // Update adapter with new charity list data
                adapter.setItemList(charityResponseModel.getCharityItemList());
                viewModel.setLoaderState(false);
            }
        });

        // Observe for changes in Loader state(Show/Hide)
        viewModel.getLoaderState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    // Show loader
                    if(!progressDialog.isShowing())
                        progressDialog.show();
                }else{
                    // Hide loader
                    if(progressDialog.isShowing())
                        progressDialog.hide();
                }
            }
        });
    }

}
