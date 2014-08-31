package com.melbournestore.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.melbournestore.adaptors.AddrZoneListAdapter;
import com.melbournestore.models.ItemEntity;

public class SuburbActivity extends ListActivity{
	
	private SearchView search_suburb;
	private ListView suburb_list;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.suburb_layout);
		
		ListView listView = getListView();

		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		//search_suburb = (SearchView) findViewById(R.id.search_view);
		//suburb_list = (ListView) findViewById(R.id.suburb_list);
		
		
		
        List<ItemEntity> data = createTestData();
        
        AddrZoneListAdapter suburbAdapter = new AddrZoneListAdapter(getApplication(), data);
        
        listView.setAdapter(suburbAdapter);
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
	


}
