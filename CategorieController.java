package com.nimaptask.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nimaptask.demo.entity.Category;
import com.nimaptask.demo.repository.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")

public class CategorieController {

	 @Autowired
	    private CategoryRepository categoryRepository;

//	    @GetMapping
//	    public ResponseEntity<Page<Category>> getAllCategories(@RequestParam(defaultValue = "0") int page) {
//	        return new ResponseEntity<>(categoryRepository.findAll(PageRequest.of(page, 10)), HttpStatus.OK);
//	    }
	    
	    @GetMapping
	    public ResponseEntity<List<Category>> getAllCategories(@RequestParam(defaultValue = "0") int page) {
	        Page<Category> categoryPage = categoryRepository.findAll(PageRequest.of(page, 10));
	        return new ResponseEntity<>(categoryPage.getContent(), HttpStatus.OK);
	    }


	    @PostMapping
	    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
	        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
	        return categoryRepository.findById(id)
	                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category categoryDetails) {
	        return categoryRepository.findById(id)
	                .map(category -> {
	                    category.setCategory_name(categoryDetails.getCategory_name());
	                    return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
	                })
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
	        return categoryRepository.findById(id)
	                .map(category -> {
	                    categoryRepository.delete(category);
	                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	                })
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }
	
	
}
