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

import com.google.gson.Gson;
import com.melbournestore.adaptors.SubmitListAdapter;
import com.melbournestore.adaptors.SubmitListCouponAdapter;
import com.melbournestore.adaptors.SubmitListMemoAdapter;
import com.melbournestore.application.SysApplication;
import com.melbournestore.db.SharedPreferenceUtils;
import com.melbournestore.models.Order_user;
import com.melbournestore.models.User;
import com.melbournestore.utils.MelbourneUtils;

public class SubmitOrderActivity extends Activity {

	public static final int result_code_address = 3;

	private Button mSubmitOrders;

	private TextView mSubmitPrice;

	private ListView mSubmitList;
	private ListView mSubmitMemoList;
	private ListView mSubmitCouponList;

	private SubmitListAdapter mSubmitListAdapter;
	private SubmitListMemoAdapter mSubmitListMemoAdapter;
	private SubmitListCouponAdapter mSubmitListCouponAdapter;


	int priceTotal;

	private PopupWindow mTimePickerPopup;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			// get the suburb chosen
			case 1:
				Bundle b1 = msg.getData();
				int action = b1.getInt("action");
				// popup the time picker
				if (action == 2) {
					showTimePicker();
				}

				break;
			case 2:
				Bundle b2 = msg.getData();
				String memo = b2.getString("memo");
				
				String current_order = SharedPreferenceUtils.getCurrentOrder(SubmitOrderActivity.this);
				Gson gson = new Gson();
				Order_user currentOrder = gson.fromJson(current_order, Order_user.class);
				currentOrder.setRemark(memo);
				SharedPreferenceUtils.saveCurrentOrder(SubmitOrderActivity.this, gson.toJson(currentOrder));

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
		mSubmitMemoList = (ListView) findViewById(R.id.submit_memo_list);
		mSubmitCouponList = (ListView) findViewById(R.id.submit_coupon_list);

		String users_string = SharedPreferenceUtils
				.getLoginUser(SubmitOrderActivity.this);
		Gson gson = new Gson();
		User[] users = gson.fromJson(users_string, User[].class);
		User activeUser = users[MelbourneUtils.getActiveUser(users)];
		
		
		String current_order = SharedPreferenceUtils.getCurrentOrder(SubmitOrderActivity.this);
		Order_user currentOrder = gson.fromJson(current_order, Order_user.class);
		

		mSubmitListAdapter = new SubmitListAdapter(this, mHandler, activeUser,
				currentOrder);
		mSubmitList.setAdapter(mSubmitListAdapter);

		mSubmitListMemoAdapter = new SubmitListMemoAdapter(this, mHandler, currentOrder);
		mSubmitMemoList.setAdapter(mSubmitListMemoAdapter);

		mSubmitListCouponAdapter = new SubmitListCouponAdapter(this, mHandler);
		mSubmitCouponList.setAdapter(mSubmitListCouponAdapter);

		mSubmitOrders.setText("ȷ���µ�");

		mSubmitPrice.setText("���Ʒ��ã�$" + String.valueOf(priceTotal));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to
		if (requestCode == result_code_address) {
			// Get the Address choosed

			// Make sure the request was successful
			if (resultCode == RESULT_OK) {
				
				String users_string = SharedPreferenceUtils.getLoginUser(SubmitOrderActivity.this);
				Gson gson  = new Gson();
				User[] users = gson.fromJson(users_string, User[].class);
				User activeUser = users[MelbourneUtils.getActiveUser(users)];
				
				String current_order = SharedPreferenceUtils.getCurrentOrder(SubmitOrderActivity.this);
				Order_user currentOrder = gson.fromJson(current_order, Order_user.class);
				
				mSubmitListAdapter.refresh(activeUser, currentOrder);
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
		Button deliveryTimeConfirm = (Button) view
				.findViewById(R.id.delivery_time_confirm);
		
		String current_order = SharedPreferenceUtils.getCurrentOrder(SubmitOrderActivity.this);
		Gson gson = new Gson();
		final Order_user currentOrder = gson.fromJson(current_order, Order_user.class);
		

		currentOrder.setDeliveryTime("2013.08.20 18:00");

		datePicker.init(2013, 8, 20, new OnDateChangedListener() {

			@Override
			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				// ��ȡһ���������󣬲���ʼ��Ϊ��ǰѡ�е�ʱ��
				Calendar calendar = Calendar.getInstance();
				calendar.set(year, monthOfYear, dayOfMonth);
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy��MM��dd��  HH:mm");

				currentOrder.setDeliveryTime(format.format(calendar.getTime()));
			}
		});

		deliveryTimeConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String users_string = SharedPreferenceUtils.getLoginUser(SubmitOrderActivity.this);
				Gson gson  = new Gson();
				User[] users = gson.fromJson(users_string, User[].class);
				User activeUser = users[MelbourneUtils.getActiveUser(users)];
				
				SharedPreferenceUtils.saveCurrentOrder(SubmitOrderActivity.this, gson.toJson(currentOrder));

				mSubmitListAdapter.refresh(activeUser, currentOrder);
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
				Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();

			} else {
				SysApplication.getInstance().exit();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
