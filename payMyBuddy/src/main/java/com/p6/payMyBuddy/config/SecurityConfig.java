package com.p6.payMyBuddy.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.p6.payMyBuddy.repository.AccountRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
		requestHandler.setCsrfRequestAttributeName("_csrf");
		http
			.authorizeHttpRequests(authConfig -> {
				authConfig.requestMatchers(HttpMethod.GET, "/").permitAll();
				authConfig.requestMatchers(HttpMethod.GET, "/signUp").permitAll();	
				authConfig.requestMatchers(HttpMethod.GET, "/signin").hasAuthority("USER");
				authConfig.requestMatchers(HttpMethod.GET, "/contact").hasAuthority("USER");
				authConfig.requestMatchers(HttpMethod.GET, "/transfer").hasAuthority("USER");
				authConfig.requestMatchers(HttpMethod.GET, "/profile").hasAuthority("USER");
				authConfig.anyRequest().permitAll();
			})
			.formLogin(login -> {
				login.defaultSuccessUrl("/profile",true);
				login.failureUrl("/login-error");
				}
			)
			.logout(logout -> {
				logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
				logout.logoutSuccessUrl("/");
				logout.deleteCookies("JSESSIONID");
				logout.invalidateHttpSession(true);
			})
			.csrf().disable();
		return http.build();
	}
	
	@Bean
	UserDetailsService myUserDetailsService(AccountRepository accountRepository) {
		return new MyUserDetailsService(accountRepository);
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	ApplicationListener<AuthenticationSuccessEvent> successEvent() {
		return event -> {
			System.out.println("Success Login " + event.getAuthentication().getClass().getSimpleName() + " - " + event.getAuthentication().getName());
		};
	}
	
	@Bean
	ApplicationListener<AuthenticationFailureBadCredentialsEvent> failureEvent() {
		return event -> {
			System.err.println("Bad Credentials Login " + event.getAuthentication().getClass().getSimpleName() + " - " + event.getAuthentication().getName());
		};
	}


}
