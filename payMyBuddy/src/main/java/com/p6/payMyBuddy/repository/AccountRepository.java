package com.p6.payMyBuddy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.p6.payMyBuddy.model.Account;


public interface AccountRepository extends JpaRepository<Account, Integer>{
	@EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
			attributePaths = {"authorities"})
	Optional<Account> findByUsername(String username);
	
	public boolean existsByEmail(String email);
	
	@EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
			attributePaths = {"authorities"})
	List<Account> findAll();
	
	@Query("SELECT u FROM Account u WHERE u.username = ?#{ principal.username}")
	Optional<Account> findLoginUser();

	public Account findByEmail(String email);

}
