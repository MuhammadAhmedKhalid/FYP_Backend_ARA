package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.fyp.root.models.Staff_Request;

public interface Staff_request_repository extends MongoRepository<Staff_Request, Integer>{

}
