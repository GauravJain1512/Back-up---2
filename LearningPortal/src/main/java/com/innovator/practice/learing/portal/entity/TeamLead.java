package com.innovator.practice.learing.portal.entity;

import java.util.ArrayList;
import java.util.Date;
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

@Entity
@Table(name = "team_lead", uniqueConstraints = {@UniqueConstraint(columnNames = {"employeeNumber",
		"email"
})})
public class TeamLead {
	
	@Id
	@SequenceGenerator(name = "lead_sequence", sequenceName = "lead_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lead_sequence")
	private Long teamLeadId;
	
	private String teamLeadName;
	
	private Long employeeNumber;
	
	private String password;
	
	private String email;
	
	private String baseBranch;
	
	private String deputeBranch;
	
	private Date joiningDate;
	
	private Long mobileNumber;
	
	private int numberOfExperience;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Associate> associates = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "team_lead_roles",
	joinColumns = @JoinColumn(name = "team_lead_Id", referencedColumnName = "teamLeadId"),
	inverseJoinColumns = @JoinColumn(name = "role_Id", referencedColumnName = "roleId"))
	private Set<Role> roles = new HashSet<>();
	

	public TeamLead() {
		super();
	}


	public TeamLead(Long teamLeadId, String teamLeadName, Long employeeNumber, String password, String email,
			String baseBranch, String deputeBranch, Date joiningDate, Long mobileNumber, int numberOfExperience,
			List<Associate> associates, Set<Role> roles) {
		super();
		this.teamLeadId = teamLeadId;
		this.teamLeadName = teamLeadName;
		this.employeeNumber = employeeNumber;
		this.password = password;
		this.email = email;
		this.baseBranch = baseBranch;
		this.deputeBranch = deputeBranch;
		this.joiningDate = joiningDate;
		this.mobileNumber = mobileNumber;
		this.numberOfExperience = numberOfExperience;
		this.associates = associates;
		this.roles = roles;
	}

	
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public void removeRole(Role role) {
		this.roles.remove(role);
	}


	public Long getTeamLeadId() {
		return teamLeadId;
	}

	public void setTeamLeadId(Long teamLeadId) {
		this.teamLeadId = teamLeadId;
	}

	public String getTeamLeadName() {
		return teamLeadName;
	}

	public void setTeamLeadName(String teamLeadName) {
		this.teamLeadName = teamLeadName;
	}

	public Long getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Long employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBaseBranch() {
		return baseBranch;
	}

	public void setBaseBranch(String baseBranch) {
		this.baseBranch = baseBranch;
	}

	public String getDeputeBranch() {
		return deputeBranch;
	}

	public void setDeputeBranch(String deputeBranch) {
		this.deputeBranch = deputeBranch;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getNumberOfExperience() {
		return numberOfExperience;
	}

	public void setNumberOfExperience(int numberOfExperience) {
		this.numberOfExperience = numberOfExperience;
	}

	public List<Associate> getAssociates() {
		return associates;
	}

	public void setAssociates(List<Associate> associates) {
		this.associates = associates;
	}

	@Override
	public String toString() {
		return "TeamLead [teamLeadId=" + teamLeadId + ", teamLeadName=" + teamLeadName + ", employeeNumber="
				+ employeeNumber + ", email=" + email + ", baseBranch=" + baseBranch + ", deputeBranch=" + deputeBranch
				+ ", joiningDate=" + joiningDate + ", mobileNumber=" + mobileNumber + ", numberOfExperience="
				+ numberOfExperience + ", associates=" + associates + "]";
	}
	
	

}
