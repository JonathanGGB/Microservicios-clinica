package com.clinic.studies.repository;

import com.clinic.studies.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    Optional<MedicalHistory> findByPatientId(Long patientId);
    Optional<MedicalHistory>findByIdAndPatientId(Long id, Long patientId);
}
