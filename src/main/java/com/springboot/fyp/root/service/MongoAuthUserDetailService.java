package com.springboot.fyp.root.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.User_repository;

@Service
public class MongoAuthUserDetailService implements UserDetailsService {

	@Autowired
	User_repository user_repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		com.springboot.fyp.root.models.User get_user = user_repository.findByEmail(email);
		if(get_user!=null && get_user.getEmail().equals(email)) {
			return new User(get_user.getEmail(), get_user.getPassword(), new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("No user exists with this email address.");
		}
	}
}
