package com.melbournestore.utils;

import java.io.File;

import android.util.Log;
import android.widget.Toast;

public class MelbourneUtils {

	public static final int sum(int[] array) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}
	
	public static final int sum_price(int[] price, int[] number){
		int sum = 0;

		for(int i = 0;i< price.length;i++){
			sum += number[i]*price[i];
		}
		return sum;
	}

	public static final boolean checkExistSharedPrefrence(String name) {

		File f = new File(
				"/data/data/com.melbournestore.activities/shared_prefs/"+name+".xml");
		if (f.exists())
			return true;
		else
			return false;

	}
}
