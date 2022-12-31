package com.springboot.fyp.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.admin.models.Department;
import com.springboot.fyp.admin.service.Department_service;

@RestController
public class Department_controller {
	
	@Autowired
	Department_service department_service;
	
	@PostMapping("/add_department")
	public ResponseEntity<String> addDepartment(@RequestBody Department department) {
		return ResponseEntity.ok(department_service.insert(department));
		
	}
	
	@GetMapping("/departments")
	public ResponseEntity<List<Department>> getDepartments(){
		return ResponseEntity.ok(department_service.getAll());
	}
	
}
