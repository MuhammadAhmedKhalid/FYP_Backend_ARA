package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.Notification;

@Repository
public interface Notification_repository extends MongoRepository<Notification, Integer> {

}
