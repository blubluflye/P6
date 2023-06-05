package com.p6.payMyBuddy.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p6.payMyBuddy.dto.EmailDto;
import com.p6.payMyBuddy.dto.MoneyDto;
import com.p6.payMyBuddy.dto.StringDto;
import com.p6.payMyBuddy.dto.TransferDto;
import com.p6.payMyBuddy.model.Account;
import com.p6.payMyBuddy.model.Contact;
import com.p6.payMyBuddy.model.Transfer;
import com.p6.payMyBuddy.repository.AccountRepository;
import com.p6.payMyBuddy.service.AccountService;
import com.p6.payMyBuddy.service.ContactService;
import com.p6.payMyBuddy.service.TransferService;

import jakarta.servlet.http.HttpSession;


@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private TransferService transferService;
	@Autowired
	private AccountRepository accountRepository;
	
	@ModelAttribute
	private void userDetails(Model m, Principal p) {
		String username = p.getName();
		Optional<Account> account = accountRepository.findByUsername(username);
		m.addAttribute("user", account.get());
	}
	
	@GetMapping("/contact")
	public String contact(Model m,Principal p){
		String username = p.getName();
		Optional<Account> account = accountRepository.findByUsername(username);
		List<Contact> myContacts = contactService.getAllContactByAccountId(account.get().getAccountId());
		m.addAttribute("myContacts",myContacts);
		return "contact";
	}
	
	@PostMapping("/createContact")
	public String createContact(@ModelAttribute EmailDto email, Principal p, HttpSession session) {
		boolean f = accountService.checkEmail(email.email());
		if (f) {
			Optional<Account> account = accountRepository.findByUsername(p.getName());
			Contact result = null;
			if (account.isPresent())
				result = contactService.createContact(account.get(), email.email());
			if (result != null) {
				session.setAttribute("msg", "Register Sucessfully");
			} else {
				session.setAttribute("msg", "Something wrong on server");
			}
		}
		return "redirect:/contact";
	}
	
	@GetMapping("/transfer")
	public String transfer(Model m,Principal p) {
		String username = p.getName();
		Optional<Account> account = accountRepository.findByUsername(username);
		List<Transfer> myTransfers = transferService.getTranferByAccountId(account.get().getAccountId());
		List<Transfer> transfersToMe = transferService.getTranferToAccountId(account.get().getAccountId());
		m.addAttribute("myTransfers",myTransfers);
		m.addAttribute("TransfersToMe",transfersToMe);
		List<Contact> myContacts = contactService.getAllContactByAccountId(account.get().getAccountId());
		m.addAttribute("myContacts",myContacts);
		return "transfer";
	}
	
	@PostMapping("/createTransfer")
	public String createTransfer(@ModelAttribute TransferDto transferDto, Principal p, HttpSession session) {
		Optional<Account> account = accountRepository.findByUsername(p.getName());
		Transfer result = null;
		if (account.isPresent())
			result = transferService.createTransfer(account.get(), transferDto.username(), transferDto.money(), transferDto.description());
		if (result != null) {
			session.setAttribute("msg", "Register Sucessfully");
		} else {
			session.setAttribute("msg", "Something wrong on server");
		}
		return "redirect:/transfer";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
	
	@PostMapping("/updateUsername")
	public String updateUsername(@ModelAttribute StringDto newUsername, Principal p, HttpSession session) {
			Optional<Account> account = accountRepository.findByUsername(p.getName());
			if (!(account.get().getUsername()).equals(newUsername.string())) {
				boolean result = false;
				if (account.isPresent())
					result = accountService.updateUsername(account.get(), newUsername.string());
				if (result) {
					session.setAttribute("msg", "Username updated Sucessfully");
				} else {
					session.setAttribute("msg", "Something wrong on server");
				}
			}
		return "redirect:/logout";
	}
	
	@PostMapping("/updatePassword")
	public String updatePassword(@ModelAttribute StringDto newPassword, Principal p, HttpSession session) {
			Optional<Account> account = accountRepository.findByUsername(p.getName());
				boolean result = false;
				if (account.isPresent())
					result = accountService.updatePassword(account.get(), newPassword.string());
				if (result) {
					session.setAttribute("msg", "Password updated Sucessfully");
				} else {
					session.setAttribute("msg", "Something wrong on server");
				}
		return "redirect:/logout";
	}
	
	@PostMapping("/updateEmailAddress")
	public String updateEmailAddress(@ModelAttribute EmailDto newEmail, Principal p, HttpSession session) {
			Optional<Account> account = accountRepository.findByUsername(p.getName());
			if (!(account.get().getEmailAddress().equals(newEmail.email()))) {
				boolean result = false;
				if (account.isPresent())
					result = accountService.updateEmailAddress(account.get(), newEmail.email());
				if (result) {
					session.setAttribute("msg", "Email updated Sucessfully");
				} else {
					session.setAttribute("msg", "Something wrong on server");
				}
			}
		return "profile";
	}
	
	
	@PostMapping("/addMoney")
	public String addMoney(@ModelAttribute MoneyDto money, Principal p, HttpSession session) {
			Optional<Account> account = accountRepository.findByUsername(p.getName());
				int result = 0;
				if (account.isPresent())
					result = accountService.addMoney(account.get(), money.money());
				if (result > 0) {
					session.setAttribute("msg", "Money added Sucessfully");
				} else {
					session.setAttribute("msg", "Something wrong on server");
				}
		return "profile";
	}
}
