package com.melbournestore.adaptors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.melbournestore.activities.ChooseAddressActivity;
import com.melbournestore.activities.DeliveryNoticeActivity;
import com.melbournestore.activities.R;
import com.melbournestore.activities.SubmitOrderActivity;

public class SubmitListMemoAdapter extends BaseAdapter{
	

	
	Handler mHandler;
	Context mContext;


	
	private static LayoutInflater inflater = null;
	
	public SubmitListMemoAdapter(Context context, Handler handler) {
		// TODO Auto-generated constructor stub

		
		mContext = context;
		mHandler = handler;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void refresh(){

		notifyDataSetChanged();
	}


	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
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

        holder_edittext = new viewHolder_edittext();
        convertView = inflater.inflate(R.layout.submit_list_item_memo, parent, false);
        	
        holder_edittext.title = (TextView)convertView.findViewById(R.id.memo_title);
        holder_edittext.memo = (EditText)convertView.findViewById(R.id.memo_info);
        	
        holder_edittext.title.setText("Æ«         ºÃ");
        holder_edittext.memo.setHint("ÇëÊäÈë±¸×¢");
        	
        convertView.setTag(holder_edittext);


		return convertView;
		
	}
	
	

    
    class viewHolder_edittext{

        private TextView title;

        private EditText memo;

    }

}
