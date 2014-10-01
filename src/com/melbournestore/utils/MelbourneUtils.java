package com.melbournestore.utils;

import java.io.File;
import java.util.ArrayList;

import com.melbournestore.models.Plate;
import com.melbournestore.models.Shop;
import com.melbournestore.models.User;

public class MelbourneUtils {

	public static final int sum(int[] array) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}

	public static final int sum_price(int[] price, int[] number) {
		int sum = 0;

		for (int i = 0; i < price.length; i++) {
			sum += number[i] * price[i];
		}
		return sum;
	}

	public static final boolean checkExistSharedPrefrence(String name) {

		File f = new File(
				"/data/data/com.melbournestore.activities/shared_prefs/" + name
						+ ".xml");
		if (f.exists())
			return true;
		else
			return false;

	}

	public static final int sum_number_all(Shop[] shops) {
		int num_all = 0;
		for (int i = 0; i < shops.length; i++) {
			for (int j = 0; j < shops[i].getPlates().length; j++) {
				num_all += shops[i].getPlates()[j].getNumber();
			}
		}
		return num_all;
	}

	public static final int sum_price_all(Shop[] shops) {
		int price_all = 0;
		for (int i = 0; i < shops.length; i++) {
			for (int j = 0; j < shops[i].getPlates().length; j++) {
				price_all += shops[i].getPlates()[j].getNumber()
						* shops[i].getPlates()[j].getPrice();
			}
		}
		return price_all;
	}

	public static final Plate[] getPlatesChosen(Shop[] shops) {

		ArrayList<Plate> plates_chosen = new ArrayList<Plate>();

		for (int i = 0; i < shops.length; i++) {
			for (int j = 0; j < shops[i].getPlates().length; j++) {
				if (shops[i].getPlates()[j].getNumber() > 0) {
					plates_chosen.add(shops[i].getPlates()[j]);
				}
			}
		}

		Plate[] plates = plates_chosen.toArray(new Plate[0]);

		return plates;

	}

	public static final int getActiveUser(User[] users) {

		boolean active_found = false;
		int index = 0;

		if (users.length > 0) {
			for (int i = 0; i < users.length; i++) {
				if (users[i].getActive()) {
					active_found = true;
					index = i;
					break;

				}
			}
			if (!active_found) {
				// no active user found
				return -2;
			}
			else{
				// found index of active user
				return index;
			}
		} else {
			// empty array
			return -1;
		}

	}
}
