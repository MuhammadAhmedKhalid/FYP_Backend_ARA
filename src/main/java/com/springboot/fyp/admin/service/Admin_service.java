package com.springboot.fyp.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.springboot.fyp.faculty.dao.Faculty_repositiory;
import com.springboot.fyp.faculty.models.Faculty;
import com.springboot.fyp.root.dao.Institute_repository;
import com.springboot.fyp.root.dao.User_repository;
import com.springboot.fyp.root.models.Institute;
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
	Institute_repository institute_repository;
	
	@Autowired
	Faculty_repositiory faculty_repositiory;
	
	public String create(User user, boolean isFaculty){
		User checkUser = user_repository.findByEmail(user.getEmail());
		if(checkUser != null) {
			return null;
		}
		user.setUser_id(sequenceGeneratorService.getSequenceNumber(user.SEQUENCE_NAME));
		String encryptedPassword = AES.encrypt(user.getPassword(), secretKey);
		user.setPassword(encryptedPassword);
		if(!isFaculty) {
			user.set_admin(true);
		}
		user_repository.insert(user);
		return "Operation performed successfully.";

	}
	
	public String getToken(String email)
	{
        UserDetails userDetails = userDetailService.loadUserByUsername(email);	
        return jwt_Utils.generateToken(userDetails);
	}
	
	public JWT_Response signin(String email, String password){
		User checkUser = user_repository.findByEmail(email);
		String encryptedPassword = AES.encrypt(password, secretKey);
		if(checkUser != null && checkUser.getPassword().equals(encryptedPassword)) {
			final String jwt = getToken(email);
			String institute_name = "";
			int institute_id = 0;
			int faculty_id = 0;
			String springStartMonth = "";
			String springEndMonth = "";
			String fallStartMonth = "";
			String fallEndMonth = "";
			 
			 List<Institute> institutesList = institute_repository.findAll();
			 for (int i=0; i < institutesList.size(); i++) {
				 if(institutesList.get(i).getUser_id() == checkUser.getUser_id()) {
					 institute_name = institutesList.get(i).getInstitute_name();
					 institute_id = institutesList.get(i).getInstitute_id();
					 springStartMonth = institutesList.get(i).getSpringStartMonth();
					 springEndMonth = institutesList.get(i).getSpringEndMonth();
					 fallStartMonth = institutesList.get(i).getFallStartMonth();
					 fallEndMonth = institutesList.get(i).getFallEndMonth();
				 }
				 else if (!checkUser.is_admin()) {
					 List<Faculty> faculty_lst = faculty_repositiory.findAll();
					 for (Faculty checkFaculty : faculty_lst) {
						 if(checkUser.getUser_id() == checkFaculty.getUser().getUser_id()) {
							 institute_id = checkFaculty.getInstitute_id();
							 faculty_id = checkFaculty.getFaculty_id();
							 institute_name = institutesList.get(i).getInstitute_name();
						 }
					 }
				 }
			 }
			 
			 JWT_Response jwt_Response = new JWT_Response(checkUser.getUser_id(), email, jwt, checkUser.getName(), 
					 institute_name, institute_id, faculty_id, checkUser.is_admin(),
					 springStartMonth, springEndMonth, fallStartMonth, fallEndMonth);
			return jwt_Response;
		}
		return null;
	}
}
