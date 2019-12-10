package com.bojanpavlovic.omiseandroid.view;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bojanpavlovic.omiseandroid.R;
import com.bojanpavlovic.omiseandroid.model.CharityItem;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CharityAdapter extends ArrayAdapter<CharityItem> {
    private List<CharityItem> itemList = new ArrayList<>();

    public CharityAdapter(@NonNull Context context, int resource, List<CharityItem> itemList) {
        super(context, resource);
        this.itemList = itemList;
    }

    public void setItemList(List<CharityItem> list){
        itemList = list;
        notifyDataSetInvalidated();
    }

    @Nullable
    @Override
    public CharityItem getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public int getCount() {
        int size = (itemList != null) ? itemList.size() : 0;
        return size;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        CharityItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.charity_item, parent, false);
        }

        ImageView logo = convertView.findViewById(R.id.logo);
        // TODO Attach click listener !!!
        TextView charityName = convertView.findViewById(R.id.name);

        // Populate with proper data for given item(position)
        String logoTextLink = item != null ? item.getLogoUrl() : "";
        Uri logoUri = Uri.parse(logoTextLink);
        Glide.with(parent.getContext())
                .load(logoUri)
                .fitCenter()
                .placeholder(android.R.drawable.btn_star)
                .into(logo);

        charityName.setText(item.getName());

        return convertView;
    }

}
