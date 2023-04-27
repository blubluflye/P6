package com.p6.payMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p6.payMyBuddy.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
