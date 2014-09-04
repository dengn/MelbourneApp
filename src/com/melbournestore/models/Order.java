package com.melbournestore.models;

public class Order {
	
	
	private int id;
	private String phone_number;
	private String unit_no;
	private String street;
	private String post_code;
	private String delivery_time;
	private int delivery_fee;
	private String remark;
	private String create_time;
	private String update_time;
	private String confirm_time;
	private String distributing_time;
	private String deliverying_time;
	private String complete_time;
	private int status;
	private OrderItem items;
	private Suburb suburb;

	
	public Order(){
		
	}
	
	public Order(int id, String phone_number, String unit_no, String street, String post_code, String delivery_time
			, int delivery_fee, String remark, String create_time, String update_time, String confirm_time, String distributing_time
			, String deliverying_time, String complete_time, int status, OrderItem items, Suburb suburb){
		this.id = id;
		this.phone_number = phone_number;
		this.unit_no = unit_no;
		this.street = street;
		this.post_code = post_code;
		this.delivery_time = delivery_time;
		this.delivery_fee = delivery_fee;
		this.remark = remark;
		this.create_time = create_time;
		this.update_time = update_time;
		this.confirm_time = confirm_time;
		this.distributing_time = distributing_time;
		this.complete_time = complete_time;
		this.status = status;
		this.items = items;
		this.suburb = suburb;
	}
	
	public int getId(){
		return id;
	}
	public String getPhoneNumber(){
		return phone_number;
	}
	public String getUnitNo(){
		return unit_no;
	}
	public String getStreet(){
		return street;
	}
	public String getPostCode(){
		return post_code;
	}
	
	public void getDeliveryTime(){
		this.delivery_time = delivery_time;
	}
	
	
	
	
	public void setId(int id){
		this.id = id;
	}
	public void setPhoneNumber(String phone_number){
		this.phone_number = phone_number;
	}
	public void setUnitNo(String unit_no){
		this.unit_no = unit_no;
	}

}
