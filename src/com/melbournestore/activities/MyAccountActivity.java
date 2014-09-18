package com.melbournestore.activities;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.melbournestore.adaptors.MyAccountListAdapter;
import com.melbournestore.application.SysApplication;

public class MyAccountActivity extends Activity{
	
	public static final int choose_address_code = 4;
	
	private Button mLogout;

	private ListView mMyAccountList;
	
	private MyAccountListAdapter mMyAccountListAdapter;
	
	private PopupWindow mpopupWindow; 
	
	private String mAddress;
	
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			Bundle b = msg.getData();
			int action = b.getInt("action");
			switch (msg.what) {

			case 1:
				// Popup Menu
				if(action == 1){
					showPopMenu();
				}

				break;

			}
		}
	};
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myaccount_layout);
		
		SysApplication.getInstance().addActivity(this); 
		
		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		mAddress ="";
		
		
		mLogout = (Button) findViewById(R.id.logout);
		
		mMyAccountList = (ListView) findViewById(R.id.myaccount_list);
		
		mMyAccountListAdapter = new MyAccountListAdapter(this, mHandler, mAddress);
		mMyAccountList.setAdapter(mMyAccountListAdapter);
		

		
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    // Check which request we're responding to
	    if (requestCode == choose_address_code) {
	    	//Get the Address choosed
	    	
	        // Make sure the request was successful
	        if (resultCode == RESULT_OK) {
	        	mAddress = data.getStringExtra("address");
	        	mMyAccountListAdapter.refresh(mAddress);
	        	mMyAccountList.setAdapter(mMyAccountListAdapter);
	        }
	    }
	}
	
	
	
	private void showPopMenu() {  
        View view = View.inflate(this, R.layout.profile_popup_menu, null);  
 
        RelativeLayout profile_camera = (RelativeLayout) view.findViewById(R.id.profile_camera);  
        RelativeLayout profile_album = (RelativeLayout) view.findViewById(R.id.profile_album);  
        Button profile_cancel = (Button) view.findViewById(R.id.profile_cancel);  
  
        profile_camera.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
        	
        });  
        profile_album.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
        	
        });  
        profile_cancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mpopupWindow.dismiss(); 
			}
        	
        });   

        view.setOnClickListener(new OnClickListener() {  
              
            @Override  
            public void onClick(View v) {  
                  
                mpopupWindow.dismiss();  
            }  
        });  
          
        view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));  
        LinearLayout profile_popup = (LinearLayout) view.findViewById(R.id.profile_popup);  
        profile_popup.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_bottom_in));  
          
        if(mpopupWindow==null){  
            mpopupWindow = new PopupWindow(this);  
            mpopupWindow.setWidth(LayoutParams.MATCH_PARENT);  
            mpopupWindow.setHeight(LayoutParams.MATCH_PARENT);  
            mpopupWindow.setBackgroundDrawable(new BitmapDrawable());  
  
            mpopupWindow.setFocusable(true);  
            mpopupWindow.setOutsideTouchable(true);  
        }  
          
        mpopupWindow.setContentView(view);  
        mpopupWindow.showAtLocation(profile_cancel, Gravity.BOTTOM, 0, 0);  
        mpopupWindow.update();  
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
