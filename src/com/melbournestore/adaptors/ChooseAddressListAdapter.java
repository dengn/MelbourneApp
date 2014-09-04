package com.melbournestore.adaptors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.melbournestore.activities.R;
import com.melbournestore.activities.SuburbActivity;

public class ChooseAddressListAdapter extends BaseAdapter {
	
	public static final int result_code_suburb = 2;

	private Handler mHandler;
	private Context mContext;
	
	private String addr_unit;
	private String addr_street;
	private String addr_suburb;

	private static LayoutInflater inflater = null;

	public ChooseAddressListAdapter(Context context, Handler handler) {
		// TODO Auto-generated constructor stub

		mContext = context;
		mHandler = handler;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		viewHolder_edittext holder_edittext = null;
		viewHolder_activity holder_activity = null;

		switch (position) {
		case 0:
        	holder_edittext = new viewHolder_edittext();
        	convertView = inflater.inflate(R.layout.submit_list_item_memo, parent, false);
        	
        	holder_edittext.title = (TextView)convertView.findViewById(R.id.memo_title);
        	holder_edittext.text = (EditText)convertView.findViewById(R.id.memo_info);
        	
        	holder_edittext.title.setText("Unit/No.");
        	
        	holder_edittext.text.setOnFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        final int position = v.getId();
                        final EditText Caption = (EditText) v;
                        addr_unit = Caption.getText().toString();
                        
        				Message message = new Message();
        				Bundle b = new Bundle();
        				// send the position
        				b.putString("unit", addr_unit);
        				message.setData(b);

        				// unit = 1
        				message.what = 1;

        				mHandler.sendMessage(message);
                    }
                }
            });
        	
        	convertView.setTag(holder_edittext);
			break;
		case 1:
        	holder_edittext = new viewHolder_edittext();
        	convertView = inflater.inflate(R.layout.submit_list_item_memo, parent, false);
        	
        	holder_edittext.title = (TextView)convertView.findViewById(R.id.memo_title);
        	holder_edittext.text = (EditText)convertView.findViewById(R.id.memo_info);
        	
        	holder_edittext.title.setText("Street");
        	
        	holder_edittext.text.setOnFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        final int position = v.getId();
                        final EditText Caption = (EditText) v;
                        addr_street = Caption.getText().toString();
                        
        				Message message = new Message();
        				Bundle b = new Bundle();
        				// send the position
        				b.putString("street", addr_street);
        				message.setData(b);

        				// street = 2
        				message.what = 2;

        				mHandler.sendMessage(message);
                    }
                }
            });
        	
        	convertView.setTag(holder_edittext);
			
			break;
		case 2:
        	holder_activity = new viewHolder_activity();
        	convertView = inflater.inflate(R.layout.submit_list_item_address, parent, false);
        	
        	holder_activity.title = (TextView)convertView.findViewById(R.id.address_title);
        	holder_activity.info = (TextView)convertView.findViewById(R.id.address_info);
        	holder_activity.rightArrow = (ImageView) convertView.findViewById(R.id.address_rightarrow);
        	
        	
        	holder_activity.title.setText("Suburb");
        	holder_activity.info.setText("Two River");
        	holder_activity.rightArrow.setImageResource(R.drawable.right_arrow);
        	
        	convertView.setTag(holder_activity);
        	
        	convertView.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(mContext, SuburbActivity.class);
					((Activity) mContext).startActivityForResult(intent, result_code_suburb);
				}
        		
        	});
			break;
		}

		return convertView;
	}

	class viewHolder_edittext {

		private TextView title;

		private EditText text;

	}

	class viewHolder_activity {

		private TextView title;

		private TextView info;

		private ImageView rightArrow;
	}

}