package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Department;
import com.springboot.fyp.root.service.Department_service;

@RestController
public class Department_controller {
	
	@Autowired
	Department_service department_service;
	
	@PostMapping("/add_department")
	public ResponseEntity<String> addDepartment(@RequestBody Department department) {
		String response = department_service.insert(department);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/departments")
	public ResponseEntity<List<Department>> getDepartments(){
		return ResponseEntity.ok(department_service.getAll());
	}
	
}
