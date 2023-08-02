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
import com.clinic.records.entity.PerinatalHistory;
import com.clinic.records.error.RecordsException;
import com.clinic.records.service.PerinatalHistoryService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/perinatal-history")
@Log4j2
public class PerinatalHistoryController {
	@Autowired
	private PerinatalHistoryService perinatalService;
	
	@GetMapping
	public ResponseEntity<?> getAllPatients() {
		try {
			return ResponseEntity.ok().body(perinatalService.getAllPerinatalHistories());
		} catch (RecordsException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>("No data found", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createPatient(@RequestBody PerinatalHistory perinatalHistory) {
		try {
			log.info("Perinatal History to create: "+ perinatalHistory.toString());
			return new ResponseEntity<>(perinatalService.createPerinatalHistory(perinatalHistory), HttpStatus.CREATED);
		} catch (RecordsException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updatePatient(@RequestBody PerinatalHistory perinatalHistory) {
		try {
			return new ResponseEntity<>(perinatalService.updatePerinatalHistory(perinatalHistory), HttpStatus.OK);
		}catch (RecordsException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable (value = "id") Long id) {
		try {
			perinatalService.deletePerinatalHistory(id);
			return new ResponseEntity<>("Perintal History's record deleted", HttpStatus.OK);
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
