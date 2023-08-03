package com.clinic.studies.controller;

import com.clinic.studies.entity.Diagnosis;
import com.clinic.studies.error.RegistriesException;
import com.clinic.studies.service.DiagnosisService;
import lombok.extern.log4j.Log4j2;
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

@RestController
@RequestMapping(value = "diagnosis")
@Log4j2
public class DiagnosisController {

    @Autowired
    DiagnosisService diagnosisService;

    @GetMapping
    public ResponseEntity<?> getAllDiagnoses(){
        try{
            return ResponseEntity.ok().body(diagnosisService.getAllDiagnoses());
        } catch(RegistriesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>("No data found", HttpStatus.NO_CONTENT);
        }catch (Exception e){
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> createDiagnosis(@RequestBody Diagnosis diagnosis) {
        try {
            log.info("Diagnosis to create: " + diagnosis.toString());
            return new ResponseEntity<>(diagnosisService.createDiagnosis(diagnosis), HttpStatus.CREATED);
        } catch (RegistriesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PutMapping
    public ResponseEntity<?> updateDiagnosis(@RequestBody Diagnosis diagnosis) {
        try {
            return new ResponseEntity<>(diagnosisService.updateDiagnosis(diagnosis), HttpStatus.OK);
        }catch (RegistriesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiagnosis(@PathVariable (value = "id") Long id) {
        try {
            diagnosisService.deleteDiagnosis(id);
            return new ResponseEntity<>("Diagnostic's record deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
