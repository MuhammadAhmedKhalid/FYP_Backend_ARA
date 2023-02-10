package com.springboot.fyp.root.service;

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
	
	public String add(Position position) {
		position.setPosition_id(sequenceGeneratorService.getSequenceNumber(position.SEQUENCE_NAME));
		position_repository.insert(position);
		return "Operation performed successfully.";
	}
	
	public List<Position> getAll(){
		List<Position> positions = position_repository.findAll();
		if(positions.isEmpty()) {
			return null;
		}
		return positions;
	}
	
}
