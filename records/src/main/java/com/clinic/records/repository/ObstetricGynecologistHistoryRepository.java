package com.clinic.records.repository;

import com.clinic.records.entity.ObstetricGynecologistHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ObstetricGynecologistHistoryRepository extends JpaRepository<ObstetricGynecologistHistory, Long> {
    Optional<ObstetricGynecologistHistory> findObstetricGynecologistHistoryByPatientId(Long patientId);
}
