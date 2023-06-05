package com.p6.payMyBuddy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.p6.payMyBuddy.model.Account;
import com.p6.payMyBuddy.model.Contact;
import com.p6.payMyBuddy.model.Transfer;
import com.p6.payMyBuddy.repository.AccountRepository;
import com.p6.payMyBuddy.repository.TransferRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferService {

	private final TransferRepository transferRepository;
	private final AccountRepository accountRepository;
	private final ContactService contactService;
	
	public List<Transfer> getTranferByAccountId(Integer accountId){
		List<Transfer> result = new ArrayList<Transfer>();
		for (Transfer transfer : transferRepository.findAll()) {
			if (transfer.getSender().getAccountId() == accountId)
				result.add(transfer);
		}
		return result;
	}
	
	public List<Transfer> getTranferToAccountId(Integer accountId){
		List<Transfer> result = new ArrayList<Transfer>();
		for (Transfer transfer : transferRepository.findAll()) {
			if (transfer.getReceiver().getAccountId() == accountId)
				result.add(transfer);
		}
		return result;
	}
	
	public Transfer createTransfer(Account user, String contactName, int money, String description) {
		if (money <= user.getMoney()) {
			Transfer transfer = new Transfer();
			transfer.setAmount(money);
			transfer.setDescription(description);
			Integer accountId = user.getAccountId();
			Contact userContact = contactService.getContactByAccountId(accountId, contactName);
			Account receiver = userContact.getFriendAccount();
			receiver.setMoney(receiver.getMoney() + money);
			accountRepository.save(receiver);
			user.setMoney(user.getMoney() - money);
			user = accountRepository.save(user);
			transfer.setReceiver(receiver);
			transfer.setSender(user);
			return transferRepository.save(transfer);
		}
		else
			return null;
	}
}
