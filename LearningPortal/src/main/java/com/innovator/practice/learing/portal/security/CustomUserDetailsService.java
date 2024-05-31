package com.innovator.practice.learing.portal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.innovator.practice.learing.portal.entity.Associate;
import com.innovator.practice.learing.portal.entity.TeamLead;
import com.innovator.practice.learing.portal.repository.AssociateRepository;
import com.innovator.practice.learing.portal.repository.TeamLeadRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private TeamLeadRepository teamLeadRepository;
	
	@Autowired
	private AssociateRepository associateRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Associate> optionalAssociate = associateRepository.findByAssociateEmail(username);
		
		if(optionalAssociate.isPresent()) {
			Associate associate = optionalAssociate.get();
			return new AssociateDetails(associate);
		}
		
		Optional<TeamLead> optionalTeamLead = teamLeadRepository.findByEmail(username);
		
		if(optionalTeamLead.isPresent()) {
			TeamLead teamLead = optionalTeamLead.get();
			return new TeamLeaderDeatils(teamLead);
		}
		
		throw new UsernameNotFoundException("User not found with "+username);
		
	}

}
