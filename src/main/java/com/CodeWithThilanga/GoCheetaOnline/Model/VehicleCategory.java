package com.CodeWithThilanga.GoCheetaOnline.Model;

public class VehicleCategory {
	private float categoryPrice;
	private String categoryId;
	private String category;
	
	public VehicleCategory(float price , String category) {
		this.category = category;
		this.categoryPrice = price;
	}
	
	public VehicleCategory(String id , float price , String category) {
		this.categoryId = id;
		this.category = category;
		this.categoryPrice = price;
	}

	public String getCategoryId() {
		return categoryId;
	}


	public float getCategoryPrice() {
		return categoryPrice;
	}

	public void setCategoryPrice(float categoryPrice) {
		this.categoryPrice = categoryPrice;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}



}
