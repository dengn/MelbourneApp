package com.melbournestore.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.melbournestore.adaptors.OrderListAdapter;
import com.melbournestore.application.SysApplication;
import com.melbournestore.db.SharedPreferenceUtils;
import com.melbournestore.models.Plate;
import com.melbournestore.models.Shop;
import com.melbournestore.models.User;
import com.melbournestore.utils.MelbourneUtils;

public class ShoppingCartActivity extends Activity {

	public static final int LOGIN_CODE = 8;

	private Button mConfirmOrders;

	private TextView mTotalPrice;

	private ListView mOrderList;

	private OrderListAdapter mOrderListAdapter;

	private int totalPrice;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Bundle b = msg.getData();
			int position = b.getInt("position");
			switch (msg.what) {
			// plus = 1
			case 1:
				// totalPrice += orderPrices[position];

				String shops_string1 = SharedPreferenceUtils
						.getCurrentChoice(ShoppingCartActivity.this);
				Gson gson1 = new Gson();
				Shop[] shops1 = gson1.fromJson(shops_string1, Shop[].class);

				totalPrice = MelbourneUtils.sum_price_all(shops1);

				mTotalPrice.setText("$" + String.valueOf(totalPrice));
				break;
			// minus = 2
			case 2:
				// totalPrice -= orderPrices[position];

				String shops_string2 = SharedPreferenceUtils
						.getCurrentChoice(ShoppingCartActivity.this);
				Gson gson2 = new Gson();
				Shop[] shops2 = gson2.fromJson(shops_string2, Shop[].class);

				totalPrice = MelbourneUtils.sum_price_all(shops2);

				mTotalPrice.setText("$" + String.valueOf(totalPrice));
				break;

			}
		}

	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopping_cart_layout);

		SysApplication.getInstance().addActivity(this);

		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.
		actionBar.setDisplayHomeAsUpEnabled(true);

		getActionBar().setTitle("购物车");

		String shops_string = SharedPreferenceUtils.getCurrentChoice(this);
		Gson gson = new Gson();
		Shop[] shops = gson.fromJson(shops_string, Shop[].class);

		totalPrice = MelbourneUtils.sum_price_all(shops);

		Plate[] plates_chosen = MelbourneUtils.getPlatesChosen(shops);

		mConfirmOrders = (Button) findViewById(R.id.confirm_order);
		mConfirmOrders.getBackground().setAlpha(80);

		mTotalPrice = (TextView) findViewById(R.id.price_total);

		mOrderList = (ListView) findViewById(R.id.shopping_list);

		mOrderListAdapter = new OrderListAdapter(this, mHandler, plates_chosen);
		mOrderList.setAdapter(mOrderListAdapter);

		mTotalPrice.setText("共计费用：$" + String.valueOf(totalPrice));

		mConfirmOrders.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String users_string = SharedPreferenceUtils
						.getLoginUser(ShoppingCartActivity.this);
				Gson gson = new Gson();
				User[] users = gson.fromJson(users_string, User[].class);
				int active_value = MelbourneUtils.getActiveUser(users);

				if (active_value >= 0) {

					if (totalPrice == 0) {
						new AlertDialog.Builder(ShoppingCartActivity.this)
								.setMessage("请选择菜品")
								.setPositiveButton("确定",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialoginterface,
													int i) {

											}
										}).show();
					} else {

						Intent intent = new Intent(ShoppingCartActivity.this,
								SubmitOrderActivity.class);
						intent.putExtra("total_price", totalPrice);

						startActivity(intent);
					}
				} else {
					Intent intent = new Intent(ShoppingCartActivity.this,
							LoginActivity.class);
					// intent.putExtra("total_price", priceTotal);

					startActivityForResult(intent, LOGIN_CODE);
				}

			}

		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to

		switch (requestCode) {
		case LOGIN_CODE:
			// Get the Address chosen

			// Make sure the request was successful
			if (resultCode == RESULT_OK) {
				SysApplication.setLoginStatus(true);
			}
			break;

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
