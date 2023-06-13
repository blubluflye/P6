package com.p6.payMyBuddy.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.p6.payMyBuddy.model.Authority;
import com.p6.payMyBuddy.repository.AuthorityRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AuthorityRepositoryTest {

	@Autowired
	private AuthorityRepository repository;
	
	@Test
	public void testFindAllAuthority() {
		List<Authority> list = repository.findAll();
		assertThat(list.size()).isGreaterThan(0);
	}
	
}
