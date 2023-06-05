package com.p6.payMyBuddy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.p6.payMyBuddy.model.Account;
import com.p6.payMyBuddy.model.Contact;
import com.p6.payMyBuddy.repository.AccountRepository;
import com.p6.payMyBuddy.repository.ContactRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactService {
	
	private final ContactRepository contactRepository;
	private final AccountRepository accountRepository;
	
	public List<Contact> getAllContactByAccountId(Integer accountId){
		List<Contact> result = new ArrayList<Contact>();
		for (Contact contact : contactRepository.findAll()) {
			if (contact.getAccount().getAccountId() == accountId)
				result.add(contact);
		}
		return result;
	}
	
	public Contact getContactByAccountId(Integer accountId, String contactUsername){
		for (Contact contact : getAllContactByAccountId(accountId)) {
			if (contact.getFriendAccount().getUsername().equals(contactUsername))
				return contact;
		}
		return null;
	}
	
	public Contact createContact(Account user, String newContactEmail) {
		if (user.getEmailAddress().equals(newContactEmail))
			return null;
		List<Contact> contacts = this.getAllContactByAccountId(user.getAccountId());
		for (Contact contact : contacts) {
			if (contact.getFriendAccount().getEmailAddress().equals(newContactEmail))
				return null;
		}
		if (accountRepository.existsByEmailAddress(newContactEmail)) {
			Contact newContact = new Contact();
			newContact.setAccount(user);
			newContact.setFriendAccount(accountRepository.findByEmailAddress(newContactEmail));
			return contactRepository.save(newContact);
		}
		return null;
	}

	public boolean deleteContact(Account user, String contactEmailToDelete) {
		for (Contact contact : contactRepository.findAll()) {
			if (contact.getFriendAccount().getEmailAddress() == contactEmailToDelete) {
				contactRepository.delete(contact);
				return true;
			}
		}
		return false;
	}
}
