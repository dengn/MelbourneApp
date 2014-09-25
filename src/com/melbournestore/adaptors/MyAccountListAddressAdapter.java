package com.melbournestore.adaptors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.melbournestore.activities.ChooseAddressActivity;
import com.melbournestore.activities.MyAccountActivity;
import com.melbournestore.activities.R;

public class MyAccountListAddressAdapter extends BaseAdapter {

	private Context mContext;

	private Handler mHandler;

	private String mAddress;

	private static LayoutInflater inflater = null;

	public MyAccountListAddressAdapter(Context context, Handler handler,
			String address) {
		// TODO Auto-generated constructor stub

		mContext = context;
		mHandler = handler;
		mAddress = address;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void refresh(String address) {
		mAddress = address;

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


		viewHolder_address holder_address = null;

		holder_address = new viewHolder_address();
		convertView = inflater.inflate(R.layout.myaccount_list_address, parent,
				false);

		holder_address.title = (TextView) convertView
				.findViewById(R.id.myaccount_address_title);
		holder_address.address = (TextView) convertView
				.findViewById(R.id.myaccount_address_info);
		holder_address.rightArrow = (ImageView) convertView
				.findViewById(R.id.myaccount_address_rightarrow);

		holder_address.title.setText("ÀÕªıµÿ÷∑");
		holder_address.address.setText(mAddress);
		holder_address.rightArrow
				.setImageResource(R.drawable.other_icon_rightarrow);

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext,
						ChooseAddressActivity.class);
				((Activity) mContext).startActivityForResult(intent,
						MyAccountActivity.choose_address_code);
			}

		});

		convertView.setTag(holder_address);

		return convertView;

	}



	class viewHolder_address {

		private TextView title;

		private TextView address;

		private ImageView rightArrow;
	}



}
