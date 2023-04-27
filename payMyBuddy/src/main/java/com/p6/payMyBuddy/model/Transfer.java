package com.p6.payMyBuddy.model;

import java.util.List;

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
@Table(name = "transfer")
public class Transfer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transfer_id")
	private int transferId;
	
	@ManyToOne(cascade =  CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private Account sender;
	
	@ManyToOne(cascade =  CascadeType.ALL)
	@JoinColumn(name = "receiver_account_id")
	private Account receiver;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "description")
	private String description;
	
}

