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

import com.melbournestore.adaptors.SubmitListAdapter;
import com.melbournestore.application.SysApplication;

public class SubmitOrderActivity extends Activity{
	
	
	public static final int result_code_address = 3;
	
	private Button mSubmitOrders;

	private TextView mSubmitPrice;

	private ListView mSubmitList;
	
	private SubmitListAdapter mSubmitListAdapter;
	
	private String mDeliveryAddress;
	
	int priceTotal;
	
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			
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
		
		
		
		Intent intent = getIntent();
		priceTotal=intent.getIntExtra("total_price", 0);

		mSubmitOrders = (Button) findViewById(R.id.submit_order);
		
		mSubmitOrders.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SubmitOrderActivity.this, OrderSubmittedActivity.class);
				startActivity(intent);
			}
			
		});
		
		mSubmitPrice = (TextView) findViewById(R.id.submit_price_total);

		mSubmitList = (ListView) findViewById(R.id.submit_list);
		
		mSubmitListAdapter = new SubmitListAdapter(this, mHandler, priceTotal, mDeliveryAddress);
		mSubmitList.setAdapter(mSubmitListAdapter);


		mSubmitOrders.setText("确认下单");
		
		mSubmitPrice.setText("$"+String.valueOf(priceTotal));
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    // Check which request we're responding to
	    if (requestCode == result_code_address) {
	    	//Get the Address choosed
	    	
	        // Make sure the request was successful
	        if (resultCode == RESULT_OK) {
	        	mDeliveryAddress = data.getStringExtra("address");
	        	mSubmitListAdapter.refresh(priceTotal, mDeliveryAddress);
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
