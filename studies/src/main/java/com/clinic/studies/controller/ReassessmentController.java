package com.clinic.studies.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.clinic.studies.entity.Reassessment;
import com.clinic.studies.error.RegistriesException;
import com.clinic.studies.service.ReassessmentService;
@RestController
@RequestMapping(value = "reassessment")
@Log4j2
public class ReassessmentController {
    @Autowired
    ReassessmentService reassessmentService;

    @GetMapping
    public ResponseEntity<?> getAllReassessments(){
        try{
            return ResponseEntity.ok().body(reassessmentService.getAllReassessments());
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
    public ResponseEntity<?> createReassessment(@RequestBody Reassessment reassessment) {
        try {
            log.info("Reassessment to create: " + reassessment.toString());
            return new ResponseEntity<>(reassessmentService.createReassessment(reassessment), HttpStatus.CREATED);
        } catch (RegistriesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PutMapping
    public ResponseEntity<?> updateReassessment(@RequestBody Reassessment reassessment) {
        try {
            return new ResponseEntity<>(reassessmentService.updateReassessment(reassessment), HttpStatus.OK);
        }catch (RegistriesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReassessment(@PathVariable (value = "id") Long id) {
        try {
            reassessmentService.deleteReassessment(id);
            return new ResponseEntity<>("Diagnostic's record deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
