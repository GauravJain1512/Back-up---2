package com.authentication.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authentication.api.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
