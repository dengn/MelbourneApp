package com.melbournestore.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.melbournestore.activities.R;
import com.melbournestore.adaptors.MyOrderListAdapter;

public class MyOrdersFragment extends Fragment{
	
	
	//private Context mContext;
	
    public MyOrdersFragment() {
        

    }

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_myorders, container, false);
        ListView myorders_list = (ListView) rootView.findViewById(R.id.my_orders);
        myorders_list.setAdapter(new MyOrderListAdapter());
        
        return rootView;
    }
}
