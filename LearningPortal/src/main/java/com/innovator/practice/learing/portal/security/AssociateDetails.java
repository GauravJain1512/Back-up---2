package com.innovator.practice.learing.portal.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.innovator.practice.learing.portal.entity.Associate;


public class AssociateDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Associate associate;
	

	public AssociateDetails() {
		super();
		
	}

	public AssociateDetails(Associate associate) {
		super();
		this.associate = associate;
	}
	
	

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<SimpleGrantedAuthority> authorities = associate.getRoles().stream().map(role-> new SimpleGrantedAuthority("ROLE_"+role.getRoleName())).collect(Collectors.toList());
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return associate.getPassword();
	}

	@Override
	public String getUsername() {
		return associate.getAssociateEmail();
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
