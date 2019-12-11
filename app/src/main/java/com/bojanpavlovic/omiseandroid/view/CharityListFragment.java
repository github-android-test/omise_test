package com.bojanpavlovic.omiseandroid.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bojanpavlovic.omiseandroid.R;
import com.bojanpavlovic.omiseandroid.interfaces.ICharityResponse;
import com.bojanpavlovic.omiseandroid.model.CharityItem;
import com.bojanpavlovic.omiseandroid.model.CharityResponseModel;
import com.bojanpavlovic.omiseandroid.viewmodel.CustomViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharityListFragment extends Fragment implements CharityAdapter.ICharityItemClickListener {
    private CustomViewModel viewModel;
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
        initLoader();

        // Get proper ViewModel
        viewModel = ViewModelProviders.of(requireActivity()).get(CustomViewModel.class);
        // Initialize adapter
        ICharityResponse responseModel = viewModel.getCharities().getValue();
        List<CharityItem> list = responseModel != null ? ((CharityResponseModel)responseModel).getCharityItemList() : null;
        adapter = new CharityAdapter(requireContext(), R.layout.charity_item, list, this);
        // Set adapter
        charityListView.setAdapter(adapter);
        startObserving();
    }

    // Initializes Progress Dialog
    private void initLoader(){
        progressDialog = new ProgressDialog(requireActivity());
        progressDialog.setTitle(getString(R.string.loading_text));
        progressDialog.setCancelable(false);
    }

    private void startObserving(){
        // Observe for data changes and update UI if needed
        viewModel.getCharityLiveData().observe(this, new Observer<ICharityResponse>() {
            @Override
            public void onChanged(ICharityResponse charityResponseModel) {
                // Check if we got valid response or error
                if(isResponseOk(charityResponseModel)){
                    // Update adapter with new charity list data
                    adapter.setItemList(((CharityResponseModel)charityResponseModel).getCharityItemList());
                    viewModel.setLoaderState(false);
                }else{
                    // Show error toast
                    viewModel.setLoaderState(false);
                    Toast.makeText(requireActivity(), getString(R.string.error_text), Toast.LENGTH_LONG).show();
                }
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
                        progressDialog.dismiss();
                }
            }
        });
    }

    @Override
    public void onItemClicked(int charityId, String itemName) {
        viewModel.onItemClicked(charityId, itemName);
    }

    // If REST returns error response, error flag is set
    // We check that flag to validate proper response
    private boolean isResponseOk(ICharityResponse response){
        return !response.isError();
    }

}
