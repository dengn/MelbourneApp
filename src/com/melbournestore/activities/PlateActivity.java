/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.melbournestore.activities;



import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.melbournestore.adaptors.PlateListAdapter;

public class PlateActivity extends Activity {

	private static final String TAG = "Melbourne";
	
    private ListView mPlatesList;
    
    private Button mConfirmChoice;
    
    private TextView mTotalPrice;
    
    private int[] plateImages = {R.drawable.plate1, R.drawable.plate2, R.drawable.plate3, R.drawable.plate4, R.drawable.plate5};

    private String[] plateTitles = {"麻辣小龙虾",
        "蒜泥小龙虾",
        "泡椒小龙虾",
        "咖喱小龙虾",
        "小龙虾炒年糕"};

    private int[] platePrices = {55, 55, 58, 58, 55};
    private String[] plateStocks = {"今日库存20份","今日库存20份","今日库存20份","今日库存10kg","今日库存10份"};
    
    private String[] plateNumbers = {"1","1","2","1","2"};
    
    private int totalPrice = 0; 
    
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle b = msg.getData();
            int position = b.getInt("position");
            switch(msg.what) {
            	//plus = 1 
                case 1:
                	totalPrice+=platePrices[position];
                	mTotalPrice.setText("$"+String.valueOf(totalPrice));
                    break;
                //minus = 2
                case 2:
                	totalPrice-=platePrices[position];
                	mTotalPrice.setText("$"+String.valueOf(totalPrice));
                    break;

            }
        }
         
    };
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plate_layout);

        // Set up action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        actionBar.setDisplayHomeAsUpEnabled(true);
        

        
        mPlatesList = (ListView) findViewById(R.id.plates_list);
        
        String[] platePrices_string = new String[platePrices.length];
        for(int i=0;i<platePrices.length;i++){
        	platePrices_string[i]="$"+String.valueOf(platePrices[i]);
        }
        
        mPlatesList.setAdapter(new PlateListAdapter(this, mHandler, plateTitles, platePrices_string, plateStocks, plateNumbers, plateImages));

        
        mConfirmChoice = (Button) findViewById(R.id.confirm_choice);
        mConfirmChoice.bringToFront();
        
        mTotalPrice = (TextView) findViewById(R.id.confirm_price);
        mTotalPrice.setText("$"+String.valueOf(totalPrice));
        
        Intent intent = getIntent();
        int category = intent.getIntExtra("Category",0);
        Log.d(TAG,"category: "+String.valueOf(category));
        
        
        
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed in the action bar.
                // Create a simple intent that starts the hierarchical parent activity and
                // use NavUtils in the Support Package to ensure proper handling of Up.
                Intent upIntent = new Intent(this, MainActivity.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder.from(this)
                            // If there are ancestor activities, they should be added here.
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    
//    /* The click listner for ListView in the navigation drawer */
//    private class PlateItemClickListener implements ListView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            //selectItem(position);
//        	Log.d(TAG, String.valueOf(position)+" plat item clicked");
//        }
//    }
//    
    
}
