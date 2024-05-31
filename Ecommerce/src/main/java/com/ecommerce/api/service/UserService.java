package com.ecommerce.api.service;

import java.util.List;

import com.ecommerce.api.models.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);

	List<UserDto> findAllUsers();
	
	UserDto getUserByUserId(Long userId);
	
	UserDto addRoleToUser(Long userId, String role);
}
