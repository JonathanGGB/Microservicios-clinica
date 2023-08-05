package com.clinic.studies.controller;

import com.clinic.studies.error.StudiesException;
import com.clinic.studies.service.PatientStudiesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient-studies")
@Log4j2
public class PatientStudiesController {
    @Autowired
    PatientStudiesService patientStudiesService;

    @GetMapping("/{patient-id}")
    public ResponseEntity<?> getPatientStudiesByPatientId(@PathVariable("patient-id")Long id){
        try {
            return new ResponseEntity<>(patientStudiesService.getPatientStudiesByPacientId(id), HttpStatus.OK);
        } catch (StudiesException ex){
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            throw new RuntimeException();
        }
    }
}
