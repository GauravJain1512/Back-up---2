package com.auth.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
	
	@GetMapping("/test")
	public ResponseEntity<String> consumerApi(){
		return new ResponseEntity<String>("Consumer Api", HttpStatus.OK);
	}

}
