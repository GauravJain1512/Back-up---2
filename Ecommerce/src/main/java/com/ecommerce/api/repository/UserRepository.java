package com.ecommerce.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.api.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByEmployeeId(Long employeeId);
	
	Optional<User> findByUserName(String userName);

}
