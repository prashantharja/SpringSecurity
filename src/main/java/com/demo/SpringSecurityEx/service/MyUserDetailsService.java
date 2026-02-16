package com.demo.SpringSecurityEx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.SpringSecurityEx.model.UserPrincipal;
import com.demo.SpringSecurityEx.model.Users;
import com.demo.SpringSecurityEx.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = userRepo.findByUsername(username);
		
		if(user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("User not found");
		}
		return new UserPrincipal(user);
	}
	
	

}
