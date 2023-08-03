package com.clinic.studies.controller;

import com.clinic.studies.entity.SystemDisease;
import com.clinic.studies.error.RegistriesException;
import com.clinic.studies.service.SystemDiseaseService;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "system_disease")
@Log4j2
public class SystemDiseaseController {
    @Autowired
    SystemDiseaseService systemDiseaseService;

    @GetMapping
    public ResponseEntity<?> getAllSystemDiseases(){
        try{
            return ResponseEntity.ok().body(systemDiseaseService.getAllSystemDiseases());
        } catch(RegistriesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>("No data found", HttpStatus.NO_CONTENT);
        }catch (Exception e){
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{humanSystemId}")
    public ResponseEntity<?> createSystemDisease(@PathVariable Long humanSystemId, @RequestBody SystemDisease systemDisease) {
        try {
            log.info("System disease to create: " + systemDisease.toString() + " with " + humanSystemId);
            return new ResponseEntity<>(systemDiseaseService.createSystemDisease(humanSystemId, systemDisease), HttpStatus.CREATED);
        } catch (RegistriesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PutMapping
    public ResponseEntity<?> updateSystemDisease(@RequestBody SystemDisease systemDisease) {
        try {
            return new ResponseEntity<>(systemDiseaseService.updateSystemDiseases(systemDisease), HttpStatus.OK);
        }catch (RegistriesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSystemDisease(@PathVariable (value = "id") Long id) {
        try {
            systemDiseaseService.deleteSystemDisease(id);
            return new ResponseEntity<>("System disease's record deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
