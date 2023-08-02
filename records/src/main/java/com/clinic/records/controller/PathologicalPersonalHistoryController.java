package com.clinic.records.controller;

import com.clinic.records.entity.PathologicalPersonalHistory;
import com.clinic.records.error.RecordsException;
import com.clinic.records.service.PathologicalPersonalHistoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pathological-history")
@Log4j2
public class PathologicalPersonalHistoryController {
    @Autowired
    private PathologicalPersonalHistoryService pathologicalPersonalHistoryService;

    @GetMapping
    public ResponseEntity<?> getAllPathologicalHistory(){
        try{
            return ResponseEntity.ok().body(pathologicalPersonalHistoryService.getAllPathologicalHistories());
        }catch (RecordsException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>("No data found", HttpStatus.NO_CONTENT);
        }catch (Exception e){
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPathologicalHistory(@RequestBody PathologicalPersonalHistory pathologicalPersonalHistory){
        try{
            return new ResponseEntity<>(pathologicalPersonalHistoryService.createPathologicalHistory(pathologicalPersonalHistory), HttpStatus.CREATED);
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
    public ResponseEntity<?> updatePathologicalHistory(@RequestBody PathologicalPersonalHistory pathologicalPersonalHistory){
        try{
            return new ResponseEntity<>(pathologicalPersonalHistoryService.updatePathologicalHistory(pathologicalPersonalHistory), HttpStatus.OK);
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
    public ResponseEntity<?> deletePathologicalHistory(@PathVariable (value = "id")Long id){
        try{
            pathologicalPersonalHistoryService.deletePathologicalHistory(id);
            return new ResponseEntity<>("Pathological personal history has been deleted", HttpStatus.OK);
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
