package com.clinic.admin.repository;
import java.util.Optional;
import com.clinic.admin.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
    Optional<Appointment>findByPatientId(Long patientId);
    Optional<Appointment>findByIdAndPatientId(Long id, Long patientId);
}
