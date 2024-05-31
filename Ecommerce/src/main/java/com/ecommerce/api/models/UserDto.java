package com.ecommerce.api.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ecommerce.api.entity.Role;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonFilter("user-filer")
public class UserDto {
	
	@JsonView(Views.Internal.class)
	private Long userId;
	
	@JsonView(Views.Internal.class)
	@NotEmpty
	@Size(min = 3,max = 40, message = "Please enter valid username")
	private String userName;
	
	@JsonView(Views.Internal.class)
	@Email(regexp = "[a-zA-Z0-9][a-zA-z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+", message = "please enter valid email")
	private String email; 
	
	@JsonIgnore
	private String password;
	
	@JsonView(Views.Admin.class)
	private Long employeeId;
	
	@JsonView(Views.Admin.class)
	private Set<Role> roles;

}
