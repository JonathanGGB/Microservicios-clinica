package com.clinic.records.repository;

import com.clinic.records.entity.NonPathologicalPersonalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NonPathologicalPersonalHistoryRepository extends JpaRepository<NonPathologicalPersonalHistory, Long> {
    Optional<NonPathologicalPersonalHistory> findByPatientId(Long patientId);
}
