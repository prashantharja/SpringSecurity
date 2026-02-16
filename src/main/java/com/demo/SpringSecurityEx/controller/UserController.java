package com.demo.SpringSecurityEx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.SpringSecurityEx.model.Users;
import com.demo.SpringSecurityEx.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
		
		return userService.register(user);
	}

}
