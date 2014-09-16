package com.melbournestore.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;
import android.widget.TextView;

public class DeliveryNoticeActivity extends Activity{
	
	private TextView notice_info1;
	private TextView notice_info2;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delivery_notice_layout);
		
		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		
		notice_info1 = (TextView) findViewById(R.id.delivery_notice_info1);
		notice_info2 = (TextView) findViewById(R.id.delivery_notice_info2);
		
		notice_info1.setText("����ʱ���Ϊ17:00 - 02:00");
		notice_info2.setText("���ͷ��ø������򶨼۲�ͬ�������½�");
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
