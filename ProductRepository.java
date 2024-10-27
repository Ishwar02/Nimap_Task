package com.nimaptask.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nimaptask.demo.entity.Product;




public interface ProductRepository  extends JpaRepository<Product, Integer> {

	
	
	
	
}