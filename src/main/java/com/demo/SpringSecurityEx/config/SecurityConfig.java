package com.demo.SpringSecurityEx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults())
				.build();
		//http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	}
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
	}

	/*@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user1 = User
				.withDefaultPasswordEncoder()
				.username("John")
				.password("j@123")
				.roles("USER")
				.build();
				
		
		UserDetails user2 = User
				.withDefaultPasswordEncoder()
				.username("Luke")
				.password("l@123")
				.roles("USER")
				.build();
				
	
		return new InMemoryUserDetailsManager(user1, user2);
	}*/
}