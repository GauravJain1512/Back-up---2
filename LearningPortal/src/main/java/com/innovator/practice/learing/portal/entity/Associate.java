package com.innovator.practice.learing.portal.entity;

import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "associate", uniqueConstraints = {@UniqueConstraint(columnNames = {"associateEmail",
		"employeeNumber", 
})})
public class Associate {
	
	@Id
	@SequenceGenerator(name = "associate_sequence", sequenceName = "associate_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "associate_sequence")
	private Long associateId;
	
	private String associateName;
	
	private String associateEmail;
	
	private String password;
	
	private int employeeNumber;
	
	private String baseBranch;
	
	private String deputeBranch;
	
	private Date joiningDate;
	
	private double yearOfExperience;
	
	private int numberOfCoursesCompleted;
	
	private int numberOfCoursesInProgress;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "team_lead_Id")
	@JsonIgnore
	private TeamLead teamLead;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "associate_roles",
	joinColumns = @JoinColumn(name = "associate_Id", referencedColumnName = "associateId"),
	inverseJoinColumns = @JoinColumn(name = "role_Id", referencedColumnName = "roleId"))
	private Set<Role> roles = new HashSet<>();

	public Associate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Associate(Long associateId, String associateName, String associateEmail, String password, int employeeNumber,
			String baseBranch, String deputeBranch, Date joiningDate, double yearOfExperience,
			int numberOfCoursesCompleted, int numberOfCoursesInProgress, TeamLead teamLead, Set<Role> roles) {
		super();
		this.associateId = associateId;
		this.associateName = associateName;
		this.associateEmail = associateEmail;
		this.password = password;
		this.employeeNumber = employeeNumber;
		this.baseBranch = baseBranch;
		this.deputeBranch = deputeBranch;
		this.joiningDate = joiningDate;
		this.yearOfExperience = yearOfExperience;
		this.numberOfCoursesCompleted = numberOfCoursesCompleted;
		this.numberOfCoursesInProgress = numberOfCoursesInProgress;
		this.teamLead = teamLead;
		this.roles = roles;
	}


	

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getNumberOfCoursesCompleted() {
		return numberOfCoursesCompleted;
	}


	public void setNumberOfCoursesCompleted(int numberOfCoursesCompleted) {
		this.numberOfCoursesCompleted = numberOfCoursesCompleted;
	}


	public int getNumberOfCoursesInProgress() {
		return numberOfCoursesInProgress;
	}


	public void setNumberOfCoursesInProgress(int numberOfCoursesInProgress) {
		this.numberOfCoursesInProgress = numberOfCoursesInProgress;
	}


	public void setYearOfExperience(double yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}


	public Long getAssociateId() {
		return associateId;
	}

	public void setAssociateId(Long associateId) {
		this.associateId = associateId;
	}

	public String getAssociateName() {
		return associateName;
	}

	public void setAssociateName(String associateName) {
		this.associateName = associateName;
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

	public double getYearOfExperience() {
		return yearOfExperience;
	}

	public void setYearOfExperience(int yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}

	public int getNumberOfCourcesCompleted() {
		return numberOfCoursesCompleted;
	}

	public void setNumberOfCourcesCompleted(int numberOfCourcesCompleted) {
		this.numberOfCoursesCompleted = numberOfCourcesCompleted;
	}

	public int getNumberOfCourcesInProgress() {
		return numberOfCoursesInProgress;
	}

	public void setNumberOfCourcesInProgress(int numberOfCourcesInProgress) {
		this.numberOfCoursesInProgress = numberOfCourcesInProgress;
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
	

	public String getAssociateEmail() {
		return associateEmail;
	}


	public void setAssociateEmail(String associateEmail) {
		this.associateEmail = associateEmail;
	}




	public int getEmployeeNumber() {
		return employeeNumber;
	}




	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}




	public TeamLead getTeamLead() {
		return teamLead;
	}




	public void setTeamLead(TeamLead teamLead) {
		this.teamLead = teamLead;
	}




	@Override
	public String toString() {
		return "Associate [associateId=" + associateId + ", associateName=" + associateName + ", associateEmail="
				+ associateEmail + ", employeeNumber=" + employeeNumber + ", baseBranch=" + baseBranch
				+ ", deputeBranch=" + deputeBranch + ", joiningDate=" + joiningDate + ", yearOfExperience="
				+ yearOfExperience + ", numberOfCourcesCompleted=" + numberOfCoursesCompleted
				+ ", numberOfCourcesInProgress=" + numberOfCoursesInProgress + ", teamLead=" + teamLead + ", roles="
				+ roles + "]";
	}
	
	
	

}
