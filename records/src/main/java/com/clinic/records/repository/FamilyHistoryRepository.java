package com.clinic.records.repository;

import com.clinic.records.entity.FamilyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamilyHistoryRepository extends JpaRepository<FamilyHistory, Long> {
    Optional<FamilyHistory> findByPatientNameAndPatientLastnames(String name, String lastNames);
}
