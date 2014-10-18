package com.melbournestore.activities;

import com.melbournestore.application.SysApplication;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

public class MyCouponActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycoupon_layout);
		
		SysApplication.getInstance().addActivity(this);
		
		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.
		actionBar.setDisplayHomeAsUpEnabled(true);

		getActionBar().setTitle("�ҵ��Ż�ȯ");
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
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
