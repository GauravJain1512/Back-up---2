package com.innovator.practice.learing.portal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(columnNames = {"authorityName"})})
public class Authorities {
	
	@Id
	@SequenceGenerator(name = "authority_sequence", sequenceName = "authority_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_sequence")
	private Long authorityId;
	
	private String authorityName;
	

	public Authorities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Authorities(Long authorityId, String authorityName) {
		super();
		this.authorityId = authorityId;
		this.authorityName = authorityName;
	}

	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	@Override
	public String toString() {
		return "Authorities [authorityId=" + authorityId + ", authorityName=" + authorityName + "]";
	}
	
	
	
	

}
