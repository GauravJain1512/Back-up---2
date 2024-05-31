package com.auth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth.api.config.CustomUserDetailService;
import com.auth.api.config.CustomUserDetails;
import com.auth.api.config.JwtTokenHelper;
import com.auth.api.model.User;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private CustomUserDetailService userDetailService;
	
	
	@PostMapping("/auth")
	public ResponseEntity<String> loginApi(@RequestBody User user){
		
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			Authentication authenticate = authenticationManager.authenticate(authenticationToken);
			UserDetails userDetails = userDetailService.loadUserByUsername(authenticate.getName());
			String token = jwtTokenHelper.generateToken(userDetails);
			return new ResponseEntity<String>(token, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("Bad Credentials", HttpStatus.BAD_REQUEST);
		}
		
	}

}
