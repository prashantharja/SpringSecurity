package com.demo.SpringSecurityEx.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.SpringSecurityEx.model.Users;

public interface UserRepo extends JpaRepository<Users, Integer>{
	
	Users findByUsername(String username);

}
 