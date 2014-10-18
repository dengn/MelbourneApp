package com.melbournestore.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.melbournestore.activities.R;
import com.melbournestore.adaptors.CategoryListAdapter;
import com.melbournestore.db.DataResourceUtils;

public class PlateFragment extends Fragment {

	Context mContext;

	ListView category;

	CategoryListHeaderView headerView;

	Boolean header_created = false;

	public PlateFragment(Context context) {
		mContext = context;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.plate_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// handle item selection
		switch (item.getItemId()) {
		case R.id.search_plate:
			

			if (!header_created) {
				category.addHeaderView(headerView);
				header_created = true;
			}
			else{
				category.removeHeaderView(headerView);
				header_created = false;
			}
			


			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_plate, container,
				false);

		category = (ListView) rootView.findViewById(R.id.category);

		headerView = new CategoryListHeaderView(mContext);

		String[] shopSubItems = new String[DataResourceUtils.shopItemsImages.length];
		for (int i = 0; i < shopSubItems.length; i++) {
			shopSubItems[i] = DataResourceUtils.plateNames[i][0] + "、 "
					+ DataResourceUtils.plateNames[i][1] + "限时特卖中";
		}
		category.setAdapter(new CategoryListAdapter(getActivity(),
				DataResourceUtils.shopItemsImages, DataResourceUtils.shopItems,
				shopSubItems));

		// headerView.setVisibility(View.INVISIBLE);

		// category.setOnTouchListener(new OnTouchListener(){
		//
		// @Override
		// public boolean onTouch(View v, MotionEvent event) {
		// // TODO Auto-generated method stub
		// // switch (event.getAction()) {
		// // case MotionEvent.ACTION_DOWN:
		// // headerView.setVisibility(View.VISIBLE);
		// // break;
		// // case MotionEvent.ACTION_UP:
		// // headerView.setVisibility(View.INVISIBLE);
		// // break;
		// // }
		// return false;
		// }
		//
		// });

		return rootView;
	}

}
