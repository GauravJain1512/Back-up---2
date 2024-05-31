package com.innovator.practice.learing.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innovator.practice.learing.portal.entity.TeamLead;

public interface TeamLeadRepository extends JpaRepository<TeamLead, Long> {
	
	Optional<TeamLead> findByEmployeeNumber(Long employeeNumber);
	
	Optional<TeamLead> findByEmail(String email);

}
