package com.innovator.practice.learing.portal.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.innovator.practice.learing.portal.entity.TeamLead;

public class TeamLeaderDeatils implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TeamLead teamLead;
	
	public TeamLeaderDeatils() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeamLeaderDeatils(TeamLead teamLead) {
		super();
		this.teamLead = teamLead;
	}
	
	
	public TeamLead getTeamLead() {
		return teamLead;
	}

	public void setTeamLead(TeamLead teamLead) {
		this.teamLead = teamLead;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<SimpleGrantedAuthority> authorities = this.teamLead.getRoles().stream().map(role-> new SimpleGrantedAuthority("ROLE_"+role.getRoleName())).collect(Collectors.toList());
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return teamLead.getPassword();
	}

	@Override
	public String getUsername() {
		return teamLead.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	

}
