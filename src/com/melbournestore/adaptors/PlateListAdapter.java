package com.melbournestore.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.melbournestore.activities.R;

public class PlateListAdapter extends BaseAdapter {
	String[] result;
	Context mContext;
	int[] imageId;
	private static LayoutInflater inflater = null;

	public PlateListAdapter(Context context, String[] prgmNameList,
			int[] prgmImages) {
		// TODO Auto-generated constructor stub
		result = prgmNameList;
		mContext = context;
		imageId = prgmImages;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return result.length;
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

	public class Holder {
		TextView tv;
		ImageView img;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.plate_list_item, null);
		holder.tv = (TextView) rowView.findViewById(R.id.plate_txt);
		holder.img = (ImageView) rowView.findViewById(R.id.plate_img);
		holder.tv.setText(result[position]);
		holder.img.setImageResource(imageId[position]);
		rowView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "You Clicked " + result[position],
						Toast.LENGTH_LONG).show();
			}
		});
		return rowView;
	}

}
