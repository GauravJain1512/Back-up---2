package com.ecommerce.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.models.UserDto;
import com.ecommerce.api.models.Views;
import com.ecommerce.api.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto> users = userService.findAllUsers();
		
		return new ResponseEntity<List<UserDto>>(users,HttpStatus.OK);
	}
	
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDto> getUserByUserId(@PathVariable Long userId){
		UserDto userDto = userService.getUserByUserId(userId);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<UserDto> addRoleToUser(@PathVariable Long userId,
			@RequestParam("Role") String role ){
		UserDto userDto = userService.addRoleToUser(userId, role);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}

}
