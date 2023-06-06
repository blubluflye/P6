package com.p6.payMyBuddy.serviceTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.p6.payMyBuddy.dto.SignUpDto;
import com.p6.payMyBuddy.mapper.AccountMapper;
import com.p6.payMyBuddy.model.Account;
import com.p6.payMyBuddy.repository.AccountRepository;
import com.p6.payMyBuddy.repository.AuthorityRepository;
import com.p6.payMyBuddy.service.AccountService;


@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	 @InjectMocks
	 private AccountService accountService;
	 @Mock
	 private static AccountRepository accountRepository;
	
	@Test 
	public void checkEmailTest() {
		String emailAddress = "test";
		given(accountRepository.existsByEmailAddress(emailAddress)).willReturn(true);
		boolean result = accountService.checkEmail(emailAddress);
		assertThat(result).isTrue();
	}
	
	
}
