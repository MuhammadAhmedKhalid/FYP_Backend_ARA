package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.User;

@Repository
public interface User_repository extends MongoRepository<User, Integer> {
		User findByEmail(String email);
}
