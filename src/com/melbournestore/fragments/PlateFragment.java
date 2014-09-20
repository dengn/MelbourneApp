package com.melbournestore.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.melbournestore.activities.R;
import com.melbournestore.adaptors.CategoryListAdapter;
import com.melbournestore.db.DataResourceUtils;

public class PlateFragment extends Fragment{
	
	
    public PlateFragment() {
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plate, container, false);

        
        ListView category = (ListView) rootView.findViewById(R.id.category);
        category.setAdapter(new CategoryListAdapter(getActivity(), DataResourceUtils.categoryItemsImages));
        
        return rootView;
    }

}
