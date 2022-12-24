package com.springboot.fyp.admin.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.admin.models.Department;

@Repository
public interface Department_repository extends MongoRepository<Department, Integer> {

}
