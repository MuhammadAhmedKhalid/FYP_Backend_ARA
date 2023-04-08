package com.springboot.fyp.root.service;

import java.util.ArrayList;
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
	
	@SuppressWarnings("unchecked")
	public List<Notification> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_NOTIFICATION_LIST+institute_id).size()>0) {
			return redisUtilityRoot.getList(HASH_KEY_NOTIFICATION_LIST+institute_id);
		}else {
			
			List<Notification> notifications = new ArrayList<>();
			for(Notification notification : notification_repository.findAll()) {
				if(notification.getInstitute_id() == institute_id) {
					notifications.add(notification);
				}
			}
			redisUtilityRoot.saveList(notifications, HASH_KEY_NOTIFICATION_LIST+institute_id);
			return notifications;
		}
	}
	
}
