package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Leave_repository;
import com.springboot.fyp.root.models.Leave;

@Service
public class Leave_service {

	@Autowired
	Leave_repository leave_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_LEAVE_REQUESTS = "LeaveRequests";
	
	public String add(Leave leave) {
		leave.setLeaveId(sequenceGeneratorService.getSequenceNumber(leave.SEQUENCE_NAME));
		leave_repository.insert(leave);
		redisUtilityRoot.deleteList(HASH_KEY_LEAVE_REQUESTS+leave.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<Leave> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_LEAVE_REQUESTS+institute_id).size()>0) {
			return redisUtilityRoot.getList(HASH_KEY_LEAVE_REQUESTS+institute_id);
		}else {
			
			List<Leave> leaveRequests = new ArrayList<>();
			for(Leave requests : leave_repository.findAll()) {
				if(requests.getInstitute_id() == institute_id) {
					leaveRequests.add(requests);
				}
			}
			redisUtilityRoot.saveList(leaveRequests, HASH_KEY_LEAVE_REQUESTS+institute_id);
			return leaveRequests;
		}
	}
	
	public String delete(int leaveId) {
		List<Leave> leaveRequests = leave_repository.findAll();
		int institute_id = 0;
		for(Leave requests : leaveRequests) {
			if(requests.getLeaveId() == leaveId) {
				institute_id = requests.getInstitute_id();
				leave_repository.deleteById(leaveId);
				redisUtilityRoot.deleteList(HASH_KEY_LEAVE_REQUESTS+institute_id);
				redisUtilityRoot.saveList(leave_repository.findAll(), HASH_KEY_LEAVE_REQUESTS+institute_id);
				return "Leave Request deleted.";
			}
		}
		return null;
	}
	
}
