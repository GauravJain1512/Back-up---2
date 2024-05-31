package com.innovator.practice.learing.portal.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"roleName"})})
public class Role {
	
	@Id
	@SequenceGenerator( name = "role_sequence", sequenceName = "role_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence" )
	private Long roleId;
	
	private String roleName;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "roles_authorities",
	joinColumns = @JoinColumn(name = "role_Id", referencedColumnName = "roleId"),
	inverseJoinColumns = @JoinColumn(name = "authority_Id", referencedColumnName = "authorityId"))
	private Set<Authorities> authorities = new HashSet<>();

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Long roleId, String roleName, Set<Authorities> authorities) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.authorities = authorities;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<Authorities> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}
	
	public void addAuthorities(Set<Authorities> newAuthorities) {
		this.authorities.addAll(newAuthorities);
	}
	
	public void removeAuthorities(Set<Authorities> removeAuthorities) {
		this.authorities.removeAll(removeAuthorities);
	}

	@Override
	public String toString() {
		return "Role [roleName=" + roleName + "]";
	}
	
	
	
	

}
