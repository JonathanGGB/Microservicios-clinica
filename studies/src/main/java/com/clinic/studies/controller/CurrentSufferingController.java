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
import com.clinic.studies.error.StudiesException;
import com.clinic.studies.service.CurrentSufferingService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/current-suffering")
@Log4j2
public class CurrentSufferingController {
	@Autowired
	CurrentSufferingService currentSufferingService;
	
	@GetMapping
	public ResponseEntity<?> getAllCurrentSufferings() {
		try {
			return ResponseEntity.ok().body(currentSufferingService.getAllCurrentSufferings());
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
	public ResponseEntity<?> findCurrentSufferingByPacientId(@PathVariable("patient-id")Long patientId){
		try{
			return new ResponseEntity<>(currentSufferingService.getCurrentSufferingByPatientId(patientId), HttpStatus.OK);
		} catch (StudiesException ex){
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>("No data found", HttpStatus.NOT_FOUND);
		} catch (Exception e){
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@PostMapping
	public ResponseEntity<?> createCurrentSuffering(@RequestBody CurrentSuffering currentSuffering) throws StudiesException {
		try {
			log.info("CurrenSuffering to create: "+ currentSuffering.toString());
			return new ResponseEntity<>(currentSufferingService.createCurrentSuffering(currentSuffering), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateCurrentSuffering(@RequestBody CurrentSuffering currentSuffering) {
		try {
			return new ResponseEntity<>(currentSufferingService.updateCurrentSuffering(currentSuffering), HttpStatus.OK);
		}catch (StudiesException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCurrentSuffering(@PathVariable (value = "id") Long id) {
		try {
			currentSufferingService.deleteCurrentSuffering(id);
			return new ResponseEntity<>("Patient's CurrentSuffering record deleted", HttpStatus.OK);
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
