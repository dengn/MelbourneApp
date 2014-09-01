package com.melbournestore.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.melbournestore.activities.R;
import com.melbournestore.adaptors.CategoryListAdapter;

public class PlateFragment extends Fragment{
	
	public static final String ARG_PLANET_NUMBER = "plate_number";
	
	private static int[] categoryImages = {R.drawable.category1, R.drawable.category2, R.drawable.category3};

	//private Context mContext;
	
    public PlateFragment() {
        

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plate, container, false);
//        int i = getArguments().getInt(ARG_PLANET_NUMBER);
//        String planet = getResources().getStringArray(R.array.planets_array)[i];
//
//        int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
//                        "drawable", getActivity().getPackageName());
//        ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
//        getActivity().setTitle(planet);
        
        ListView category = (ListView) rootView.findViewById(R.id.category);
        category.setAdapter(new CategoryListAdapter(getActivity(), categoryImages));
        
        return rootView;
    }

}
