package com.clinic.records.controller;

import com.clinic.records.entity.MedicalHistory;
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
import com.clinic.records.error.RecordsException;
import com.clinic.records.service.MedicalHistoryService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/medical-history")
@Log4j2
public class MedicalHistoryController {
    @Autowired
    private MedicalHistoryService familyService;

    @GetMapping
    public ResponseEntity<?> getAllFamilyHistories() {
        try {
            return ResponseEntity.ok().body(familyService.getAllFamilyHistories());
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
    public ResponseEntity<?> createFamilyHistory(@RequestBody MedicalHistory medicalHistory) {
        try {
            log.info("Family History to create: "+ medicalHistory.toString());
            return new ResponseEntity<>(familyService.createFamilyHistory(medicalHistory), HttpStatus.CREATED);
        } catch (RecordsException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateFamilyHistory(@RequestBody MedicalHistory medicalHistory) {
        try {
            return new ResponseEntity<>(familyService.updateFamilyHistory(medicalHistory), HttpStatus.OK);
        }catch (RecordsException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFamilyHistory(@PathVariable (value = "id") Long id) {
        try {
            familyService.deleteFamilyHistory(id);
            return new ResponseEntity<>("Family History's record deleted", HttpStatus.OK);
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
