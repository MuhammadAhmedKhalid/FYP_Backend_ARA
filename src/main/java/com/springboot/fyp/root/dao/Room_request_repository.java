package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.fyp.root.models.Room_Request;

public interface Room_request_repository extends MongoRepository<Room_Request, Integer> {
	
}
