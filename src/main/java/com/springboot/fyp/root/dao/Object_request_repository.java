package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.Object_Request;

@Repository
public interface Object_request_repository extends MongoRepository<Object_Request, Integer> {

}
