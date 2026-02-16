package com.demo.SpringSecurityEx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.SpringSecurityEx.model.Users;
import com.demo.SpringSecurityEx.repo.UserRepo;
import com.demo.SpringSecurityEx.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
    private UserRepo repo;

	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
		
		return userService.register(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		
		
		System.out.println("--- DEBUGGING START ---");
        System.out.println("1. Login requested for: '" + user.getUsername() + "'");
        
        // Check what is actually in the DB
        List<Users> allUsers = repo.findAll();
        System.out.println("2. Total users in DB: " + allUsers.size());
        
        for (Users u : allUsers) {
            System.out.println("   -> Found DB User: '" + u.getUsername() + "' (ID: " + u.getId() + ")");
            
            // Check for hidden spaces
            if (u.getUsername().equals(user.getUsername())) {
                System.out.println("   -> MATCH FOUND! The strings are identical.");
            } else {
                System.out.println("   -> NO MATCH. DB: '" + u.getUsername() + "' vs Input: '" + user.getUsername() + "'");
            }
        }
        System.out.println("--- DEBUGGING END ---");
		
		
		return userService.verify(user);
	}

}
