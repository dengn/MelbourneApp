package com.melbournestore.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.melbournestore.activities.R;

public class CategoryListHeaderView extends LinearLayout{
	
	private Context mContext;     
    private SearchView mSearchView; 

	public CategoryListHeaderView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;     
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_list_item_header, null);   
         
        addView(view);     
        mSearchView = (SearchView) view.findViewById(R.id.category_search_view);
        
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			
        	@Override
        	public boolean onQueryTextSubmit(String query) {
        		// TODO Auto-generated method stub
        		return false;
        	}

        	@Override
        	public boolean onQueryTextChange(String newText) {
        		// TODO Auto-generated method stub

//        		mSearchView.setVisibility(View.INVISIBLE);
//        		if (newText.length() != 0) {
//        			mSearchView.setFilterText(newText);
//        		} else {
//        			mSearchView.clearTextFilter();
//        		}
        		return false;
        	}
		});
        mSearchView.setSubmitButtonEnabled(false);
	}

}
