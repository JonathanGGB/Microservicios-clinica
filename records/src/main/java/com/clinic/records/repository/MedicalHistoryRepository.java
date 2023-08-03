package com.clinic.records.repository;

import java.util.Optional;

import com.clinic.records.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long>{
    Optional<MedicalHistory>findByPatientId(Long patientId);
    Optional<MedicalHistory>findByIdAndPatientId(Long id, Long patientId);
}
