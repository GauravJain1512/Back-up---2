package com.innovator.practice.learing.portal.entity;

public class LoginDto {
	
	private String email;
	
	private Long employeeNumber;
	
	private String Password;
	
	

	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public LoginDto(String email, Long employeeNumber, String password) {
		super();
		this.email = email;
		this.employeeNumber = employeeNumber;
		Password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Long getEmployeeNumber() {
		return employeeNumber;
	}



	public void setEmployeeNumber(Long employeeNumber) {
		this.employeeNumber = employeeNumber;
	}



	public String getPassword() {
		return Password;
	}



	public void setPassword(String password) {
		Password = password;
	}
	
	
	
	

}
