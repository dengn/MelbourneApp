package com.melbournestore.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.melbournestore.activities.R;
import com.melbournestore.adaptors.CategoryListAdapter;
import com.melbournestore.db.DataResourceUtils;

public class PlateFragment extends Fragment{
	
	Context mContext;
	
	ListView category;
	
	CategoryListHeaderView headerView;
	
    public PlateFragment(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plate, container, false);

           
        
        category = (ListView) rootView.findViewById(R.id.category);
        
        headerView = new CategoryListHeaderView(mContext);
        category.addHeaderView(headerView); 
        
        
        String[] shopSubItems = new String[DataResourceUtils.shopItemsImages.length];
        for(int i=0;i<shopSubItems.length;i++)
        {
        	shopSubItems[i] = DataResourceUtils.plateNames[i][0]+"、 "+DataResourceUtils.plateNames[i][1]+"限时特卖中";
        }
        category.setAdapter(new CategoryListAdapter(getActivity(), DataResourceUtils.shopItemsImages, DataResourceUtils.shopItems, shopSubItems));
        
        //headerView.setVisibility(View.INVISIBLE);
        
//        category.setOnTouchListener(new OnTouchListener(){
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
////				switch (event.getAction()) { 
////				case MotionEvent.ACTION_DOWN:  
////					headerView.setVisibility(View.VISIBLE);
////	                break;  
////				case MotionEvent.ACTION_UP:
////					headerView.setVisibility(View.INVISIBLE);
////	                break; 
////				}
//				return false;
//			}
//        	
//        });
        
        return rootView;
    }

}
