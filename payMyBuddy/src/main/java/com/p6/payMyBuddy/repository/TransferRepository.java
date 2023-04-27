package com.p6.payMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p6.payMyBuddy.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Integer>{

}
