package com.ecommerce.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.config.CustomUserDetails;
import com.ecommerce.api.config.CustomUserDetailsService;
import com.ecommerce.api.config.JwtTokenHelper;
import com.ecommerce.api.entity.User;
import com.ecommerce.api.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/auth")
	public ResponseEntity<String> login(@RequestBody User user){
		Optional<User> optionalUser = Optional.empty();
		String email = user.getEmail();
		if(StringUtils.hasText(email)) {
			optionalUser = userRepository.findByEmail(email);
		}
		
		Long employeeId = user.getEmployeeId();
		if(employeeId != null && employeeId > 0) {
			optionalUser = userRepository.findByEmployeeId(employeeId);
		}
		String userName = user.getUserName();
		
		if(StringUtils.hasText(userName)) {
			optionalUser = userRepository.findByUserName(userName);
		}
		if(optionalUser.isPresent()) {
			User user2 = optionalUser.get();
			try {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user2.getEmail(), user2.getPassword());
				Authentication authenticate = authenticationManager.authenticate(authenticationToken);
				UserDetails details = customUserDetailsService.loadUserByUsername(authenticate.getName());
				String token = jwtTokenHelper.generateToken(details);
				return new ResponseEntity<String>(token, HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
