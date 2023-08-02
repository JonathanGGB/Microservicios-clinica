package com.clinic.records.controller;

import com.clinic.records.entity.NonPathologicalPersonalHistory;
import com.clinic.records.error.RecordsException;
import com.clinic.records.service.NonPathologicalPersonalHistoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/non-pathological-history")
@Log4j2
public class NonPathologicalPersonalHistoryController {
    @Autowired
    private NonPathologicalPersonalHistoryService nonPathologicalPersonalHistoryService;

    @GetMapping
    public ResponseEntity<?> getAllNonPathologicalHistory(){
        try{
            return ResponseEntity.ok().body(nonPathologicalPersonalHistoryService.getAllNonPathologicalHistory());
        } catch (RecordsException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>("No data found", HttpStatus.NO_CONTENT);
        }catch (Exception e){
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> createNonPathologicalHistory(@RequestBody NonPathologicalPersonalHistory nonPathologicalPersonalHistory){
        try{
            return new ResponseEntity<>(nonPathologicalPersonalHistoryService.createNonPathologicalHistory(nonPathologicalPersonalHistory), HttpStatus.CREATED);
        }catch (RecordsException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateNonPathologicalHistory(@RequestBody NonPathologicalPersonalHistory nonPathologicalPersonalHistory){
        try{
            return new ResponseEntity<>(nonPathologicalPersonalHistoryService.updateNonPathologicalHistory(nonPathologicalPersonalHistory), HttpStatus.OK);
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
    public ResponseEntity<?> deleteNonPathologicalHistory(@PathVariable (value = "id") Long id){
        try{
            nonPathologicalPersonalHistoryService.deleteNonPathologicalHistory(id);
            return new ResponseEntity<>("Non-pathological personal history has been deleted", HttpStatus.OK);
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
