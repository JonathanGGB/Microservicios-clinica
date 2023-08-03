package com.clinic.studies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.studies.entity.CurrentSuffering;


public interface CurrentSufferingRepository extends JpaRepository<CurrentSuffering, Long>{
	
	Optional<CurrentSuffering> findByPatientIdAndState(Long patientId, String state);

}
