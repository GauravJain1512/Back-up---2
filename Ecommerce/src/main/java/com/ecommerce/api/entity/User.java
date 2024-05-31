package com.ecommerce.api.entity;

import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1 )
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	private Long userId;
	
	private String userName;
	
	private String email;
	
	private String password;
	
	private Long employeeId;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles",
	joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
	inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId")
			)
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "seller")
	private List<Product> product;
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
}
