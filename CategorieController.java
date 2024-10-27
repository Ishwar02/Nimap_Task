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
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")

public class CategorieController {

	 @Autowired
	    private CategoryRepository categoryRepository;


	    
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
	        Optional<Category> optionalCategory = categoryRepository.findById(id);

	        if (optionalCategory.isPresent()) {
	            Category category = optionalCategory.get();
	            return new ResponseEntity<>(category, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category categoryDetails) {
	        Optional<Category> optionalCategory = categoryRepository.findById(id);

	        if (optionalCategory.isPresent()) {
	            Category category = optionalCategory.get();
	            category.setCategory_name(categoryDetails.getCategory_name());
	            category.setCategory_discription(categoryDetails.getCategory_discription());
	            Category updatedCategory = categoryRepository.save(category);
	            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    


	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
	      Optional<Category> data = categoryRepository.findById(id);
	      if(data == null)
	    	  return  new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	      else	
	      {
	    	  categoryRepository.deleteById(id);
	    	  return new ResponseEntity<>(HttpStatus.OK);
	      }
	       
	    }
	
	
}
