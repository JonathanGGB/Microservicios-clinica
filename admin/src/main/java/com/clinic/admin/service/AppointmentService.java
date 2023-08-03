package com.clinic.admin.service;

import java.util.List;

import com.clinic.admin.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinic.admin.error.RecordsException;
import com.clinic.admin.repository.AppointmentRepository;
import lombok.extern.log4j.Log4j2;
import java.util.Optional;

@Service
@Log4j2
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment createAppointment(Appointment appointment) throws Exception {
        Optional<Appointment> appointmentExists = appointmentRepository
                .findByPatientId(appointment.getPatientId());
        if(!appointmentExists.isPresent()) {
            log.info("Create Appointment: " + appointment.toString());
        }
        throw new RecordsException("There's an already existing appointment for that patient");
    }

    public Appointment updateAppointment(Appointment appointment)  throws Exception {
        if(appointment.getId() == null) {
            throw new RecordsException("This appointment cannot be updated");
        }
        Optional<Appointment> appointmentExists = appointmentRepository
                .findByIdAndPatientId(appointment.getId(), appointment.getPatientId());
        if(appointmentExists.isPresent()) {
            log.info("Update Family History: "+ appointment.toString());
            return appointmentRepository.save(appointment);
        }
        throw new RecordsException("This patient's appointment does not exists");
    }

    public List<Appointment> getAllAppointments() throws Exception {
        List<Appointment> appointments = appointmentRepository.findAll();
        if(appointments.isEmpty()) {
            throw new RecordsException("No appointments found");
        }
        return appointments;
    }

    public void deleteAppointment(Long id) throws Exception {
        Optional<Appointment> appointmentExists = appointmentRepository.findById(id);
        if(!appointmentExists.isPresent()) {
            throw new RecordsException("No appointment found for deletion");
        }
        appointmentRepository.deleteById(id);
    }
}
