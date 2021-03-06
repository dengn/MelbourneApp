package com.melbournestore.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.melbournestore.adaptors.DishListAdapter;
import com.melbournestore.application.SysApplication;
import com.melbournestore.db.SharedPreferenceUtils;
import com.melbournestore.models.Plate;
import com.melbournestore.models.Shop;
import com.melbournestore.utils.MelbourneUtils;

public class DishActivity extends Activity {

	private ListView mDishList;

	private DishListAdapter mDishListAdapter;

	private Button mDishConfirmChoice;

	private TextView mDishTotalPrice;

	private TextView mDishTotalNum;

	private String mName;
	private int mPrice;
	private int mStockMax;
	private int mNum;
	private int mLikeNum;

	private int mShopId;
	private int mPlateId;

	private int mTotalPrice;
	private int mTotalNum;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// plus = 1

				String shops_string1 = SharedPreferenceUtils
						.getCurrentChoice(DishActivity.this);
				Gson gson1 = new Gson();
				Shop[] shops1 = gson1.fromJson(shops_string1, Shop[].class);
				Plate plate1 = shops1[mShopId].getPlates()[mPlateId];
				
				mDishListAdapter.refresh(plate1);
				mDishList.setAdapter(mDishListAdapter);

				mTotalNum = MelbourneUtils.sum_number_all(shops1);
				mTotalPrice = MelbourneUtils.sum_price_all(shops1);

				mDishTotalNum.setText(String.valueOf(mTotalNum));
				mDishTotalPrice.setText("$" + String.valueOf(mTotalPrice));

				break;
			case 2:
				// minus = 2

				String shops_string2 = SharedPreferenceUtils
						.getCurrentChoice(DishActivity.this);
				Gson gson2 = new Gson();
				Shop[] shops2 = gson2.fromJson(shops_string2, Shop[].class);
				Plate plate2 = shops2[mShopId].getPlates()[mPlateId];
				
				mDishListAdapter.refresh(plate2);
				mDishList.setAdapter(mDishListAdapter);


				mTotalNum = MelbourneUtils.sum_number_all(shops2);
				mTotalPrice = MelbourneUtils.sum_price_all(shops2);

				mDishTotalNum.setText(String.valueOf(mTotalNum));
				mDishTotalPrice.setText("$" + String.valueOf(mTotalPrice));

				break;

			}
		}
	};

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
		mShopId = intent.getIntExtra("shopId", 0);
		mPlateId = intent.getIntExtra("plateId", 0);

		String shop_info = SharedPreferenceUtils.getCurrentChoice(this);
		Gson gson = new Gson();
		Shop[] shops = gson.fromJson(shop_info, Shop[].class);
		Plate plate = shops[mShopId].getPlates()[mPlateId];

		mName = plate.getName();

		getActionBar().setTitle(mName);

		mDishList = (ListView) findViewById(R.id.dish_list);
		mDishListAdapter = new DishListAdapter(this, mHandler, plate);
		mDishList.setAdapter(mDishListAdapter);

		mDishConfirmChoice = (Button) findViewById(R.id.dish_confirm_choice);
		mDishTotalPrice = (TextView) findViewById(R.id.dish_price);
		mDishTotalNum = (TextView) findViewById(R.id.dish_num_total);

		mTotalNum = MelbourneUtils.sum_number_all(shops);
		mTotalPrice = MelbourneUtils.sum_price_all(shops);

		mDishTotalNum.setText(String.valueOf(mTotalNum));
		mDishTotalPrice.setText("$" + String.valueOf(mTotalPrice));
		
		
		mDishConfirmChoice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(DishActivity.this,
						ShoppingCartActivity.class);
				startActivity(intent);

			}

		});

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
