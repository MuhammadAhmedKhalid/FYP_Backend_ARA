package com.springboot.fyp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.User_repository;
import com.springboot.fyp.root.models.JWT_Response;
import com.springboot.fyp.root.models.User;
import com.springboot.fyp.root.security.AES;
import com.springboot.fyp.root.service.JWT_Utils;
import com.springboot.fyp.root.service.MongoAuthUserDetailService;
import com.springboot.fyp.root.service.SequenceGeneratorService;

@Service
public class Admin_service {

final String secretKey = "3t6w9y$B&E)H@McQ";
	
	@Autowired
	User_repository user_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	JWT_Utils jwt_Utils;
	
	@Autowired
	MongoAuthUserDetailService userDetailService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
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
	
	public String getToken(String email)
	{
        UserDetails userDetails = userDetailService.loadUserByUsername(email);	
        return jwt_Utils.generateToken(userDetails);
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(String email, String password){
		User checkUser = user_repository.findByEmail(email);
		String encryptedPassword = AES.encrypt(password, secretKey);
		if(checkUser != null && checkUser.getPassword().equals(encryptedPassword)) {
			 final String jwt = getToken(email);
			 JWT_Response jwt_Response = new JWT_Response(checkUser.getUser_id(), email, jwt, checkUser.getName());
			return ResponseEntity.ok(jwt_Response);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials.");
	}
}
