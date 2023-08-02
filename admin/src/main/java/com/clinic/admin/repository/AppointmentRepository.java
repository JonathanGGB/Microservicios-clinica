package com.clinic.admin.repository;
import java.util.Optional;
import com.clinic.admin.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
    Optional<Appointment>findByPatient_Id(Long patientId);
    Optional<Appointment>findByIdAndPatient_Id(Long id, Long patientId);
}
