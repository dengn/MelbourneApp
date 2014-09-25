package com.melbournestore.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.melbournestore.adaptors.SubmitListAdapter;
import com.melbournestore.application.SysApplication;

public class SubmitOrderActivity extends Activity {

	public static final int result_code_address = 3;

	private Button mSubmitOrders;

	private TextView mSubmitPrice;

	private ListView mSubmitList;

	private SubmitListAdapter mSubmitListAdapter;

	private String mDeliveryAddress;
	
	private String mDeliveryTime;

	int priceTotal;

	private PopupWindow mTimePickerPopup;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Bundle b = msg.getData();
			int action = b.getInt("action");
			switch (msg.what) {
			// get the suburb chosen
			case 1:

				// popup the time picker
				if (action == 2) {
					showTimePicker();
				}

				break;

			}

		}

	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit_order_layout);

		SysApplication.getInstance().addActivity(this);

		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.
		actionBar.setDisplayHomeAsUpEnabled(true);

		mDeliveryAddress = "";

		mDeliveryTime = "";
		
		Intent intent = getIntent();
		priceTotal = intent.getIntExtra("total_price", 0);

		mSubmitOrders = (Button) findViewById(R.id.submit_order);
		mSubmitOrders.getBackground().setAlpha(80);

		mSubmitOrders.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SubmitOrderActivity.this,
						OrderSubmittedActivity.class);
				startActivity(intent);
			}

		});

		mSubmitPrice = (TextView) findViewById(R.id.submit_price_total);

		mSubmitList = (ListView) findViewById(R.id.submit_list);

		mSubmitListAdapter = new SubmitListAdapter(this, mHandler, priceTotal,
				mDeliveryAddress, mDeliveryTime);
		mSubmitList.setAdapter(mSubmitListAdapter);

		mSubmitOrders.setText("确认下单");

		mSubmitPrice.setText("$" + String.valueOf(priceTotal));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to
		if (requestCode == result_code_address) {
			// Get the Address choosed

			// Make sure the request was successful
			if (resultCode == RESULT_OK) {
				mDeliveryAddress = data.getStringExtra("address");
				mSubmitListAdapter.refresh(priceTotal, mDeliveryAddress, mDeliveryTime);
				mSubmitList.setAdapter(mSubmitListAdapter);
			}
		}
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

	private void showTimePicker() {
		View view = View.inflate(this, R.layout.delivery_time_popup, null);

		DatePicker datePicker = (DatePicker) view
				.findViewById(R.id.delivery_time_picker);
		Button deliveryTimeConfirm = (Button) view.findViewById(R.id.delivery_time_confirm);
		
		mDeliveryTime = "2013.08.20 18:00";
		
		datePicker.init(2013, 8, 20, new OnDateChangedListener() {

			
			@Override
			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				// 获取一个日历对象，并初始化为当前选中的时间
				Calendar calendar = Calendar.getInstance();
				calendar.set(year, monthOfYear, dayOfMonth);
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy年MM月dd日  HH:mm");
				
				mDeliveryTime = format.format(calendar.getTime());
			}
		});
		
		deliveryTimeConfirm.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				mSubmitListAdapter.refresh(priceTotal, mDeliveryAddress, mDeliveryTime);
				mSubmitList.setAdapter(mSubmitListAdapter);
				mTimePickerPopup.dismiss();
			}
			
		});

		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				mTimePickerPopup.dismiss();
			}
		});
		
		LinearLayout delivery_time_popup = (LinearLayout) view
				.findViewById(R.id.delivery_time_popup);
		delivery_time_popup.startAnimation(AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.push_bottom_in));
		
		if (mTimePickerPopup == null) {
			mTimePickerPopup = new PopupWindow(this);
			mTimePickerPopup.setWidth(LayoutParams.MATCH_PARENT);
			mTimePickerPopup.setHeight(LayoutParams.MATCH_PARENT);
			mTimePickerPopup.setBackgroundDrawable(new BitmapDrawable());

			mTimePickerPopup.setFocusable(true);
			mTimePickerPopup.setOutsideTouchable(true);
		}

		mTimePickerPopup.setContentView(view);
		mTimePickerPopup.showAtLocation(datePicker, Gravity.BOTTOM, 0, 0);
		mTimePickerPopup.update();

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
