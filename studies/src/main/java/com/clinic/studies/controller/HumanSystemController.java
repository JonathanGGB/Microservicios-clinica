package com.clinic.studies.controller;

import com.clinic.studies.entity.HumanSystem;
import com.clinic.studies.error.RegistriesException;
import com.clinic.studies.service.HumanSystemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "human_system")
@Log4j2
public class HumanSystemController {

    @Autowired
    HumanSystemService humanSystemService;

    @GetMapping
    public ResponseEntity<?> getAllSystems(){
        try{
            return ResponseEntity.ok().body(humanSystemService.getAllSystems());
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
    public ResponseEntity<?> createSystem(@RequestBody HumanSystem humanSystem){
        try {
            log.info("System to create: "+ humanSystem.toString());
            return new ResponseEntity<>(humanSystemService.createSystem(humanSystem), HttpStatus.CREATED);
        } catch (RegistriesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateSystem(@RequestBody HumanSystem humanSystem) {
        try {
            return new ResponseEntity<>(humanSystemService.updateSystem(humanSystem), HttpStatus.OK);
        }catch (RegistriesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSystem(@PathVariable (value = "id") Long id) {
        try {
            humanSystemService.deleteSystem(id);
            return new ResponseEntity<>("System's record deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
