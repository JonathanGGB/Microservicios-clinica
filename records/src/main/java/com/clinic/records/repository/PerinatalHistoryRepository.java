package com.clinic.records.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.records.entity.PerinatalHistory;

public interface PerinatalHistoryRepository extends JpaRepository<PerinatalHistory, Long>{
	Optional<PerinatalHistory>findByPatientId(Long patientId);
	Optional<PerinatalHistory>findByIdAndPatientId(Long id, Long patientId);
}
