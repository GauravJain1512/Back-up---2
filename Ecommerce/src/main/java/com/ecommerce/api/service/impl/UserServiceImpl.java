package com.ecommerce.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.api.entity.Role;
import com.ecommerce.api.entity.User;
import com.ecommerce.api.exception.ResourceNotFoundException;
import com.ecommerce.api.models.UserDto;
import com.ecommerce.api.repository.RoleRepository;
import com.ecommerce.api.repository.UserRepository;
import com.ecommerce.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = mapper.map(userDto, User.class);
		Set<Role> roles = user.getRoles();
		
		User savedUser = userRepository.save(user);
		
		return mapper.map(savedUser, UserDto.class);
	}

	@Override
	public List<UserDto> findAllUsers() {
		List<User> users = userRepository.findAll();
		
		List<UserDto> userDtos = users.stream().map(user-> mapper.map(user, UserDto.class)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public UserDto getUserByUserId(Long userId) {
		
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		
		return mapper.map(user, UserDto.class);
	}

	@Override
	public UserDto addRoleToUser(Long userId, String role) {
		
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		if(role.equalsIgnoreCase("Consumer")) {
			Optional<Role> optional = roleRepository.findByRoleName("CONSUMER");
			if(optional.isPresent())
			user.addRole(optional.get());
		}else {
			Optional<Role> optional = roleRepository.findByRoleName("SELLER");
			if(optional.isPresent())
			user.addRole(optional.get());
		}
		
		User savedUser = userRepository.save(user);
		
		return mapper.map(savedUser, UserDto.class);
	}

}
