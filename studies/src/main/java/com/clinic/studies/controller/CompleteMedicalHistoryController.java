package com.clinic.studies.controller;

import com.clinic.studies.dto.CompleteMedicalHistoryDto;
import com.clinic.studies.entity.MedicalHistory;
import com.clinic.studies.error.StudiesException;
import com.clinic.studies.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/complete-medical-history")
@Log4j2
public class CompleteMedicalHistoryController {
    @Autowired
    CompleteMedicalHistoryService completeMedicalHistoryService;

    @GetMapping("/{patientId}/{medicalHistoryId}")
    public ResponseEntity<?> getCompleteMedicalHistory(
            @PathVariable(value = "patientId") Long patientId,
            @PathVariable(value = "medicalHistoryId") Long medicalHistoryId) {
        try {
            CompleteMedicalHistoryDto completeMedicalHistoryDto =
                    completeMedicalHistoryService.getCompleteMedicalHistoryById(patientId, medicalHistoryId);
            return ResponseEntity.ok().body(completeMedicalHistoryDto);
        } catch (StudiesException ex) {
            log.warn("No data");
            log.error(ex);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
        } catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
