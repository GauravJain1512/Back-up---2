package com.innovator.practice.learing.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.innovator.practice.learing.portal.entity.Associate;

public interface AssociateRepository extends JpaRepository<Associate, Long> {
	
	Optional<Associate> findByAssociateEmail(String associateEmail);

	@Query(value = "select * from associate a where a.team_lead_Id = :teamLeadId ", nativeQuery = true)
	List<Associate> findByTeamLeadId(@Param("teamLeadId") Long teamLeadId);

}
