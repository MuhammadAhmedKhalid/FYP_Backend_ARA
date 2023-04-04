package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.fyp.root.dao.Leave_repository;
import com.springboot.fyp.root.dao.Staff_request_repository;
import com.springboot.fyp.root.models.Leave;
import com.springboot.fyp.root.models.LeaveRequest;
import com.springboot.fyp.root.models.Staff_Request;
import com.springboot.fyp.root.models.Teacher;
import com.springboot.fyp.root.models.TeacherResult;

@Service
public class Leave_service {

	@Autowired
	Leave_repository leave_repository;
	
	@Autowired
	Staff_request_repository staff_request_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_LEAVE_REQUESTS = "LeaveRequests";
	
	public static final String HASH_KEY_STAFF_REQUESTS = "StaffRequests";
	
	public String add(LeaveRequest leaveRequest) {
		
		Leave leave = new Leave();
		leave.setLeaveId(sequenceGeneratorService.getSequenceNumber(leave.SEQUENCE_NAME));
		leave.setDate(leaveRequest.getDate());
		leave.setEndTime(leaveRequest.getEndTime());
		leave.setFaculty_id(leaveRequest.getFaculty_id());
		leave.setInstitute_id(leaveRequest.getInstitute_id());
		leave.setReason(leaveRequest.getReason());
		leave.setStartTime(leaveRequest.getStartTime());
		leave_repository.insert(leave);
		redisUtilityRoot.deleteList(HASH_KEY_LEAVE_REQUESTS+leave.getInstitute_id());
		
		Staff_Request staff_Request = new Staff_Request();
		staff_Request.setStaff_req_id(sequenceGeneratorService.getSequenceNumber(staff_Request.SEQUENCE_NAME));
		staff_Request.setLeaveId(leave.getLeaveId());
		staff_Request.setDate(leaveRequest.getDate());
		staff_Request.setDepartment_id(leaveRequest.getDepartment_id());
		staff_Request.setEndTime(leaveRequest.getEndTime());
		staff_Request.setInstitute_id(leaveRequest.getInstitute_id());
		staff_Request.setRequested_faculty_id(leaveRequest.getFaculty_id());
		staff_Request.setRoom_id(0);
		staff_Request.setStartTime(leaveRequest.getStartTime());
		staff_Request.setUser_id(leaveRequest.getUser_id());
		staff_request_repository.insert(staff_Request);
		redisUtilityRoot.deleteList(HASH_KEY_STAFF_REQUESTS+staff_Request.getInstitute_id());
		
		return "Operation performed successfully.";
	}

	public static double jaccardIndex(Teacher teacher1, Teacher teacher2) {
	    Set<Integer> set1 = new HashSet<>();
	    Set<Integer> set2 = new HashSet<>();

	    for (int i = 1; i <= teacher1.getYearsOfExperience(); i++) {
	        set1.add(i);
	    }

	    for (int i = 1; i <= teacher2.getYearsOfExperience(); i++) {
	        set2.add(i);
	    }

	    Set<Integer> intersection = new HashSet<>(set1);
	    intersection.retainAll(set2);
	    
	    Set<Integer> union = new HashSet<>(set1);
	    union.addAll(set2);

	    double jaccardIndex = (double) intersection.size() / union.size();
	    return jaccardIndex;
	}
	
	public List<TeacherResult> getBestTeacher(@RequestBody List<Teacher> teachers) {
        List<TeacherResult> teacherResults = new ArrayList<>();

        for (Teacher teacher : teachers) {
            double totalJaccardSimilarity = 0;
            for (Teacher otherTeacher : teachers) {
                if (!teacher.equals(otherTeacher)) {
                    totalJaccardSimilarity += jaccardIndex(teacher, otherTeacher);
                }
            }
            teacherResults.add(new TeacherResult(teacher.getFaculty_id(), totalJaccardSimilarity));
        }

        return teacherResults;
    }
	
//	public Teacher findBestTeacher(List<Teacher> teachers) {
//	    Teacher bestTeacher = null;
//	    double bestIndex = 0.0;
//
//	    for (int i = 0; i < teachers.size(); i++) {
//	        for (int j = i + 1; j < teachers.size(); j++) {
//	            double index = jaccardIndex(teachers.get(i), teachers.get(j));
//
//	            if (index > bestIndex) {
//	                bestIndex = index;
//	                bestTeacher = index > bestIndex ? teachers.get(i) : teachers.get(j);
//	            }
//	        }
//	    }
//
//	    return bestTeacher;
//	}
	
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
			}
		}
		List<Staff_Request> staffRequests = staff_request_repository.findAll();
		for(Staff_Request requests : staffRequests) {
			if(requests.getLeaveId() == leaveId) {
				staff_request_repository.deleteById(requests.getStaff_req_id());
				redisUtilityRoot.deleteList(HASH_KEY_STAFF_REQUESTS+institute_id);
				redisUtilityRoot.saveList(staff_request_repository.findAll(), HASH_KEY_STAFF_REQUESTS+institute_id);
			}
		}
		return null;
	}
	
}
