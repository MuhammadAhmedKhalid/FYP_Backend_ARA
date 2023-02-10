package com.springboot.fyp.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.service.Specialization_service;

@RestController
public class Specialization_controller {
	
	@Autowired
	Specialization_service specialization_service;
	
}
