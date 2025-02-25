package com.innovator.practice.learing.portal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.innovator.practice.learing.portal.entity.Associate;
import com.innovator.practice.learing.portal.entity.TeamLead;
import com.innovator.practice.learing.portal.jwt.JwtTokenHelper;
import com.innovator.practice.learing.portal.repository.AssociateRepository;
import com.innovator.practice.learing.portal.repository.TeamLeadRepository;

@RestController
public class AuthController {
	
	@Autowired
	private AssociateRepository associateRepository;
	
	@Autowired
	private TeamLeadRepository teamLeadRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@PostMapping("/auth")
	public ResponseEntity<String> loginApi(@RequestBody com.innovator.practice.learing.portal.entity.LoginDto loginDto){
		
		String associateEmail = loginDto.getEmail();
		Long employeeNumber = loginDto.getEmployeeNumber();
		if(associateEmail != null && !associateEmail.isEmpty()) {
			Associate associate = associateRepository.findByAssociateEmail(associateEmail).get();
			
			Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), associate.getPassword()));
			//SecurityContextHolder.getContext().setAuthentication(authenticate);
			String token = jwtTokenHelper.generateToken(authenticate);
			return new ResponseEntity<String>(token, HttpStatus.OK);
		}
		if(employeeNumber != null) {
			
			TeamLead teamLead = teamLeadRepository.findByEmployeeNumber(employeeNumber).get();
			
			Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(teamLead.getEmail(), teamLead.getPassword()));
			//SecurityContextHolder.getContext().setAuthentication(authenticate);
			String token = jwtTokenHelper.generateToken(authenticate);
			return new ResponseEntity<String>(token, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Login failed", HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("/")
	public String dummyApi() {
		return "Dummy";
	}

}
