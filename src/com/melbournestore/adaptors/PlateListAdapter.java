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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.melbournestore.activities.ChooseAddressActivity;
import com.melbournestore.activities.DishActivity;
import com.melbournestore.activities.R;
import com.melbournestore.activities.SubmitOrderActivity;

public class PlateListAdapter extends BaseAdapter {
	String[] plate_names;
	int[] plate_prices;
	int[] plate_stocks;

	int[] plate_stocks_max;

	int[] plates_nums;

	int[] like_nums;

	Context mContext;
	int[] imageId;

	Handler mHandler;

	private static LayoutInflater inflater = null;

	private boolean likeClicked = false;

	public PlateListAdapter(Context context, Handler handler, String[] names,
			int[] prices, int[] stocks, int[] numbers, int[] imgs,
			int[] like_numbers) {
		// TODO Auto-generated constructor stub
		plate_names = names;
		plate_prices = prices;
		plate_stocks = stocks;
		plates_nums = numbers;

		plate_stocks_max = stocks;

		like_nums = like_numbers;

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

		TextView like_number_view;
		ImageView like_view;

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
		
		holder.imgs_view = (ImageView) rowView.findViewById(R.id.plate_img);
		holder.num_view = (TextView) rowView.findViewById(R.id.plate_number);

		holder.like_number_view = (TextView) rowView
				.findViewById(R.id.plate_like_number_stock);
		holder.like_view = (ImageView) rowView
				.findViewById(R.id.plate_like_heart);

		holder.like_view.setImageResource(R.drawable.other_icon_like);

		holder.like_number_view.setText(String.valueOf(like_nums[position])
				+ "         今日库存" + plate_stocks[position] + "份");

		holder.plus = (Button) rowView.findViewById(R.id.plate_plus);
		holder.minus = (Button) rowView.findViewById(R.id.plate_minus);

		setComponentsStatus(holder.plus, holder.minus, holder.num_view,
				position);

		holder.names_view.setText(plate_names[position]);
		holder.prices_view
				.setText("$" + String.valueOf(plate_prices[position]));

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

		rowView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext, DishActivity.class);
				intent.putExtra("position", position);
				((Activity) mContext).startActivity(intent);

			}

		});

		holder.like_number_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!likeClicked) {
					holder.like_view
							.setImageResource(R.drawable.other_icon_liked);

					holder.like_number_view.setText(String
							.valueOf(like_nums[position] + 1));

					likeClicked = true;
				} else {
					Toast.makeText(mContext, "亲，今天已经点过赞了。", Toast.LENGTH_SHORT)
							.show();
				}
			}

		});

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

				plate_stocks[position] = plate_stocks[position] - 1;
				plates_nums[position] = plates_nums[position] + 1;

				// Log.d(TAG,"plus clicked. plate_stocks number: ");

//				holder.stocks_view.setText("今日库存"
//						+ String.valueOf(plate_stocks[position]) + "份");
				holder.like_number_view.setText(String.valueOf(like_nums[position])
						+ "         今日库存" + plate_stocks[position] + "份");
				holder.num_view.setText(String.valueOf(plates_nums[position]));

				setComponentsStatus(holder.plus, holder.minus, holder.num_view,
						position);

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

				plate_stocks[position] = plate_stocks[position] + 1;
				plates_nums[position] = plates_nums[position] - 1;

//				holder.stocks_view.setText("今日库存"
//						+ String.valueOf(plate_stocks[position]) + "份");
				holder.like_number_view.setText(String.valueOf(like_nums[position])
						+ "         今日库存" + plate_stocks[position] + "份");
				holder.num_view.setText(String.valueOf(plates_nums[position]));

				setComponentsStatus(holder.plus, holder.minus, holder.num_view,
						position);

			}
		});

		return rowView;
	}

	private void setComponentsStatus(Button plusButton, Button minusButton,
			TextView numView, int position) {
		int stock_num = plate_stocks[position];
		int plate_num = plates_nums[position];

		if (stock_num <= 0) {
			plusButton.setEnabled(false);
		} else {
			plusButton.setEnabled(true);
		}
		if (plate_num <= 0) {
			numView.setVisibility(View.INVISIBLE);
			minusButton.setEnabled(false);
		} else {
			numView.setVisibility(View.VISIBLE);
			minusButton.setEnabled(true);
		}
	}

}
