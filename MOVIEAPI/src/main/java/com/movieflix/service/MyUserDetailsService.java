package com.movieflix.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.movieflix.auth.entity.Users;
import com.movieflix.repositories.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 System.out.println("entry check " + userRepo.findByUsername(username));
	      Optional <Users> user = userRepo.findByUsername(username);
	        System.out.println("check completed" + user.get().getUsername());
	        if (user.isPresent()) {
	        	System.out.println("inside contiition");
	            var userObj = user.get();
	            System.out.println("userobj username " + userObj.getUsername());
	            System.out.println("userobj userpass " + userObj.getPassword());
	            System.out.println("userobj userrole " +getRoles(userObj));


	            return User.builder()
	                    .username(userObj.getUsername())
	                    .password(userObj.getPassword())
	                    .roles(getRoles(userObj))
	                    .build();

	        } else {
	            throw new UsernameNotFoundException(username);
	        }
	        
	        
	  
	 }

			private String[] getRoles(Users user) {
				System.out.println("inside private "+ user.getRole());
	            if (user.getRole() == null) {
	            	System.out.println("inside private contio "+ user.getRole());
	                return new String[]{"USER"};
	            }
	            System.out.println("inside private contio else part "+user.getRole().split(","));
	            return user.getRole().split(",");
	        }
}
