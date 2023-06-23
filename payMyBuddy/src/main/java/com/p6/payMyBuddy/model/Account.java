package com.p6.payMyBuddy.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer accountId;
	
	@Column(name = "emailAddress")
	private String emailAddress;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "money", nullable = false)
	private float money;
	
	@Singular
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "users_authorities", joinColumns = {
			@JoinColumn(name = "USERS_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "AUTHORITIES_ID", referencedColumnName = "ID") })
	private List<Authority> authorities;
	
	@OneToMany(
			mappedBy = "sender",
			cascade =  CascadeType.ALL,
			orphanRemoval = true)
	List<Transfer> transfers = new ArrayList<Transfer>();
	
	@OneToMany(
			mappedBy = "account",
			cascade =  CascadeType.ALL,
			orphanRemoval = true)
	List<Contact> contacts = new ArrayList<Contact>();
	
	@Builder.Default
	private Boolean accountNonExpired = true;
	@Builder.Default
	private Boolean accountNonLocked = true;
	@Builder.Default
	private Boolean credentialsNonExpired = true;
	@Builder.Default
	private Boolean enabled = true;
}