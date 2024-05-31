package com.ecommerce.api.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.api.models.ProductDto;
import com.ecommerce.api.models.Ratings;
import com.ecommerce.api.models.UserDto;
import com.ecommerce.api.models.Views;
import com.ecommerce.api.service.ProductService;
import com.ecommerce.api.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/api/public")
public class PublicController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@PostMapping("/users")
	public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto){
		UserDto user = userService.createUser(userDto);
		String location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId())
				.toUriString();
		
		return new ResponseEntity<String>(location, HttpStatus.CREATED);
	}
	

	@GetMapping("/users")
	public ResponseEntity<MappingJacksonValue> getAllUser(){
		List<UserDto> users = userService.findAllUsers();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
		Set<String> fields = new HashSet<>();
		fields.add("userName");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("user-filer", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
		mappingJacksonValue.setFilters(filterProvider);
		return new ResponseEntity<MappingJacksonValue>(mappingJacksonValue,HttpStatus.OK);
	}
	
	@JsonView(Views.Internal.class)
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDto> getUserByUserId(@PathVariable Long userId){
		UserDto userDto = userService.getUserByUserId(userId);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductDto> getRating(@PathVariable Integer productId){
		ProductDto productDto = productService.getProductById(productId);
		String ratingUrl = "http://localhost:8080/rating"+productDto.getProductId();
		
		Ratings[] ratings = restTemplate.getForObject(ratingUrl, Ratings[].class);
		productDto.setRatings(Arrays.asList(ratings));
		
		return new ResponseEntity<ProductDto>(productDto,HttpStatus.OK);
	}
	
}




