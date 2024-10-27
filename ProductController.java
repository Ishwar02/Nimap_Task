package com.nimaptask.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nimaptask.demo.entity.Product;
import com.nimaptask.demo.repository.ProductRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductController {

	 @Autowired
	    private ProductRepository productRepository;

//	    @GetMapping
//	    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page) {
//	        return new ResponseEntity<>(productRepository.findAll(PageRequest.of(page, 10)), HttpStatus.OK);
//	    }
	    
	    @GetMapping
	    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page) {
	    	Page<Product> productPage = productRepository.findAll(PageRequest.of(page, 10));
	        return new ResponseEntity<>(productPage.getContent(), HttpStatus.OK);
	    }

	    @PostMapping
	    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
	        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable int id) {
	        return productRepository.findById(id)
	                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
	        return productRepository.findById(id)
	                .map(product -> {
	                    product.setProduct_name(productDetails.getProduct_name());
	                    product.setPrice(productDetails.getPrice());
	                    return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
	                })
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
	        return productRepository.findById(id)
	                .map(product -> {
	                    productRepository.delete(product);
	                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	                })
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }
}
