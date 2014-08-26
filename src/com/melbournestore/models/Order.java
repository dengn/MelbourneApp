package com.melbournestore.models;

public class Order {
	
	String plateName;
	int plateNumber;
	int platePrice;
	
	public Order(){
		
	}
	
	public Order(String plateName, int plateNumber, int platePrice){
		this.plateName = plateName;
		this.plateNumber = plateNumber;
		this.platePrice = platePrice;
	}
	
	public String getPlateName(){
		return plateName;
	}
	
	public int getPlateNumber(){
		return plateNumber;
	}
	
	public int getPlatePrice(){
		return platePrice;
	}
	
	public void setPlateName(String plateName){
		this.plateName = plateName;
	}
	
	public void setPlateNumber(int plateNumber){
		this.plateNumber = plateNumber;
	}
	
	public void setPlatePrice(int platePrice){
		this.platePrice = platePrice;
	}

}
