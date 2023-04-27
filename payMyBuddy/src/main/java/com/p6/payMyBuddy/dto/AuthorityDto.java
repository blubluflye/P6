package com.p6.payMyBuddy.dto;

import lombok.Builder;

@Builder
public record AuthorityDto(
		Integer id,
		String authority) {}
