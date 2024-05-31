package com.auth.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.api.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
