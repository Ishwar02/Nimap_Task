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
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductController {

	 @Autowired
	    private ProductRepository productRepository;


	    
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
	        Optional<Product> optionalProduct = productRepository.findById(id);

	        if (optionalProduct.isPresent()) {
	            Product product = optionalProduct.get();
	            return new ResponseEntity<>(product, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }


	    @PutMapping("/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
	        Optional<Product> optionalProduct = productRepository.findById(id);

	        if (optionalProduct.isPresent()) {
	            Product product = optionalProduct.get();
	            product.setProduct_name(productDetails.getProduct_name());
	            product.setPrice(productDetails.getPrice());
	            product.setProduct_discription(productDetails.getProduct_discription());
	            Product updatedProduct = productRepository.save(product);
	            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }


	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
	        Optional<Product> optionalProduct = productRepository.findById(id);

	        if (optionalProduct.isPresent()) {
	            Product product = optionalProduct.get();
	            productRepository.delete(product);
	            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

}
