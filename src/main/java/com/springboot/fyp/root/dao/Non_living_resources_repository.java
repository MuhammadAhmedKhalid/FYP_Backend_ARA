package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.Non_Living_Resources;

@Repository
public interface Non_living_resources_repository extends MongoRepository<Non_Living_Resources, Integer> {

}
