package com.p6.payMyBuddy.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.p6.payMyBuddy.dto.AccountDto;
import com.p6.payMyBuddy.model.Account;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AccountMapper {
	
private final AuthorityMapper authorityMapper;
	
	public AccountDto accountEntityToDto(Account account) {
		return AccountDto.builder()
				.id((account.getAccountId()))
				.username(account.getUsername())
				.authorities(authorityMapper.authorityListEntityToDto(account.getAuthorities()))
				.accountNonExpired(account.getAccountNonExpired())
				.accountNonLocked(account.getAccountNonLocked())
				.credentialsNonExpired(account.getCredentialsNonExpired())
				.enabled(account.getEnabled())
				.email(account.getEmailAddress())
				.money(account.getMoney())
				.build();
	}
	
	public List<AccountDto> accountListEntityToDto(List<Account> accounts) {
		return accounts.stream()
				.map(account -> accountEntityToDto(account))
				.toList();
	}
	
	public Account accountDtoToEntity(AccountDto account, String password) {
		return Account.builder()
				.accountId(account.id())
				.username(account.username())
				.password(password)
				.authorities(authorityMapper.authorityListDtoToEntity(account.authorities()))
				.accountNonExpired(account.accountNonExpired())
				.accountNonLocked(account.accountNonLocked())
				.credentialsNonExpired(account.credentialsNonExpired())
				.enabled(account.enabled())
				.emailAddress(account.email())
				.money(account.money())
				.build();
	}

	public List<Account> accountListDtoToEntity(List<AccountDto> accounts, String password) {
		return accounts.stream()
				.map(account -> accountDtoToEntity(account, password))
				.toList();
	}

}
