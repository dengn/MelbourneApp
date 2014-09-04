package com.melbournestore.models;

public class courier {
	
	private int id;
	private String login_name;
	private String display_name;
	private String phone_number;
	private String head_icon;
	private String duty_area;
	private String status;
	private String device_token;
	
	public courier(){
		
	}
	
	public courier(int id, String login_name, String display_name, String phone_number, String head_icon, String duty_area, String status, String device_token){
		this.id = id;
		this.login_name = login_name;
		this.display_name = display_name;
		this.phone_number = phone_number;
	}
	
	public int getId(){
		return id;
	}
	public String getLoginName(){
		return login_name;
	}
	public String getDisplayName(){
		return display_name;
	}
	public String getPhoneNumber(){
		return phone_number;
	}
	
	

}
