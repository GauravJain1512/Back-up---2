package com.innovator.practice.learing.portal.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innovator.practice.learing.portal.entity.Associate;
import com.innovator.practice.learing.portal.entity.TeamLead;
import com.innovator.practice.learing.portal.repository.AssociateRepository;
import com.innovator.practice.learing.portal.repository.TeamLeadRepository;

@RestController
@RequestMapping("/team-lead")
public class TeamLeadController {
	
	@Autowired
	private TeamLeadRepository teamLeadRepository;
	
	@Autowired
	private AssociateRepository associateRepository;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{teamLeadId}/associates")
	public ResponseEntity<List<Associate>> getAllAssociate(@PathVariable Long teamLeadId, Principal principal){
		Optional<TeamLead> optionalTeamLead = teamLeadRepository.findById(teamLeadId);
		if(optionalTeamLead.isPresent()) {
			String name = principal.getName();
			Optional<TeamLead> optTeamLead = teamLeadRepository.findByEmail(name);
			if(optTeamLead.isPresent()) {
				TeamLead teamLead = optTeamLead.get();
			
				List<Associate> associates = associateRepository.findByTeamLeadId(teamLeadId);
				
				
				//List<Associate> associates = optionalTeamLead.get().getAssociates();
				if(teamLead.getTeamLeadId().equals(teamLeadId))
				return new ResponseEntity<List<Associate>>(associates, HttpStatus.OK);
			}
		}
		return new ResponseEntity<List<Associate>>(HttpStatus.NOT_FOUND);
	}

}
