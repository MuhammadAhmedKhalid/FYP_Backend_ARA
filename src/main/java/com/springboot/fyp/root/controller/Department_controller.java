package com.springboot.fyp.root.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Department;
import com.springboot.fyp.root.service.Department_service;

@RestController
public class Department_controller {
	
	@Autowired
	Department_service department_service;
	
	@PostMapping("/add_department")
	public ResponseEntity<String> addDepartment() {
		
		List<Department> departmentList = new ArrayList<>();
		departmentList.add(new Department(1, "Software Engineering", 1));
		departmentList.add(new Department(2, "Chemical Engineering", 1));
		departmentList.add(new Department(3, "Mechanical Engineering", 1));
		departmentList.add(new Department(4, "Petroleum Engineering", 1));
		departmentList.add(new Department(5, "Electrical Engineering", 1));
		
		return ResponseEntity.ok(department_service.insert(departmentList));
		
	}
	
	@GetMapping("/departments")
	public ResponseEntity<List<Department>> getDepartments(){
		return ResponseEntity.ok(department_service.getAll());
	}
	
}
