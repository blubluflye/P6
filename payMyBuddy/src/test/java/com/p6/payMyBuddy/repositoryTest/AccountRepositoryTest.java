package com.p6.payMyBuddy.repositoryTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import com.p6.payMyBuddy.model.Account;
import com.p6.payMyBuddy.repository.AccountRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AccountRepositoryTest {

	@Autowired
	private AccountRepository repository;
	
	@Test
	public void testSaveAccount() {
		if (repository.existsByEmailAddress("testEmail")) {
			Account account = repository.findByEmailAddress("testEmail");
			String username = account.getUsername() + "1";
			account.setUsername(username);
			Account testAccount = repository.save(account);
			assertThat(testAccount.getUsername()).isEqualTo(username);
		}
		else {
			Account account = new Account();
			account.setEmailAddress("testEmail");
			account.setUsername("testUsername");
			account.setMoney(0);
			account.setPassword("test");
			Account testAccount = repository.save(account);
			assertThat(testAccount.getUsername()).isEqualTo("testUsername");
		}
	}

}
