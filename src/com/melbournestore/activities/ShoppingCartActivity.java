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

import com.melbournestore.adaptors.OrderListAdapter;
import com.melbournestore.application.SysApplication;
import com.melbournestore.utils.MelbourneUtils;

public class ShoppingCartActivity extends Activity {

	private Button mConfirmOrders;

	private TextView mTotalPrice;

	private ListView mOrderList;

	private int priceTotal;

	private String[] orderNames = { "ÂéÀ±Ð¡ÁúÏº", "ËâÄàÐ¡ÁúÏº", "ÅÝ½·Ð¡ÁúÏº", "¿§à¬Ð¡ÁúÏº",
			"Ð¡ÁúÏº³´Äê¸â" };
	private int[] orderPrices = { 55, 55, 58, 58, 55 };
	private int[] orderNumbers = { 1, 1, 1, 2, 2 };

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Bundle b = msg.getData();
			int position = b.getInt("position");
			switch (msg.what) {
			// plus = 1
			case 1:
				priceTotal+=orderPrices[position];
				mTotalPrice.setText("$"+String.valueOf(priceTotal));
				break;
			// minus = 2
			case 2:
				priceTotal-=orderPrices[position];
				mTotalPrice.setText("$"+String.valueOf(priceTotal));
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

		mConfirmOrders = (Button) findViewById(R.id.confirm_order);
		mTotalPrice = (TextView) findViewById(R.id.price_total);

		mOrderList = (ListView) findViewById(R.id.shopping_list);
		mOrderList.setAdapter(new OrderListAdapter(this, mHandler, orderNames,
				orderPrices, orderNumbers));

		priceTotal = MelbourneUtils.sum_price(orderPrices,orderNumbers);
		mTotalPrice.setText("$"+String.valueOf(priceTotal));
		
		
		mConfirmOrders.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(ShoppingCartActivity.this, SubmitOrderActivity.class);
				intent.putExtra("total_price", priceTotal);
				
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
                                    Toast.makeText(this, "ÔÙ°´Ò»´ÎÍË³ö³ÌÐò", Toast.LENGTH_SHORT).show();
                                    mExitTime = System.currentTimeMillis();

                            } else {
                            		SysApplication.getInstance().exit();  
                            }
                            return true;
                    }
                    return super.onKeyDown(keyCode, event);
            }
}
