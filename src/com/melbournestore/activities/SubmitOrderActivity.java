package com.melbournestore.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.melbournestore.adaptors.SubmitListAdapter;

public class SubmitOrderActivity extends Activity{
	
	private Button mSubmitOrders;

	private TextView mSubmitPrice;

	private ListView mSubmitList;
	
	int priceTotal;
	
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			
		}

	};
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit_order_layout);

		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.
		actionBar.setDisplayHomeAsUpEnabled(true);
		
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
		mSubmitList.setAdapter(new SubmitListAdapter(this, mHandler, priceTotal));


		mSubmitOrders.setText("确认下单");
		
		mSubmitPrice.setText("$"+String.valueOf(priceTotal));
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    // Check which request we're responding to
	    if (requestCode == SubmitListAdapter.result_code_address) {
	    	//Get the Address choosed
	    	
	        // Make sure the request was successful
	        if (resultCode == RESULT_OK) {
	           
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
}
