package com.nimaptask.demo.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Component
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  product_id;
	private String product_name;
	private String product_discription;
	private double price;
	
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category  category_id; 
    
    
    
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_discription() {
		return product_discription;
	}
	public void setProduct_discription(String product_discription) {
		this.product_discription = product_discription;
	}
	public Category  getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Category  category_id) {
		this.category_id = category_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
}
