package com.melbournestore.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.melbournestore.adaptors.AddrZoneListAdapter;
import com.melbournestore.models.ItemEntity;

public class SuburbActivity extends Activity implements  
SearchView.OnQueryTextListener{
	
	private SearchView search_suburb;
	private ListView suburb_list;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.suburb_layout);
		
		
		initActionBar(); 
		
		//search_suburb = (SearchView) findViewById(R.id.search_view);
		suburb_list = (ListView) findViewById(R.id.suburb_list);
		
		search_suburb.setOnQueryTextListener(this);  
		search_suburb.setSubmitButtonEnabled(false);
		
        List<ItemEntity> data = createTestData();
        
        AddrZoneListAdapter suburbAdapter = new AddrZoneListAdapter(getApplication(), data);
        
        suburb_list.setAdapter(suburbAdapter);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This is called when the Home (Up) button is pressed in the action
			// bar.
			// Create a simple intent that starts the hierarchical parent
			// activity and
			// use NavUtils in the Support Package to ensure proper handling of
			// Up.
			Intent upIntent = new Intent(this, ChooseAddressActivity.class);
			if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
				// This activity is not part of the application's task, so
				// create a new task
				// with a synthesized back stack.
				TaskStackBuilder.from(this)
				// If there are ancestor activities, they should be added here.
						.addNextIntent(upIntent).startActivities();
				finish();
			} else {
				// This activity is part of the application's task, so simply
				// navigate up to the hierarchical parent activity.
				NavUtils.navigateUpTo(this, upIntent);
			}
			return true;

		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	
	private List<ItemEntity> createTestData() {
        
        List<ItemEntity> data = new ArrayList<ItemEntity>();
        
        ItemEntity itemEntity1 = new ItemEntity("Northeast", "Airport");
        ItemEntity itemEntity2 = new ItemEntity("Center", "City");
        ItemEntity itemEntity3 = new ItemEntity("Center", "Cross Roads");
        ItemEntity itemEntity4 = new ItemEntity("Center", "Jiefang Bei");
        ItemEntity itemEntity5 = new ItemEntity("Northwest", "Two River");
        ItemEntity itemEntity6 = new ItemEntity("Southwest", "University Town");
        ItemEntity itemEntity7 = new ItemEntity("Southeast", "South Area");
        
        data.add(itemEntity1);
        data.add(itemEntity2);
        data.add(itemEntity3);
        data.add(itemEntity4);
        data.add(itemEntity5);
        data.add(itemEntity6);
        data.add(itemEntity7);

        
        return data;
        
    }


	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		return false;
	}
	

	
	private void initActionBar(){
		
		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		// 自定义标题栏  
        getActionBar().setDisplayShowHomeEnabled(false);  
        getActionBar().setDisplayShowTitleEnabled(false);  
        getActionBar().setDisplayShowCustomEnabled(true);  
        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        View mTitleView = mInflater.inflate(R.layout.suburb_action_bar_layout,  
                null);  
        getActionBar().setCustomView(  
                mTitleView,  
                new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT,  
                        LayoutParams.WRAP_CONTENT));  
        search_suburb = (SearchView) mTitleView.findViewById(R.id.search_view);  
	}

}
