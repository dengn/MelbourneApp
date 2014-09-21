package com.melbournestore.fragments;

import android.app.Fragment;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.melbournestore.activities.R;

public class GoogleMapFragment extends Fragment{
	
	private GoogleMap map;
	
	private Context mContext;
	
	public GoogleMapFragment(Context context){
		mContext = context;
	}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        map = ((MapFragment) getFragmentManager().
                findFragmentById(R.id.map)).getMap();
        setUpMap();
        return rootView;
    }
    
    private void setUpMap() {
        // Enable MyLocation Layer of Google Map
    	map.setMyLocationEnabled(true);

        // Get LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Get Current Location
        Location myLocation = locationManager.getLastKnownLocation(provider);

        //set map type
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Get latitude of the current location
        double latitude = myLocation.getLatitude();

        // Get longitude of the current location
        double longitude = myLocation.getLongitude();

        // Create a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);      

        // Show the current location in Google Map        
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in the Google Map
        map.animateCamera(CameraUpdateFactory.zoomTo(15));
        //map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here!"));
    }
    

}
