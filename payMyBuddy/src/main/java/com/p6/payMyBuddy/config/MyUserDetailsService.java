package com.p6.payMyBuddy.config;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.p6.payMyBuddy.model.Account;
import com.p6.payMyBuddy.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

	private final AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Account> userByUsername = accountRepository.findByUsername(username);
		if (!userByUsername.isPresent()) {
			log.error("Could not find user with that username: {}", username);
            throw new UsernameNotFoundException("Invalid credentials!");
		}
		Account account = userByUsername.get();
        if (account == null || !account.getUsername().equals(username)) {
        	log.error("Could not find user with that username: {}", username);
            throw new UsernameNotFoundException("Invalid credentials!");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        account.getAuthorities().stream().forEach(authority -> 
        grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority())));
        
        return new MySecurityUser(account.getUsername(), account.getPassword(), true, true, true, true, grantedAuthorities, 
        		account.getUsername(), account.getEmailAddress());
	}

}
