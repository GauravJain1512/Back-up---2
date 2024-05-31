package com.ecommerce.api.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecommerce.api.entity.User;
import com.ecommerce.api.models.ProductDto;
import com.ecommerce.api.repository.UserRepository;
import com.ecommerce.api.service.ProductService;

@RestController
@RequestMapping("/api/seller")
@Validated
public class SellerController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@PostMapping("/products/{userId}/{categoryId}")
	public ResponseEntity<Void> addProduct(@PathVariable @Min(1) Long userId, @PathVariable @Min(1) Integer categoryId,
			@RequestBody ProductDto productDto,UriComponentsBuilder builder){
		ProductDto addedProduct = productService.addProduct(userId, categoryId, productDto);
		String redirectUrl = "/api/seller/products/{productId}";
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path(redirectUrl).buildAndExpand(addedProduct.getProductId()).toUri());
		
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable @Min(1) Integer productId){
		ProductDto productDto = productService.getProductById(productId);
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<String> deleteProductById(@PathVariable @Min(1) Integer productId){
		String deleteProductById = productService.deleteProductById(productId);
		return new ResponseEntity<String>(deleteProductById,HttpStatus.OK);
	}
	
	@PutMapping("/products/{productId}")
	public ResponseEntity<ProductDto> updateProductById(@PathVariable @Min(1) Integer productId, 
			@Valid @RequestBody ProductDto productDto){
		ProductDto updateProduct = productService.updateProduct(productId, productDto);
		return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts(Principal principal){
		String name = principal.getName();
		User user = userRepository.findByEmail(name).get();
		List<ProductDto> products = productService.getAllProducts(user);
		return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
	}
	 
}
