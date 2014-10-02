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
	
	// The SharedPrefrence file to store User login info
	public static String getLoginUser(Context context){
		SharedPreferences pref = context.getSharedPreferences(  
                "user_login", 0); 
		return pref.getString("user_login", "");  
	}
	
	public static boolean saveLoginUser(Context context, String info) {  
        SharedPreferences pref = context.getSharedPreferences(  
                "user_login", 0);  
        return pref.edit().putString("user_login", info).commit();  
    }  
	
	
	// The SharedPrefrence file to store Orders.
	public static String getCurrentOrder(Context context){
		SharedPreferences pref = context.getSharedPreferences(  
                "current_order", 0); 
		return pref.getString("current_order", "");  		
	}
	public static boolean saveCurrentOrder(Context context, String info) {  
        SharedPreferences pref = context.getSharedPreferences(  
                "current_order", 0);  
        return pref.edit().putString("current_order", info).commit();  
    }  
}

