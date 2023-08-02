package com.clinic.records.controller;

import com.clinic.records.entity.FamilyHistory;
import com.clinic.records.error.RecordsException;
import com.clinic.records.service.FamilyHistoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "family-history")
@Log4j2
public class FamilyHistoryController {
    @Autowired
    private FamilyHistoryService familyHistoryService;

    @GetMapping
    public ResponseEntity<?> getAllFamilyHistories() {
        try{
            return ResponseEntity.ok().body(familyHistoryService.getAllFamilyHistories());
        }catch(RecordsException ex){
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>("No data found", HttpStatus.NO_CONTENT);
        }catch (Exception e){
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> createFamilyHistory(@RequestBody FamilyHistory familyHistory){
        try{
            return new ResponseEntity<>(familyHistoryService.createFamilyHistory(familyHistory), HttpStatus.CREATED);
        } catch (RecordsException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateFamilyHistory(@RequestBody FamilyHistory familyHistory){
        try {
            return new ResponseEntity<>(familyHistoryService.updateFamilyHistory(familyHistory), HttpStatus.OK);
        } catch (RecordsException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFamilyHistory(@PathVariable (value = "id") Long id){
        try{
            familyHistoryService.deleteFamilyHistory(id);
            return new ResponseEntity<>("Family History deleted", HttpStatus.OK);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
