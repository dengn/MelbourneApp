package com.melbournestore.adaptors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.melbournestore.activities.R;
import com.melbournestore.db.DataResourceUtils;

public class DrawerListAdapter extends BaseAdapter {

	boolean mIsLoggedIn;

	Handler mHandler;

	Bitmap mProfile;

	Context mContext;

	String mLoginNumber;

	private static LayoutInflater inflater = null;

	public DrawerListAdapter(Context context, boolean isLoggedIn,
			String loginNumber, Handler handler, Bitmap profile) {
		// TODO Auto-generated constructor stub

		mContext = context;
		mHandler = handler;
		mIsLoggedIn = isLoggedIn;
		mLoginNumber = loginNumber;
		mProfile = profile;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void refresh(boolean isLoggedIn, String loginNumber,
			Handler handler, Bitmap profile) {

		mHandler = handler;
		mIsLoggedIn = isLoggedIn;
		mLoginNumber = loginNumber;
		mProfile = profile;
		notifyDataSetChanged();
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
		viewHolder_normal holder_normal = null;
		viewHolder_login holder_login = null;

		// the first row
		if (position == 0) {
			holder_login = new viewHolder_login();
			convertView = inflater.inflate(R.layout.drawer_list_item_login,
					parent, false);

			holder_login.phone_number = (TextView) convertView
					.findViewById(R.id.drawer_login_number);
			holder_login.profile = (ImageView) convertView
					.findViewById(R.id.drawer_login_image);

			holder_login.phone_number.setTextColor(Color.WHITE);

			if (!mIsLoggedIn) {
				holder_login.phone_number.setText("δ��¼");
				holder_login.profile
						.setImageResource(R.drawable.sidebar_userphoto_default);
			} else {
				holder_login.phone_number.setText(mLoginNumber);
				if (mProfile == null) {
					holder_login.profile
							.setImageResource(R.drawable.profile_userphoto);
				}else{
					holder_login.profile.setImageBitmap(mProfile);
				}
			}

		}

		else {
			holder_normal = new viewHolder_normal();
			convertView = inflater.inflate(R.layout.drawer_list_item_normal,
					parent, false);

			holder_normal.logo = (ImageView) convertView
					.findViewById(R.id.drawer_normal_image);
			holder_normal.title = (TextView) convertView
					.findViewById(R.id.drawer_normal_title);

			holder_normal.title.setTextColor(Color.WHITE);

			holder_normal.logo
					.setImageResource(DataResourceUtils.drawerItemsImages[position - 1]);
			holder_normal.title
					.setText(DataResourceUtils.drawerItemsTitles[position - 1]);
		}

		return convertView;
	}

	class viewHolder_normal {

		private ImageView logo;

		private TextView title;

	}

	class viewHolder_login {

		private ImageView profile;

		private TextView phone_number;

	}

}
