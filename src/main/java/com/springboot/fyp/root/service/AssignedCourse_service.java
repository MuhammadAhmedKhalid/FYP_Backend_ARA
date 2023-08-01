package com.springboot.fyp.root.service;

import java.text.SimpleDateFormat;
//import java.time.DayOfWeek;
//import java.time.LocalDate;
import java.time.Month;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.AssignedCourse_repository;
import com.springboot.fyp.root.dao.AssignedCoursesForTable_repository;
import com.springboot.fyp.root.dao.Institute_repository;
import com.springboot.fyp.root.dao.OfferCourse_repository;
import com.springboot.fyp.root.dao.Room_request_repository;
import com.springboot.fyp.root.dao.Staff_request_repository;
import com.springboot.fyp.root.models.AssignedCourse;
//import com.springboot.fyp.root.models.AssignedCoursesForTable;
//import com.springboot.fyp.root.models.Institute;
import com.springboot.fyp.root.models.MakeResBusy;
import com.springboot.fyp.root.models.OfferCourse;
import com.springboot.fyp.root.models.Room_Request;
import com.springboot.fyp.root.models.Staff_Request;
import com.springboot.fyp.root.models.UpdateAssignedCourse;

@Service
public class AssignedCourse_service {
	
	@Autowired
	AssignedCourse_repository assignCourse_repository;
	
	@Autowired
	Staff_request_repository staff_request_repository;
	
	@Autowired
	Room_request_repository room_request_repository;
	
	@Autowired
	OfferCourse_repository offerCourse_repository;

	@Autowired
	Room_request_service room_request_service;
	
	@Autowired
	Staff_request_service staff_request_service;
	
	@Autowired
	AssignedCoursesForTable_repository assignedCoursesForTable_repository;
	
	@Autowired
	Institute_repository institute_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_ASSIGNED_COURSE_LIST = "AssignedCourseList";
//	public static final String HASH_KEY_ASSIGNED_COURSES_FOR_TABALE_LIST = "AssignedCoursesForTableList";
	public static final String HASH_KEY_ROOM_REQUESTS = "RoomRequests";
	public static final String HASH_KEY_STAFF_REQUESTS = "StaffRequests";
	public static final String HASH_KEY_OFFERED_COURSES_LIST = "OfferedCourses";
	
//	public static List<LocalDate> fetchDatesForDayOfMonth(int year, int startMonth, int endMonth, DayOfWeek dayOfWeek) {
//        LocalDate start = LocalDate.of(year, startMonth, 1);
//        LocalDate end = LocalDate.of(year, endMonth, 1).plusMonths(1).withDayOfMonth(1).minusDays(1);
//        List<LocalDate> dates = new ArrayList<>();
//        while (start.isBefore(end) || start.equals(end)) {
//            if (start.getDayOfWeek() == dayOfWeek) {
//                dates.add(start);
//            }
//            start = start.plusDays(1);
//        }
//        return dates;
//    }
	
	public static int fetchMonthNumber(String monthName) {
        return Month.valueOf(monthName.toUpperCase()).getValue();
    }
	
