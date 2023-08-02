package com.clinic.admin.controller;

import com.clinic.admin.entity.Appointment;
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
import com.clinic.admin.error.RecordsException;
import com.clinic.admin.service.AppointmentService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/appointment")
@Log4j2
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<?> getAllAppointments() {
        try {
            return ResponseEntity.ok().body(appointmentService.getAllAppointments());
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
    public ResponseEntity<?> createAppointment(@RequestBody Appointment appointment) {
        try {
            log.info("Appointment to create: "+ appointment.toString());
            return new ResponseEntity<>(appointmentService.createAppointment(appointment), HttpStatus.CREATED);
        } catch (RecordsException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment) {
        try {
            return new ResponseEntity<>(appointmentService.updateAppointment(appointment), HttpStatus.OK);
        }catch (RecordsException ex) {
            log.warn("No data");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable (value = "id") Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return new ResponseEntity<>("Appointment record deleted", HttpStatus.OK);
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
