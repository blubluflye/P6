package com.p6.payMyBuddy.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record AccountDto(
	Integer id,
	String username,
	List<AuthorityDto> authorities,
	Boolean accountNonExpired,
	Boolean accountNonLocked,
	Boolean credentialsNonExpired,
	Boolean enabled,
	float money,
	String email) {}