	public String insert(MakeResBusy makeResBusy){
		
		AssignedCourse assignedCourse = makeResBusy.getAssignedCourse();
		
//		String startingMonth = "";
//		String endingMonth = "";
//		String day = assignedCourse.getDay();
//		
//		for(Institute institute : institute_repository.findAll()) {
//			if(institute.getInstitute_id() == assignedCourse.getInstitute_id()) {
//				if(assignedCourse.getSemesterType().equals("Spring")) {
//					startingMonth = institute.getSpringStartMonth();
//					endingMonth = institute.getSpringEndMonth();
//				}else {
//					startingMonth = institute.getFallStartMonth();
//					endingMonth = institute.getFallEndMonth();
//				}
//			}
//		}
		
//		AssignedCoursesForTable assignedCoursesForTable = new AssignedCoursesForTable();
//		int assignedCoursesId = sequenceGeneratorService.getSequenceNumber(assignedCoursesForTable.SEQUENCE_NAME);
//		assignedCoursesForTable.setAssignedCoursesId(assignedCoursesId);
//		assignedCoursesForTable.setCourseId(assignedCourse.getCourse_id());
//		assignedCoursesForTable.setDepartmentId(assignedCourse.getDepartment_id());
//		assignedCoursesForTable.setFacultyId(assignedCourse.getFaculty_id());
//		assignedCoursesForTable.setBatchId(assignedCourse.getBatchId());
//		assignedCoursesForTable.setSemesterType(assignedCourse.getSemesterType());
//		assignedCoursesForTable.setInstituteId(assignedCourse.getInstitute_id());
//		assignedCoursesForTable_repository.insert(assignedCoursesForTable);
		
//		List<LocalDate> dates = fetchDatesForDayOfMonth(LocalDate.now().getYear(), 
//				fetchMonthNumber(startingMonth), fetchMonthNumber(endingMonth), DayOfWeek.valueOf(day.toUpperCase()));
		
//		for (LocalDate date : dates) {
//			assignedCourse.setAssignedCourseId(sequenceGeneratorService.getSequenceNumber(assignedCourse.SEQUENCE_NAME));
//			
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
////			assignedCourse.setAssignedCoursesId(assignedCoursesId);
//			assignedCourse.setDate(date.format(formatter));
//			assignCourse_repository.insert(assignedCourse);
//		}
		
		
		assignedCourse.setAssignedCourseId(sequenceGeneratorService.getSequenceNumber(assignedCourse.SEQUENCE_NAME));
		assignCourse_repository.insert(assignedCourse);
		
//		ArrayList<String> dateList = makeResBusy.getDates_lst();
		
//		for(int i=0; i<makeResBusy.getDates_lst().size(); i++) {
//			Room_Request room_Request = makeResBusy.getRoom_Request();
//			Staff_Request staff_Request = makeResBusy.getStaff_Request();
//			room_Request.setDate(dateList.get(i));
////			room_Request.setAssignedCoursesId(assignedCoursesId);
//			staff_Request.setDate(dateList.get(i));
////			staff_Request.setAssignedCoursesId(assignedCoursesId);
//			room_request_service.add(room_Request);
//			staff_request_service.add(staff_Request);
//		}

		Room_Request room_Request = makeResBusy.getRoom_Request();
		Staff_Request staff_Request = makeResBusy.getStaff_Request();
		
//		room_Request.setDate(assignedCourse.getDate());
//		staff_Request.setDate(assignedCourse.getDate());
		
		room_request_service.add(room_Request);
		staff_request_service.add(staff_Request);
		
		for(OfferCourse offerCourse : offerCourse_repository.findAll()) {
			if(offerCourse.getOfferCourseId() == makeResBusy.getOfferCourseId()) {
				offerCourse.setAddedInTimetable(true);
				offerCourse_repository.save(offerCourse);
				break;
			}
		}
		
//		redisUtilityRoot.deleteList(HASH_KEY_ASSIGNED_COURSES_FOR_TABALE_LIST+assignedCourse.getInstitute_id());
		redisUtilityRoot.deleteList(HASH_KEY_ASSIGNED_COURSE_LIST+assignedCourse.getInstitute_id());
		redisUtilityRoot.deleteList(HASH_KEY_OFFERED_COURSES_LIST+assignedCourse.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<AssignedCourse> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_ASSIGNED_COURSE_LIST+institute_id).size() > 0) {
			return redisUtilityRoot.getList(HASH_KEY_ASSIGNED_COURSE_LIST+institute_id);
		}else {
			List<AssignedCourse> assignedCourses = new ArrayList<>();
			for(AssignedCourse course : assignCourse_repository.findAll()) {
				if(course.getInstitute_id() == institute_id) {
					assignedCourses.add(course);
				}
			}
			redisUtilityRoot.saveList(assignedCourses, HASH_KEY_ASSIGNED_COURSE_LIST+institute_id);
			return assignedCourses;
		}
	}
	
	@SuppressWarnings("deprecation")
	public String update(UpdateAssignedCourse updateAssignedCourse, int faculty_id) {
		
		AssignedCourse assignedCourse = updateAssignedCourse.getAssignedCourse();
		
		int institute_id= 0;
		for(AssignedCourse course : assignCourse_repository.findAll()) {
			if(course.getAssignedCourseId() == assignedCourse.getAssignedCourseId()) {
				course.setFaculty_id(faculty_id);
				institute_id = course.getInstitute_id();
				assignCourse_repository.save(course);
				break;
			}
		}
		
		if(updateAssignedCourse.isAddRoomRequest()) {
			Room_Request room_Request = new Room_Request();
			room_Request.setRoom_req_id(sequenceGeneratorService.getSequenceNumber(room_Request.SEQUENCE_NAME));
			room_Request.setDepartment_id(assignedCourse.getDepartment_id());
			room_Request.setInstitute_id(assignedCourse.getInstitute_id());
			room_Request.setRoom_id(assignedCourse.getRoom_id());
			room_Request.setStartTime(assignedCourse.getStartTime());
			room_Request.setEndTime(assignedCourse.getEndTime());
			
			String pattern = "MM/dd/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			
			room_Request.setDate(simpleDateFormat.format(new Date(assignedCourse.getDate())));
			room_request_repository.insert(room_Request);
			redisUtilityRoot.deleteList(HASH_KEY_ROOM_REQUESTS+room_Request.getInstitute_id());
		}
		
		redisUtilityRoot.deleteList(HASH_KEY_ASSIGNED_COURSE_LIST+institute_id);
		return "Operation performed successfully.";
	}
	
	public String deleteCourse(int assignedCourseId) {
		int institute_id= 0;
		for(AssignedCourse course : assignCourse_repository.findAll()) {
			if(course.getAssignedCourseId() == assignedCourseId) {
				institute_id = course.getInstitute_id();
				assignCourse_repository.deleteById(assignedCourseId);
				break;
			}
		}
		redisUtilityRoot.deleteList(HASH_KEY_ASSIGNED_COURSE_LIST+institute_id);
		return "Operation performed successfully.";
	}
	
