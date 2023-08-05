package com.clinic.admin.controller;

import com.clinic.admin.error.RecordsException;
import com.clinic.admin.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/general-patient-data")
@Log4j2
public class GeneralPatientDataController {
    @Autowired
    GeneralPatientDataService generalPatientDataService;

    @GetMapping("/{patientId}/{medicalHistoryId}/{email}")
    public ResponseEntity<String> getGeneralPatientData(
            @PathVariable(value = "patientId") Long patientId,
            @PathVariable(value = "medicalHistoryId") Long medicalHistoryId,
            @PathVariable(value = "email") String email) {
        try {
            String generalPatientDataDto =
                    generalPatientDataService.getGeneralPatientData(patientId, medicalHistoryId, email);
            return ResponseEntity.ok().body(generalPatientDataDto);
        } catch (RecordsException ex) {
            log.warn("No data");
            log.error(ex);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
