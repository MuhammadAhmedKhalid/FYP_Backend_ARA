package com.springboot.fyp.faculty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.faculty.service.FacultyConstraints_service;

@RestController
public class FacultyConstraints_controller {
	
	@Autowired
	FacultyConstraints_service facultyConstraints_service;

}
