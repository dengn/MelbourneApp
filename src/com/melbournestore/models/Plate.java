package com.melbournestore.models;

public class Plate {

	private int mPrice;
	private String mName;
	private int mNumber;
	private int mStock;
	private int mStockMax;
	private int mLikeNum;

	public Plate() {

	}

	public Plate(int price, String name, int number, int stock, int stockMax,
			int likeNum) {
		mPrice = price;
		mName = name;
		mNumber = number;
		mStock = stock;
		mStockMax = stockMax;
		mLikeNum = likeNum;
	}
	
	public String getName(){
		return mName;
	}
	public int getNumber(){
		return mNumber;
	}
	public int getPrice(){
		return mPrice;
	}
	public int getStock(){
		return mStock;
	}
	public int getStockMax(){
		return mStockMax;
	}
	public int getLikeNum(){
		return mLikeNum;
	}
	
	public void setName(String name){
		mName = name;
	}
	public void setPrice(int price){
		mPrice = price;
	}
	public void setNumber(int number){
		mNumber = number;
	}
	public void setStock(int stock){
		mStock = stock;
	}
	public void setStockMax(int stockMax){
		mStockMax = stockMax;
	}
	public void setLikeNum(int likeNum){
		mLikeNum = likeNum;
	}
}
