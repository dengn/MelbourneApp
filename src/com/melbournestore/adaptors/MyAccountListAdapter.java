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

public class MyAccountListAdapter extends BaseAdapter {


	private Context mContext;

	private Handler mHandler;

	private Bitmap mProfile;

	private String mNumber;

	private static LayoutInflater inflater = null;

	public MyAccountListAdapter(Context context, Handler handler,
			Bitmap profile, String number) {
		// TODO Auto-generated constructor stub

		mContext = context;
		mHandler = handler;
		mProfile = profile;
		mNumber = number;

		
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void refresh(Bitmap profile, String number) {
		mProfile = profile;
		mNumber = number;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
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


		switch (position) {
		case 0:
			holder_profile = new viewHolder_profile();
			convertView = inflater.inflate(R.layout.myaccount_list_profile,
					parent, false);

			holder_profile.profile = (ImageView) convertView
					.findViewById(R.id.myaccount_profile_image);
			holder_profile.number = (TextView) convertView
					.findViewById(R.id.myaccount_profile_number);

			// set images
			if (mProfile == null) {
				holder_profile.profile
						.setImageResource(R.drawable.profile_userphoto);
			} else {
				holder_profile.profile.setImageBitmap(mProfile);
			}

			holder_profile.number.setText(mNumber);

			holder_profile.profile.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Message message = new Message();
					Bundle b = new Bundle();
					// send the action to do
					// action = 1 open popup
					b.putInt("action", 1);
					message.setData(b);

					// plus = 1
					message.what = 1;

					mHandler.sendMessage(message);
				}

			});

			convertView.setTag(holder_profile);

			break;
		case 1:
			holder_like = new viewHolder_like();
			convertView = inflater.inflate(R.layout.myaccount_list_like,
					parent, false);

			holder_like.like = (TextView) convertView
					.findViewById(R.id.myaccount_like);
			holder_like.order = (TextView) convertView
					.findViewById(R.id.myaccount_order);
			holder_like.coupon = (TextView) convertView
					.findViewById(R.id.myaccount_coupon);

			holder_like.like.setText("0\nϲ��");
			holder_like.order.setText("0\n����");
			holder_like.coupon.setText("0\n�Ż�ȯ");

			convertView.setTag(holder_like);

			break;
		

		default:
			break;
		}
		return convertView;

	}

	class viewHolder_profile {

		private ImageView profile;

		private TextView number;
	}

	class viewHolder_like {

		private TextView like;

		private TextView order;

		private TextView coupon;
	}



}
