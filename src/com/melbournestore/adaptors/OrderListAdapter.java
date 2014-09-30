package com.melbournestore.adaptors;

import com.melbournestore.activities.R;
import com.melbournestore.adaptors.PlateListAdapter.Holder;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderListAdapter extends BaseAdapter{

	String[] orderNames;
	int[] orderNumbers;
	int[] orderPrices;
	
	Handler mHandler;
	
	Context mContext;
	
	private static LayoutInflater inflater = null;
	
	public OrderListAdapter(Context context, Handler handler, String[] names, int[] prices, int[] numbers) {
		// TODO Auto-generated constructor stub
		orderNames = names;
		orderPrices = prices;
		orderNumbers = numbers;
		
		mContext = context;
		mHandler = handler;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return orderNames.length;
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
		
		TextView numbers_view;

		Button plus;
		Button minus;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.shopping_list_item, null);
		holder.names_view = (TextView) rowView.findViewById(R.id.order_name);
		holder.prices_view = (TextView) rowView.findViewById(R.id.order_price);
		holder.numbers_view = (TextView) rowView.findViewById(R.id.order_number);

		holder.plus = (Button) rowView.findViewById(R.id.order_plus);
		holder.minus = (Button) rowView.findViewById(R.id.order_minus);

		holder.names_view.setText(orderNames[position]);
		holder.prices_view.setText("$"+String.valueOf(orderPrices[position]));		
		holder.numbers_view.setText(String.valueOf(orderNumbers[position]));

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
				orderNumbers[position]++;
				
				holder.numbers_view.setText(String.valueOf(orderNumbers[position]));
				
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
				
				
				
				if(orderNumbers[position]<=0){
					holder.minus.setEnabled(false);
					orderNumbers[position] =0;
				}
				else{
					holder.minus.setEnabled(true);
					orderNumbers[position]--;
				}

				holder.numbers_view.setText(String.valueOf(orderNumbers[position]));
				
			}
		});

		return rowView;
	}

}
