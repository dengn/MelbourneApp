package com.melbournestore.models;

public class Shop {
	private int id;
	private String name;
	private String desc;
	private String addr;
	private String contact_number;
	private int seq;
	private String image;
	private String update_time;
	
	public Shop(){
		
	}
	
	public Shop(int id, String name, String desc, String addr, String contact_number, int seq, String image, String update_time){
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.addr = addr;
		this.contact_number = contact_number;
		this.seq = seq;
		this.image = image;
		this.update_time = update_time;
	}
	
	public int getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getDesc(){
		return desc;
	}
	public String getAddr(){
		return addr;
	}
	public String getContactNumber(){
		return contact_number;
	}
	public int getSeq(){
		return seq;
	}
	public String getImage(){
		return image;
	}
	public String getUpdateTime(){
		return update_time;
	}
}

