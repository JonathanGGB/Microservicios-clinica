package com.clinic.records.controller;

import com.clinic.records.entity.ObstetricGynecologistHistory;
import com.clinic.records.error.RecordsException;
import com.clinic.records.service.ObstetricGynecologistHistoryService;
import com.fasterxml.jackson.core.sym.Name;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/obstretic-gynecologist-history")
@Log4j2
public class ObstetricGynecologistHistoryController {
    @Autowired
    private ObstetricGynecologistHistoryService obstetricGynecologistHistoryService;

    @GetMapping
    public ResponseEntity<?> getAllObstetricHistory(){
        try{
            return ResponseEntity.ok().body(obstetricGynecologistHistoryService.getAllObstetricHistory());
        }catch (RecordsException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>("No data found", HttpStatus.NO_CONTENT);
        }catch (Exception e){
            log.error(e);
            throw new RuntimeException(e);
        }
    }
   
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getObstetricGynecologistHistoryDtoByPatientId(@PathVariable (name = "id") Long id) {
    	try {
			return ResponseEntity.ok().body(obstetricGynecologistHistoryService.getObstGynecHistoryDtoByPatientId(id));
		} catch (RecordsException ex) {
			log.warn("No data");
			log.error(ex);
			return new ResponseEntity<>("No data found", HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			log.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
    }

    @PostMapping
    public ResponseEntity<?> createObstetricHistory(@RequestBody ObstetricGynecologistHistory obstetricGynecologistHistory){
        try{
            return new ResponseEntity<>(obstetricGynecologistHistoryService.createObstetricHistory(obstetricGynecologistHistory), HttpStatus.CREATED);
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
    public ResponseEntity<?> updateObstetricHistory(@RequestBody ObstetricGynecologistHistory obstetricGynecologistHistory){
        try{
            return new ResponseEntity<>(obstetricGynecologistHistoryService.updateObstetricHistory(obstetricGynecologistHistory), HttpStatus.OK);
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
    public ResponseEntity<?> deleteObstetricHistory(@PathVariable (value = "id")Long id){
        try{
            obstetricGynecologistHistoryService.deleteObstetricHistory(id);
            return new ResponseEntity<>("Obstetric-Gynecologist personal history has been deleted", HttpStatus.OK);
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
