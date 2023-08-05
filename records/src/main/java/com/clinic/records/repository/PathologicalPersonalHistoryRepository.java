package com.clinic.records.repository;

import com.clinic.records.entity.PathologicalPersonalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PathologicalPersonalHistoryRepository extends JpaRepository<PathologicalPersonalHistory, Long> {
    Optional<PathologicalPersonalHistory> findByPatientId(Long patientId);
}
