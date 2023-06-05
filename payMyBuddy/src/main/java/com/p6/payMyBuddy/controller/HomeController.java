package com.p6.payMyBuddy.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.p6.payMyBuddy.dto.SignUpDto;
import com.p6.payMyBuddy.model.Account;
import com.p6.payMyBuddy.model.Contact;
import com.p6.payMyBuddy.repository.AccountRepository;
import com.p6.payMyBuddy.service.AccountService;
import com.p6.payMyBuddy.service.ContactService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/signin")
	public String user(Authentication authentication) {
		return "profile";
	}
	
	
	@GetMapping("/signUp")
	public String signUp(){
		return "signUp";
	}
	
	@PostMapping("/createUser")
	public String createUser(@ModelAttribute SignUpDto account, HttpSession session) {
		boolean f = accountService.checkEmail(account.email());
		if (f) {
			session.setAttribute("msg", "Email Id alreday exists");
		}

		else {
			Account resultSignUp = accountService.signUp(account);
			if (resultSignUp != null) {
				session.setAttribute("msg", "Register Sucessfully");
			} else {
				session.setAttribute("msg", "Something wrong on server");
			}
		}
		return "index";
	}
	

}
