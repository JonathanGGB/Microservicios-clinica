package com.clinic.studies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.studies.entity.CurrentSuffering;
import com.clinic.studies.entity.PhysicalExploration;
import com.clinic.studies.error.StudiesException;
import com.clinic.studies.service.PhysicalExplorationService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/physical-exploration")
@Log4j2
public class PhysicalExplorationController {
	@Autowired
	PhysicalExplorationService physicalExplorationService;
	
	@GetMapping
	public ResponseEntity<?> getAllPhysicalExplorations() {
		try {
			return ResponseEntity.ok().body(physicalExplorationService.getAllPhysicalExplorations());
		} catch (StudiesException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>("No data found", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@GetMapping("/{patient-id}")
	public ResponseEntity<?> getPhysicalExplorationByPatientId(@PathVariable ("patient-id")Long patientId){
		try{
			return new ResponseEntity<>(physicalExplorationService.getPhysicalExplorationByPatientId(patientId), HttpStatus.OK);
		} catch (StudiesException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createPhysicalExploration(@RequestBody PhysicalExploration physicalExploration) throws StudiesException {
		try {
			log.info("PhysicalExploration to create: "+ physicalExploration.toString());
			return new ResponseEntity<>(physicalExplorationService.createPhysicalExploration(physicalExploration), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updatePhysicalExploration(@RequestBody PhysicalExploration physicalExploration) {
		try {
			return new ResponseEntity<>(physicalExplorationService.updatePhysicalExploration(physicalExploration), HttpStatus.OK);
		}catch (StudiesException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePhysicalExploration(@PathVariable (value = "id") Long id) {
		try {
			physicalExplorationService.deletePhysicalExploration(id);
			return new ResponseEntity<>("Patient's PhysicalExploration record deleted", HttpStatus.OK);
		} catch (StudiesException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
