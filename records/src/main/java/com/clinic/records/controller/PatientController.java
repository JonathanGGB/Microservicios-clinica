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
@RequestMapping(value = "patient")
@Log4j2
public class PatientController {
	
	@Autowired 
	PatientService patientService;
	
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
	
//	@GetMapping("/{name}")
//	public ResponseEntity<?> getPatient(@PathVariable(name = "name") String name, @PathParam("lastnames") String lastnames ){
//		try {
//			return ResponseEntity.ok(body(patientService.getPatientByNameAndLastnames(name,lastnames)));
//		}catch (RecordsException ex) {
//			log.warn("No data");
//			log.error(ex);
//			return new ResponseEntity<>("No data found", HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			log.error(e);
//			throw new RuntimeException(e);
//		}
//	}
	
	@PostMapping
	public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
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
	public ResponseEntity<?> updatePatient(@RequestBody Patient patient) {
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
	public void deletePatient(@PathVariable (value = "id") Long id) {
		patientService.deletePatient(id);
	}
	
}
