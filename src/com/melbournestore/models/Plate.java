package com.melbournestore.models;

public class Plate {

	private int mPrice;
	private String mName;
	private int mNumber;
	private int mStockMax;
	private int mLikeNum;
	private int mImageId;
	private int mShopId;
	private int mPlateId;
	

	public Plate() {

	}

	public Plate(int price, String name, int number, int stockMax,
			int likeNum, int imageId, int shopId, int plateId) {
		mPrice = price;
		mName = name;
		mNumber = number;
		mStockMax = stockMax;
		mLikeNum = likeNum;
		mImageId = imageId;
		mShopId = shopId;
		mPlateId = plateId;
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
	public int getStockMax(){
		return mStockMax;
	}
	public int getImageId(){
		return mImageId;
	}
	public int getLikeNum(){
		return mLikeNum;
	}
	public int getShopId(){
		return mShopId;
	}
	public int getPlateId(){
		return mPlateId;
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
	public void setStockMax(int stockMax){
		mStockMax = stockMax;
	}
	public void setLikeNum(int likeNum){
		mLikeNum = likeNum;
	}
	public void setImageId(int imageId){
		mImageId = imageId;
	}
	public void setShopId(int shopId){
		mShopId = shopId;
	}
	public void setPlateId(int plateId){
		mPlateId = plateId;
	}

}
