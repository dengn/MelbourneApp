package com.melbournestore.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import com.melbournestore.db.DataResourceUtils;
import com.melbournestore.models.Order_user;
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

	public static final int sum_price_all(Plate[] plates) {
		int price_all = 0;
		for (int i = 0; i < plates.length; i++) {
			price_all += plates[i].getNumber() * plates[i].getPrice();
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
			} else {
				// found index of active user
				return index;
			}
		} else {
			// empty array
			return -1;
		}

	}

	public static final User[] setUsersDeactive(User[] users) {
		if (users.length > 0) {
			for (int i = 0; i < users.length; i++) {
				users[i].setActive(false);
			}
			return users;
		} else {
			return users;
		}
	}

	public static final String getCompleteAddress(User user) {
		String address = "";
		if (!user.getUnitNo().equals("") || !user.getStreet().equals("")
				|| !user.getSuburb().equals("")) {
			address = user.getUnitNo() + "," + user.getStreet() + ","
					+ user.getSuburb();
		}
		return address;
	}

	public static final String getSystemTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = df.format(c.getTime());
		return formattedDate;
	}

	public static final String getAllPlateNames(Plate[] plates) {
		String plate_names = "";
		if (plates.length == 1) {
			plate_names = plates[0].getName();
		} else {
			for (int i = 0; i < (plates.length - 1); i++) {
				plate_names += plates[i].getName() + "、";
			}
			plate_names += plates[plates.length - 1].getName();
		}

		if (plate_names.length() >= 7) {
			plate_names = plate_names.substring(0, 7) + "...";
		}
		return plate_names;

	}

	public static final String getStatusString(int status) {
		if (status == 0) {
			return "等待确认";
		}
		else if (status == 1) {
			return "已确认";
		} else if (status == 2) {
			return "订单已完成";
		} else {
			return "";
		}
	}
	
	
	public static final User deleteOrder(User user, int position){
		Order_user[] orders = user.getOrders();
		ArrayList<Order_user> orders_array = new ArrayList(Arrays.asList(orders));
		
		orders_array.remove(position);
		
		user.setOrders(orders_array.toArray(new Order_user[0]));
		
		return user;
	}
	
	public static final String getPostcode(String suburb){
		if(Arrays.asList(DataResourceUtils.names_center).contains(suburb)){
			return DataResourceUtils.post_center;
		}
		else if(Arrays.asList(DataResourceUtils.names_north).contains(suburb)){
			return DataResourceUtils.post_north;
		}
		else if(Arrays.asList(DataResourceUtils.names_northeast).contains(suburb)){
			return DataResourceUtils.post_northeast;
		}
		else if(Arrays.asList(DataResourceUtils.names_west).contains(suburb)){
			return DataResourceUtils.post_west;
		}
		else if(Arrays.asList(DataResourceUtils.names_southeast).contains(suburb)){
			return DataResourceUtils.post_southeast;
		}
		else{
			return "";
		}
	}
	
	public static final String getSuburbRegion(String suburb){
		
		if(Arrays.asList(DataResourceUtils.names_center).contains(suburb)){
			return "City";
		}
		else if(Arrays.asList(DataResourceUtils.names_north).contains(suburb)){
			return "北";
		}
		else if(Arrays.asList(DataResourceUtils.names_northeast).contains(suburb)){
			return "东北";
		}
		else if(Arrays.asList(DataResourceUtils.names_west).contains(suburb)){
			return "西";
		}
		else if(Arrays.asList(DataResourceUtils.names_southeast).contains(suburb)){
			return "东南";
		}
		else{
			return "";
		}
	}
	
	public static final int getSuburbDeliveryPrice(String suburb){
		if(getSuburbRegion(suburb).equals("City")){
			return 5;
		}
		else if(getSuburbRegion(suburb).equals("西")){
			return 10;
		}
		else if(getSuburbRegion(suburb).equals("东北")){
			return 5;
		}
		else if(getSuburbRegion(suburb).equals("北")){
			return 10;
		}
		else if(getSuburbRegion(suburb).equals("东南")){
			return 5;
		}
		else{
			return 0;
		}
	}

}
