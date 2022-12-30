package com.springboot.fyp.faculty.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.faculty.models.Faculty;
import com.springboot.fyp.root.models.User;

@Repository
public interface Faculty_repositiory extends MongoRepository<Faculty, User> {
	Faculty findByOfficialEmailAddress(String officialEmailAddress);
}