//	public String delete(int assignedCoursesId) {
//		int institute_id= 0;
//		for(AssignedCoursesForTable course : assignedCoursesForTable_repository.findAll()) {
//			if(course.getAssignedCoursesId() == assignedCoursesId) {
//				institute_id = course.getInstituteId();
//				assignedCoursesForTable_repository.deleteById(assignedCoursesId);
//				for(AssignedCourse assignedCourse : assignCourse_repository.findAll()) {
//					if(assignedCourse.getAssignedCoursesId() == assignedCoursesId) {
//						assignCourse_repository.delete(assignedCourse);
//					}
//				}
//				for(Staff_Request request : staff_request_repository.findAll()) {
//					if(request.getAssignedCoursesId() == assignedCoursesId) {
//						staff_request_repository.delete(request);
//					}
//				}
//				for(Room_Request request : room_request_repository.findAll()) {
//					if(request.getAssignedCoursesId() == assignedCoursesId) {
//						room_request_repository.delete(request);
//					}
//				}
//				break;
//			}
//		}
//		redisUtilityRoot.deleteList(HASH_KEY_ASSIGNED_COURSES_FOR_TABALE_LIST+institute_id);
//		redisUtilityRoot.deleteList(HASH_KEY_ASSIGNED_COURSE_LIST+institute_id);
//		redisUtilityRoot.deleteList(HASH_KEY_STAFF_REQUESTS+institute_id);
//		redisUtilityRoot.deleteList(HASH_KEY_ROOM_REQUESTS+institute_id);
//		return "Operation performed successfully.";
//	}
	
}
