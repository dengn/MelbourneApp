package com.melbournestore.adaptors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.melbournestore.activities.ChooseAddressActivity;
import com.melbournestore.activities.MyAccountActivity;
import com.melbournestore.activities.R;

public class MyAccountListAdapter extends BaseAdapter{
	
	final int TYPE_PROFILE   = 0;
    final int TYPE_LIKE  = 1;
    final int TYPE_ADDRESS   = 2;
    final int TYPE_COUPON= 3;
    
    
	private Context mContext;
	
	private String mAddress;
    
	private static LayoutInflater inflater = null;
    
    
    
	public MyAccountListAdapter(Context context, String address) {
		// TODO Auto-generated constructor stub

		
		mContext = context;
		mAddress = address;
		
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void refresh(String address){
		mAddress = address;
		notifyDataSetChanged();
	}
    
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
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
		
		viewHolder_profile holder_profile = null;
		viewHolder_like holder_like = null;
		viewHolder_address holder_address = null;
		viewHolder_coupon holder_coupon = null;
		
		
		switch(position)
        {
        case TYPE_PROFILE:
        	holder_profile = new viewHolder_profile();
        	convertView = inflater.inflate(R.layout.myaccount_list_profile, parent, false);
        	
        	holder_profile.profile = (ImageView)convertView.findViewById(R.id.myaccount_profile_image);
        	holder_profile.number = (TextView)convertView.findViewById(R.id.myaccount_profile_number);
        	
        	//set images 
        	holder_profile.profile.setImageResource(R.drawable.profile_userphoto);
        	holder_profile.number.setText("0485245801");
        	
        	holder_profile.profile.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
        		
        	});
        	
        	convertView.setTag(holder_profile);
        	
        	
        	
        	break;
        case TYPE_LIKE:
        	holder_like = new viewHolder_like();
        	convertView = inflater.inflate(R.layout.myaccount_list_like, parent, false);
        	
        	holder_like.like = (TextView)convertView.findViewById(R.id.myaccount_like);
        	holder_like.order = (TextView)convertView.findViewById(R.id.myaccount_order);
        	holder_like.coupon = (TextView) convertView.findViewById(R.id.myaccount_coupon);
        	
        	
        	holder_like.like.setText("0\n喜欢");
        	holder_like.order.setText("0\n订单");
        	holder_like.coupon.setText("0\n优惠券");
        	
        	convertView.setTag(holder_like);
        	

        	
        	break;
        case TYPE_ADDRESS:
        	
        	holder_address = new viewHolder_address();
        	convertView = inflater.inflate(R.layout.myaccount_list_address, parent, false);
        	
        	holder_address.title = (TextView)convertView.findViewById(R.id.myaccount_address_title);
        	holder_address.address = (TextView)convertView.findViewById(R.id.myaccount_address_info);
        	holder_address.rightArrow = (ImageView) convertView.findViewById(R.id.myaccount_address_rightarrow);
        	
        	
        	holder_address.title.setText("送货地址");
        	holder_address.address.setText(mAddress);
        	holder_address.rightArrow.setImageResource(R.drawable.right_arrow);
        	
        	convertView.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(mContext, ChooseAddressActivity.class);
					((Activity) mContext).startActivityForResult(intent, MyAccountActivity.choose_address_code);
				}
        		
        	});
        	
        	convertView.setTag(holder_address);
        	break;
        case TYPE_COUPON:
        	
        	holder_coupon = new viewHolder_coupon();
        	convertView = inflater.inflate(R.layout.myaccount_list_coupon, parent, false);
        	
        	holder_coupon.title = (TextView)convertView.findViewById(R.id.myaccount_coupon_title);
        	holder_coupon.rightArrow = (ImageView) convertView.findViewById(R.id.myaccount_coupon_rightarrow);
        	
        	
        	holder_coupon.title.setText("我的优惠券");
        	holder_coupon.rightArrow.setImageResource(R.drawable.right_arrow);
        	
        	convertView.setTag(holder_coupon);
        	break;
       
        default:
        	break;
        }
		return convertView;

	}
    
    
	
	
	
	
	class viewHolder_profile{       
		 
        private ImageView profile;

        private TextView number;
	}
    class viewHolder_like{

        private TextView like;

        private TextView order;

        private TextView coupon;
    }

    class viewHolder_address{

        private TextView title;

        private TextView address;

        private ImageView rightArrow;
    }
    
    class viewHolder_coupon{

        private TextView title;

        private ImageView rightArrow;

    }
    


}
