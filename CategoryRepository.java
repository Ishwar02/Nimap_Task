package com.nimaptask.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nimaptask.demo.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> 
{
	

}

