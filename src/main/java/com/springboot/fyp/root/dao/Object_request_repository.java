package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.fyp.root.models.Object_Request;

public interface Object_request_repository extends MongoRepository<Object_Request, Integer> {

}
