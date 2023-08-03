package com.clinic.studies.controller;

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

import com.clinic.studies.entity.Prognostic;
import com.clinic.studies.error.StudiesException;
import com.clinic.studies.service.PrognosticService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/studies")
@Log4j2
public class PrognosticController {
    @Autowired
    PrognosticService prognosticService;

    @GetMapping
    public ResponseEntity<?> getAllPrognostics() {
        try {
            return ResponseEntity.ok().body(prognosticService.getAllPrognostics());
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
    public ResponseEntity<?> createPrognostic(@RequestBody Prognostic prognostic) throws StudiesException {
        try {
            log.info("CurrenSuffering to create: "+ prognostic.toString());
            return new ResponseEntity<>(prognosticService.createPrognostic(prognostic), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> updatePrognostic(@RequestBody Prognostic prognostic) {
        try {
            return new ResponseEntity<>(prognosticService.updatePrognostic(prognostic), HttpStatus.OK);
        }catch (StudiesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrognostic(@PathVariable (value = "id") Long id) {
        try {
            prognosticService.deletePrognostic(id);
            return new ResponseEntity<>("Patient's Prognostic record deleted", HttpStatus.OK);
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
