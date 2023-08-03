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

import com.clinic.studies.entity.Study;
import com.clinic.studies.error.StudiesException;
import com.clinic.studies.service.StudyService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/studies-records")
@Log4j2
public class StudyController {
    @Autowired
    StudyService studyService;

    @GetMapping
    public ResponseEntity<?> getAllStudies() {
        try {
            return ResponseEntity.ok().body(studyService.getAllStudies());
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
    public ResponseEntity<?> createStudy(@RequestBody Study study) throws StudiesException {
        try {
            log.info("CurrenSuffering to create: "+ study.toString());
            return new ResponseEntity<>(studyService.createStudy(study), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateStudy(@RequestBody Study study) {
        try {
            return new ResponseEntity<>(studyService.updateStudy(study), HttpStatus.OK);
        }catch (StudiesException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudy(@PathVariable (value = "id") Long id) {
        try {
            studyService.deleteStudy(id);
            return new ResponseEntity<>("Patient's Study record deleted", HttpStatus.OK);
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
