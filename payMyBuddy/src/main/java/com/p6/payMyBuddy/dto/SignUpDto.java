package com.p6.payMyBuddy.dto;

import lombok.Builder;

@Builder
public record SignUpDto(String username,
		String email,
		String password) {}
