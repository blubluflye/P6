package com.p6.payMyBuddy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.p6.payMyBuddy.dto.AuthorityDto;
import com.p6.payMyBuddy.mapper.AuthorityMapper;
import com.p6.payMyBuddy.model.Authority;
import com.p6.payMyBuddy.repository.AuthorityRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthorityService {

	private final AuthorityRepository authorityRepository;
	private final AuthorityMapper authorityMapper;
	
	public AuthorityDto getAuthorityById(Integer id) {
		return authorityMapper.authorityEntityToDto(authorityRepository.findById(id).orElse(null));
	}
	
	public List<AuthorityDto> getAllAuthorities() {
		return authorityMapper.authorityListEntityToDto(authorityRepository.findAll());
	}
	
	public AuthorityDto createAuthority(Authority authority) {
		return authorityMapper.authorityEntityToDto(authorityRepository.save(authority));
	}
	
	public AuthorityDto updateAuthority(Authority authority) {
		return authorityMapper.authorityEntityToDto(authorityRepository.save(authority));
	}
	
	public void deleteAuthority(Authority authority) {
		authorityRepository.delete(authority);
	}
}
