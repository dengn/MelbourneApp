package com.melbournestore.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.melbournestore.adaptors.SuburbListAdapter;
import com.melbournestore.application.SysApplication;
import com.melbournestore.models.ItemEntity;

public class SuburbActivity extends Activity implements
		SearchView.OnQueryTextListener {

	private SearchView search_suburb;
	private ListView suburb_list;

	private ListView suburb_chosen;

	private ArrayAdapter<String> suburb_chosen_adapter;
	private String[] suburb_chosen_names;
	private ArrayList<String> suburb_chosen_list;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Bundle b = msg.getData();
			String suburbChosen = b.getString("suburb");
			
			switch (msg.what) {
			// get the suburb chosen
			case 1:

				Intent returnIntent = new Intent();
				returnIntent.putExtra("suburb", suburbChosen);
				setResult(RESULT_OK, returnIntent);
				finish();

				break;

			}
		}

	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.suburb_layout);

		SysApplication.getInstance().addActivity(this);

		initActionBar();

		// search_suburb = (SearchView) findViewById(R.id.search_view);
		suburb_list = (ListView) findViewById(R.id.suburb_list);

		suburb_chosen = (ListView) findViewById(R.id.suburb_chosen);

		suburb_chosen.setVisibility(View.INVISIBLE);

		suburb_chosen_names = new String[] { "Airport","Two River","City", "Cross Roads",
				"Jiefang Bei", "University Town", "South Area","Northeast Area" };
		suburb_chosen_adapter = new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_expandable_list_item_1,
				suburb_chosen_names);

		suburb_chosen.setAdapter(suburb_chosen_adapter);
		suburb_chosen.setTextFilterEnabled(true);

		search_suburb.setOnQueryTextListener(this);
		search_suburb.setSubmitButtonEnabled(false);

		List<ItemEntity> data = createTestData();

		SuburbListAdapter suburbAdapter = new SuburbListAdapter(
				getApplication(), data, mHandler);

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
			Intent upIntent = NavUtils.getParentActivityIntent(this);
			upIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(upIntent);
			finish();
			return true;

		}
		return super.onOptionsItemSelected(item);
	}

	private List<ItemEntity> createTestData() {

		List<ItemEntity> data = new ArrayList<ItemEntity>();

		ItemEntity itemEntity1 = new ItemEntity("北", "Airport");
		ItemEntity itemEntity2 = new ItemEntity("北", "Two River");
		ItemEntity itemEntity3 = new ItemEntity("City", "City");
		ItemEntity itemEntity4 = new ItemEntity("City", "Cross Roads");
		ItemEntity itemEntity5 = new ItemEntity("City", "Jiefang Bei");
		ItemEntity itemEntity6 = new ItemEntity("西", "University Town");
		ItemEntity itemEntity7 = new ItemEntity("东南", "South Area");
		ItemEntity itemEntity8 = new ItemEntity("东北", "Northeast Area");

		data.add(itemEntity1);
		data.add(itemEntity2);
		data.add(itemEntity3);
		data.add(itemEntity4);
		data.add(itemEntity5);
		data.add(itemEntity6);
		data.add(itemEntity7);
		data.add(itemEntity8);

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

		suburb_chosen.setVisibility(View.INVISIBLE);
		if (newText.length() != 0) {
			suburb_chosen.setFilterText(newText);
		} else {
			suburb_chosen.clearTextFilter();
		}
		return false;
	}

	private void initActionBar() {

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

	private long mExitTime;

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();

			} else {
				SysApplication.getInstance().exit();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
