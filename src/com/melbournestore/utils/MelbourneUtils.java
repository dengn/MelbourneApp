package com.melbournestore.utils;

import java.io.File;

import com.melbournestore.models.Shop;


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
	
	public static final int sum_number_all(Shop[] shops){
		int num_all = 0;
		for(int i =0;i<shops.length;i++){
			for(int j=0;j<shops[i].getPlates().length;j++){
				num_all+=shops[i].getPlates()[j].getNumber();
			}
		}
		return num_all;
	}
	public static final int sum_price_all(Shop[] shops){
		int price_all = 0;
		for(int i =0;i<shops.length;i++){
			for(int j=0;j<shops[i].getPlates().length;j++){
				price_all+=shops[i].getPlates()[j].getNumber()*shops[i].getPlates()[j].getPrice();
			}
		}
		return price_all;
	}
}
