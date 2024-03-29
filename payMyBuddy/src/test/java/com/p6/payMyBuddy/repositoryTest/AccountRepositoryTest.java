package com.p6.payMyBuddy.repositoryTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import com.p6.payMyBuddy.model.Account;
import com.p6.payMyBuddy.model.Authority;
import com.p6.payMyBuddy.repository.AccountRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AccountRepositoryTest {

	@Autowired
	private AccountRepository repository;
	
	@Test
	public void testSaveAccount() {
			Account account = new Account();
			account.setEmailAddress("testEmail");
			account.setUsername("testUsername");
			account.setMoney(0);
			account.setPassword("test");
			Account testAccount = repository.save(account);
			assertThat(testAccount.getUsername()).isEqualTo("testUsername");
	}
	
	@Test
	public void testFindAllAccount() {
		List<Account> list = repository.findAll();
		assertThat(list.size()).isGreaterThanOrEqualTo(0);
	}

	@Test
	public void testfindByEmailAddress() {
		assertThat(repository.findByEmailAddress("testEmail")).isNotNull();
	}
	
	public void testfindByUsername() {
		assertThat(repository.findByUsername(repository.findByEmailAddress("testEmail").getUsername())).isNotNull();
	}
}
