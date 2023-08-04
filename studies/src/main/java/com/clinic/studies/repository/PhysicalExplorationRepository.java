package com.clinic.studies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.studies.entity.PhysicalExploration;

public interface PhysicalExplorationRepository extends JpaRepository<PhysicalExploration, Long>{
	Optional<PhysicalExploration>findByIdAndPatientId(Long id, Long patientId);
	Optional<PhysicalExploration> findByPatientId(Long id);
}
