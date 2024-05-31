package com.auth.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
	
	@GetMapping("/test")
	public ResponseEntity<String> sellerApi(){
		return new ResponseEntity<String>("Seller Api", HttpStatus.OK);
	}

}
