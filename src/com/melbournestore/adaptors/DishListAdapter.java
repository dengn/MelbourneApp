package com.melbournestore.adaptors;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.melbournestore.activities.R;
import com.melbournestore.adaptors.SubmitListAdapter.viewHolder_activity;
import com.melbournestore.adaptors.SubmitListAdapter.viewHolder_text;
import com.melbournestore.db.DataResourceUtils;

public class DishListAdapter extends BaseAdapter {

	private Context mContext;

	private Handler mHandler;

	private int mPosition;

	private static LayoutInflater inflater = null;

	public DishListAdapter(Context context, Handler handler, int position) {
		// TODO Auto-generated constructor stub

		mContext = context;
		mHandler = handler;
		mPosition = position;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void refresh() {

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

		viewHolder_image holder_image = null;
		viewHolder_dish holder_dish = null;

		switch (position) {
		case 0:
			holder_image = new viewHolder_image();
			convertView = inflater.inflate(R.layout.dish_list_image, parent,
					false);

			holder_image.dishImage = (ImageView) convertView
					.findViewById(R.id.dish_img);
			holder_image.dishText = (TextView) convertView.findViewById(R.id.dish_img_text);
			holder_image.dishImage
					.setImageResource(DataResourceUtils.plateImages[mPosition]);
			holder_image.dishText.setText("5");
			
			convertView.setTag(holder_image);

			break;
		case 1:
			holder_dish = new viewHolder_dish();
			convertView = inflater.inflate(R.layout.dish_list_item, parent,
					false);

			holder_dish.price = (TextView) convertView
					.findViewById(R.id.dish_price);
			holder_dish.like = (ImageView) convertView.findViewById(R.id.dish_like);
			holder_dish.like_num = (TextView) convertView.findViewById(R.id.dish_like_number);
			holder_dish.stock = (TextView) convertView.findViewById(R.id.dish_stock);
			holder_dish.plus = (Button) convertView.findViewById(R.id.dish_plus);
			holder_dish.minus = (Button) convertView.findViewById(R.id.dish_minus);
			
			holder_dish.price.setText("$55");
			holder_dish.like.setImageResource(R.drawable.other_icon_like);
			holder_dish.like_num.setText("0");
			holder_dish.stock.setText("今日库存还有7份");
			
			convertView.setTag(holder_dish);
			
			break;

		}

		return convertView;
	}

	class viewHolder_image {

		private ImageView dishImage;
		
		private TextView dishText;
	}

	class viewHolder_dish {

		private TextView price;

		private ImageView like;

		private TextView like_num;

		private TextView stock;

		private Button plus;

		private Button minus;

	}

}
