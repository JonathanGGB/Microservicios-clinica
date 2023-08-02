package com.clinic.records.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.records.entity.PerinatalHistory;

public interface PerinatalHistoryRepository extends JpaRepository<PerinatalHistory, Long>{
	Optional<PerinatalHistory>findByPatient_Id(Long patientId);
	Optional<PerinatalHistory>findByIdAndPatient_Id(Long id, Long patientId);
}
