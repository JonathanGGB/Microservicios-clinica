package com.clinic.records.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.records.error.RecordsException;
import com.clinic.records.service.PatientRecordService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/patient-record")
@Log4j2
public class PatientRecordController {
	
	@Autowired
	PatientRecordService patientRecordService;
	
	@GetMapping(value = "/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
		try {
			return ResponseEntity.ok().body(patientRecordService.getPatientRecordByEmail(email));
		} catch (RecordsException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
