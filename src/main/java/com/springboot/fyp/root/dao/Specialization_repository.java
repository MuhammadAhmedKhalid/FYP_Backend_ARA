package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.Specialization;

@Repository
public interface Specialization_repository extends MongoRepository<Specialization, Integer>{}