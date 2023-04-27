package com.p6.payMyBuddy.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.p6.payMyBuddy.dto.AuthorityDto;
import com.p6.payMyBuddy.service.AuthorityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthorityController {
	
	private final AuthorityService authorityServce;
	
	@GetMapping("/authorities")
	public List<AuthorityDto> getauthorities() {
		return authorityServce.getAllAuthorities();
	}

	@GetMapping("/authorities/{id}")
	public AuthorityDto getauthorityById(@PathVariable Integer id) {
		return authorityServce.getAuthorityById(id);
	}
}
