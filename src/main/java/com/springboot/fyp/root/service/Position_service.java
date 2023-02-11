package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Position_repository;
import com.springboot.fyp.root.models.Position;

@Service
public class Position_service {

	@Autowired
	Position_repository position_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_POSITION_LIST = "PositionList";
	
	public String add(Position position) {
		position.setPosition_id(sequenceGeneratorService.getSequenceNumber(position.SEQUENCE_NAME));
		position_repository.insert(position);
		redisUtilityRoot.deleteList(HASH_KEY_POSITION_LIST+position.getInstitue_id());
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<Position> getAll(int institute_id){
		List<Position> positions = redisUtilityRoot.getList(HASH_KEY_POSITION_LIST+institute_id);;
		if(positions.size() > 0) {
			return positions;
		}else {
			List<Position> position_lst = position_repository.findAll();
			if(position_lst.isEmpty()) {
				return null;
			} else {
				List<Position> positions_lst = new ArrayList<>();
				for(Position position : position_lst) {
					if(position.getInstitue_id() == institute_id) {
						positions_lst.add(position);
					}
				}
				redisUtilityRoot.saveList(positions_lst, HASH_KEY_POSITION_LIST+institute_id);
				return positions_lst;
			}
		}
	}
	
}
