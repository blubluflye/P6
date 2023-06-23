package com.p6.payMyBuddy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.RequiredArgsConstructor;

import com.p6.payMyBuddy.dto.AccountDto;
import com.p6.payMyBuddy.dto.SignUpDto;
import com.p6.payMyBuddy.mapper.AccountMapper;
import com.p6.payMyBuddy.model.Account;
import com.p6.payMyBuddy.model.Authority;
import com.p6.payMyBuddy.repository.AccountRepository;
import com.p6.payMyBuddy.repository.AuthorityRepository;

@Service
@RequiredArgsConstructor
public class AccountService {
	
	private final AccountRepository accountRepository;
	private final AuthorityRepository authorityRepository;
	private final AccountMapper accountMapper;
	
	public AccountDto getLoginAccount() {
		return accountMapper.accountEntityToDto(accountRepository.findLoginUser().orElse(null));
	}
	
	public AccountDto getAccountById(Integer id) {
		return accountMapper.accountEntityToDto(accountRepository.findById(id).orElse(null));
	}
	
	public List<AccountDto> getAllAccounts(){
		return accountMapper.accountListEntityToDto(accountRepository.findAll());
	}
	
	public AccountDto createAccount(Account account) {
		return accountMapper.accountEntityToDto(accountRepository.save(account));
	}
	
	public AccountDto updateUser(Account account) {
		return accountMapper.accountEntityToDto(accountRepository.save(account));
	}
	
	public boolean updateUsername(Account account, String username) {
		if (accountRepository.findByUsername(username).isEmpty()) {
			account.setUsername(username);
			accountRepository.save(account);
		}
		return account.getUsername().equals(username);
	}
	
	public boolean updatePassword(Account account, String password) {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String newPassword = bc.encode(password);
		account.setPassword(newPassword);
		accountRepository.save(account);
		return account.getPassword().equals(newPassword);
	}
	
	public boolean updateEmailAddress(Account account, String emailAddress) {
		if (!accountRepository.existsByEmailAddress(emailAddress)) {
			account.setEmailAddress(emailAddress);
			accountRepository.save(account);
		}
		return account.getEmailAddress().equals(emailAddress);
	}
	
	public float addMoney(Account account, float f) {
		if (f > 0) {
			f += account.getMoney();
			account.setMoney(f);
			accountRepository.save(account);
		}
		return f;
	}
	
	public boolean checkEmail(String emailAddress) {
		return accountRepository.existsByEmailAddress(emailAddress);
	}
	
	public Account signUp(SignUpDto signUpDto) {
		Account newAccount = new Account();
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		newAccount.setEmailAddress(signUpDto.email());
		newAccount.setUsername(signUpDto.username());
		newAccount.setPassword(bc.encode(signUpDto.password()));
		newAccount.setMoney(0);
		Account result = accountRepository.save(newAccount);
		Authority newAccountAuthority = new Authority();
		newAccountAuthority.setAuthority("USER");
		authorityRepository.save(newAccountAuthority);
		List<Authority> listAutho = new ArrayList<Authority>();
		listAutho.add(newAccountAuthority);
		result.setAuthorities(listAutho);
		return accountRepository.save(result);
	}
}
