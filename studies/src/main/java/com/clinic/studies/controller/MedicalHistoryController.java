package com.clinic.studies.controller;

import com.clinic.studies.entity.MedicalHistory;
import com.clinic.studies.error.StudiesException;
import com.clinic.studies.service.MedicalHistoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/medical-history")
@Log4j2
public class MedicalHistoryController {
    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @GetMapping
    public ResponseEntity<?> getAllMedicalHistories() {
        try {
            return ResponseEntity.ok().body(medicalHistoryService.getAllMedicalHistories());
        } catch (StudiesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>("No data found", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> createMedicalHistory(@RequestBody MedicalHistory medicalHistory) {
        try {
            log.info("Medical History to create: "+ medicalHistory.toString());
            return new ResponseEntity<>(medicalHistoryService.createmedicalHistory(medicalHistory), HttpStatus.CREATED);
        } catch (StudiesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> updatemedicalHistory(@RequestBody MedicalHistory medicalHistory) {
        try {
            return new ResponseEntity<>(medicalHistoryService.updatemedicalHistory(medicalHistory), HttpStatus.OK);
        }catch (StudiesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedicalHistory(@PathVariable (value = "id") Long id) {
        try {
            medicalHistoryService.deleteMedicalHistory(id);
            return new ResponseEntity<>("Medical History's record deleted", HttpStatus.OK);
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
