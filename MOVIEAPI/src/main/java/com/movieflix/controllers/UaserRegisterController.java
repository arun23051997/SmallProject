package com.movieflix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieflix.auth.entity.Users;
import com.movieflix.repositories.UserRepo;

//@Controller
@RestController
@RequestMapping("/register")
public class UaserRegisterController {

	@Autowired
	private UserRepo userRepo;
	
	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @PostMapping("/user")
	    public Users createUser(@RequestBody Users user) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        return userRepo.save(user);
	    }
}
