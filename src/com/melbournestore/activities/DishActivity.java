package com.melbournestore.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.melbournestore.adaptors.DishListAdapter;
import com.melbournestore.application.SysApplication;
import com.melbournestore.db.SharedPreferenceUtils;
import com.melbournestore.models.Plate;

public class DishActivity extends Activity {

	private ListView mDishList;

	private DishListAdapter mDishListAdapter;

	private Button mDishConfirmChoice;

	private TextView mDishTotalPrice;

	private TextView mDishTotalNum;

	private int mPosition;
	
	private String mName;
	private int mPrice;
	private int mStock;
	private int mStockMax;
	private int mNum;
	private int mLikeNum;

	private Handler mHandler = new Handler();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dish_layout);

		SysApplication.getInstance().addActivity(this);

		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.
		actionBar.setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();
		mPosition = intent.getIntExtra("position", 0);

		String dish_info = SharedPreferenceUtils.getCurrentChoice(this);
		Gson gson  = new Gson();
		Plate plate = gson.fromJson(dish_info, Plate.class);
		mName = plate.getName();
		mPrice = plate.getPrice();
		mNum = plate.getNumber();
		mStock = plate.getStock();
		mStockMax = plate.getStockMax();
		mLikeNum = plate.getLikeNum();
		
		getActionBar().setTitle(mName);
		

		mDishList = (ListView) findViewById(R.id.dish_list);
		mDishListAdapter = new DishListAdapter(this, mHandler, mPosition, mName, mPrice, mNum, mStock, mStockMax, mLikeNum);
		mDishList.setAdapter(mDishListAdapter);

		mDishConfirmChoice = (Button) findViewById(R.id.dish_confirm_choice);
		mDishTotalPrice = (TextView) findViewById(R.id.dish_price);
		mDishTotalNum = (TextView) findViewById(R.id.dish_num_total);

		mDishTotalPrice.setText("$128");
		mDishTotalNum.setText("5");
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
