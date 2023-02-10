package com.springboot.fyp.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.service.Position_service;

@RestController
public class Position_controller {

	@Autowired
	Position_service position_service;
	
}
