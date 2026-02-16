package com.demo.SpringSecurityEx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers( "/register", "/login").permitAll()
						.anyRequest().authenticated())
						.httpBasic(Customizer.withDefaults())
						.build();
		//http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	}
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
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