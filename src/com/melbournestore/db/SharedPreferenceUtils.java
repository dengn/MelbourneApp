package com.melbournestore.db;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtils {

	// The SharedPrefrence file to store current choosed flates, which are not added to Shopping cart.
	public static String getCurrentChoice(Context context){
		SharedPreferences pref = context.getSharedPreferences(  
                "current_choice", 0); 
		return pref.getString("current_choice", "");  
	}
	
	public static boolean saveCurrentChoice(Context context, String info) {  
        SharedPreferences pref = context.getSharedPreferences(  
                "current_choice", 0);  
        return pref.edit().putString("current_choice", info).commit();  
    }  
	
	// The SharedPrefrence file to store Shopping cart items.
	public static String getShoppingCart(Context context){
		SharedPreferences pref = context.getSharedPreferences(  
                "shopping_cart", 0); 
		return pref.getString("shopping_cart", "");  		
	}
	
	// The SharedPrefrence file to store Orders.
	public static String getOrders(Context context){
		SharedPreferences pref = context.getSharedPreferences(  
                "orders", 0); 
		return pref.getString("orders", "");  		
	}
}

