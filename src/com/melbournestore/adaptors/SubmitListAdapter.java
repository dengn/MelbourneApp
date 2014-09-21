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

public class SubmitListAdapter extends BaseAdapter{
	
	
	
    final int TYPE_TEXT   = 0;
    final int TYPE_ACTIVITY  = 1;
    final int TYPE_CHECKBOX   = 2;
    final int TYPE_EDITTEXT= 3;
    final int TYPE_URL =4;
    final int TYPE_EMPTY = 5;
	
	Handler mHandler;
	Context mContext;
	int mPriceTotal;
	
	String mAddress;
	String mTime;
	
	private static LayoutInflater inflater = null;
	
	public SubmitListAdapter(Context context, Handler handler, int priceTotal, String address, String time) {
		// TODO Auto-generated constructor stub

		
		mContext = context;
		mHandler = handler;
		mPriceTotal = priceTotal;
		mAddress = address;
		mTime = time;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void refresh(int priceTotal, String address, String time){
		mPriceTotal = priceTotal;
		mAddress = address;
		mTime = time;
		notifyDataSetChanged();
	}

	@Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        int p = position;
        if(p == 0)
            return TYPE_TEXT;
        else if(p == 1)
            return TYPE_ACTIVITY;
        else if(p == 2)
            return TYPE_CHECKBOX;
        else if(p == 3)
            return TYPE_EDITTEXT;
        else if(p == 4)
            return TYPE_URL;
        else 
            return TYPE_EMPTY;
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 6;
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
		viewHolder_text holder_text = null;
		viewHolder_activity holder_activity = null;
		viewHolder_checkbox holder_checkbox = null;
		viewHolder_edittext holder_edittext = null;
		viewHolder_url holder_url = null;
		viewHolder_empty holder_empty = null;
         
        int type = getItemViewType(position);
		
        switch(type)
        {
        case TYPE_TEXT:
        	holder_text = new viewHolder_text();
        	convertView = inflater.inflate(R.layout.submit_list_item_phone, parent, false);
        	
        	holder_text.title = (TextView)convertView.findViewById(R.id.phone_title);
        	holder_text.info = (TextView)convertView.findViewById(R.id.phone_info);
        	
        	holder_text.title.setText("电话号码");
        	holder_text.info.setText("0485245801");
        	
        	convertView.setTag(holder_text);
        	
        	
        	
        	break;
        case TYPE_ACTIVITY:
        	holder_activity = new viewHolder_activity();
        	convertView = inflater.inflate(R.layout.submit_list_item_address, parent, false);
        	
        	holder_activity.title = (TextView)convertView.findViewById(R.id.address_title);
        	holder_activity.info = (TextView)convertView.findViewById(R.id.address_info);
        	holder_activity.rightArrow = (ImageView) convertView.findViewById(R.id.address_rightarrow);
        	
        	
        	holder_activity.title.setText("运送地址 "+mAddress);
        	holder_activity.rightArrow.setImageResource(R.drawable.right_arrow);
        	
        	convertView.setTag(holder_activity);
        	
        	convertView.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(mContext, ChooseAddressActivity.class);
					((Activity) mContext).startActivityForResult(intent, SubmitOrderActivity.result_code_address);
				}
        		
        	});
        	
        	break;
        case TYPE_CHECKBOX:
        	
        	holder_checkbox = new viewHolder_checkbox();
        	convertView = inflater.inflate(R.layout.submit_list_item_time, parent, false);
        	
        	holder_checkbox.title = (TextView)convertView.findViewById(R.id.time_title);
        	holder_checkbox.info = (TextView)convertView.findViewById(R.id.time_info);
        	holder_checkbox.rightArrow = (ImageView) convertView.findViewById(R.id.time_rightarrow);
        	
        	
        	holder_checkbox.title.setText("运送时间"+mTime);
        	holder_checkbox.rightArrow.setImageResource(R.drawable.right_arrow);
        	
        	convertView.setTag(holder_checkbox);
        	
        	convertView.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//popup the delivery time picker
					Message message = new Message();
					Bundle b = new Bundle();
					// send the action to do
					// action = 2 open time picker
					b.putInt("action", 2);
					message.setData(b);

					
					message.what = 1;

					mHandler.sendMessage(message);
				}
        		
        	});
        	break;
        case TYPE_EDITTEXT:
        	
        	holder_edittext = new viewHolder_edittext();
        	convertView = inflater.inflate(R.layout.submit_list_item_memo, parent, false);
        	
        	holder_edittext.title = (TextView)convertView.findViewById(R.id.memo_title);
        	holder_edittext.memo = (EditText)convertView.findViewById(R.id.memo_info);
        	
        	holder_edittext.title.setText("备注");
        	
        	convertView.setTag(holder_edittext);
        	break;
        case TYPE_URL:
        	
        	holder_url = new viewHolder_url();
        	convertView = inflater.inflate(R.layout.submit_list_item_delivery, parent, false);
        	
        	holder_url.title = (TextView)convertView.findViewById(R.id.delivery_title);
        	holder_url.info = (TextView)convertView.findViewById(R.id.delivery_info);
        	
        	holder_url.title.setText("配送费(Northwest+$8)");
        	holder_url.info.setText(Html.fromHtml("<u>"+"派送说明"+"</u>"));  
        	
        	convertView.setTag(holder_url);
        	
        	holder_url.info.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(mContext, DeliveryNoticeActivity.class);
					mContext.startActivity(intent);
				}
        		
        	});
        	
        	break;
        case TYPE_EMPTY:
        	
        	holder_empty = new viewHolder_empty();
        	convertView = inflater.inflate(R.layout.submit_list_item_coupon, parent, false);
        	
        	holder_empty.title = (TextView)convertView.findViewById(R.id.coupon_title);
        
        	
        	holder_empty.title.setText("使用优惠券");

        	
        	convertView.setTag(holder_empty);
        	break;
        default:
        	break;
        }
		return convertView;
		
	}
	
	
	class viewHolder_text{       
		 
        private TextView title;

        private TextView info;
	}
    class viewHolder_activity{

        private TextView title;

        private TextView info;

        private ImageView rightArrow;
    }

    class viewHolder_checkbox{

        private TextView title;

        private TextView info;

        private ImageView rightArrow;
    }
    
    class viewHolder_edittext{

        private TextView title;

        private EditText memo;

    }
    
    class viewHolder_url{

        private TextView title;

        private TextView info;
    }
	
    class viewHolder_empty{

        private TextView title;
    }
}
