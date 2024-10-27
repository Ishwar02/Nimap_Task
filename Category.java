package com.nimaptask.demo.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;
@Component
@Entity

public class Category  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  category_id;
	private String category_name;
	private String category_discription;
	
	@OneToMany
	private List<Product> products;
	
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_discription() {
		return category_discription;
	}
	public void setCategory_discription(String category_discription) {
		this.category_discription = category_discription;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
