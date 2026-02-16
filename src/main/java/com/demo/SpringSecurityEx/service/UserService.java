package com.demo.SpringSecurityEx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.SpringSecurityEx.model.Users;
import com.demo.SpringSecurityEx.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); 
	
	public Users register(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

}
