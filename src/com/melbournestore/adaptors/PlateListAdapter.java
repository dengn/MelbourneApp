package com.melbournestore.adaptors;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.melbournestore.activities.R;

public class PlateListAdapter extends BaseAdapter {
	String[] plate_names;
	int[] plate_prices;
	int[] plate_stocks;
	
	int[] plates_nums;

	Context mContext;
	int[] imageId;

	Handler mHandler;

	private static LayoutInflater inflater = null;

	public PlateListAdapter(Context context, Handler handler, String[] names,
			int[] prices, int[] stocks, int[] numbers, int[] imgs) {
		// TODO Auto-generated constructor stub
		plate_names = names;
		plate_prices = prices;
		plate_stocks = stocks;
		plates_nums = numbers;
		
		mContext = context;
		mHandler = handler;

		imageId = imgs;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return plate_names.length;
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
		TextView names_view;
		TextView prices_view;
		TextView stocks_view;
		ImageView imgs_view;
		
		TextView num_view;

		Button plus;
		Button minus;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.plate_list_item, null);
		holder.names_view = (TextView) rowView.findViewById(R.id.plate_name);
		holder.prices_view = (TextView) rowView.findViewById(R.id.plate_price);
		holder.stocks_view = (TextView) rowView.findViewById(R.id.plate_like);
		holder.imgs_view = (ImageView) rowView.findViewById(R.id.plate_img);
		holder.num_view = (TextView) rowView.findViewById(R.id.plate_number);

		holder.plus = (Button) rowView.findViewById(R.id.plate_plus);
		holder.minus = (Button) rowView.findViewById(R.id.plate_minus);

		holder.names_view.setText(plate_names[position]);
		holder.prices_view.setText("$"+String.valueOf(plate_prices[position]));
		holder.stocks_view.setText("今日库存"+plate_stocks[position]+"份");
		holder.imgs_view.setImageResource(imageId[position]);
		
		holder.num_view.setText(String.valueOf(plates_nums[position]));
		// rowView.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Toast.makeText(mContext, "You Clicked " + plate_names[position],
		// Toast.LENGTH_LONG).show();
		//
		//
		// }
		// });

		holder.plus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Message message = new Message();
				Bundle b = new Bundle();
				// send the position
				b.putInt("position", position);
				message.setData(b);

				// plus = 1
				message.what = 1;

				mHandler.sendMessage(message);
				
				plate_stocks[position]--;
				plates_nums[position]++;
				
				holder.stocks_view.setText("今日库存"+plate_stocks[position]+"份");
				holder.num_view.setText(String.valueOf(plates_nums[position]));
				
			}
		});

		holder.minus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Message message = new Message();
				Bundle b = new Bundle();
				// send the position
				b.putInt("position", position);
				message.setData(b);

				// minus = 2
				message.what = 2;

				mHandler.sendMessage(message);
				
				plate_stocks[position]++;
				plates_nums[position]--;

				holder.stocks_view.setText("今日库存"+plate_stocks[position]+"份");
				holder.num_view.setText(String.valueOf(plates_nums[position]));
				
			}
		});

		return rowView;
	}

}
