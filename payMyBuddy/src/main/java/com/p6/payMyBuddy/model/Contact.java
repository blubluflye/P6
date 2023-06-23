package com.p6.payMyBuddy.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contact")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contact_id")
	private Integer contactId;
	
	@ManyToOne(cascade =  CascadeType.ALL)
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;
	
	
	@ManyToOne(cascade =  CascadeType.ALL)
	@JoinColumn(name = "friend_account_id", nullable = false)
	private Account friendAccount;
}
