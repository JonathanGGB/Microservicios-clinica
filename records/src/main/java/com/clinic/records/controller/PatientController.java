package com.clinic.records.controller;

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

import com.clinic.records.entity.Patient;
import com.clinic.records.error.RecordsException;
import com.clinic.records.service.PatientService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/patient")
@Log4j2
public class PatientController {
	
	@Autowired 
	private PatientService patientService;
	
	@GetMapping
	public ResponseEntity<?> getAllPatients() {
		try {
			return ResponseEntity.ok().body(patientService.getAllPatients());
		} catch (RecordsException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>("No data found", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}


	@GetMapping("/{id}")
	public ResponseEntity<?> findPatientById(@PathVariable("id")Long id){
		try{
			return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
		} catch (RecordsException ex){
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	@GetMapping(value = "/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
		try {
			return ResponseEntity.ok().body(patientService.getPatientByEmail(email));
		} catch (RecordsException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	

	@PostMapping
	public ResponseEntity createPatient(@RequestBody Patient patient) {
		try {
			log.info("Patient to create: "+ patient.toString());
			return new ResponseEntity<>(patientService.createPatient(patient), HttpStatus.CREATED);
		} catch (RecordsException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@PutMapping
	public ResponseEntity updatePatient(@RequestBody Patient patient) {
		try {
			return new ResponseEntity<>(patientService.updatePatient(patient), HttpStatus.OK);
		}catch (RecordsException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deletePatient(@PathVariable (value = "id") Long id) {
		try {
			patientService.deletePatient(id);
			return new ResponseEntity<>("Patient's record deleted", HttpStatus.OK);
		} catch (RecordsException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
