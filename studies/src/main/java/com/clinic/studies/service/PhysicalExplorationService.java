package com.clinic.studies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.studies.entity.PhysicalExploration;
import com.clinic.studies.error.StudiesException;
import com.clinic.studies.repository.PhysicalExplorationRepository;

import java.util.Optional;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PhysicalExplorationService {
	@Autowired
	PhysicalExplorationRepository physicalExplorationRepository;
	
	public PhysicalExploration createPhysicalExploration(PhysicalExploration physicalExploration) {
		log.info("Create PhysicalExploration record: " + physicalExploration.toString());
		return physicalExplorationRepository.save(physicalExploration);
	}
	
	public PhysicalExploration updatePhysicalExploration(PhysicalExploration physicalExploration)  throws Exception {
		if(physicalExploration.getId() == null) {
			throw new StudiesException("This PhysicalExploration record cannot be updated");
		}
		Optional<PhysicalExploration> physicalExplorationExists = physicalExplorationRepository.findById(physicalExploration.getId());
		if(physicalExplorationExists.isPresent()) {
			log.info("Update PhysicalExploration record: "+ physicalExploration.toString());
			return physicalExplorationRepository.save(physicalExploration);
		}
		throw new StudiesException("This PhysicalExploration record does not exists");
	}
	
	public List<PhysicalExploration> getAllPhysicalExplorations() throws Exception {
		List<PhysicalExploration> physicalExplorationRecords = physicalExplorationRepository.findAll();
		if(physicalExplorationRecords.isEmpty()) {
			throw new StudiesException("No PhysicalExploration records found");
		}
		return physicalExplorationRecords;
	}
	
	public void deletePhysicalExploration(Long id) throws Exception{
		Optional<PhysicalExploration> physicalExplorationExists = physicalExplorationRepository.findById(id);
		if(!physicalExplorationExists.isPresent()) {
			throw new StudiesException("No PhysicalExploration record found for deletion");
		}
		physicalExplorationRepository.deleteById(id);
	}
}
