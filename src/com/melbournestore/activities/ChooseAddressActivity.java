package com.melbournestore.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.melbournestore.adaptors.ChooseAddressListAdapter;
import com.melbournestore.application.SysApplication;

public class ChooseAddressActivity extends Activity {

	public static final int result_code_suburb = 2;
	
	private ListView chooseAddr_list;



	
	private ChooseAddressListAdapter mChooseAddressListAdapter;

	private String addr_unit;
	private String addr_street;
	private String addr_suburb;
	
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			Bundle b = msg.getData();

			switch (msg.what) {
			// unit = 1
			case 1:
				addr_unit = b.getString("unit");

			// street = 2
			case 2:
				addr_street = b.getString("street");

				break;

			}
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_address_layout);
		
		SysApplication.getInstance().addActivity(this); 

		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		getActionBar().setTitle("送货地址");
		
		
		addr_unit = "";
		addr_street = "";
		addr_suburb = "";

		chooseAddr_list = (ListView) findViewById(R.id.chooseAddr_list);

		
		mChooseAddressListAdapter = new ChooseAddressListAdapter(this, mHandler, addr_suburb);
		chooseAddr_list.setAdapter(mChooseAddressListAdapter);







	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.choose_address, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		menu.findItem(R.id.finish).setVisible(true);
		return super.onPrepareOptionsMenu(menu);
	}
	
	
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	        if (requestCode == result_code_suburb) {
	            if(resultCode == RESULT_OK){
	            	//get the suburb 
	            	
	                String suburb=data.getStringExtra("suburb");
	                addr_suburb = suburb;
	                mChooseAddressListAdapter.refresh(addr_unit, addr_street, addr_suburb);
	                chooseAddr_list.setAdapter(mChooseAddressListAdapter);
	                
	            }
	            if (resultCode == RESULT_CANCELED) {
	                //Write your code if there's no result
	            }
	        }
	    }//onActivityResult
	

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
//			Intent upIntent = NavUtils.getParentActivityIntent(this);
//			upIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//			startActivity(upIntent);
			finish();
			return true;
		case R.id.finish:
			
			Intent returnIntent = new Intent();
			returnIntent.putExtra("address",addr_unit+","+addr_street+","+addr_suburb);
			setResult(RESULT_OK,returnIntent);
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
