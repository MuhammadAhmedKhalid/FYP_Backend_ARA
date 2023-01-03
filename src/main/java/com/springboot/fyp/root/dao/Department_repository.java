package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.Department;

@Repository
public interface Department_repository extends MongoRepository<Department, Integer> {

}
