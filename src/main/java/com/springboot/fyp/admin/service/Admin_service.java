package com.springboot.fyp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.User_repository;
import com.springboot.fyp.root.models.User;
import com.springboot.fyp.root.security.AES;
import com.springboot.fyp.root.service.SequenceGeneratorService;

@Service
public class Admin_service {

final String secretKey = "3t6w9y$B&E)H@McQ";
	
	@Autowired
	User_repository user_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	public ResponseEntity<String> create(User user){
		User checkUser = user_repository.findByEmail(user.getEmail());
		if(checkUser != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists with this email.");
		}
		user.setUser_id(sequenceGeneratorService.getSequenceNumber(user.SEQUENCE_NAME));
		String encryptedPassword = AES.encrypt(user.getPassword(), secretKey);
		user.setPassword(encryptedPassword);
		user_repository.insert(user);
		return ResponseEntity.ok("Operation performed successfully.");
	}
	
	public ResponseEntity<String> get(String email, String password){
		User checkUser = user_repository.findByEmail(email);
		String encryptedPassword = AES.encrypt(password, secretKey);
		if(checkUser.getPassword().equals(encryptedPassword)) {
			return ResponseEntity.ok("Operation performed successfully.");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials.");
	}
	
}
