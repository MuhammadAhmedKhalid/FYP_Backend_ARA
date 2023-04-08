package com.springboot.fyp.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Notification_repository;
import com.springboot.fyp.root.models.Notification;

@Service
public class Notification_service {

	@Autowired
	Notification_repository notification_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_NOTIFICATION_LIST = "NotificationList";
	
	public String insert(List<Notification> notifications) {
		for(Notification notification : notifications) {
			notification.setNotificationId(sequenceGeneratorService.getSequenceNumber(notification.SEQUENCE_NAME));
			notification_repository.insert(notification);
			redisUtilityRoot.deleteList(HASH_KEY_NOTIFICATION_LIST+notification.getInstitute_id());
		}
		return "Operation performed successfully.";
	}
	
}
