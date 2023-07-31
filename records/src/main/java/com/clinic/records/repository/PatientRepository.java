package com.clinic.records.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.records.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	Optional<Patient>findByNameAndLastnames(String name, String lastNames);
}
