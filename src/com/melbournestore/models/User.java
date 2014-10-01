package com.melbournestore.models;

import android.graphics.Bitmap;

public class User {

	private String phone_number;
	private String unit_no;
	private String street;
	private Suburb suburb;
	private Bitmap head_icon;
	private boolean active;

	public User() {

	}

	public User(String phone_number, String unit_no, String street,
			Suburb suburb, Bitmap head_icon, boolean active) {
		this.phone_number = phone_number;
		this.unit_no = unit_no;
		this.suburb = suburb;
		this.street = street;
		this.head_icon = head_icon;
		this.active = active;
	}

	public String getPhoneNumber() {
		return phone_number;
	}

	public String getUnitNo() {
		return unit_no;
	}

	public String getStreet() {
		return street;
	}

	public Suburb getSuburb() {
		return suburb;
	}

	public Bitmap getHeadIcon() {
		return head_icon;
	}

	public boolean getActive() {
		return active;
	}

	public void setPhoneNumber(String phone_number) {
		this.phone_number = phone_number;
	}

	public void setUnitNo(String unit_no) {
		this.unit_no = unit_no;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setSuburb(Suburb suburb) {
		this.suburb = suburb;
	}

	public void setHeadIcon(Bitmap head_icon) {
		this.head_icon = head_icon;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
