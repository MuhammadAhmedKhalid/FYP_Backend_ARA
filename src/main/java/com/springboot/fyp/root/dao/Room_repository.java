package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.Room;

@Repository
public interface Room_repository extends MongoRepository<Room, Integer> {

}
